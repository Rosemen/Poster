package com.scau.rent.service;


import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;

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
}
