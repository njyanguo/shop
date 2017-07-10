package com.shop.yi.common.util.bean2map;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static ThreadLocal<Set> recurseBeanSet = new ThreadLocal() {
		protected synchronized Set initialValue() {
			return new HashSet();
		}
	};

	/**
	 * @Title: map2Bean
	 * @Description: 
	 * @param map
	 * @param obj
	 * @return
	 * @return: T
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T map2Bean(Map map, T obj) {
		BeanWrapper bw = new BeanWrapperImpl(obj);
		PropertyDescriptor[] props = bw.getPropertyDescriptors();
		for (PropertyDescriptor pd : props) {
			String name = pd.getName();
			if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name))) {
				Class class0 = pd.getPropertyType();
				if (Enum.class.isAssignableFrom(class0)) {
					String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
					Object value = map.get(convertedName);
					if (value != null) {
						if (value.getClass() == class0) {
							bw.setPropertyValue(name, value);
						} else {
							String enumValue = String.valueOf(value);
							if (enumValue.length() > 0) {
								Enum v = Enum.valueOf(class0, enumValue);
								bw.setPropertyValue(name, v);
							}
						}
					}
				} else {
					String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
					Object value = map.get(convertedName);
					if (value != null) {
						bw.setPropertyValue(name, value);
					}
				}
			}
		}
		return (T) bw.getWrappedInstance();
	}
	/**
	 * @Title: map2Bean
	 * @Description: 
	 * @param map
	 * @param clazz
	 * @return
	 * @return: T
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T map2Bean(Map map, Class<T> clazz) {
		BeanWrapper bw = new BeanWrapperImpl(clazz);
		PropertyDescriptor[] props = bw.getPropertyDescriptors();
		for (PropertyDescriptor pd : props) {
			String name = pd.getName();
			if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name))) {
				Class class0 = pd.getPropertyType();
				if (Enum.class.isAssignableFrom(class0)) {
					String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
					Object value = map.get(convertedName);
					if (value != null) {
						if (value.getClass() == class0) {
							bw.setPropertyValue(name, value);
						} else {
							String enumValue = String.valueOf(value);
							if (enumValue.length() > 0) {
								Enum v = Enum.valueOf(class0, String.valueOf(value));
								bw.setPropertyValue(name, v);
							}
						}
					}
				} else {
					String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
					Object value = map.get(convertedName);
					if (value != null) {
						bw.setPropertyValue(name, value);
					}
				}
			}
		}
		return (T) bw.getWrappedInstance();
	}
	/**
	 * @Title: bean2Map
	 * @Description: 
	 * @param beanObject
	 * @return
	 * @return: Map
	 */
	public static Map bean2Map(Object beanObject) {
		BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
		PropertyDescriptor[] desc = bean.getPropertyDescriptors();
		Map dataMap = new HashMap(desc.length);
		try {
			for (int i = 0; i < desc.length; i++) {
				String name = desc[i].getName();
				if ((bean.isWritableProperty(name)) && (bean.isReadableProperty(name))) {
					Object object = bean.getPropertyValue(name);
					if (object != null) {
						String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
						dataMap.put(convertedName, object);
					}
				}
			}
			return dataMap;
		} catch (Exception e1) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("method bean2Map is error: please check exception", e1);
			}
		}
		return null;
	}
	/**
	 * @Title: listBean2ListMap
	 * @Description: 
	 * @param list
	 * @return
	 * @return: List<Map>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> listBean2ListMap(List list) {
		List result = new ArrayList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map tmp = bean2Map(it.next());
			result.add(tmp);
		}
		return result;
	}
	/**
	 * @Title: listMap2ListBean
	 * @Description: 
	 * @param list
	 * @param class0
	 * @return
	 * @return: List<T>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> listMap2ListBean(List list, Class<T> class0) {
		List result = new ArrayList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Object t = map2Bean((Map) it.next(), class0);
			result.add(t);
		}
		return result;
	}
	/**
	 * @Title: bean2MapRecurse
	 * @Description: 
	 * @param beanObject
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map bean2MapRecurse(Object beanObject) {
		Set set = (Set) recurseBeanSet.get();
		if (set.contains(beanObject)) {
			return null;
		}
		set.add(beanObject);
		try {
			BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
			PropertyDescriptor[] desc = bean.getPropertyDescriptors();
			Map dataMap = new HashMap(desc.length);
			try {
				for (int i = 0; i < desc.length; i++) {
					String name = desc[i].getName();
					if ((bean.isWritableProperty(name)) && (bean.isReadableProperty(name))) {
						Object object = bean.getPropertyValue(name);
						if (object != null) {
							String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
							Class class0 = object.getClass();
							if ((class0.getName().startsWith("java")) || (Enum.class.isAssignableFrom(class0))) {
								dataMap.put(convertedName, object);
							} else {
								Map subMap = bean2MapRecurse(object);
								if (subMap != null) {
									for (Iterator it = subMap.entrySet().iterator(); it.hasNext();) {
										Map.Entry entry = (Map.Entry) it.next();
										dataMap.put(convertedName + "_" + entry.getKey(), entry.getValue());
									}
								}
							}
						}
					}
				}
				return dataMap;
			} catch (Exception e1) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("method bean2Map is error: please check exception", e1);
				}
			}
		} finally {
			set.remove(beanObject);
		}
		return null;
	}
	/**
	 * 
	 * @Title: list2Bean 
	 * @Description: 
	 * @param srcBeanObject
	 * @param destBeanObject
	 * @param listPropName
	 * @return: void
	 */
	public static void list2Bean(List<?> srcBeanObject, Object destBeanObject, String listPropName) {
		BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);
		destBean.setPropertyValue(listPropName, srcBeanObject);
	}
	
	/**
	 * 
	 * @Title: bean2Bean 
	 * @Description: 
	 * @param srcBeanObject
	 * @param class0
	 * @return
	 * @return: T
	 */
	public static <T> T bean2Bean(Object srcBeanObject, Class<T> class0) {
		try {
			Object t = class0.newInstance();
			if ((srcBeanObject instanceof List)) {
				list2Bean((List) srcBeanObject, t, "list");
			} else {
				bean2Bean(srcBeanObject, t);
			}
			return (T) t;
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("method bean2Map is error: please check exception", e);
			}
		}
		return null;
	}
	/**
	 * 
	 * @Title: bean2Bean 
	 * @Description: 
	 * @param srcBeanObject
	 * @param destBeanObject
	 * @return: void
	 */
	public static void bean2Bean(Object srcBeanObject, Object destBeanObject) {
		BeanWrapperImpl srcBean = new BeanWrapperImpl(srcBeanObject);
		BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);
		PropertyDescriptor[] destDesc = destBean.getPropertyDescriptors();
		try {
			for (int i = 0; i < destDesc.length; i++) {
				String name = destDesc[i].getName();
				if (destBean.isWritableProperty(name)) {
					if (srcBean.isReadableProperty(name)) {
						Object srcValue = srcBean.getPropertyValue(name);
						if (srcValue != null) {
							destBean.setPropertyValue(name, srcValue);
						}
					}
				}
			}
		} catch (Exception e1) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("method bean2Map is error: please check exception", e1);
			}
		}
	}
	public static void main(String[] args) {
		Map map = new HashMap();
		System.err.println(map.containsKey("sdfdsf"));
		System.err.println(map.get("sdfdsf"));
		List alist = new ArrayList();
		System.err.println((ParameterizedType) alist.getClass().getGenericInterfaces()[1]);
		System.err.println(((ParameterizedType) alist.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		Class classt = (Class) ((ParameterizedType) alist.getClass().getGenericInterfaces()[0])
				.getActualTypeArguments()[0];
		System.err.println(classt);
	}
	public static <T> T map2BeanStrict(Map map, T obj) {
		BeanWrapper bw = new BeanWrapperImpl(obj);
		PropertyDescriptor[] props = bw.getPropertyDescriptors();
		for (PropertyDescriptor pd : props) {
			String name = pd.getName();
			if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name))) {
				Class class0 = pd.getPropertyType();
				if (Enum.class.isAssignableFrom(class0)) {
					Object value = map.get(name);
					if (value != null) {
						if (value.getClass() == class0) {
							bw.setPropertyValue(name, value);
						} else {
							Enum v = Enum.valueOf(class0, String.valueOf(value));
							bw.setPropertyValue(name, v);
						}
					}
				} else {
					Object value = map.get(name);
					if (value != null) {
						bw.setPropertyValue(name, value);
					}
				}
			}
		}
		return (T) bw.getWrappedInstance();
	}
	public static <T> T map2BeanStrict(Map map, Class<T> clazz) {
		BeanWrapper bw = new BeanWrapperImpl(clazz);
		PropertyDescriptor[] props = bw.getPropertyDescriptors();
		for (PropertyDescriptor pd : props) {
			String name = pd.getName();
			if ((bw.isWritableProperty(name)) && (bw.isReadableProperty(name))) {
				Class class0 = pd.getPropertyType();
				if (Enum.class.isAssignableFrom(class0)) {
					Object value = map.get(name);
					if (value != null) {
						if (value.getClass() == class0) {
							bw.setPropertyValue(name, value);
						} else {
							Enum v = Enum.valueOf(class0, String.valueOf(value));
							bw.setPropertyValue(name, v);
						}
					}
				} else {
					Object value = map.get(name);
					if (value != null) {
						bw.setPropertyValue(name, value);
					}
				}
			}
		}
		return (T) bw.getWrappedInstance();
	}

	static class Animal {
		private BeanUtils.MyBean myBean = new BeanUtils.MyBean();
		private int sex;

		public BeanUtils.MyBean getMyBean() {
			this.myBean.setAbc("sfsf");
			return this.myBean;
		}
		public int getSex() {
			return this.sex;
		}
		public void setSex(int sex) {
			this.sex = sex;
		}
	}

	static enum Color {
		Red, Color, CC, Green, DC;
	}

	static class MyBean {
		private String abc;
		private BeanUtils.Person person;

		public String getAbc() {
			return this.abc;
		}
		public void setAbc(String abc) {
			this.abc = abc;
		}
		public BeanUtils.Person getPerson() {
			return this.person;
		}
		public void setPerson(BeanUtils.Person person) {
			this.person = person;
		}
	}

	static class Person {
		private BeanUtils.MyBean myBean;
		private int sex;
		private BeanUtils.Color color;

		public BeanUtils.MyBean getMyBean() {
			return this.myBean;
		}
		public void setMyBean(BeanUtils.MyBean myBean) {
			this.myBean = myBean;
		}
		public int getSex() {
			return this.sex;
		}
		public void setSex(int sex) {
			this.sex = sex;
		}
		public BeanUtils.Color getColor() {
			return this.color;
		}
		public void setColor(BeanUtils.Color color) {
			this.color = color;
		}
	}
}
