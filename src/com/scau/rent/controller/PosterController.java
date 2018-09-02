package com.scau.rent.controller;

import java.io.File;
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
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.service.PosterService;

/**
 * 海报申请处理器
 * 
 * @author Administrator
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

	@RequestMapping(value = "/applyPoster.action", method = { RequestMethod.POST })
	public String applyPoster(@RequestParam("files") MultipartFile[] files,HttpSession session,Model model, QueryVo queryVo)
			throws Exception {
		// 上传图片
		if(files != null)
		for (int i = 0; i < files.length; i++) {
			String oldFileName = files[i].getOriginalFilename();
			if(oldFileName.length() == 0)
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
		queryVo.setUserExtend((UserExtend)session.getAttribute("user"));
		PageBean<PosterRecordExtend> pageBean = posterService.applyPoster(queryVo);
		model.addAttribute("pageBean", pageBean);
		//返回到申请记录列表
		return "poster/list";
	}
	
	@RequestMapping(value = "/getAllRecords.action")
	public String getAllRecords(QueryVo queryVo,HttpSession session,Model model) throws Exception {
		queryVo.setUserExtend((UserExtend)session.getAttribute("user"));
		PageBean<PosterRecordExtend> pageBean = posterService.getPageBean(queryVo);
		model.addAttribute("pageBean", pageBean);
		return "poster/list";
	}
	
	@RequestMapping(value = "/getPosterNumber.action")
	public @ResponseBody Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterService.getPosterNumber(posterExtend);
	}
}
