package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_dic_blogtype")
@Accessors(chain = true)
public class BlogType {

    /**
     * 类型编号
     */
    @TableId(value = "u_type_code", type = IdType.AUTO)
    private int typeCode;

    /**
     * 类型名称，用于描述类型的名称
     */
    @TableField(value = "u_type_name")
    private String typeName;

    /**
     * 状态，表示类型是否处于启用状态：0-启用，1-禁用
     */
    @TableField(value = "b_status")
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
