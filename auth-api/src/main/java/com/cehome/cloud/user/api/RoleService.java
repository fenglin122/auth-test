package com.cehome.cloud.user.api;

import com.cehome.cloud.common.dao.base.Page;
import com.cehome.cloud.user.UserAPI;
import com.cehome.cloud.user.model.po.Role;
import com.cehome.cloud.user.model.request.RoleReqDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色管理接口
 *
 * Created by hyl on 2019/07/09/ 11:54
 */
@FeignClient(value = UserAPI.SERVICE_ID)
public interface RoleService {

    /**
     * 添加角色
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_ROLE + "/add",method = RequestMethod.POST)
    Integer add(@RequestBody RoleReqDto roleReqDto);

    /**
     * 更新角色
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_ROLE + "/update", method = RequestMethod.POST)
    Integer update(@RequestBody RoleReqDto roleReqDto);

    /**
     * 根据角色ID获取角色详情
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_ROLE + "/getById", method = RequestMethod.GET)
    Role getById(@RequestParam("roleId") Integer roleId);

    /**
     * 根据角色ID删除角色
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_ROLE + "/delete", method = RequestMethod.GET)
    Integer deleteById(@RequestParam("id")Integer id);

    /**
     * 获取角色分页列表
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_ROLE + "/page", method = RequestMethod.GET)
    Page<Role> page(@RequestParam("name") String name, @RequestParam("status") Integer status, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize")Integer pageSize);

    /**
     * 获取角色列表
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_ROLE + "/listAll", method = RequestMethod.GET)
    List<Role> listAll();

}
