package com.sample.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "xml_body")//设置表名
public class XmlBody extends BaseModel {

    @TableId(type = IdType.AUTO)//mybatis-plus主键注解
    @IsAutoIncrement   //自增
    @IsKey
    @Column(comment = "ID")
    private Long id;


    @Column(comment = "节点名称")
    private String nodeName;

    @Column(comment = "节点类型")
    private String nodeType;

    @Column(comment = "默认值")
    private String defaultValue;

    @Column(comment = "父节点名称")
    private String parentNode;


}
