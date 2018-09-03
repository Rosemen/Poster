package com.scau.rent.mapper;

import java.util.List;

import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;

public interface PosterMapper {
	/*向数据库插入一张海报信息*/
    public void insertPoster(QueryVo queryVo) throws Exception;
    
    /*向数据库插入海报申请记录信息*/
    public void insertRecord(QueryVo queryVo) throws Exception;
    
    /*查询申请同一楼栋海报数量*/
    public Integer getRowCount(PosterExtend posterExtend) throws Exception;
    
    /*查询某用户的所有申请记录*/
    public List<PosterRecordExtend> selectAllRecords(QueryVo queryVo) throws Exception;
    
    /*查询用户的总记录数*/
    public Integer getRecordNumber(QueryVo queryVo) throws Exception;
}
