package com.sample.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "xml_header")
public class XmlHeader {


    @TableId(type = IdType.AUTO)//mybatis-plus主键注解
    @IsAutoIncrement   //自增
    @IsKey
    @Column(comment = "ID")
    private Long id;

    @Column(comment = "协议Code")
    private String code;


    @Column(comment = "协议描述")
    private String desc;


    @Column(comment = "json头节点")
    private String jsonHeader;


    @Column(comment = "XML根节点路径")
    private String xmlHeader;
}
