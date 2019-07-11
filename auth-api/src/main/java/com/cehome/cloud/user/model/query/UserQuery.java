package com.cehome.cloud.user.model.query;


import com.cehome.cloud.user.model.enums.UserStatus;
import com.cehome.cloud.user.model.enums.ValueDescEnum;

/**
 * 用户查询（抽象类）
 *
 * Created by hyl on 2019/04/03
 */
public abstract class UserQuery extends QueryBase {
    
    private static final long serialVersionUID = -7685197378255091199L;
    
    
    public enum UserOrderBy implements ValueDescEnum {
        
        ID(1, "id"), /* ROLEID(2, "role_id"), PATH(3, "path"), */LOGINNAME(4, "login_name"), CREATETIME(5, "create_time"),UPDATETIME(5, "update_time");
        
        private int value;
        private String desc;
        
        
        private UserOrderBy(int value, String desc) {
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
        
        public static UserOrderBy valueOf(int value) {
            for (UserOrderBy status : values()) {
                if (status.value() == value)
                    return status;
            }
            
            throw new IllegalArgumentException("Can't find enum[" + UserOrderBy.class.getCanonicalName()
                                               + "] by the value[" + value + "]");
        }
    }
    
    
    /** 用户ID */
    private Integer userId;
    /** 用户名 */
    private String username;
    /** 用户手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 用户状态 */
    private UserStatus status;
    
    private UserOrderBy order = UserOrderBy.ID;

    public UserQuery(){}
    
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public UserStatus getStatus() {
        return status;
    }
    
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
    public UserOrderBy getOrder() {
        return order;
    }
    
    public void setOrder(UserOrderBy order) {
        this.order = order;
    }
    
}
