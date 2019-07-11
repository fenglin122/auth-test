package com.cehome.cloud.user.config.shiro;

import com.cehome.cloud.user.model.po.Permission;
import com.cehome.cloud.user.model.po.Resource;
import com.cehome.cloud.user.service.PermissionInnerService;
import com.cehome.cloud.user.service.ResourceInnerService;
import com.cehome.cloud.user.service.RoleInnerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.*;

/**
 * @Description: TODO
 * Created by hyl on 2019/06/04/ 11:12
 */
@Service
public class AuthorityUpdateService implements DisposableBean, ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private static Logger logger = LoggerFactory.getLogger(AuthorityUpdateService.class);
    @Value("${metadata.broker.list}")
    private String kafkaServers;
    private static String TOPIC = "user_perms_update";
    private Producer<String, String> producer;
    KafkaConsumer<String, String> consumer;
    private volatile boolean running = true;

    private int port = 0;
    private static String ip;

    static {
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
            ip = localHost.getHostAddress();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() throws Exception {
        running = false;
        consumer.close();
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        port = event.getEmbeddedServletContainer().getPort();
        logger.info("port={}", port);
        if (producer == null) createKafkaProducer();
        if (consumer == null) createKafkaConsumer();
    }

    private void createKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);

    }

    private void createKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServers);
        String groupId = "g_" + ip + "_" + port;
        logger.info("group.id=" + groupId);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("offset = %d, value = %s%n", record.offset(), record.value());
                        try {
                            //todo 更新权限
                            updatePerms();
                        } catch (Exception e) {
                            logger.error("clearCache error", e);
                        }
                    }
                }
            }
        }).start();
    }

    @Lazy
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private RoleInnerService roleService;

    @Autowired
    private PermissionInnerService permissionInnerService;

    @Autowired
    private ResourceInnerService resourceInnerService;

    public void sendPermsUpdateMessage(long updateTag) {
        logger.info("send perms update message for updateTag={} ", updateTag);
        try {
            producer.send(new ProducerRecord<String, String>(TOPIC, "" + updateTag));
        } catch (Exception e) {
            logger.error("sendPermsUpdateMessage fail", e);
        }
    }

    /**
     * 从数据库加载用户拥有的菜单权限和 API 权限.
     */
    public Map<String, String> getUrlPermsMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 系统默认过滤器
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        // 验证码
        filterChainDefinitionMap.put("/user/authcode", "anon");
        // 检查用户名是否存在
        filterChainDefinitionMap.put("/user/validate", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        List<Permission> permissions = permissionInnerService.permssionList();
        permissions.stream().filter(permission -> StringUtils.isNotBlank(permission.getUrl()))
                .forEach(permission -> filterChainDefinitionMap.put(permission.getUrl(), "authc[" + permission.getPerms() + "]"));

        List<Resource> resourceList = resourceInnerService.listAll();
        for (Resource resource : resourceList) {
            String url = resource.getUrl();
            if (url != null) {
//                if (resource.getHttpMethod() != null && !"".equals(resource.getHttpMethod())) {
//                    url += ("==" + resource.getHttpMethod());
//                }
                String perms = "authc[" + resource.getPerms() + "]";
                filterChainDefinitionMap.put(url, perms);
            }
        }

        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 更新 Shiro 过滤器链
     */
    private void updatePerms() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(getUrlPermsMap());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            logger.info("更新 Shiro 过滤器链");
        }
    }
}
