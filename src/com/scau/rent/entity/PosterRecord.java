package com.scau.rent.entity;

import java.util.Date;

/**
 * 用户申请海报的记录实体类
 * 
 * @author Administrator
 *
 */
public class PosterRecord {
	private String record_id; /* 申请记录ID，主键 */
	private Poster record_poster; /* 对应哪张海报 */
	private Date record_createtime; /* 记录创建时间 */
	private Integer record_status; /* 此次申请处理状态,0表示未处理，1表示已处理 */
	private Integer record_result; /* 申请处理结果 */
	private User record_user; /* 申请人 */
	private String record_admin; /* 处理人 */

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public Poster getRecord_poster() {
		return record_poster;
	}

	public void setRecord_poster(Poster record_poster) {
		this.record_poster = record_poster;
	}

	public Date getRecord_createtime() {
		return record_createtime;
	}

	public void setRecord_createtime(Date record_createtime) {
		this.record_createtime = record_createtime;
	}

	public Integer getRecord_status() {
		return record_status;
	}

	public void setRecord_status(Integer record_status) {
		this.record_status = record_status;
	}

	public Integer getRecord_result() {
		return record_result;
	}

	public void setRecord_result(Integer record_result) {
		this.record_result = record_result;
	}

	public User getRecord_user() {
		return record_user;
	}

	public void setRecord_user(User record_user) {
		this.record_user = record_user;
	}

	public String getRecord_admin() {
		return record_admin;
	}

	public void setRecord_admin(String record_admin) {
		this.record_admin = record_admin;
	}

}
