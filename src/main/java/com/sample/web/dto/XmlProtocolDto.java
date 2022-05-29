package com.sample.web.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class XmlProtocolDto {

    private String xmlText; //xml文本

    private JSONObject protocol; //xml转换规则

}
