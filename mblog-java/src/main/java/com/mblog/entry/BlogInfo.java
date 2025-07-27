package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName("blog")
@Accessors(chain = true)
public class BlogInfo {
    /**
     * 博客文章类
     * 该类用于表示博客系统中的文章信息，包括文章ID、标题、图片、内容、作者ID、类型代码、文章状态以及创建和更新时间
     */
    private int blogId; // 博客文章的唯一标识符

    private String blogTitle; // 博客文章的标题

    private String image; // 博客文章的特色图片URL

    private String blogContent; // 博客文章的内容

    private int userId; // 博客文章作者的用户ID

    private int typeCode; // 博客文章的类型代码，用于分类文章

    private int blogStatus; // 博客文章的状态代码，表示文章的发布状态

    private LocalDateTime createTime; // 博客文章的创建时间

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
