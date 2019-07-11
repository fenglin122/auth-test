package com.cehome.cloud.user.model.query;


import com.cehome.cloud.user.model.enums.RoleStatus;
import com.cehome.cloud.user.model.enums.ValueDescEnum;

/**
 * 角色查询
 *
 * Created by hyl on 2019/04/03
 */
public class RoleQuery extends QueryBase {

    private static final long serialVersionUID = -7685197378255091199L;


    public enum RoleOrderBy implements ValueDescEnum {

        ID(1, "id"),NAME(2, "name"), STATUS(3, "status"),UPDATETIME(4, "update_time");

        private int value;
        private String desc;


        private RoleOrderBy(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public int value() {
            return this.value;
        }


        @Override
        public String desc() {
            return this.desc;
        }

        public String getDesc() {
            return this.desc;
        }

        public static RoleOrderBy valueOf(int value) {
            for (RoleOrderBy status : values()) {
                if (status.value() == value)
                    return status;
            }

            throw new IllegalArgumentException("Can't find enum[" + RoleOrderBy.class.getCanonicalName()
                                               + "] by the value[" + value + "]");
        }
    }


    /** 角色ID */
    private Integer roleId;
    /** 角色名 */
    private String name;
    /** 角色状态 */
    private RoleStatus status;

    /** 组织ID */
    private Integer organizationId;
    /** 平台ID */
    private Integer platformId;

    private RoleOrderBy order = RoleOrderBy.ID;

    public RoleQuery(){}

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleStatus getStatus() {
        return status;
    }
    
    public void setStatus(RoleStatus status) {
        this.status = status;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public RoleOrderBy getOrder() {
        return order;
    }
    
    public void setOrder(RoleOrderBy order) {
        this.order = order;
    }

    @Override
    public boolean check() {
        if ((platformId == null)) return false;
        return true;
    }
}
