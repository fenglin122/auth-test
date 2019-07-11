package com.cehome.cloud.user.service;

import com.cehome.cloud.common.dao.base.Page;
import com.cehome.cloud.user.mapper.UserMapper;
import com.cehome.cloud.user.model.po.User;
import com.cehome.cloud.user.model.po.UserRole;
import com.cehome.cloud.user.model.query.QueryBase;
import com.cehome.cloud.user.model.query.SearchUserQuery;
import com.cehome.cloud.user.model.query.UserQuery;
import com.cehome.cloud.user.model.request.UserReqDto;
import com.cehome.cloud.user.model.response.UserResDto;
import com.cehome.cloud.user.util.BeanUtils;
import com.cehome.cloud.user.util.PasswordHelper;
import com.cehome.cloud.user.util.StringUtil;
import com.cehome.utils.exception.MicroserviceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class UserInnerService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleInnerService userRoleInnerService;
    @Autowired
    private RoleInnerService roleInnerService;

    
    @Transactional(rollbackFor={Exception.class})
    public int add(UserReqDto userReqDto) throws RuntimeException{
        User user = BeanUtils.copy(userReqDto,User.class);
        user.setCreateTime(Calendar.getInstance().getTime());
        user.setUpdateTime(Calendar.getInstance().getTime());
        PasswordHelper.encryptPassword(user);
        userMapper.insert(user);
        Integer userId = user.getId();
        Set<UserRole> userRoles = checkRole(userReqDto.getRoleIds(),userId);
        userRoleInnerService.add(userRoles);
        return 1;
    }

    
    @Transactional(rollbackFor={Exception.class})
    public int update(UserReqDto userReqDto) {
        User user = BeanUtils.copy(userReqDto,User.class);
        user.setUpdateTime(Calendar.getInstance().getTime());
        userMapper.update(user);
        userRoleInnerService.deleteByUser(user.getId());
        Set<UserRole> userRoles = checkRole(userReqDto.getRoleIds(),user.getId());
        userRoleInnerService.add(userRoles);
        return userMapper.update(user);
    }

    
    @Transactional(rollbackFor={Exception.class})
    public int delete(Integer id) {
        userRoleInnerService.deleteByUser(id);
        return userMapper.deleteById(id);
    }

    
    public User selectByLoginName(String userName, Integer platformId) {
        return userMapper.selectByLoginName(userName, platformId);
    }

    
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    
    public User login(String loginName, String passwod, Integer platformId) {
        return userMapper.selectByLoginNameAndPassword(loginName, passwod, platformId);
    }

    
    public Page<UserResDto> search(String loginName, String phone, String orgId, Integer pageNo, Integer pageSize) {
        SearchUserQuery searchUserQuery = new SearchUserQuery();
        searchUserQuery.setPlatformId(1);
        searchUserQuery.setPhone(phone);
        searchUserQuery.setAccountLike(loginName);
        int count = userMapper.selectCount(searchUserQuery);
        Page page = new Page();
        if (count > 0){
            if (pageNo == null){
                pageNo = 1;
            }
            if (pageSize == null){
                pageSize = 20;
            }
            searchUserQuery.setCount(pageSize);
            searchUserQuery.setStartIndex((pageNo-1)*pageSize);
            searchUserQuery.setOrder(UserQuery.UserOrderBy.UPDATETIME);
            searchUserQuery.setSort(QueryBase.QuerySort.DESC);
            page.setDatas(createUserVO(userMapper.selectList(searchUserQuery),1,orgId));
            page.setPageIndex(pageNo);
            page.setPageOffset((pageNo-1)*pageSize);
            page.setPageSize(pageSize);
            page.setTotalRecord(count);
            int totalPage = count>0?(count/pageSize+(count%pageSize==0?0:1)):0;
            page.setTotalPage(totalPage);
        }
        return page;
    }

    
    public boolean validate(String loginName) {
        return userMapper.validate(loginName) <= 0;
    }

    private Set<UserRole> checkRole(List<String> roles, Integer userId){
        if (CollectionUtils.isEmpty(roles)){
            throw new NullPointerException("角色信息不能为空");
        }
        Set<UserRole> userRoles = Sets.newHashSet();
        for (String roleId:roles) {
            if (roleInnerService.selectById(Integer.parseInt(roleId)) == null){
                throw new MicroserviceException("角色ID["+ roleId +"]不存在");
            }
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(roleId));
            userRole.setCreateTime(Calendar.getInstance().getTimeInMillis());
            userRole.setUpdateTime(Calendar.getInstance().getTimeInMillis());
            userRoles.add(userRole);
        }
        return userRoles;
    }

    private List<UserResDto> createUserVO(List<User> users,Integer platformId,String orgId){
        List<UserResDto> userVOS = Lists.newArrayList();
        users.stream().forEach(user -> {
            StringBuffer roles = new StringBuffer();
            List<Integer> roleIds = Lists.newArrayList();
            UserResDto userVO = BeanUtils.copy(user,UserResDto.class);
            List<UserRole> userRoles = userRoleInnerService.selectByUserId(user.getId());
            userRoles.stream().forEach(userRole -> {
                roleIds.add(userRole.getRoleId());
                roles.append(roleInnerService.selectById(userRole.getRoleId()).getName()).append(",");
            });
            userVO.setRoleIds(roleIds);
            userVO.setRoles(roles.toString().substring(0,roles.length()-1));
            userVOS.add(userVO);
        });
        return userVOS;
    }

    private boolean checkUser(User user){
        if (user == null) throw new MicroserviceException("用户信息不能为空。");
        if (StringUtils.isBlank(user.getLoginName())) throw new MicroserviceException("账号不能为空。");
        if (StringUtils.isBlank(user.getName())) throw new MicroserviceException("姓名不能为空。");
        if (StringUtils.isBlank(user.getPhone())) throw new MicroserviceException("手机号不能为空。");
        if (!StringUtil.checkMobile(user.getPhone())) throw new MicroserviceException("手机号格式无效。");
        if (!StringUtils.isBlank(user.getEmail()) && !StringUtil.checkEmail(user.getEmail())) throw new MicroserviceException("邮箱格式无效。");
        return true;
    }
}
