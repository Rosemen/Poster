package com.scau.rent.service;

import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.User;
import com.scau.rent.entity.extend.UserExtend;

/**
 * 用户业务接口:定义用户操作
 * @author Administrator
 *
 */
public interface UserService {
	/*注册用户*/
	public void regist(UserExtend userExtend) throws Exception;
	
	/*登录用户*/
	public User login(UserExtend userExtend) throws Exception;
	
	/*修改用户密码*/
	public User updatePassword(QueryVo vo) throws Exception;
	
	/*修改用户头像*/
	public User updatePic(QueryVo vo) throws Exception;
	
	/*修改用户信息*/
	public User updateUser(QueryVo vo) throws Exception;
	
	/*根据ID查询用户*/
	public UserExtend findByID(UserExtend userExtend) throws Exception;
	
	/*查询所有用户*/
	public PageBean<UserExtend> getAllUser(QueryVo vo) throws Exception;
    
}
