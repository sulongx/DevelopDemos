package com.sulongx.patterns.factorypattern.example;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * 描述:
 * 配置文件加载对象
 *
 * @author xiongsulong
 * @create 2020-10-27 21:51
 */
public class ReadXML2 {

    public static Object  getObject(){
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("DesignPatterns/src/main/resources/config2.xml"));
            //获取包含类名的文本节点
            NodeList nodeList = doc.getElementsByTagName("class");
            Node node = nodeList.item(0).getFirstChild();
            String  cName = node.getNodeValue();
            //反射生成对象
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
