package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName("t_comment")
@Accessors(chain = true)
public class Comment {

    /**
     * 评论的唯一标识符
     * 用于唯一标识一条评论
     */
    @TableField(value = "u_user_Id")
    private int userId;

    /**
     * 上级用户ID
     * 用于标识回复评论的上级用户，如果该评论不是回复，则此字段为0
     */
    @TableField(value = "u_superior_user_Id")
    private int superiorUserId;

    /**
     * 博客ID
     * 标识评论所属的博客，每个评论都关联到特定的博客
     */
    @TableField(value = "u_blog_Id")
    private int blogId;

    /**
     * 评论内容
     * 存储评论的具体文本信息
     */
    @TableField(value = "u_content")
    private String content;

    /**
     * 创建时间
     * 记录评论创建的时间，使用LocalDateTime类型以保存日期和时间信息
     */
    @TableField(value = "d_create_time")
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", superiorUserId=" + superiorUserId +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
