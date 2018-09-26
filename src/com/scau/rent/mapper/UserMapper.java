package com.scau.rent.mapper;

import java.util.List;

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.UserExtend;

/**
 * �û�Mapper�ӿڣ������ݿ�������ݽ���
 * @author Administrator
 *
 */
public interface UserMapper {
   /*�����û�����ѯ���ݿ�*/
	public UserExtend findByName(UserExtend userExtend) throws Exception;
	
   /*����ID��ѯ�û�*/
	public UserExtend findByID(UserExtend userExtend) throws Exception;
	
	/*��ѯ�����û�*/
	public List<UserExtend> findAll(QueryVo vo) throws Exception;
	
	/*��ѯ��ͨ�û��ܼ�¼��*/
	public Integer getRowCount() throws Exception;
	
   /*���û���Ϣ��ӵ����ݿ�*/
	public void insertUser(UserExtend userExtend) throws Exception;
	
	/*�޸��û�����*/
	public void updatePassword(QueryVo vo) throws Exception;
	
	/*�޸��û���Ϣ*/
	public void updateUser(QueryVo vo) throws Exception;
	
	/*�޸��û�ͷ��*/
	public void updatePic(QueryVo vo) throws Exception;
}
