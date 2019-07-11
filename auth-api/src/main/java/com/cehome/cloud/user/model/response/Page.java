package com.cehome.cloud.user.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7395507780937350288L;
	/**
	 * 第几页
	 */
	private int pageIndex;
	/**
	 * 每页显示多少条
	 */
	private int pageSize;
	/**
	 * 分页的开始值
	 */
	private int pageOffset;
	/**
	 * 总共多少条记录
	 */
	private int totalRecord;
	/**
	 * 总共多少页
	 */
	private int totalPage;
	/**
	 * 放置具体数据的列表
	 */
	private List<E> datas;
	
	/**
	 * 专题页和其他页面的区分
	 */
	private String type;
	
    public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Page() {
    }


    public Page(Page source, Convert convert) {
        this.pageIndex = source.getPageIndex();
        this.pageSize = source.getPageSize();
        this.pageOffset = source.getPageOffset();
        this.totalRecord = source.getTotalRecord();
        this.totalPage = source.getTotalPage();
        List<E> re = new ArrayList<E>(this.pageSize);
        for (Object o : source.getDatas()) {
            Object n = convert.convert(o);
            re.add((E) n);
        }
        this.datas = re;
    }


    public static interface Convert<U> {
        <E> E convert(U u);
    }


    public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
}