package com.cehome.cloud.user.model.enums;

/**
 * Created by hyl on 2019/03/29
 **/
public enum PlatformStatus implements ValueDescEnum {
    ONLINE(1, "在线"), OFFLINE(0, "下线");

    private int value;
    private String desc;

    private PlatformStatus(int value, String desc) {
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
    public static PlatformStatus valueOf(int value) {
        for (PlatformStatus status : values()) {
            if (status.value() == value)
                return status;
        }
        throw new IllegalArgumentException("Can't find enum[" + PlatformStatus.class.getCanonicalName()
                + "] by the value[" + value + "]");
    }
}