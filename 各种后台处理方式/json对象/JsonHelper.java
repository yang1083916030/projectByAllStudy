package com.zdsoft.framework.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JsonHelper {

	  /**
     * json对象转换为java对象
     * 
     * @throws JSONException
     */
    @SuppressWarnings("rawtypes")
	public static Object jsonToJava(String json,Class entity)throws JSONException{
        //接收{}对象，此处接收数组对象会有异常
        if(json.indexOf("[")!=-1){
            json=json.replace("[", "");
        }
        if(json.indexOf("]")!=-1){
            json=json.replace("]", "");
        }
        //替换回车符号
        json = json.replaceAll("\n", "&n").replaceAll("\r", "&r");
        JSONObject obj=JSONObject.fromObject(json);
        return resetReplace(JSONObject.toBean(obj, entity));
        
    }
    //还原替换的回车
	private static Object resetReplace(Object object) {
    	// 拿到该类
		Class<?> clz = object.getClass();
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = clz.getDeclaredFields();
		//取出bean里的所有方法  
		Method[] methods = clz.getDeclaredMethods();  
		//将方法写入到map中
		Map<String, String> methodsNm = new HashMap<String, String>();
		for (Method met : methods) {
			methodsNm.put(met.getName(), met.getName());
        } 
		for (Field field : fields) {
			// 如果类型是String
			if (field.getGenericType().toString().equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				// 拿到该属性的gettet方法
				/**
				 * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
				 * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
				 * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
				 */
				Method m;
				try {
					//判断bean中是否存在set方法（是否可以设置）
					if(methodsNm.containsKey("set" + getMethodName(field.getName()))){
						m = (Method) object.getClass().getMethod(
								"get" + getMethodName(field.getName()));
						String val = (String) m.invoke(object);// 调用getter方法获取属性值
						if(ObjectHelper.isNotEmpty(val)){
							val = val.replaceAll("&n", "\n").replaceAll("&r","\r");
							object.getClass().getMethod("set"+getMethodName(field.getName()),val.getClass()).invoke(object, val);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return object;
    }

    /**
     * 将json转换为java集合对象
     */
    public static List<?> jsonToJavas(String jsons,Object object) throws JSONException{
        return getJavaCollection(object,jsons);
    }

    /**
     * 封装将json对象转换为java集合对象
     * 
     * @param <T>
     * @param clazz
     * @param jsons 
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T> List<T> getJavaCollection(T clazz, String jsons) {
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
    
    /**
     * 将json串转换成实体列表
     * @param jsons
     * @param entity
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List<Object> jsonToJavas(String jsons,Class entity){
    	return getJavaCollection(entity,jsons);
    }
    
    /**
     *  封装将json对象转换为java集合对象
     * @param entity
     * @param jsons
     * @return
     */
	@SuppressWarnings("rawtypes")
	private static List<Object> getJavaCollection(Class entity, String jsons) {
        List<Object> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray!=null){
            objs=new ArrayList<Object>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                Object obj=(Object)JSONObject.toBean(jsonObject, entity);
                objs.add(obj);
            }
        }
        return objs;
    }
    
    /**
     * 将json转换为java集合对象
     */
    @SuppressWarnings("unused")
	private static List<?> josnToJavas(JSONArray josn,Object object) throws JSONException{
    	return getJavaCollection(object, josn);
    }
    /**
     * 封装将json对象转换为java集合对象
     * 
     * @param <T>
     * @param clazz
     * @param jsons 
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T> List<T> getJavaCollection(T clazz, JSONArray jsonArray) {
        List<T> objs=null;
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
    
	/**
	 * 把一个字符串的第一个字母大写、效率是最高的、
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		//判断首字母是否己经是大写
		if(items[0]>=97&&items[0]<=122){
			items[0] = (byte) ((char) items[0] - 'a' + 'A');
		}
		return new String(items);
	}
}
