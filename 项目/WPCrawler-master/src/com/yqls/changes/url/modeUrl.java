package com.yqls.changes.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class modeUrl {
	 // 糗事百科图片贴地址
    public static final String URL = "http://www.baidu.com";
    // 更新参数
    public static final String PARAM = "?s=4490316";
    // 模仿UA
    public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
    // 匹配属性名
    public static final String MATCH_ATTR_NAME = "alt";
    // 匹配属性前缀
    public static final String MATCH_ATTR_PREFIX = "糗事#";
    // div节点选择器
    public static final String GOOD_IMG_DIV_SELECTOR = ".untagged";
    public static final int MAX_PAGE = 100;
    // 最低顶次数
    public static final int MIN_UP = 50;
    // 存储路径
    public static final String BASE_PATH = "C:\\糗事百科图片";
    
    public static void main(String[] args) {
        String urls =URL;
        GengerlMode(urls);
    }
    
    /**
     * 
     * 解析一个网站
     *
     * @author yql
     * @param url
     * @param match_attr_prefix
     * @param Max
     */
	public static  void  GengerlMode(String urls){
	 String result = getResultByUrl(urls);
	 System.out.println(result);
	}
	
	/**
	 * 
	 * 指定的url解析数据
	 *
	 * @author yql
	 * @param url
	 */
	public static String  getResultByUrl(String url){
        try {
            HttpClient hc = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", UA);
            HttpResponse reponse;
            reponse = hc.execute(httpGet);
            HttpEntity entity = reponse.getEntity();
            if(entity != null  ){
                InputStream in =entity.getContent();
                StringWriter sw = new StringWriter();
                IOUtils.copy(in , sw);
                in.close();
                return sw.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
	}
	/**
	 * 
	 * 将图片写入本地
	 *
	 * @author yql
	 * @param img_url
	 * @param file
	 * @return
	 */
	public static  boolean  saveImg(String img_url,File file){
        return false;
	}
	

}
