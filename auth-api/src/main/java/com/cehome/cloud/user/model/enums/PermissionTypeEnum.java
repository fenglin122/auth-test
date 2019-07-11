package com.cehome.cloud.user.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by hyl on 2019/03/29
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PermissionTypeEnum implements ValueDescEnum {

    MENU(1, "菜单"), BUTTON(2, "按钮");

    private int value;
    private String desc;

    private PermissionTypeEnum(int value, String desc) {
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

    public static PermissionTypeEnum valueOf(int value) {
        for (PermissionTypeEnum status : values()) {
            if (status.value() == value)
                return status;
        }
        throw new IllegalArgumentException("Can't find enum[" + PermissionTypeEnum.class.getCanonicalName() + "] by the value[" + value + "]");
    }
}
