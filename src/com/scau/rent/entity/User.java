package com.scau.rent.entity;

import java.util.ArrayList;
import java.util.List;

import com.scau.rent.entity.extend.PosterRecordExtend;

/**
 * ��ͨ�û�ʵ����
 * @author Administrator
 *
 */

public class User {
	private String user_id; // �û�ID
	private String user_name; // �û���
	private String user_password; // �û�����
	private String user_phone; // �û��ֻ�
	private String user_email; // �û�����
	private String user_org; // �û���֯
	private Integer user_type;// �û�����
	private String user_pic;// �û�ͷ��
	//�û������¼�б�
	private List<PosterRecordExtend> records = new ArrayList<PosterRecordExtend>();

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_org() {
		return user_org;
	}

	public void setUser_org(String user_org) {
		this.user_org = user_org;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}

	public List<PosterRecordExtend> getRecords() {
		return records;
	}

	public void setRecords(List<PosterRecordExtend> records) {
		this.records = records;
	}

}
