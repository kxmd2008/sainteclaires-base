package org.luis.sainteclaires.base.bean.service;

import java.util.List;

import org.luis.sainteclaires.base.bean.PicShow;
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
}
