package com.board.project.blockboard.mapper;

import com.board.project.blockboard.dto.BoardDTO;
import com.board.project.blockboard.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface PostMapper {
    int insertPost(PostDTO post);
    void deletePostByPostID(int postID);
    PostDTO selectPostByPostID(int postID);
    void updatePost(PostDTO post);
}