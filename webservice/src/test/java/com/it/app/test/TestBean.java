package com.it.app.test;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.it.app.types.util.MapAdapter;

@XmlRootElement
@XmlType(propOrder={"name","address","properties"})
@XmlAccessorType(XmlAccessType.FIELD)
public class TestBean {

	private String name = "Name";

	private String address = "1234 Main street";

	@XmlElement(name="properties")
	@XmlJavaTypeAdapter(MapAdapter.class)
	private Map<String, Object> properties=new HashMap<>();

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
