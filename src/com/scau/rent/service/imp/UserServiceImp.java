package com.scau.rent.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.User;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.mapper.UserMapper;
import com.scau.rent.service.UserService;
import com.scau.rent.utils.MD5Utils;
/**
 * 用户业务接口实现类:定义用户具体业务操作
 * @author Administrator
 *
 */
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper userMapper;

	/*注册用户*/
	public void regist(UserExtend userExtend) throws Exception {
		//查询数据库是否存在同名用户
		User user = userMapper.findByName(userExtend);
		if(user != null) {
	    //存在同名用户,抛出异常;否则添加用户到数据库
			throw new Exception("用户名已存在");
		}else {
		//设置用户为普通用户
		userExtend.setUser_type(0);
	    //将密码加密，并将用户添加到数据库
		userExtend.setUser_password(MD5Utils.md5(userExtend.getUser_password()));
		userMapper.insertUser(userExtend);
		}
	}

	/*用户登录*/
	public User login(UserExtend userExtend) throws Exception {
		//根据用户名查询用户是否存在
		UserExtend user = userMapper.findByName(userExtend);
		if(user == null || 
				!user.getUser_password().equals(MD5Utils.md5(userExtend.getUser_password()))) {
			throw new Exception("用户名或密码错误");
		}else if(user.getUser_type() != userExtend.getUser_type()){
			throw new Exception("请正确选择身份");
		}
		else return user;
	}

	/*修改用户密码*/
	public User updatePassword(QueryVo vo) throws Exception {
		//得到用户
		UserExtend userExtend = vo.getUserExtend();
		//判断旧密码是否输入正确
		if(userExtend != null && 
				!userExtend.getUser_password().equals(MD5Utils.md5(vo.getOld_password()))) {
			throw new Exception("旧密码输入错误");
		}else {
			//对新密码进行加密
			String user_password = MD5Utils.md5(vo.getNew_password());
			userExtend.setUser_password(user_password);
			vo.setUserExtend(userExtend);
			userMapper.updatePassword(vo);
		}
		return userExtend;
		
	}
	
	/*修改用户信息*/
	public User updateUser(QueryVo vo) throws Exception {
		//更新用户信息
		userMapper.updateUser(vo);
		//根据ID查询修改之后的用户
		UserExtend user = userMapper.findByID(vo.getUserExtend());
		return user;
	}

	/*根据ID查询用户*/
	public UserExtend findByID(UserExtend userExtend) throws Exception {
		UserExtend user = userMapper.findByID(userExtend);
		return user;
	}

	/*修改头像*/
	public User updatePic(QueryVo vo) throws Exception {
		userMapper.updatePic(vo);
		UserExtend user = userMapper.findByID(vo.getUserExtend());
		return user;
	}

	/*查询所有用户,属于管理员的操作*/
	public PageBean<UserExtend> getAllUser(QueryVo vo) throws Exception {
		Integer total_record = userMapper.getRowCount();
		PageBean<UserExtend> pageBean = new PageBean<>(vo.getCurrent_page(), total_record);
		Integer start = (pageBean.getCurrent_page() - 1)*pageBean.getPage_size();
		Integer end = pageBean.getPage_size();
		vo.setStart(start);
		vo.setEnd(end);
		List<UserExtend> userList = userMapper.findAll(vo);
		pageBean.setRecords(userList);
		return pageBean;
	}

}
