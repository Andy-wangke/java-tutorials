package com.it.app.test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Main {

	public static void main(String[] args) {
		TestBean bean=new TestBean();
		
		Map<String,Object> properties=new HashMap<>();
		
		properties.put("key1", "value1");
		properties.put("key2", "value2");
		
		bean.setProperties(properties);
		
		StringWriter sb=new StringWriter();
		try {
			JAXBContext.newInstance(TestBean.class).createMarshaller().marshal(bean, sb);
			System.out.println(sb.toString());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
