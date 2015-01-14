package org.luis.sainteclaires.base.bean.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.util.DateUtil;
import org.luis.basic.util.IbatisBuilder;
import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.bean.PicShow;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class PicShowService {

	@SuppressWarnings("unchecked")
	public List<PicShow> findAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quarter", Integer.valueOf(BaseUtil.getCurrQuarter().getValue()));
		map.put("year", DateUtil.getYear());
		List<PicShow> list = null;
		try {
			list = ((List<PicShow>) IbatisBuilder.queryForList(
					"picShow.findByYearQ", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PicShow> findByCate(Long cateId){
		List<PicShow> list = null;
		
		
		return list;
	}
	
	/**
	 * +赞
	 * @param id
	 */
	public void love(Long id){
		try {
			IbatisBuilder.doUpdate("picShow.picLove", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * +浏览量
	 * @param id
	 */
	public void view(Long id){
		try {
			IbatisBuilder.doUpdate("picShow.picView", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PicShow findDetail(Long id){
		return ServiceFactory.getPicShowSvr().get(id);
	}
	
	public void save(PicShow ps){
		List<PicShow> list = parsePic(ps);
		for (PicShow picShow : list) {
			ServiceFactory.getPicShowSvr().save(picShow);
		}
	}
	
	private List<PicShow> parsePic(PicShow ps) {
		List<PicShow> list = new ArrayList<PicShow>();
		if (!StringUtils.isNullOrBlank(ps.getPath())) {
			String[] picArray = ps.getPath().split(",");
			for (String path : picArray) {
				PicShow pic = new PicShow();
				pic.setCateId(ps.getCateId());
				pic.setQuarter(ps.getQuarter());
				pic.setYear(ps.getYear());
				pic.setViews(0);
				pic.setLoves(0);
				pic.setPath(BaseUtil.SHOW_PATH + pic.getYear() + "/" + pic.getQuarter() + "/" + path);
				FilterAttributes fa = FilterAttributes.blank()
						.add("year", pic.getYear())
						.add("quarter", pic.getQuarter())
						.add("path", pic.getPath());
				List<PicShow> has = ServiceFactory.getPicShowSvr().findByAttributes(fa);
				if(has.isEmpty()){
					list.add(pic);
				}
			}
		}
		return list;
	}
}
