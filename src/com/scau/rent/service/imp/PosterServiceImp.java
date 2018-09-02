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
		return pageBean;
	}

	/*得到同一楼栋海报数量*/
	public Integer getPosterNumber(PosterExtend posterExtend) throws Exception {
		return posterMapper.getRowCount(posterExtend);
	}


	/*分页查询*/
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

}
