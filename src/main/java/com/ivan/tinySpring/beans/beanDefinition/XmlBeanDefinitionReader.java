package com.ivan.tinySpring.beans.beanDefinition;

import com.ivan.tinySpring.beans.beanDefinition.AbstractBeanDefinitionReader;
import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.BeanReference;
import com.ivan.tinySpring.beans.PropertyValue;
import com.ivan.tinySpring.beans.resource.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 从XML配置文件中读取，加载为BeanDefinition对象
 *
 *
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }



    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);


    }

    // TODO: 2018/8/6 实际上真正加载BeanDefinitions
    protected void doLoadBeanDefinitions(InputStream inputStream) throws  Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);

        //解析bean
        registerBeanDefinitions(doc);
        //关闭输入流
        inputStream.close();


    }

    // TODO: 2018/8/6 注册BeanDefinitions
    public void registerBeanDefinitions(Document document){
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);


    }

    // TODO: 2018/8/6 解析BeanDefinitions
    protected void parseBeanDefinitions(Element root){
        NodeList childNodes = root.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            if (item instanceof Element){
                processBeanDefinition((Element) item);

            }

        }

    }

    // TODO: 2018/8/6 正式处理XML文件的解析
    protected void processBeanDefinition(Element element){
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(element,beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name,beanDefinition);


    }

    // TODO: 2018/8/6 处理属性
    protected void processProperty(Element element,BeanDefinition beanDefinition){
        NodeList propertyNode = element.getElementsByTagName("property");
        for (int i=0;i<propertyNode.getLength();i++){
            Node node = propertyNode.item(i);
            if (node instanceof Element){
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value!=null&&value.length()>0){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                }else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref==null||ref.length()==0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference=new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));

                }

            }

        }

    }
}
