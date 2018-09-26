package com.scau.rent.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.MessagesExtend;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;
import com.scau.rent.mapper.PosterMapper;
import com.scau.rent.service.PosterService;
/**
 * ���ں��������ҵ��ӿ�ʵ����
 * @author Administrator
 *
 */
@Transactional
public class PosterServiceImp implements PosterService {
	@Autowired
	private PosterMapper posterMapper;

	/*����ճ������*/
	public PageBean<PosterRecordExtend> applyPoster(QueryVo queryVo) throws Exception {
		//��������ĺ�����Ϣ
		posterMapper.insertPoster(queryVo);
		//���һ�����뺣���ļ�¼,������Ϊδ����
		PosterRecordExtend posterRecordExtend = new PosterRecordExtend();
		posterRecordExtend.setRecord_user(queryVo.getUserExtend());
		posterRecordExtend.setRecord_status(0);
		posterRecordExtend.setRecord_poster(queryVo.getPosterExtend());
        posterRecordExtend.setRecord_createtime(new Date());
		queryVo.setPosterRecordExtend(posterRecordExtend);
		posterMapper.insertRecord(queryVo);
		//���ظ��û�����������ķ�ҳ����
		PageBean<PosterRecordExtend> pageBean = this.getPageBean(queryVo);
		//֪ͨ����Ա��������
		sendMessage("381651b5-ab5d-11e8-adde-28c2dd9bf376", 0);
		return pageBean;
	}

	/*�õ�ͬһ¥����������*/
	public Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterMapper.getRowCount(posterExtend);
	}


	/*��ҳ��ѯĳ���û������м�¼*/
	public PageBean<PosterRecordExtend> getPageBean(QueryVo queryVo) throws Exception {
		//�õ��ܼ�¼��
		Integer total_record = posterMapper.getRecordNumber(queryVo);
		//������ҳ����
		PageBean<PosterRecordExtend> pageBean =
				new PageBean<PosterRecordExtend>(queryVo.getCurrent_page(),total_record);
		//��ʼ����
		Integer start = (pageBean.getCurrent_page() - 1)*pageBean.getPage_size();
		Integer end = pageBean.getPage_size();
		queryVo.setStart(start);
		queryVo.setEnd(end);
		//��ѯ���ݿ⣬�õ���¼�б�
        List<PosterRecordExtend> records = posterMapper.selectAllRecords(queryVo);
		pageBean.setRecords(records);
		return pageBean;
	}
	
	/*��ҳ��ѯ�����û������м�¼*/
	public PageBean<PosterRecordExtend> getPageBeans(QueryVo queryVo) throws Exception {
		//�õ��ܼ�¼��
		Integer total_record = posterMapper.getTotalNumber(queryVo);
		//������ҳ����
		PageBean<PosterRecordExtend> pageBean =
				new PageBean<PosterRecordExtend>(queryVo.getCurrent_page(),total_record);
		//��ʼ����
		Integer start = (pageBean.getCurrent_page() - 1)*pageBean.getPage_size();
		Integer end = pageBean.getPage_size();
		queryVo.setStart(start);
		queryVo.setEnd(end);
		//��ѯ���ݿ⣬�õ���¼�б�
        List<PosterRecordExtend> records = posterMapper.selectAll(queryVo);
		pageBean.setRecords(records);
		return pageBean;
	}

	/*���������Ƿ�ͨ��*/
	public void handleRecord(PosterRecordExtend posterRecordExtend) throws Exception {
	    //��������Ϊ�Ѵ���
		posterRecordExtend.setRecord_status(1);
		//���û���һ��֪ͨ
		sendMessage(posterRecordExtend.getRecord_user().getUser_id(), 1);
		posterMapper.updateRecordStatus(posterRecordExtend);
	}
	
	/*��ѯδ����Ϣ������*/
	public Integer getMsgNumber(UserExtend userExtend) throws Exception {
		Integer number = posterMapper.selectMsgNumber(userExtend);
		return number;
	}

	/*��ѯ���е�δ����Ϣ */
	public PageBean<MessagesExtend> getMessages(QueryVo vo) throws Exception {
		Integer total_record = posterMapper.selectMsgNumber(vo.getUserExtend());
		PageBean<MessagesExtend> pageBean = new PageBean<>(vo.getCurrent_page(), total_record);
		Integer start = (pageBean.getCurrent_page() - 1)*pageBean.getPage_size();
		Integer end = pageBean.getPage_size();
		vo.setStart(start);
		vo.setEnd(end);
		List<MessagesExtend> messages = posterMapper.selectMsg(vo);
		pageBean.setRecords(messages);
		return pageBean;
	}

    /*������ϢΪ�Ѷ�*/
	public void updateMsgStatus(MessagesExtend messagesExtend) throws Exception {
		posterMapper.updateMsgStatus(messagesExtend);
	}
	
	
	private void sendMessage(String user_id,Integer user_type) {
		MessagesExtend message = new MessagesExtend();
		UserExtend msg_user = new UserExtend();
		msg_user.setUser_id(user_id);
		message.setMsg_user(msg_user);
		message.setMsg_status(0);
		if(user_type == 1) {
			message.setMsg_content("����Ա�Ѵ���������룬��ǰ���ҵ�����鿴!");	
		}else message.setMsg_content("���µ����룬��ǰ���ҵ�����鿴!");
		posterMapper.insertMessage(message);
	}

}
