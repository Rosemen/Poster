package com.scau.rent.entity;
/**
 * 消息实体类
 * @since 2018/09/21
 * @author chen
 *
 */

import com.scau.rent.entity.extend.UserExtend;

public class Messages {
	private String msg_id;         //消息ID
	private UserExtend msg_user;   //消息所属用户
	private String msg_content;    //消息内容
	private Integer msg_status;    //消息类型，0：未读，1：已读

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
