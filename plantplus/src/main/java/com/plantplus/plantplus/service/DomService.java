package com.plantplus.plantplus.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DomService {
    private List<Map<String, String>> domNodeList = new ArrayList<>();
    private Map<String, String> domNode = new HashMap<String, String>();

    public void readDom(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {

            // XML 파싱
            DocumentBuilder builder = factory.newDocumentBuilder();
            // xml 객체로 전달 받기
            InputSource is = new InputSource(new StringReader(xml));
            is.setEncoding("UTF-8");

            Document doc = builder.parse(is);

            //
            Element element = doc.getDocumentElement();
            System.out.println("Node: " + element.getNodeName());
            System.out.println("code: " + element.getAttribute("code"));
            //NodeList nodeList = element.getChildNodes();

            // 2차 시도
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("item");
            travNode(nodeList);

            // 마지막 노드
            domNodeList.add(domNode);
            domNode = new HashMap<String, String>();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //노드 순회 메서드
    public void travNode (NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childNodes = n.getChildNodes();
                int cnt = childNodes.getLength();


                if (cnt == 1) {
                    System.out.printf("%s : %s%n", n.getNodeName(), n.getTextContent());
                    domNode.put(n.getNodeName(), n.getTextContent());
                } else if (cnt > 1 && n.getNodeName() == "item"){
                    if (domNode.size() > 0){
                        domNodeList.add(domNode);
                        System.out.printf("domNodeList add " + domNode + "%n");
                    }
                    System.out.printf("--------[ " + n.getNodeName() + " ]--------%n");
                    domNode = new HashMap<>();
                }

                if (cnt > 1) {
                    travNode(childNodes);
                }
            }
        }
    }

    public List<Map<String, String>> getDomNodeList() {
        return domNodeList;
    }

    public Map<String, String> getDomNode() {
        return domNode;
    }

    public void setDomNode(Map<String, String> domNode) {
        this.domNode = domNode;
    }

    public void setDomNodeList(List<Map<String, String>> domNodeList) {
        this.domNodeList = domNodeList;
    }
}
