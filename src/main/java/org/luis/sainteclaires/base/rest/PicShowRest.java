package org.luis.sainteclaires.base.rest;

import java.util.List;

import org.luis.basic.rest.model.SimpleMessage;
import org.luis.basic.util.SpringContextFactory;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.Comment;
import org.luis.sainteclaires.base.bean.PicShow;
import org.luis.sainteclaires.base.bean.service.CommentService;
import org.luis.sainteclaires.base.bean.service.PicShowService;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 图片展示
 * @author Guoliang.Li
 */
@Controller
@RequestMapping
public class PicShowRest {
	
	@RequestMapping(value = "show/pic/{id}", method = RequestMethod.GET)
	public String toDetail(ModelMap map, @PathVariable Long id){
		picShowService.view(id);
		PicShow ps = picShowService.findDetail(id);
		List<Comment> list = commentService.findByPic(id);
		map.put("comments", list);
		map.put("pic", ps);
		map.put("picShowId", id);
		return "show/pic";
	}
	
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String toShow(ModelMap map){
		List<PicShow> list = picShowService.findAll();
		map.put("showpics", list);
		setCate(map);
		return "show/showPics";
	}
	
	@RequestMapping(value = "show/{cateId}", method = RequestMethod.GET)
	public String toShowCate(ModelMap map, @PathVariable Long cateId){
		List<PicShow> list = picShowService.findByCate(cateId);
		map.put("showpics", list);
		setCate(map);
		return "show/showPics";
	}
	
	/**
	 * 点赞
	 * @param map
	 * @param cateId
	 * @return
	 */
	@RequestMapping(value = "show/love", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage<Object> love(ModelMap map, Long id){
		SimpleMessage<Object> sm = new SimpleMessage<Object>();
		picShowService.love(id);
		return sm;
	}
	
	/**
	 * 评论
	 * @param map
	 * @param cateId
	 * @return
	 */
	@RequestMapping(value = "show/comment", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage<Object> comment(ModelMap map, Comment comment){
		SimpleMessage<Object> sm = new SimpleMessage<Object>();
		boolean b = commentService.comment(comment);
		if(!b){
			sm.getHead().setRep_code("100");
		}
		return sm;
	}
	
	private void setCate(ModelMap map){
//		List<Category> parents = BaseUtil.getParentCates();
		List<Category> parents = BaseUtil.getShowCates();
		map.put("parents", parents);
	}
	
	private PicShowService picShowService = SpringContextFactory
			.getSpringBean(PicShowService.class);
	private CommentService commentService = SpringContextFactory
			.getSpringBean(CommentService.class);
}
