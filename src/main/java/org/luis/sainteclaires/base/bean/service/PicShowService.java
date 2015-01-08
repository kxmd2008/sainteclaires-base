package org.luis.sainteclaires.base.bean.service;

import java.util.ArrayList;
import java.util.List;

import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.bean.PicShow;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class PicShowService {

	public List<PicShow> findAll(){
		List<PicShow> list = null;
		
		
		return list;
	}
	
	public List<PicShow> findByCate(Long cateId){
		List<PicShow> list = null;
		
		
		return list;
	}
	
	
	
	public PicShow findDetail(Long id){
		
		
		
		return null;
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
				pic.setPath(BaseUtil.getProductPath2() + path);
				list.add(pic);
			}
		}
		return list;
	}
}
