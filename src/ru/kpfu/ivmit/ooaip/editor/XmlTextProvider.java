package ru.kpfu.ivmit.ooaip.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlTextProvider extends AbstractTextProvider {

	public static final String XML_EXT = "xml";
	private String source;
	private String text;

	public XmlTextProvider(File inFile) {
		super(inFile);
		// TODO Auto-generated constructor stub
	}

	public void treeWalk(Document document) {
		treeWalk(document.getRootElement());
	}

	public void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
			} else {
				if (node instanceof Text) {
					String str = node.getStringValue();
					if (str != null) {
						node.setText(str.replaceAll(source, text));
						System.out.println(node.getText());
					}
				}

				//
				// }
			}
		}
	}

	@Override
	public void writeFile(String fileName, String source, String text) {
		SAXReader xmlReader = new SAXReader();
		this.source = source;
		this.text = text;
		Document doc = null;
		try {
			try (FileInputStream file = new FileInputStream(getReadFile())) {
				doc = xmlReader.read(file);
				treeWalk(doc);
				// Element root = doc.getRootElement();
				// Iterator elementIterator = root.elementIterator();
				// while (elementIterator.hasNext()) {
				// Element element = (Element) elementIterator.next();
				// String str = element.getStringValue();
				// if (str != null) {
				// element.setText(str.replaceAll(source, text));
				// }
				//
				// }
				try (FileOutputStream outFile = new FileOutputStream(new File(
						fileName + "." + XML_EXT))) {
					OutputFormat outformat = OutputFormat.createPrettyPrint();
					outformat.setEncoding("UTF-8");
					XMLWriter writer = new XMLWriter(outFile, outformat);
					writer.write(doc);
					writer.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {

		}
	}
}
