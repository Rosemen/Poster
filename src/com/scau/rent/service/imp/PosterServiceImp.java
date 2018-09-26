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
 * 关于海报申请的业务接口实现类
 * @author Administrator
 *
 */
@Transactional
public class PosterServiceImp implements PosterService {
	@Autowired
	private PosterMapper posterMapper;

	/*申请粘贴海报*/
	public PageBean<PosterRecordExtend> applyPoster(QueryVo queryVo) throws Exception {
		//保存申请的海报信息
		posterMapper.insertPoster(queryVo);
		//添加一条申请海报的记录,并设置为未处理
		PosterRecordExtend posterRecordExtend = new PosterRecordExtend();
		posterRecordExtend.setRecord_user(queryVo.getUserExtend());
		posterRecordExtend.setRecord_status(0);
		posterRecordExtend.setRecord_poster(queryVo.getPosterExtend());
        posterRecordExtend.setRecord_createtime(new Date());
		queryVo.setPosterRecordExtend(posterRecordExtend);
		posterMapper.insertRecord(queryVo);
		//返回该用户的所有申请的分页对象
		PageBean<PosterRecordExtend> pageBean = this.getPageBean(queryVo);
		//通知管理员有新申请
		sendMessage("381651b5-ab5d-11e8-adde-28c2dd9bf376", 0);
		return pageBean;
	}

	/*得到同一楼栋海报数量*/
	public Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterMapper.getRowCount(posterExtend);
	}


	/*分页查询某个用户的所有记录*/
	public PageBean<PosterRecordExtend> getPageBean(QueryVo queryVo) throws Exception {
		//得到总记录数
		Integer total_record = posterMapper.getRecordNumber(queryVo);
		//创建分页对象
		PageBean<PosterRecordExtend> pageBean =
				new PageBean<PosterRecordExtend>(queryVo.getCurrent_page(),total_record);
		//开始索引
		Integer start = (pageBean.getCurrent_page() - 1)*pageBean.getPage_size();
		Integer end = pageBean.getPage_size();
		queryVo.setStart(start);
		queryVo.setEnd(end);
		//查询数据库，得到记录列表
        List<PosterRecordExtend> records = posterMapper.selectAllRecords(queryVo);
		pageBean.setRecords(records);
		return pageBean;
	}
	
	/*分页查询所有用户的所有记录*/
	public PageBean<PosterRecordExtend> getPageBeans(QueryVo queryVo) throws Exception {
		//得到总记录数
		Integer total_record = posterMapper.getTotalNumber(queryVo);
		//创建分页对象
		PageBean<PosterRecordExtend> pageBean =
				new PageBean<PosterRecordExtend>(queryVo.getCurrent_page(),total_record);
		//开始索引
		Integer start = (pageBean.getCurrent_page() - 1)*pageBean.getPage_size();
		Integer end = pageBean.getPage_size();
		queryVo.setStart(start);
		queryVo.setEnd(end);
		//查询数据库，得到记录列表
        List<PosterRecordExtend> records = posterMapper.selectAll(queryVo);
		pageBean.setRecords(records);
		return pageBean;
	}

	/*处理申请是否通过*/
	public void handleRecord(PosterRecordExtend posterRecordExtend) throws Exception {
	    //设置申请为已处理
		posterRecordExtend.setRecord_status(1);
		//给用户发一条通知
		sendMessage(posterRecordExtend.getRecord_user().getUser_id(), 1);
		posterMapper.updateRecordStatus(posterRecordExtend);
	}
	
	/*查询未读消息的总数*/
	public Integer getMsgNumber(UserExtend userExtend) throws Exception {
		Integer number = posterMapper.selectMsgNumber(userExtend);
		return number;
	}

	/*查询所有的未读消息 */
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

    /*设置消息为已读*/
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
			message.setMsg_content("管理员已处理你的申请，请前往我的申请查看!");	
		}else message.setMsg_content("有新的申请，请前往我的申请查看!");
		posterMapper.insertMessage(message);
	}

}
