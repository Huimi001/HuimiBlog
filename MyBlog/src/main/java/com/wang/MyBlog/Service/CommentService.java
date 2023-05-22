package com.wang.MyBlog.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.MyBlog.entity.Comment;
import com.wang.MyBlog.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	public List<Comment> getCommentById(int newsId) {
		return commentRepository.findBynewsId(newsId);
	}
	
	public Comment addComment(Comment comment){
		return commentRepository.save(comment);
	}
	
	
	private List<Comment> tempReplys = new ArrayList<>();
	public List<Comment> listComment(int newsId)
	{
        //查询出父节点
        List<Comment> comments = commentRepository.findBynewsId(newsId);
        for(Comment comment : comments){
            int id = comment.getCommentID();
            String parentNickname1 = comment.getCommentName();
            List<Comment> childComments = commentRepository.findByParentIdNotNull(id);
            //查询出子评论
            combineChildren(childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }
	
	private void combineChildren(List<Comment> childComments, String parentNickname1)
	{
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                childComment.setParentName(parentNickname1);
                tempReplys.add(childComment);
            }
        }
    }
	
}
