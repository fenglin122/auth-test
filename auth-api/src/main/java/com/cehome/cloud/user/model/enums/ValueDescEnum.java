package com.cehome.cloud.user.model.enums;


/**
 * 含有value()和desc()方法的枚举接口。
 * 领域模型对象中所有枚举都应实现此接口，以便MyBatis的TypeHandler能够处理枚举类型。
 *
 * Created by hyl on 2019/03/29
 */
public interface ValueDescEnum {
    
    /**
     * 返回枚举值
     * 
     * @return 枚举值
     */
    public int value();
    
    /**
     * 返回枚举描述
     * 
     * @return 枚举描述
     */
    public String desc();
}
