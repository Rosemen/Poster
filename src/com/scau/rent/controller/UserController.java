package com.scau.rent.controller;

import java.io.File;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.User;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.service.UserService;

/**
 * 用户处理器类：处理用户的请求
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Value("${image.user.real}")
	private String realPath;
	@Value("${image.user.logic}")
	private String logicPath;

	/* 用户注册 */
	@RequestMapping(value = "/regist.action", method = { RequestMethod.POST })
	public String regist(RedirectAttributes model, UserExtend userExtend) {
		try {
			// 注册
			userService.regist(userExtend);

		} catch (Exception e) {
			// 保存错误信息，并转发到注册页面
			model.addFlashAttribute("error", e.getMessage());
			// 保存表单用
			model.addFlashAttribute("user", userExtend);

			return "redirect:/user/toRegist.action";
		}
		// 转发到成功页面
		return "user/success";
	}

	/* 用户登录 */
	@RequestMapping(value = "/login.action", method = { RequestMethod.POST })
	public String login(RedirectAttributes model, HttpSession session, UserExtend userExtend) {
		User user = null;
		try {
			//设置同一浏览器只能登录一个用户
			if(null != session.getAttribute("user"))
				return "redirect:/user/toLogin.action";
			// 登录
			user = userService.login(userExtend);
			//将user保存到session中
			session.setAttribute("user",user);
		} catch (Exception e) {
			System.out.println(e);
			// 保存错误信息
			model.addFlashAttribute("error", e.getMessage());
			// 保存表单对象，回显
			model.addFlashAttribute("user", userExtend);
			// 重定向
			return "redirect:/user/toLogin.action";
		}
		//重定向到主页面
		return "redirect:/user/toHome.action";
	}

	/* 用户退出登录 */
	@RequestMapping(value = "/exit.action")
	public String exit(HttpSession session) {
		// 登录
		User user = (User) session.getAttribute("user");
		if (user != null) {// 销毁session
			session.invalidate();
			return "redirect:/user/toLogin.action";
		} else
			return "redirect:/user/toError";

	}

	/* 修改用户密码 */
	@RequestMapping("/updatePassword.action")
	public String updatePassword(QueryVo vo, Model model, HttpSession session) {
		try {
			// 取出session中的用户对象,并保存到vo中
			vo.setUserExtend((UserExtend) session.getAttribute("user"));
			// 修改密码
			User user = userService.updatePassword(vo);
			session.setAttribute("user", user);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "user/edit_password";
		}
		return "success";
	}

	/* 修改用户信息 */
	@RequestMapping("/updateUser.action")
	public String updateUser(QueryVo vo, Model model, HttpSession session) throws Exception {
		User user = userService.updateUser(vo);
		session.setAttribute("user", user);
		return "success";
	}
	
	/* 用户上传头像*/
	@RequestMapping("/uploadPic.action")
	public String uploadPic(QueryVo vo,HttpSession session,@RequestParam("picture") MultipartFile picture) throws Exception {
		if(picture != null) {
			String oldFileName = picture.getOriginalFilename();
			String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
			File f = new File(realPath + newFileName);
			// 目录不存在，则创建目录
			if (!f.exists())
				f.mkdirs();
			picture.transferTo(f);
			vo.setUserExtend((UserExtend)session.getAttribute("user"));
			vo.getUserExtend().setUser_pic(logicPath + newFileName);
			User user = userService.updatePic(vo);
			session.setAttribute("user", user);
		}
		return "home";
	}
	
	/*管理员查询所有用户*/
	/* 修改用户信息 */
	@RequestMapping("/getAllUser.action")
	public String getAllUser(QueryVo vo, Model model) throws Exception {
		PageBean<UserExtend> pageBean = userService.getAllUser(vo);
		model.addAttribute("pageBean", pageBean);
		return "admin/list";
	}

	/* 重定向到登录页面 */
	@RequestMapping("/toLogin")
	public String toLogin(@ModelAttribute("user") UserExtend user, @ModelAttribute("error") String error, Model model) {
		// 保存错误信息
		model.addAttribute("error", error);
		// 保存表单对象，回显
		model.addAttribute("user", user);
		return "user/login";
	}

	/* 重定向到注册页面 */
	@RequestMapping("/toRegist")
	public String toRegist(@ModelAttribute("user") UserExtend user, @ModelAttribute("error") String error,
			Model model) {
		// 保存错误信息
		model.addAttribute("error", error);
		// 保存表单对象，回显
		model.addAttribute("user", user);
		return "user/regist";
	}
	
	/* 重定向到主页面 */
	@RequestMapping("/toHome")
	public String toHome() {
		return "home";
	}
	
	/* 重定向到错误页面 */
	@RequestMapping("/toError")
	public String toError() {
		return "error";
	}
}
