package com.scau.rent.service;

import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.User;
import com.scau.rent.entity.extend.UserExtend;

/**
 * �û�ҵ��ӿ�:�����û�����
 * @author Administrator
 *
 */
public interface UserService {
	/*ע���û�*/
	public void regist(UserExtend userExtend) throws Exception;
	
	/*��¼�û�*/
	public User login(UserExtend userExtend) throws Exception;
	
	/*�޸��û�����*/
	public User updatePassword(QueryVo vo) throws Exception;
	
	/*�޸��û�ͷ��*/
	public User updatePic(QueryVo vo) throws Exception;
	
	/*�޸��û���Ϣ*/
	public User updateUser(QueryVo vo) throws Exception;
	
	/*����ID��ѯ�û�*/
	public UserExtend findByID(UserExtend userExtend) throws Exception;
	
	/*��ѯ�����û�*/
	public PageBean<UserExtend> getAllUser(QueryVo vo) throws Exception;
    
}
