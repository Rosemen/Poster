package com.scau.rent.entity;
/**
 * ��Ϣʵ����
 * @since 2018/09/21
 * @author chen
 *
 */

import com.scau.rent.entity.extend.UserExtend;

public class Messages {
	private String msg_id;         //��ϢID
	private UserExtend msg_user;   //��Ϣ�����û�
	private String msg_content;    //��Ϣ����
	private Integer msg_status;    //��Ϣ���ͣ�0��δ����1���Ѷ�

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public UserExtend getMsg_user() {
		return msg_user;
	}

	public void setMsg_user(UserExtend msg_user) {
		this.msg_user = msg_user;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public Integer getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(Integer msg_status) {
		this.msg_status = msg_status;
	}

}
