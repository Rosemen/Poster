package com.scau.rent.entity;

/**
 * 海报实体类
 * 
 * @author Administrator
 *
 */
public class Poster {
	private String poster_id;        /* 海报ID，主键 */
	private String poster_content;   /* 海报内容 */
	private String poster_user;      /* 海报申请人 */
	private String poster_org;       /* 申请组织社团 */
	private String poster_phone;     /* 联系电话 */
	private String poster_location;  /* 粘贴位置 */
	private Integer poster_support;  /* 是否有赞助，0表示没有赞助,1表示有 */
	private String poster_pic;       /* 图片1 */
	private Integer poster_time;     /* 粘贴持续天数 */
	private String poster_anotherpic;/* 图片2 */

	public String getPoster_anotherpic() {
		return poster_anotherpic;
	}

	public void setPoster_anotherpic(String poster_anotherpic) {
		this.poster_anotherpic = poster_anotherpic;
	}

	public String getPoster_id() {
		return poster_id;
	}

	public void setPoster_id(String poster_id) {
		this.poster_id = poster_id;
	}

	public String getPoster_content() {
		return poster_content;
	}

	public void setPoster_content(String poster_content) {
		this.poster_content = poster_content;
	}

	public String getPoster_user() {
		return poster_user;
	}

	public void setPoster_user(String poster_user) {
		this.poster_user = poster_user;
	}

	public String getPoster_org() {
		return poster_org;
	}

	public void setPoster_org(String poster_org) {
		this.poster_org = poster_org;
	}

	public String getPoster_phone() {
		return poster_phone;
	}

	public void setPoster_phone(String poster_phone) {
		this.poster_phone = poster_phone;
	}

	public String getPoster_location() {
		return poster_location;
	}

	public void setPoster_location(String poster_location) {
		this.poster_location = poster_location;
	}

	public Integer getPoster_support() {
		return poster_support;
	}

	public void setPoster_support(Integer poster_support) {
		this.poster_support = poster_support;
	}

	public String getPoster_pic() {
		return poster_pic;
	}

	public void setPoster_pic(String poster_pic) {
		this.poster_pic = poster_pic;
	}

	public Integer getPoster_time() {
		return poster_time;
	}

	public void setPoster_time(Integer poster_time) {
		this.poster_time = poster_time;
	}

}
