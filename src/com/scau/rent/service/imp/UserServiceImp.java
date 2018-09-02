package com.scau.rent.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.User;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.mapper.UserMapper;
import com.scau.rent.service.UserService;
import com.scau.rent.utils.MD5Utils;
/**
 * �û�ҵ��ӿ�ʵ����:�����û�����ҵ�����
 * @author Administrator
 *
 */
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper userMapper;

	/*ע���û�*/
	public void regist(UserExtend userExtend) throws Exception {
		//��ѯ���ݿ��Ƿ����ͬ���û�
		User user = userMapper.findByName(userExtend);
		if(user != null) {
	    //����ͬ���û�,�׳��쳣;���������û������ݿ�
			throw new Exception("�û����Ѵ���");
		}else {
	    //��������ܣ������û����ӵ����ݿ�
		userExtend.setUser_password(MD5Utils.md5(userExtend.getUser_password()));
		userMapper.insertUser(userExtend);
		}
	}

	/*�û���¼*/
	public User login(UserExtend userExtend) throws Exception {
		//�����û�����ѯ�û��Ƿ����
		UserExtend user = userMapper.findByName(userExtend);
		if(user == null || 
				!user.getUser_password().equals(MD5Utils.md5(userExtend.getUser_password()))) {
			throw new Exception("�û������������");
		}else return user;
	}

	/*�޸��û�����*/
	public User updatePassword(QueryVo vo) throws Exception {
		//�õ��û�
		UserExtend userExtend = vo.getUserExtend();
		//�жϾ������Ƿ�������ȷ
		if(userExtend != null && 
				!userExtend.getUser_password().equals(MD5Utils.md5(vo.getOld_password()))) {
			throw new Exception("�������������");
		}else {
			//����������м���
			String user_password = MD5Utils.md5(vo.getNew_password());
			userExtend.setUser_password(user_password);
			vo.setUserExtend(userExtend);
			userMapper.updatePassword(vo);
		}
		return userExtend;
		
	}
	
	/*�޸��û���Ϣ*/
	public User updateUser(QueryVo vo) throws Exception {
		//�����û���Ϣ
		userMapper.updateUser(vo);
		//����ID��ѯ�޸�֮����û�
		UserExtend user = userMapper.findByID(vo.getUserExtend());
		return user;
	}

	/*����ID��ѯ�û�*/
	public UserExtend findByID(UserExtend userExtend) throws Exception {
		UserExtend user = userMapper.findByID(userExtend);
		return user;
	}

	/*�޸�ͷ��*/
	public User updatePic(QueryVo vo) throws Exception {
		userMapper.updatePic(vo);
		UserExtend user = userMapper.findByID(vo.getUserExtend());
		return user;
	}

}