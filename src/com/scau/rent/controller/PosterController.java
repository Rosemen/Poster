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
 * �������봦����:�йغ����Ĳ��������� �������뺣������ѯ������
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

	/* �û����뺣�� */
	@RequestMapping(value = "/applyPoster.action", method = { RequestMethod.POST })
	public String applyPoster(@RequestParam("files") MultipartFile[] files, HttpSession session, Model model,
			QueryVo queryVo) throws Exception {
		// �����û��ϴ��ĺ���ͼƬ
		uploadImage(files, queryVo);
		// ���ú����������û�
		queryVo.setUserExtend((UserExtend) session.getAttribute("user"));
		// ���뺣��
		PageBean<PosterRecordExtend> pageBean = posterService.applyPoster(queryVo);
		// �����ҳ����,���ص������¼�б�
		model.addAttribute("pageBean", pageBean);
		return "poster/records";
	}

	/* ��ѯ���������¼(�����û���ѯ���˼�¼������Ա��ѯ���м�¼) */
	@RequestMapping(value = "/getAllRecords.action")
	public String getAllRecords(QueryVo queryVo, HttpSession session, Model model) throws Exception {
		// ����service��ķ������õ���ҳ����
		PageBean<PosterRecordExtend> pageBean = posterService.getPageBean(queryVo);
		// �����ҳ����
		model.addAttribute("pageBean", pageBean);
		// �����жϲ�ѯ���ǵ����û�
		if (queryVo.getUserExtend() != null) {
			model.addAttribute("flag_id", queryVo.getUserExtend().getUser_id());
		}
		return "poster/records";
	}

	/* �õ����е������¼���� */
	@RequestMapping(value = "/getPosterNumber.action")
	public @ResponseBody Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterService.getPosterNumber(posterExtend);
	}

	/* �����û�������(����Ա�Ĳ���) */
	@RequestMapping(value = "/handle.action")
	public String handle(PosterRecordExtend posterRecordExtend) throws Exception {
		posterService.handleRecord(posterRecordExtend);
		return "success";
	}

	/* ��ȡ�û�������δ����Ϣ */
	@RequestMapping("/getAllMessages.action")
	public String getAllMessages(HttpSession session, QueryVo vo, Model model) throws Exception {
		UserExtend userExtend = (UserExtend) session.getAttribute("user");

		vo.setUserExtend(userExtend);
		PageBean<MessagesExtend> pageBean = posterService.getMessages(vo);
		model.addAttribute("pageBean", pageBean);
		return "user/messages";
	}

	/* ��ѯδ����Ϣ������ */
	@RequestMapping("/getMsgNumber.action")
	public @ResponseBody Integer getMsgNumber(HttpSession session) throws Exception {
		UserExtend userExtend = (UserExtend) session.getAttribute("user");
		Integer number = posterService.getMsgNumber(userExtend);
		return number;
	}

	/* �����û���ϢΪ�Ѷ� */
	@RequestMapping("/updateMsgStatus.action")
	public String updateMsgStatus(MessagesExtend messagesExtend) throws Exception {
		posterService.updateMsgStatus(messagesExtend);
		return "redirect:/poster/getAllMessages.action";
	}

	/* �����ϴ�ͼƬ */
	private void uploadImage(MultipartFile[] files, QueryVo queryVo) throws IOException {
		if (files != null)
			for (int i = 0; i < files.length; i++) {
				String oldFileName = files[i].getOriginalFilename();
				if (oldFileName.length() == 0)
					continue;
				String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
				File file = new File(realPath + newFileName);
				// Ŀ¼�����ڣ��򴴽�Ŀ¼
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
