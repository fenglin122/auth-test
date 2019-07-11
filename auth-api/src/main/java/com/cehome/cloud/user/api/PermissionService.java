package com.cehome.cloud.user.api;

import com.cehome.cloud.user.UserAPI;
import com.cehome.cloud.user.model.po.Permission;
import com.cehome.cloud.user.model.request.PermissionGrantReqDto;
import com.cehome.cloud.user.model.request.PermissionReqDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 提供权限相关接口：
 * 1.权限（菜单或按钮）管理（增删改查等）
 * 2.授权管理
 * Created by hyl on 2019/07/11/ 14:06
 */
@FeignClient(value = UserAPI.SERVICE_ID)
public interface PermissionService {
    /**
     * 添加菜单或者按钮等vue页面
     * 如：标签管理
     *          修改
     *          日志
     *     类别管理
     *          修改
     *          日志
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/add", method = RequestMethod.POST)
    Integer add(@RequestBody PermissionReqDto permissionReqDto);

    /**
     * 更新菜单或者按钮等vue页面
     * 如：增加
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/update", method = RequestMethod.POST)
    Integer update(@RequestBody PermissionReqDto permissionReqDto);

    /**
     * 通过菜单或按钮主键获取其详情
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/getById", method = RequestMethod.GET)
    Permission getById(Integer permission);

    /**
     * 通过主键ID删除菜单或者按钮等vue页面
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/delete", method = RequestMethod.GET)
    Integer deleteById(Integer id);

    /**
     * 给角色分配权限
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/grantPerm", method = RequestMethod.POST)
    Integer grantPerm(@RequestBody PermissionGrantReqDto permissionGrantReqDto);

    /**
     * 获取所有菜单和按钮，并且格式化为树形接口
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/listPermsTree", method = RequestMethod.GET)
    List<Permission> listPermsTree();

    /**
     * 获取所有菜单和按钮，并一列表的方式返回
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/listPerms", method = RequestMethod.GET)
    List<Permission> listPerms();

    /**
     * 获取当前登录用户授权的菜单和按钮，并且格式化为树形接口
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/listPermsTreeByUser", method = RequestMethod.GET)
    List<Permission> listPermsTreeByUser();

    /**
     * 获取当前登录用户授权的菜单和按钮，并以列表的形式返回
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/listPremsByUser", method = RequestMethod.GET)
    List<Permission> listPremsByUser();

    /**
     * 根据角色ID获取此角色授权的菜单和按钮，列表信息返回
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_PERMS + "/listpermIdsByRole", method = RequestMethod.GET)
    List<Integer> listpermIdsByRole(Integer roleId);
}
