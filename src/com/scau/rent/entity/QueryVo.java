package com.scau.rent.entity;

import com.scau.rent.entity.extend.MessagesExtend;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;

/**
 * 包装类对象：可以包含pojo类型的属性
 * 
 * @author Administrator
 *
 */
public class QueryVo {
	private String old_password;
	private String new_password;
	private Integer current_page;
	private Integer start;
	private Integer end;
	private UserExtend userExtend;
	private PosterExtend posterExtend;
	private PosterRecordExtend posterRecordExtend;
	private MessagesExtend messageExtend;

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public Integer getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public UserExtend getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtend userExtend) {
		this.userExtend = userExtend;
	}

	public PosterExtend getPosterExtend() {
		return posterExtend;
	}

	public void setPosterExtend(PosterExtend posterExtend) {
		this.posterExtend = posterExtend;
	}

	public PosterRecordExtend getPosterRecordExtend() {
		return posterRecordExtend;
	}

	public void setPosterRecordExtend(PosterRecordExtend posterRecordExtend) {
		this.posterRecordExtend = posterRecordExtend;
	}

	public MessagesExtend getMessageExtend() {
		return messageExtend;
	}

	public void setMessageExtend(MessagesExtend messageExtend) {
		this.messageExtend = messageExtend;
	}

}
