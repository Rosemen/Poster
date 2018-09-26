package com.scau.rent.mapper;

import java.util.List;

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.MessagesExtend;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
import com.scau.rent.entity.extend.UserExtend;

public interface PosterMapper {
	/*�����ݿ����һ�ź�����Ϣ*/
    public void insertPoster(QueryVo queryVo) throws Exception;
    
    /*�����ݿ���뺣�������¼��Ϣ*/
    public void insertRecord(QueryVo queryVo) throws Exception;
    
    /*��ѯ����ͬһ¥����������*/
    public Integer getRowCount(PosterExtend posterExtend) throws Exception;
    
    /*��ѯĳ�û������������¼*/
    public List<PosterRecordExtend> selectAllRecords(QueryVo queryVo) throws Exception;
    
    /*��ѯĳ�û������������¼*/
    public List<PosterRecordExtend> selectAll(QueryVo queryVo) throws Exception;
    
    /*��ѯ�û����ܼ�¼��*/
    public Integer getRecordNumber(QueryVo queryVo) throws Exception;
    
    /*��ѯ�û����ܼ�¼��*/
    public Integer getTotalNumber(QueryVo queryVo) throws Exception;
    
    /*���������¼״̬*/
    public void updateRecordStatus(PosterRecordExtend posterRecordExtend) throws Exception;
    
    /*��ȡ���е�δ����Ϣ*/
   	public List<MessagesExtend> selectMsg(QueryVo vo) throws Exception;
   	
   	/*��ȡδ����Ϣ������*/
   	public Integer selectMsgNumber(UserExtend userExtend) throws Exception;	
   	
   	/*������Ϣ״̬*/
   	public void updateMsgStatus(MessagesExtend messagesExtend) throws Exception;
    
   	/*����һ������Ϣ*/
	public void insertMessage(MessagesExtend message);
}
