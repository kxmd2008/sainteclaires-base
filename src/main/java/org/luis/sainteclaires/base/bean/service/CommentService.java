package org.luis.sainteclaires.base.bean.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.luis.basic.util.IbatisBuilder;
import org.luis.sainteclaires.base.bean.Comment;
import org.luis.sainteclaires.base.bean.Commentator;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@SuppressWarnings("unchecked")
	public List<Comment> findByPic(Long picId){
		List<Comment> comments = new ArrayList<Comment>();
		Map<Long, Comment> map = new LinkedHashMap<Long, Comment>();
		try {
			List<Comment> list = (List<Comment>) IbatisBuilder.queryForList(
					"comment.findByPicId", picId);
			for (Comment comment : list) {
				//一个评论块的开始
				if(comment.getParentId() == null){
					//一个评论块的第一条评论
					map.put(comment.getId(), comment);
				} else {
					map.get(comment.getParentId()).getComments().add(comment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<Entry<Long, Comment>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<Long, Comment> e = it.next();
			comments.add(e.getValue());
		}
		return comments;
	}
	
	public boolean comment(Comment comment){
		Commentator commentator = new Commentator();
		commentator.setEmail(comment.getEmail());
		commentator.setName(comment.getName());
		ServiceFactory.getCommentatorSvr().save(commentator);
		comment.setCommentatorId(commentator.getId());
		comment.setDate(new Date());
		ServiceFactory.getCommGenericService().save(comment);
		
		
		return true;
	}
}
