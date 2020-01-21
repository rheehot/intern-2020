package com.board.project.blockboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("PostDTO")
@Data
@NoArgsConstructor
public class PostDTO {
    private int postID;
    private String userID;
    private String userName;
    private int boardID;
    private String boardName;
    private int companyID;
    private String postTitle;
    private String postContent;
    private String postRegisterTime;
}
