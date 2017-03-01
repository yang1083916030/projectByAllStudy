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
     * json����ת��Ϊjava����
     * 
     * @throws JSONException
     */
    @SuppressWarnings("rawtypes")
	public static Object jsonToJava(String json,Class entity)throws JSONException{
        //����{}���󣬴˴����������������쳣
        if(json.indexOf("[")!=-1){
            json=json.replace("[", "");
        }
        if(json.indexOf("]")!=-1){
            json=json.replace("]", "");
        }
        //�滻�س�����
        json = json.replaceAll("\n", "&n").replaceAll("\r", "&r");
        JSONObject obj=JSONObject.fromObject(json);
        return resetReplace(JSONObject.toBean(obj, entity));
        
    }
    //��ԭ�滻�Ļس�
	private static Object resetReplace(Object object) {
    	// �õ�����
		Class<?> clz = object.getClass();
		// ��ȡʵ������������ԣ�����Field����
		Field[] fields = clz.getDeclaredFields();
		//ȡ��bean������з���  
		Method[] methods = clz.getDeclaredMethods();  
		//������д�뵽map��
		Map<String, String> methodsNm = new HashMap<String, String>();
		for (Method met : methods) {
			methodsNm.put(met.getName(), met.getName());
        } 
		for (Field field : fields) {
			// ���������String
			if (field.getGenericType().toString().equals("class java.lang.String")) { // ���type�������ͣ���ǰ�����"class "�����������
				// �õ������Ե�gettet����
				/**
				 * ������Ҫ˵��һ�£����Ǹ���ƴ�յ��ַ�������д��getter������
				 * ��Booleanֵ��ʱ����isXXX��Ĭ��ʹ��ide����getter�Ķ���isXXX��
				 * �������NoSuchMethod�쳣 ��˵�����Ҳ����Ǹ�gettet���� ��Ҫ�����淶
				 */
				Method m;
				try {
					//�ж�bean���Ƿ����set�������Ƿ�������ã�
					if(methodsNm.containsKey("set" + getMethodName(field.getName()))){
						m = (Method) object.getClass().getMethod(
								"get" + getMethodName(field.getName()));
						String val = (String) m.invoke(object);// ����getter������ȡ����ֵ
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
     * ��jsonת��Ϊjava���϶���
     */
    public static List<?> jsonToJavas(String jsons,Object object) throws JSONException{
        return getJavaCollection(object,jsons);
    }

    /**
     * ��װ��json����ת��Ϊjava���϶���
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
     * ��json��ת����ʵ���б�
     * @param jsons
     * @param entity
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List<Object> jsonToJavas(String jsons,Class entity){
    	return getJavaCollection(entity,jsons);
    }
    
    /**
     *  ��װ��json����ת��Ϊjava���϶���
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
     * ��jsonת��Ϊjava���϶���
     */
    @SuppressWarnings("unused")
	private static List<?> josnToJavas(JSONArray josn,Object object) throws JSONException{
    	return getJavaCollection(object, josn);
    }
    /**
     * ��װ��json����ת��Ϊjava���϶���
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
	 * ��һ���ַ����ĵ�һ����ĸ��д��Ч������ߵġ�
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		//�ж�����ĸ�Ƿ񼺾��Ǵ�д
		if(items[0]>=97&&items[0]<=122){
			items[0] = (byte) ((char) items[0] - 'a' + 'A');
		}
		return new String(items);
	}
}
