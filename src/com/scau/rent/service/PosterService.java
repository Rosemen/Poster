package com.scau.rent.service;


import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.MessagesExtend;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;

/**
 * 关于海报申请的业务类
 * @author Administrator
 *
 */
public interface PosterService {
   /*申请粘贴海报*/
   public PageBean<PosterRecordExtend> applyPoster(QueryVo queryVo) throws Exception;
   
   /*查询同一楼栋海报数量*/
   public Integer getPosterNumber(PosterExtend posterExtend) throws Exception;
   
   /*分页查询用户的所有申请记录*/
   public PageBean<PosterRecordExtend> getPageBean(QueryVo queryVo) throws Exception;
   
   /*分页查询所有用户的所有申请记录*/
   public PageBean<PosterRecordExtend> getPageBeans(QueryVo queryVo) throws Exception;
   
   /*处理申请是否通过*/
   public void handleRecord(PosterRecordExtend posterRecordExtend) throws Exception;

   /*查询未读消息的总数*/
	public Integer getMsgNumber(UserExtend userExtend) throws Exception;
	
	/*查询所有的未读消息*/
	public PageBean<MessagesExtend> getMessages(QueryVo vo) throws Exception;
	
	/*设置消息为已读*/
	public void updateMsgStatus(MessagesExtend messagesExtend) throws Exception;
}
