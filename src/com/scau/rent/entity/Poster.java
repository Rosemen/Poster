package com.scau.rent.entity;

/**
 * ����ʵ����
 * 
 * @author Administrator
 *
 */
public class Poster {
	private String poster_id;        /* ����ID������ */
	private String poster_content;   /* �������� */
	private String poster_user;      /* ���������� */
	private String poster_org;       /* ������֯���� */
	private String poster_phone;     /* ��ϵ�绰 */
	private String poster_location;  /* ճ��λ�� */
	private Integer poster_support;  /* �Ƿ���������0��ʾû������,1��ʾ�� */
	private String poster_pic;       /* ͼƬ1 */
	private Integer poster_time;     /* ճ���������� */
	private String poster_anotherpic;/* ͼƬ2 */

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
