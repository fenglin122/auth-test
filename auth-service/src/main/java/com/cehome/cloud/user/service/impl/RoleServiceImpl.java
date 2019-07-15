package com.cehome.cloud.user.service.impl;

import com.cehome.cloud.common.dao.base.Page;
import com.cehome.cloud.user.api.RoleService;
import com.cehome.cloud.user.model.po.Role;
import com.cehome.cloud.user.model.request.RoleReqDto;
import com.cehome.cloud.user.service.RoleInnerService;
import com.cehome.cloud.user.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 16:35
 */
@RestController
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleInnerService roleInnerService;

    public Integer add(@RequestBody RoleReqDto roleReqDto) {
        Role role = BeanUtils.copy(roleReqDto,Role.class);
        return roleInnerService.add(role);
    }

    public Integer update(@RequestBody RoleReqDto roleReqDto) {
        Role role = BeanUtils.copy(roleReqDto,Role.class);

        return roleInnerService.update(role);
    }

    public Role getById(@RequestParam("roleId") Integer roleId) {
        return roleInnerService.selectById(roleId);
    }

    public Integer deleteById(@RequestParam("id") Integer id) {
        return roleInnerService.deleteById(id);
    }

    public Page<Role> page(@RequestParam("name") String name, @RequestParam("status") Integer status, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        return roleInnerService.search(name,status,1,pageIndex,pageSize);
    }

    public List<Role> listAll() {
        return roleInnerService.listAll();
    }
}
