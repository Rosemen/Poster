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

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.User;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.service.UserService;

/**
 * �û��������ࣺ�����û�������
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

	/* �û�ע�� */
	@RequestMapping(value = "/regist.action", method = { RequestMethod.POST })
	public String regist(RedirectAttributes model, UserExtend userExtend) {
		try {
			// ע��
			userService.regist(userExtend);

		} catch (Exception e) {
			// ���������Ϣ����ת����ע��ҳ��
			model.addFlashAttribute("error", e.getMessage());
			// �������
			model.addFlashAttribute("user", userExtend);

			return "redirect:/user/toRegist.action";
		}
		// ת�����ɹ�ҳ��
		return "user/success";
	}

	/* �û���¼ */
	@RequestMapping(value = "/login.action", method = { RequestMethod.POST })
	public String login(RedirectAttributes model, HttpSession session, UserExtend userExtend) {
		try {
			// ��¼
			User user = userService.login(userExtend);
			// �������session��
			session.setAttribute("user", user);
		} catch (Exception e) {
			System.out.println(e);
			// ���������Ϣ
			model.addFlashAttribute("error", e.getMessage());
			// ��������󣬻���
			model.addFlashAttribute("user", userExtend);
			// �ض���
			return "redirect:/user/toLogin.action";
		}
		// �ض�����ҳ��
		return "redirect:/jsp/home.jsp";
	}

	/* �û��˳���¼ */
	@RequestMapping(value = "/exit.action")
	public String exit(HttpSession session) {
		// ��¼
		User user = (User) session.getAttribute("user");
		if (user != null) {// ����session
			session.invalidate();
			return "redirect:/user/toLogin.action";
		} else
			return "redirect:/jsp/error.jsp";

	}

	/* �޸��û����� */
	@RequestMapping("/updatePassword.action")
	public String updatePassword(QueryVo vo, Model model, HttpSession session) {
		try {
			// ȡ��session�е��û�����,�����浽vo��
			vo.setUserExtend((UserExtend) session.getAttribute("user"));
			// �޸�����
			User user = userService.updatePassword(vo);
			session.setAttribute("user", user);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "user/edit_password";
		}
		return "success";
	}

	/* �޸��û���Ϣ */
	@RequestMapping("/updateUser.action")
	public String updateUser(QueryVo vo, Model model, HttpSession session) throws Exception {
		User user = userService.updateUser(vo);
		session.setAttribute("user", user);
		return "success";
	}
	
	/* �û��ϴ�ͷ��*/
	@RequestMapping("/uploadPic.action")
	public String uploadPic(QueryVo vo,HttpSession session,@RequestParam("picture") MultipartFile picture) throws Exception {
		if(picture != null) {
			String oldFileName = picture.getOriginalFilename();
			String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
			File f = new File(realPath + newFileName);
			// Ŀ¼�����ڣ��򴴽�Ŀ¼
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

	/* �ض��򵽵�¼ҳ�� */
	@RequestMapping("/toLogin")
	public String toLogin(@ModelAttribute("user") UserExtend user, @ModelAttribute("error") String error, Model model) {
		// ���������Ϣ
		model.addAttribute("error", error);
		// ��������󣬻���
		model.addAttribute("user", user);
		return "user/login";
	}

	/* �ض���ע��ҳ�� */
	@RequestMapping("/toRegist")
	public String toRegist(@ModelAttribute("user") UserExtend user, @ModelAttribute("error") String error,
			Model model) {
		// ���������Ϣ
		model.addAttribute("error", error);
		// ��������󣬻���
		model.addAttribute("user", user);
		return "user/regist";
		//return "user/delete";
	}

}
