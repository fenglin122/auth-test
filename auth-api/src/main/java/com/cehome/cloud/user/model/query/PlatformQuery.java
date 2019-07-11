package com.cehome.cloud.user.model.query;


import com.cehome.cloud.user.model.enums.PlatformStatus;
import com.cehome.cloud.user.model.enums.ValueDescEnum;

/**
 * 平台查询
 *
 * Created by hyl on 2019/04/03
 */
public class PlatformQuery extends QueryBase {

    private static final long serialVersionUID = -7685197378255091199L;


    public enum PlatformOrderBy implements ValueDescEnum {

        ID(1, "id"),NAME(2, "name"), STATUS(3, "status"),UPDATETIME(4, "update_time");

        private int value;
        private String desc;


        private PlatformOrderBy(int value, String desc) {
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

        public static PlatformOrderBy valueOf(int value) {
            for (PlatformOrderBy status : values()) {
                if (status.value() == value)
                    return status;
            }

            throw new IllegalArgumentException("Can't find enum[" + PlatformOrderBy.class.getCanonicalName()
                                               + "] by the value[" + value + "]");
        }
    }


    /** 平台ID */
    private Integer roleId;
    /** 平台名 */
    private String name;
    /** 平台状态 */
    private PlatformStatus status;

    private PlatformOrderBy order = PlatformOrderBy.ID;

    public PlatformQuery(){}

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

    public PlatformStatus getStatus() {
        return status;
    }
    
    public void setStatus(PlatformStatus status) {
        this.status = status;
    }

    @Override
    public boolean check() {
        if (name == null) return false;
        return true;
    }
}
