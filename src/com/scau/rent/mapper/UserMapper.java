package com.scau.rent.mapper;

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.UserExtend;

/**
 * 用户Mapper接口：与数据库进行数据交互
 * @author Administrator
 *
 */
public interface UserMapper {
   /*根据用户名查询数据库*/
	public UserExtend findByName(UserExtend userExtend) throws Exception;
	
   /*根据ID查询用户*/
	public UserExtend findByID(UserExtend userExtend) throws Exception;
	
   /*将用户信息添加到数据库*/
	public void insertUser(UserExtend userExtend) throws Exception;
	
	/*修改用户密码*/
	public void updatePassword(QueryVo vo) throws Exception;
	
	/*修改用户信息*/
	public void updateUser(QueryVo vo) throws Exception;
	
	/*修改用户头像*/
	public void updatePic(QueryVo vo) throws Exception;
	

	
}
