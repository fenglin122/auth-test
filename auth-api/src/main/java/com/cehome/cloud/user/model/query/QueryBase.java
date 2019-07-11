package com.cehome.cloud.user.model.query;


import com.cehome.cloud.user.model.enums.DataStatus;
import com.cehome.cloud.user.model.enums.ValueDescEnum;

import java.io.Serializable;


/**
 * 查询基础类。（分页查询）
 *
 * Created by hyl on 2019/03/29
 */
public abstract class QueryBase implements Serializable {
    private static final long serialVersionUID = -3233855679986216986L;

    public enum QuerySort implements ValueDescEnum {
        DESC(1, "DESC"), ASC(2, "ASC");

        private int value;
        private String desc;
        
        private QuerySort(int value, String desc) {
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
        
        public static QuerySort valueOf(int value) {
            for (QuerySort status : values()) {
                if (status.value() == value)
                    return status;
            }
            throw new IllegalArgumentException("Can't find enum[" + QuerySort.class.getCanonicalName()
                                               + "] by the value[" + value + "]");
        }
    }
    /**
     * 开始下标，默认0
     */
    private Integer startIndex = 0;
    /**
     * 查询个数，默认10
     */
    private Integer count = 10;
    /**
     * 排序，默认降序
     */
    private QuerySort sort = QuerySort.DESC;
    /**
     * 数据的逻辑删除，默认未删除
     */
    private DataStatus deleteStatus = DataStatus.VALID;
    
    
    public Integer getStartIndex() {
        return startIndex;
    }
    
    public void setStartIndex(Integer startIndex) {
        if (startIndex == null) return ;
        if (startIndex.intValue() < 0) throw new IllegalArgumentException("startIndex 不能小于0");
        this.startIndex = startIndex;
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        if (count == null) return ;
        if (count.intValue() <= 0) throw new IllegalArgumentException("count 必需大于0.");
        this.count = count;
    }

    public QuerySort getSort() {
        return sort;
    }

    public void setSort(QuerySort sort) {
        if (sort == null) return ;
        this.sort = sort;
    }

    public DataStatus getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(DataStatus deleteStatus) {
        if (deleteStatus == null) return ;
        this.deleteStatus = deleteStatus;
    }

    /**
     * 检查数据状态
     */
    public abstract boolean check();
}
