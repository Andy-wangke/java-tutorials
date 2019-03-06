package com.it.app.types.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

public class MapAdapter extends XmlAdapter<MapWrapper, Map<String, Object>> {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public MapWrapper marshal(Map<String, Object> map) throws Exception {
		if (map == null || map.isEmpty())
			return null;
		MapWrapper wrapper = new MapWrapper();

		List elements = new ArrayList();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				elements.add(new JAXBElement<MapWrapper>(new QName(getCleanLabel(entry.getKey())), MapWrapper.class,
						marshal((Map) entry.getValue())));
			} else {
				elements.add(new JAXBElement<String>(new QName(getCleanLabel(entry.getKey())), String.class,
						String.valueOf(entry.getValue())));
			}
		}
		wrapper.elements = elements;
		return wrapper;
	}

	@Override
	public Map<String, Object> unmarshal(MapWrapper entries) throws Exception {
		if (entries == null || entries.elements != null)
			return null;
		Map<String, Object> unmarshalled = new HashMap<String, Object>();
		// for (Object element: entries.elements)
		// unmarshalled.put(mapelement.getKey(), mapelement.getValue());
		// return unmarshalled;
		throw new OperationNotSupportedException();
	}

	// Return a lower-camel XML-safe attribute
	private String getCleanLabel(String attributeLabel) {
		attributeLabel = attributeLabel.replaceAll("[()]", "").replaceAll("[^\\w\\s]", "_").replaceAll(" ", "_");
		//attributeLabel = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,attributeLabel);
		return attributeLabel;
	}
}
