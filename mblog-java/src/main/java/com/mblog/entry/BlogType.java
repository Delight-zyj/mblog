package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("dic_blogtype")
@Accessors(chain = true)
public class BlogType {

    /**
     * 类型编号
     */
    private int typeCode;

    /**
     * 类型名称，用于描述类型的名称
     */
    private String typeName;

    /**
     * 状态，表示类型是否处于启用状态：0-启用，1-禁用
     */
    private boolean status;

    @Override
    public String toString() {
        return "BlogType{" +
                "typeCode=" + typeCode +
                ", typeName='" + typeName + '\'' +
                ", status=" + status +
                '}';
    }
}
