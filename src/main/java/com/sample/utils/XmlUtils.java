package com.sample.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sample.web.dto.XmlProtocolDto;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.util.Iterator;
import java.util.List;

public class XmlUtils {

    /**
     * xml根据规则转换成对应的 JSON
     * {
     * "Protocol:": {
     * "@Main": {
     * "Header": "/a",
     * "Json": {
     * "b": "/b",
     * "c": "/c",
     * "condition": {
     * "@Key": "/key",
     * "@Vaule": "/val"
     * },
     * "details": "@details"
     * }
     * },
     * "@details": {
     * "Header": "/a/list",
     * "Json": {
     * "d": "/d",
     * "condition2": {
     * "@Key": "/key",
     * "@Vaule": "/val"
     * }
     * }
     * }
     * },
     * "xmlText": "<a> <b>b</b> <c>c</c> <key>a</key> <val>1</val> <list> <d>d</d> <key>b</key> <val>2</val> </list> <list> <d>d</d> </list> </a>"
     * }
     *
     * @param xmlProtocolDto
     * @return JSONObject
     */
    public static JSONObject xml2Json(XmlProtocolDto xmlProtocolDto) throws DocumentException {
        String xmlText = xmlProtocolDto.getXmlText();
        JSONObject protocol = xmlProtocolDto.getProtocol();
        Document document = null;
        document = DocumentHelper.parseText(xmlText);
        JSONArray resJson = func(protocol, "@Main", document);
        return resJson.getJSONObject(0);
    }

    public static JSONArray func(JSONObject protocol, String code, Document document) throws DocumentException {
        JSONObject main = protocol.getJSONObject(code);
        String headerPath = main.getString("@Header");
        JSONObject json = main.getJSONObject("@Json");
        List<Node> nodes = document.selectNodes(headerPath);
        JSONArray array = new JSONArray();
        for (Node node : nodes) {
            JSONObject resJson = new JSONObject();
            array.add(resJson);
            travelLiJSONObject(protocol, json, resJson, DocumentHelper.parseText(node.asXML()), getLastPath(headerPath));
        }

        return array;
    }

    public static String getLastPath(String allPath) {
        String[] paths = allPath.split("/");
        return "/" + paths[paths.length - 1];
    }

    public static boolean isPath(String value) {
        if (value.startsWith("/")) {
            return true;
        }
        return false;
    }

    public static boolean isProtocol(String value) {
        if (value.startsWith("@")) {
            return true;
        }
        return false;
    }

    public static String findSingleNodeValue(Document document, String path) {
        String value = null;
        Node node = document.selectSingleNode(path);
        if (node != null) {
            value = node.getText();
        }
        return value;
    }

    public static void travelLiJSONObject(JSONObject protocol, JSONObject originalJSONObject, JSONObject resJSONObject, Document document, String headerPath) throws DocumentException {
        Iterator keyItr = originalJSONObject.keySet().iterator();
        while (keyItr.hasNext()) {    // 最外层的key
            String key = (String) keyItr.next();
            Object value = originalJSONObject.get(key); //路径或协议规则
            if (isPath(key)) {
                key = headerPath + key;
                String newKey = findSingleNodeValue(document, key);
                keyItr.remove();
                key = newKey;
            }
            // 空字符串也是字符串
            if (value instanceof String) {
                if (isProtocol((String) value)) {
                    JSONArray resJson = func(protocol, (String) value, document);
                    resJSONObject.put(key, resJson);
                } else {
                    String path = headerPath + (String) value;
                    String val = findSingleNodeValue(document, path);
                    resJSONObject.put(key, val);
                    System.out.println(key + " : " + val);
                }
                continue;
            }
            if (value instanceof JSONObject) {
                JSONObject object = (JSONObject) value;
                System.out.println("=======》" + key + "(json对象)");
                resJSONObject.put(key, object);
                travelLiJSONObject(protocol, object, (JSONObject) resJSONObject.get(key), document, headerPath);
                continue;
            }
            if (value instanceof JSONArray) {
                System.out.println("=======》" + key + "(json数组)");
                if (value == null) {    //不会存在
                    System.out.println("***************************************8");
                }
                JSONArray jsonArray = (JSONArray) value;
                if (jsonArray.isEmpty()) {
                    System.out.println(key + " : " + "[]");
                    continue;
                } else {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject o1 = jsonArray.getJSONObject(i);
                        resJSONObject.put(key, o1);
                        travelLiJSONObject(protocol, o1, (JSONObject) resJSONObject.get(key), document, headerPath);
                    }
                }
            }

        }
    }


}