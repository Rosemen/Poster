package com.scau.rent.entity;

import java.util.Date;

/**
 * �û����뺣���ļ�¼ʵ����
 * 
 * @author Administrator
 *
 */
public class PosterRecord {
	private String record_id; /* �����¼ID������ */
	private Poster record_poster; /* ��Ӧ���ź��� */
	private Date record_createtime; /* ��¼����ʱ�� */
	private Integer record_status; /* �˴����봦��״̬,0��ʾδ����1��ʾ�Ѵ��� */
	private Integer record_result; /* ���봦���� */
	private User record_user; /* ������ */
	private String record_admin; /* ������ */

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
