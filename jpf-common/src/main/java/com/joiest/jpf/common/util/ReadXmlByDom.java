package com.joiest.jpf.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

public class ReadXmlByDom {

    /**
     * 获取xml内容通过dom  支持三维
     * @param xmlStr
     * @return
     */
    public Map<String,String> getXmlByDom(String xmlStr) {

        Map<String,String> resultMap = new HashMap<>();
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlStr.getBytes("GBK"))));

            Element root = document.getDocumentElement();
            NodeList node = root.getChildNodes();
            //document.getElementsByTagName("card");
            //System.out.println("Name: "+ stu.getElementsByTagName("cardid").item(0).getFirstChild().getNodeValue());
            for(int i=0;i<node.getLength();i++){

                if(node.item(i) instanceof Element){

                    Node one = node.item(i);
                    NodeList oneNodeList = one.getChildNodes();
                    if(oneNodeList.getLength() == 1){

                        resultMap.put(one.getNodeName(),one.getTextContent());
                        continue;
                    }
                    for(int j=0;j<oneNodeList.getLength();j++){

                        if(oneNodeList.item(j) instanceof Element){

                            Node two = oneNodeList.item(j);
                            NodeList twoNodeList = two.getChildNodes();
                            if(twoNodeList.getLength() == 1){

                                resultMap.put(two.getNodeName(),two.getTextContent());
                                continue;
                            }
                            for(int h=0;h<twoNodeList.getLength();h++){

                                if(twoNodeList.item(j) instanceof Element){

                                    Node three = twoNodeList.item(h);
                                    NodeList threeNodeList = three.getChildNodes();
                                    if(threeNodeList.getLength() == 1){

                                        resultMap.put(three.getNodeName(),three.getTextContent());
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return resultMap;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {

        StringBuilder sbf1 = new StringBuilder();
        sbf1.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>\n" +
                "<cardinfo>\n" +
                "    <err_msg>success</err_msg>\n" +
                "    <retcode>1</retcode>\n" +
                "    <ret_cardinfos>\n" +
                "        <test>wode</test>\n" +
                "           <card>\n" +
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

        Map<String,String> map = new ReadXmlByDom().getXmlByDom(sbf1.toString());

        System.out.println(map);
        System.out.println(map.toString());
    }

}
