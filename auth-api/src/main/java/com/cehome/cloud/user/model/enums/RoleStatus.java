package com.cehome.cloud.user.model.enums;

/**
 * Created by hyl on 2019/04/03
 **/
public enum RoleStatus implements ValueDescEnum {

    VALID(1, "有效"), INVALID(0, "无效");

    private int value;
    private String desc;


    private RoleStatus(int value, String desc) {
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

    public static RoleStatus valueOf(int value) {
        for (RoleStatus status : values()) {
            if (status.value() == value)
                return status;
        }

        throw new IllegalArgumentException("Can't find enum[" + RoleStatus.class.getCanonicalName()
                + "] by the value[" + value + "]");
    }
}
