package com.joiest.jpf.common.util;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

/**
 * 用DOM4J方法读取xml文件
 */
public class ReadXML {

    private List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();

    private Map<String,String> resultMap = new HashMap<>();

    private SAXReader reader = new SAXReader();

    /**
     * 获取xml内容 单条内容 by file
     * @param file
     * @return
     */
    public Map<String,String> getBooksOneByFile(File file)
    {
        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            while(storeit.hasNext()){
                Element bookElement = (Element) storeit.next();
                String name = bookElement.getName();
                String value = bookElement.getStringValue();

                resultMap.put(name, value);
            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }

        return resultMap;
    }


    /**
     * 获取xml内容 包含多条内容 by file
     * @param file
     * @return
     */
    public List<Map<String,String>> getBooksListByFile(File file){

        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            while(storeit.hasNext()){
                Map<String,String> resultMap = new HashMap<>();

                Element bookElement = (Element) storeit.next();

                //遍历bookElement的属性
                List<Attribute> attributes = bookElement.attributes();
                for(Attribute attribute : attributes){
                        String name = attribute.getName();
                        String value = attribute.getValue();
                        resultMap.put(name, value);
                }

                Iterator bookit = bookElement.elementIterator();
                while(bookit.hasNext()){
                    Element child = (Element) bookit.next();
                    String nodeName = child.getName();
                    String nodeValue = child.getStringValue();
                    resultMap.put(nodeName, nodeValue);
                }
                resultList.add(resultMap);

            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }

        return resultList;
    }

    /**
     * 获取xml内容 单条内容 by xmlStr
     * @param xmlStr
     * @return
     */
    public Map<String,String> getBooksOneByStr(String xmlStr)
    {
        try {
            Document document = DocumentHelper.parseText(xmlStr);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            while(storeit.hasNext()){
                Element bookElement = (Element) storeit.next();
                String name = bookElement.getName();
                String value = bookElement.getStringValue();

                resultMap.put(name, value);
            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 获取xml内容 包含多条内容 by xmlStr
     * @param xmlStr
     * @return
     */
    public List<Map<String,String>> getBooksListByStr(String xmlStr){

        try {
            Document document = DocumentHelper.parseText(xmlStr);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            while(storeit.hasNext()){

                Map<String,String> resultMap = new HashMap<>();

                Element bookElement = (Element) storeit.next();

                //遍历bookElement的属性
                List<Attribute> attributes = bookElement.attributes();
                for(Attribute attribute : attributes){
                    String name = attribute.getName();
                    String value = attribute.getValue();
                    resultMap.put(name, value);
                }

                Iterator bookit = bookElement.elementIterator();
                while(bookit.hasNext()){
                    Element child = (Element) bookit.next();
                    String nodeName = child.getName();
                    String nodeValue = child.getStringValue();
                    resultMap.put(nodeName, nodeValue);
                }
                resultList.add(resultMap);

            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
      /*
      File file = new File("e:\\efg.xml");
        StringBuilder sbf = new StringBuilder();
        sbf.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<orderinfo> \n" +
                "  <err_msg/>  \n" +
                "  <retcode>1</retcode>  \n" +
                "  <orderid>S1808122614605</orderid>  \n" +
                "  <cardid>141203</cardid>  \n" +
                "  <cardnum>1</cardnum>  \n" +
                "  <ordercash>49.875</ordercash>  \n" +
                "  <cardname>北京移动手机快充50元</cardname>  \n" +
                "  <sporder_id>5044041592071553</sporder_id>  \n" +
                "  <game_userid>15810063151</game_userid>  \n" +
                "  <game_state>0</game_state> \n" +
                "</orderinfo>\n");

        Map<String,String> map = new ReadXML().getBooksOneByStr(sbf.toString());

        System.out.println(map);
        System.out.println(map.toString());
        */

        File file1 = new File("e:\\abc.xml");
        StringBuilder sbf1 = new StringBuilder();
        sbf1.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>\n" +
                "<cardinfo>\n" +
                "    <err_msg></err_msg>\n" +
                "    <retcode>1</retcode>\n" +
                "    <ret_cardinfos>\n" +
                "        <card>\n" +
                "            <cardid>64157004</cardid>\n" +
                "            <pervalue>100</pervalue>\n" +
                "            <inprice>100.3</inprice>\n" +
                "            <sysddprice>100</sysddprice>\n" +
                "            <sysdd1price>100</sysdd1price>\n" +
                "            <sysdd2price>100</sysdd2price>\n" +
                "            <agentprice>100</agentprice>\n" +
                "            <agentprice1>100</agentprice1>\n" +
                "            <agentprice2>100</agentprice2>\n" +
                "            <memberprice>100</memberprice>\n" +
                "            <innum>30</innum>\n" +
                "            <cardname>全国中石化加油卡直充100元</cardname>\n" +
                "            <othername>全国中石化加油卡直充100元</othername>\n" +
                "            <howeasy>A</howeasy>\n" +
                "            <amounts>1-10</amounts>\n" +
                "            <subclassid>64888</subclassid>\n" +
                "            <classtype>2</classtype>\n" +
                "            <fullcostsite></fullcostsite>\n" +
                "            <caption></caption>\n" +
                "            <lastreftime>2015-12-16 13:14:09</lastreftime>\n" +
                "            <accountdesc></accountdesc>\n" +
                "        </card>\n" +
                "    </ret_cardinfos>\n" +
                "</cardinfo> ");

        List<Map<String,String>> list1 = new ReadXML().getBooksListByStr(sbf1.toString());

        System.out.println(list1);
        System.out.println(list1.toString());
    }

}
