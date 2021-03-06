package com.scau.rent.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.MessagesExtend;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.service.PosterService;

/**
 * 海报申请处理器:有关海报的操作处理器 包括申请海报、查询海报等
 * 
 * @since 2018/09/27
 * @author chen
 *
 */
@Controller
@RequestMapping("/poster")
public class PosterController {
	@Autowired
	private PosterService posterService;

	@Value("${image.real.path}")
	private String realPath;
	@Value("${image.logic.path}")
	private String logicPath;

	/* 用户申请海报 */
	@RequestMapping(value = "/applyPoster.action", method = { RequestMethod.POST })
	public String applyPoster(@RequestParam("files") MultipartFile[] files, HttpSession session, Model model,
			QueryVo queryVo) throws Exception {
		// 处理用户上传的海报图片
		uploadImage(files, queryVo);
		// 设置海报的申请用户
		queryVo.setUserExtend((UserExtend) session.getAttribute("user"));
		// 申请海报
		PageBean<PosterRecordExtend> pageBean = posterService.applyPoster(queryVo);
		// 保存分页对象,返回到申请记录列表
		model.addAttribute("pageBean", pageBean);
		return "poster/records";
	}

	/* 查询所有申请记录(包括用户查询个人记录、管理员查询所有记录) */
	@RequestMapping(value = "/getAllRecords.action")
	public String getAllRecords(QueryVo queryVo, HttpSession session, Model model) throws Exception {
		// 调用service层的方法，得到分页对象
		PageBean<PosterRecordExtend> pageBean = posterService.getPageBean(queryVo);
		// 保存分页对象
		model.addAttribute("pageBean", pageBean);
		// 用来判断查询的是单个用户
		if (queryVo.getUserExtend() != null) {
			model.addAttribute("flag_id", queryVo.getUserExtend().getUser_id());
		}
		return "poster/records";
	}

	/* 得到所有的申请记录总数 */
	@RequestMapping(value = "/getPosterNumber.action")
	public @ResponseBody Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterService.getPosterNumber(posterExtend);
	}

	/* 处理用户申请结果(管理员的操作) */
	@RequestMapping(value = "/handle.action")
	public String handle(PosterRecordExtend posterRecordExtend) throws Exception {
		posterService.handleRecord(posterRecordExtend);
		return "success";
	}

	/* 获取用户的所有未读消息 */
	@RequestMapping("/getAllMessages.action")
	public String getAllMessages(HttpSession session, QueryVo vo, Model model) throws Exception {
		UserExtend userExtend = (UserExtend) session.getAttribute("user");

		vo.setUserExtend(userExtend);
		PageBean<MessagesExtend> pageBean = posterService.getMessages(vo);
		model.addAttribute("pageBean", pageBean);
		return "user/messages";
	}

	/* 查询未读消息的总数 */
	@RequestMapping("/getMsgNumber.action")
	public @ResponseBody Integer getMsgNumber(HttpSession session) throws Exception {
		UserExtend userExtend = (UserExtend) session.getAttribute("user");
		Integer number = posterService.getMsgNumber(userExtend);
		return number;
	}

	/* 设置用户消息为已读 */
	@RequestMapping("/updateMsgStatus.action")
	public String updateMsgStatus(MessagesExtend messagesExtend) throws Exception {
		posterService.updateMsgStatus(messagesExtend);
		return "redirect:/poster/getAllMessages.action";
	}

	/* 处理上传图片 */
	private void uploadImage(MultipartFile[] files, QueryVo queryVo) throws IOException {
		if (files != null)
			for (int i = 0; i < files.length; i++) {
				String oldFileName = files[i].getOriginalFilename();
				if (oldFileName.length() == 0)
					continue;
				String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
				File file = new File(realPath + newFileName);
				// 目录不存在，则创建目录
				if (!file.exists())
					file.mkdirs();
				if (i == 0)
					queryVo.getPosterExtend().setPoster_pic(logicPath + newFileName);
				if (i == 1)
					queryVo.getPosterExtend().setPoster_anotherpic(logicPath + newFileName);
				files[i].transferTo(file);

			}
	}

}
