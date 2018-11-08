package com.aswans.xml;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class ParseXmlDom4j {

	/**
	 * @param args
	 * @author zhangsj add 2015-6-9
	 */
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		Document document = null;
		URL url = ParseXmlDom4j.class.getClassLoader().getResource("zsj.xml");
		try {
			 document = reader.read(new File(url.getPath()));
			/* 
			 Element root = document.getRootElement();
			 Element ele = root.addElement("item");
			 ele.addAttribute("id", "114");
			 ele.setText("zhao");
			 */
			// writer(document);
			 read(document);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	/**
	 * @function:从文件读取XML，输入文件名，返回XML文档
	 * @param fileName
	 * @return
	 * @throws MalformedURLException
	 * @throws DocumentException
	 */
    public static void read(Document document){
      // SAXReader reader = new SAXReader();
       try {
		    // Document document = reader.read(new File(fileName));
		     Element root = document.getRootElement();
		     System.out.println(root.attributeValue("name")+"\n");
			 List<?> nodes = root.elements("item");  
			 Iterator<?> iterator = nodes.iterator();
			 System.out.println("属性id的值\titem元素内容");
			 while(iterator.hasNext()){
				 Element elm = (Element) iterator.next();
				 Attribute attribute=elm.attribute("id");  
				 String attrValue = attribute.getText();
				 String text =  elm.getText();
				 System.out.println(attrValue +"\t" + text+"\r\n");
			 }
		} catch (Exception e) {
			System.out.println("从文件读取XML出错！");
		}
    }
	 /** 
     * 把document对象写入新的文件 
     *  
     * @param document 
     * @throws Exception 
     */  
    public static void writer(Document document) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(format);  
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }  
}
