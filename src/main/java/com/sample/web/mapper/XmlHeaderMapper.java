package com.sample.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.web.pojo.XmlHeader;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XmlHeaderMapper extends BaseMapper<XmlHeader> {

    XmlHeader selectByCode(String code);



}
