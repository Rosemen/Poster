package com.scau.rent.service;


import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.MessagesExtend;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;

/**
 * ���ں��������ҵ����
 * @author Administrator
 *
 */
public interface PosterService {
   /*����ճ������*/
   public PageBean<PosterRecordExtend> applyPoster(QueryVo queryVo) throws Exception;
   
   /*��ѯͬһ¥����������*/
   public Integer getPosterNumber(PosterExtend posterExtend) throws Exception;
   
   /*��ҳ��ѯ�û������������¼*/
   public PageBean<PosterRecordExtend> getPageBean(QueryVo queryVo) throws Exception;
   
   /*��ҳ��ѯ�����û������������¼*/
   public PageBean<PosterRecordExtend> getPageBeans(QueryVo queryVo) throws Exception;
   
   /*���������Ƿ�ͨ��*/
   public void handleRecord(PosterRecordExtend posterRecordExtend) throws Exception;

   /*��ѯδ����Ϣ������*/
	public Integer getMsgNumber(UserExtend userExtend) throws Exception;
	
	/*��ѯ���е�δ����Ϣ*/
	public PageBean<MessagesExtend> getMessages(QueryVo vo) throws Exception;
	
	/*������ϢΪ�Ѷ�*/
	public void updateMsgStatus(MessagesExtend messagesExtend) throws Exception;
}
