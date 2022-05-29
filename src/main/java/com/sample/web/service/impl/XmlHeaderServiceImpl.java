package com.sample.web.service.impl;

import com.sample.web.mapper.XmlHeaderMapper;
import com.sample.web.pojo.XmlHeader;
import com.sample.web.service.XmlHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlHeaderServiceImpl implements XmlHeaderService {

    @Autowired
    private XmlHeaderMapper xmlHeaderMapper;

    @Override
    public XmlHeader findXmlHeader(String code) {
        return xmlHeaderMapper.selectByCode(code);
    }
}
