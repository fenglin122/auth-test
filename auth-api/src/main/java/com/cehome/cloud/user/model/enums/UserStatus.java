package com.cehome.cloud.user.model.enums;

/**
 * Created by hyl on 2019/04/03
 **/
public enum UserStatus implements ValueDescEnum {

    VALID(1, "有效"), INVALID(0, "无效");

    private int value;
    private String desc;


    private UserStatus(int value, String desc) {
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

    public static UserStatus valueOf(int value) {
        for (UserStatus status : values()) {
            if (status.value() == value)
                return status;
        }

        throw new IllegalArgumentException("Can't find enum[" + UserStatus.class.getCanonicalName()
                + "] by the value[" + value + "]");
    }
}
