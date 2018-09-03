package com.scau.rent.mapper;

import java.util.List;

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;

public interface PosterMapper {
	/*�����ݿ����һ�ź�����Ϣ*/
    public void insertPoster(QueryVo queryVo) throws Exception;
    
    /*�����ݿ���뺣�������¼��Ϣ*/
    public void insertRecord(QueryVo queryVo) throws Exception;
    
    /*��ѯ����ͬһ¥����������*/
    public Integer getRowCount(PosterExtend posterExtend) throws Exception;
    
    /*��ѯĳ�û������������¼*/
    public List<PosterRecordExtend> selectAllRecords(QueryVo queryVo) throws Exception;
    
    /*��ѯ�û����ܼ�¼��*/
    public Integer getRecordNumber(QueryVo queryVo) throws Exception;
}
