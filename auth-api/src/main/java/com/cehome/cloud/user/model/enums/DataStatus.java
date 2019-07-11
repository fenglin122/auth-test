package com.cehome.cloud.user.model.enums;

/**
 * Created by hyl on 2019/04/03
 **/
public enum DataStatus implements ValueDescEnum {

    VALID(0, "有效"), INVALID(1, "无效");

    private int value;
    private String desc;


    private DataStatus(int value, String desc) {
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

    public static DataStatus valueOf(int value) {
        for (DataStatus status : values()) {
            if (status.value() == value)
                return status;
        }

        throw new IllegalArgumentException("Can't find enum[" + DataStatus.class.getCanonicalName()
                + "] by the value[" + value + "]");
    }
}
