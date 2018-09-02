package com.scau.rent.entity;

import java.util.ArrayList;
import java.util.List;



/**
 * 分页实体类
 * 
 * @author Administrator
 *
 */
public class PageBean<T> {
	private Integer current_page; // 当前页
	private Integer page_size; // 每页记录数
	private Integer total_record; // 总记录数
	private Integer total_page; // 总页数
	private List<T> records = new ArrayList<T>();

	public PageBean(Integer current_page, Integer total_record) {
		//设置当前页
		if (current_page == null || current_page == 0)
			this.current_page = 1;
		else 
			this.current_page = current_page;
		
		//设置总记录数
		if (total_record == null)
			this.total_record = 0;
		else this.total_record = total_record;
		
		//每页记录数
		this.page_size = 6;
		
		//总页数
		if (this.total_record % this.page_size != 0)
			this.total_page = this.total_record / this.page_size + 1;
		else
			this.total_page = this.total_record / this.page_size;
		
		if(this.total_page < this.current_page && this.current_page != 1)
			this.current_page = this.total_page;
		
		if(this.current_page <= 0)
			this.current_page = 1;
	}

	public Integer getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getTotal_record() {
		return total_record;
	}

	public void setTotal_record(Integer total_record) {
		this.total_record = total_record;
	}

	public Integer getTotal_page() {
		return total_page;
	}

	public void setTotal_page(Integer total_page) {
		this.total_page = total_page;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

}
