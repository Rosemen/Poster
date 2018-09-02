package com.scau.rent.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.scau.rent.entity.PageBean;
import com.scau.rent.entity.QueryVo;
import com.scau.rent.entity.extend.PosterExtend;
import com.scau.rent.entity.extend.PosterRecordExtend;
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
		return pageBean;
	}

	/*�õ�ͬһ¥����������*/
	public Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterMapper.getRowCount(posterExtend);
	}


	/*��ҳ��ѯ*/
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

}
