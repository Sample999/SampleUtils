package com.sample.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.sample.utils.XmlUtils;
import com.sample.web.dto.AjaxResult;
import com.sample.web.dto.XmlProtocolDto;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class XmlController {

    @PostMapping("/toJson")
    public AjaxResult toJson(@RequestBody XmlProtocolDto xmlProtocolDto) throws DocumentException {
        JSONObject json = XmlUtils.xml2Json(xmlProtocolDto);
        return AjaxResult.success(json);
    }
}
