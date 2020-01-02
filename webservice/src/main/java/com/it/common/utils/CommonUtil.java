package com.it.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

//import com.hp.it.mdm.core.processor.MessageProcessor;
//import com.siperian.sif.message.Record;

public class CommonUtil    {
	
//	data format like "2001-12-17T09:30:47Z"
	public static XMLGregorianCalendar newMockXMLCalendar() {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar xgc = null;
		try {
			xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xgc;
	}
	
	
	
	public static boolean isNotBlankList(List list) {
		boolean flag = true;
		if(list == null || list.size()==0) {
			flag = false;
		}
		return flag;
	}
	
	
	/**
	 * @param entity :  business entity that want to fill with database records
	 * @param fieldName : field name of entity
	 * @param dbRecord : record search from database
	 * @param columnName : table column that mapping to entity field
	 * @return 
	 * @return 
	 */
	/*public static <T> T setFieldByColumn( T entity, String fieldName, Record dbRecord,  String columnName  ) {
		if(dbRecord.getField(columnName) == null) {
			return entity;
		}
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, entity.getClass());
			Method setMethod = pd.getWriteMethod();
			Class<?>  paramType =setMethod.getParameterTypes()[0];
			String paramTypeName = paramType.getSimpleName();
//			cause there not all datatype match between database and edmodel, so we need to convert all of them to String as a temp datatype
//			1 convert record field to String
//			2 convert all the String type from database to the matching type to edmodel
			if( "String".equals(paramTypeName) ) {
				if(dbRecord.getField(columnName).getBigIntegerValue() != null ) {
					setMethod.invoke(entity, dbRecord.getField(columnName).getBigIntegerValue().toString() );
				} else if( dbRecord.getField(columnName).getStringValue() != null && dbRecord.getField(columnName).getStringValue().trim() != null) {
					setMethod.invoke(entity, dbRecord.getField(columnName).getStringValue().trim() );
				} else if( dbRecord.getField(columnName).getBigDecimalValue() != null ) {
					setMethod.invoke(entity, dbRecord.getField(columnName).getBigDecimalValue().toString() );
				}
			}else if( "BigDecimal".equals(paramTypeName) ) {
				if(dbRecord.getField(columnName).getBigIntegerValue() != null) {
					if( dbRecord.getField(columnName) != null ) {
						BigDecimal big = new BigDecimal(dbRecord.getField(columnName).getBigIntegerValue());
						setMethod.invoke(entity, big);
					}
				}else if (dbRecord.getField(columnName).getStringValue() != null) {
					if( dbRecord.getField(columnName) != null  && dbRecord.getField(columnName).getStringValue() != null ) {
						BigDecimal big = new BigDecimal(dbRecord.getField(columnName).getStringValue().trim());
						setMethod.invoke(entity, big);
					}
				}else {
					if( dbRecord.getField(columnName) != null ) {
						setMethod.invoke(entity, dbRecord.getField(columnName).getBigDecimalValue());
					}
				}
			}else if( "BigInteger".equals(paramTypeName) ) {
				if( dbRecord.getField(columnName) != null ) {
					setMethod.invoke(entity, dbRecord.getField(columnName).getBigIntegerValue());
				}
			}else if("XMLGregorianCalendar".equals(paramTypeName)) {
				if( dbRecord.getField(columnName).getDateValue() != null) {
					Date timeStamp = dbRecord.getField(columnName).getDateValue();
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(timeStamp);
					XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
					setMethod.invoke(entity, xgc);
				}
			}else if("Boolean".equals(paramTypeName)) {
				if( dbRecord.getField(columnName) != null ) {
					setMethod.invoke(entity, dbRecord.getField(columnName).getBooleanValue());
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return entity;
	}*/
	
	
	
	
	
	
	public static <T>   void  setResponseParamsToNull( T  response ){
		if(response != null) {
			Field []  fields  =  response.getClass().getDeclaredFields() ;
			for( Field field : fields) {
				String fieldName = field.getName();
				if( !"messages".equals(fieldName) || !"errors".equals(fieldName)) {
					try {
						PropertyDescriptor pd = new PropertyDescriptor(fieldName, response.getClass());
						Method setMethod = pd.getWriteMethod();
						Object[] o = {null};
						setMethod.invoke(response , o );
					} catch (IntrospectionException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static <T> void setResponseParamsStringToEmpty( T  response ) {
//do nothing now
		/*if(response != null) {
			Field []  fields  =  response.getClass().getDeclaredFields() ;
			for( Field field : fields) {
				String fieldName = field.getName();
				String fieldTypeName = field.getType().getSimpleName();
//System.out.println("fieldTypeName="+fieldTypeName);
				if( !"messages".equals(fieldName) || !"errors".equals(fieldName)) {
					if("String".equals( fieldTypeName )) {
						try {
							PropertyDescriptor pd = new PropertyDescriptor(fieldName, response.getClass());
							Method setMethod = pd.getWriteMethod();
							Object[] o = {new String()};
							setMethod.invoke(response , o );
						} catch (IntrospectionException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} */
	}
	
	
	
	
}
