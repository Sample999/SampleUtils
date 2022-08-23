package com.sample.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.sample.utils.XmlUtils;
import com.sample.web.dto.AjaxResult;
import com.sample.web.dto.HaveDateDto;
import com.sample.web.dto.XmlProtocolDto;
import io.swagger.annotations.Api;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

@Api(tags = "XML转换")
@RestController
@RequestMapping("/xml")
public class XmlController {

    @CrossOrigin //跨域
    @PostMapping("/toJson")
    public AjaxResult toJson(@RequestBody XmlProtocolDto xmlProtocolDto) throws DocumentException {
        JSONObject json = XmlUtils.xml2Json(xmlProtocolDto);
        return AjaxResult.success(json);
    }

    @PostMapping("/data")
    public AjaxResult updateData(@RequestBody HaveDateDto dateDto){
        System.out.println(dateDto.toString());
        return AjaxResult.success();
    }

    @GetMapping("/data")
    public AjaxResult getData(HaveDateDto dateDto){
        System.out.println(dateDto.toString());
        return AjaxResult.success();
    }

    @GetMapping("/data2")
    public AjaxResult getTest(@RequestBody JSONObject dateDto){
        System.out.println(dateDto.toString());
        return AjaxResult.success();
    }
}
