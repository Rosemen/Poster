package com.scau.rent.service;


import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;

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
}
