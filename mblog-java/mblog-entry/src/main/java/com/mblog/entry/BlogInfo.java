package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName("t_blog_info")
@Accessors(chain = true)
public class BlogInfo {
    /**
     * 博客文章类
     * 该类用于表示博客系统中的文章信息，包括文章ID、标题、图片、内容、作者ID、类型代码、文章状态以及创建和更新时间
     */
    @TableId(value = "u_blog_Id", type = IdType.AUTO)
    private int blogId; // 博客文章的唯一标识符
    @TableField(value = "u_blog_title")
    private String blogTitle; // 博客文章的标题
    @TableField(value = "u_image")
    private String image; // 博客文章的特色图片URL
    @TableField(value = "u_blog_content")
    private String blogContent; // 博客文章的内容
    @TableField(value = "u_user_Id")
    private int userId; // 博客文章作者的用户ID
    @TableField(value = "u_type_code")
    private int typeCode; // 博客文章的类型代码，用于分类文章
    @TableField(value = "b_blog_status")
    private int blogStatus; // 博客文章的状态代码，表示文章的发布状态
    @TableField(value = "d_create_time")
    private LocalDateTime createTime; // 博客文章的创建时间
    @TableField(value = "d_update_time")
    private LocalDateTime updateTime; // 博客文章的最后更新时间

    @Override
    public String toString() {
        return "BlogInfo{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", image='" + image + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", userId=" + userId +
                ", typeCode=" + typeCode +
                ", blogStatus=" + blogStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
