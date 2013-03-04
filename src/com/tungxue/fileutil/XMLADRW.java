/**
 * 
 */
package com.tungxue.fileutil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * @author tungxue
 * 
 */
public class XMLADRW {
	private static Document addXMLChildByArray(Document doc, NodeList nodes,
			String[] layer) {// 将layer数组作为结点加入doc最底层的所有结点，使它们成为最底层结点
		if (nodes.getLength() > 0 && nodes.item(0).hasChildNodes()) {// 初建的文件每层都是对称的，选用Item(0)即可代表全部
			for (int i = 0; i < nodes.getLength(); i++) {
				addXMLChildByArray(doc, nodes.item(i).getChildNodes(), layer);// 递归地为找到最底层
			}
		} else {
			for (int i = 0; i < nodes.getLength(); i++) {// 到最底层了之后就把layer添加上去
				Node node = nodes.item(i);
				for (int j = 0; j < layer.length; j++) {
					Element field_node = doc.createElement(layer[j]);
					node.appendChild(field_node);
				}
			}
		}
		return doc;
	}

	public static void createXML(String path1, String rootname,
			String[]... layers) {// 生成的XML文件的层数为layers个数+1。
		String path="./log/"+path1;
		if (FileADRW.createFile(path)) {
			Document doc = null;
			try {
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = doc.createElement(rootname);
			doc.appendChild(root);
			for (String[] layer : layers) {// 逐层地将结点加入到已经带有根结点的xml文件
				doc = addXMLChildByArray(doc, doc.getChildNodes(), layer);
			}
			// 以下是对XML文件的序列化。

			FileOutputStream os = null;
			OutputFormat outformat = new OutputFormat(doc);
			try {
				os = new FileOutputStream(path);
				XMLSerializer xmlserilizer = new XMLSerializer(os, outformat);
				xmlserilizer.serialize(doc);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
