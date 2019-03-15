package commonUtil;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.params.CoreConnectionPNames;

public class HttpUtil {

	

	/**
	 * 获取ssl url
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String postSSLUrlForFile(String url,String encoding,String filePath, Map<String,String> map) {
		return postSSLUrlForFile( url,encoding,1, filePath,  map) ;
	}

	/**
	 * POST信息 政企部分上传图片
	 * @param url      地址
	 * @param encoding 编码
	 * @param sort     0不带ssl  1 带ssl
	 * @param map      参数信息
	 * @return
	 */
	public static String postSSLUrlForFile(String url,String encoding,int sort,String filePath, Map<String,String> map){
		String msg = "";
		try {

			HttpClient client = null;
			if(sort==0){
				client = ClientUtil.getHttpClient(encoding);
			}else{
				client = ClientUtil.getSSLHttpClient(encoding);
			}
			// 设置为get取连接的方式.
			HttpPost post = new HttpPost(url);

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
			// 或者
			//HttpConnectionParams.setConnectionTimeout(params, 6000);


			MultipartEntity entity3 = new MultipartEntity();

			//文件流
			if(null!=filePath&&(!"".equals(filePath))){
				//System.out.println("wj_kk--------------------"+filePath);
				File upfile = new File(filePath);
				//System.out.println("wj_kk--------------------");
				entity3.addPart("file", new FileBody(upfile));
			}

			if(map!=null){
				Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
					entity3.addPart("entry.getKey()", new StringBody(entry.getValue()));
				}
			}

			post.setEntity(entity3);


			// 得到返回的response.
			HttpResponse response = client.execute(post);
			// 得到返回的client里面的实体对象信息.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println("内容编码是：" + resEntity.getContentEncoding());
				System.out.println("内容类型是：" + resEntity.getContentType());
				// 得到返回的主体内容.
				InputStream instream = resEntity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, encoding));
					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null)
						sb.append(line);
					msg = sb.toString();
				} catch (Exception e) {
					msg = "超时";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 关闭连接.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "超时";
		}

		return msg;

	}



	/**
	 *  微信
	 * @param url
	 * @param encoding
	 * @param fileName
	 * @param filePath
	 * @param map
	 * @return
	 */
	public static String postSSLUrlForFile(String url,String encoding,String fileName,String filePath, Map<String,String> map) {
		return postSSLUrlForFile( url,encoding,0,fileName,filePath,map) ;
	}
	/**
	 * POST信息 上传图片 微信
 	 * @param url      地址
	 * @param encoding 编码
	 * @param sort     0不带ssl  1 带ssl
	 * @param map      参数信息
	 * @return
	 */
	public static String postSSLUrlForFile(String url,String encoding,int sort,String fileName,String filePath, Map<String,String> map){
		String msg = "";
		try {

			HttpClient client = null;
			if(sort==0){
				client = ClientUtil.getHttpClient(encoding);
			}else{
				client = ClientUtil.getSSLHttpClient(encoding);
			}
			// 设置为get取连接的方式.
			HttpPost post = new HttpPost(url);

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
			// 或者
			//HttpConnectionParams.setConnectionTimeout(params, 6000);


			MultipartEntity entity3 = new MultipartEntity();

			//文件流
			if(null!=filePath&&(!"".equals(filePath))){
				File upfile = new File(filePath);
				entity3.addPart(fileName, new FileBody(upfile));
			}

			if(map!=null){
				Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
					entity3.addPart("entry.getKey()", new StringBody(entry.getValue()));
				}
			}

			post.setEntity(entity3);


			// 得到返回的response.
			HttpResponse response = client.execute(post);
			// 得到返回的client里面的实体对象信息.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println("内容编码是：" + resEntity.getContentEncoding());
				System.out.println("内容类型是：" + resEntity.getContentType());
				// 得到返回的主体内容.
				InputStream instream = resEntity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, encoding));
					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null)
						sb.append(line);
					msg = sb.toString();
				} catch (Exception e) {
					msg = "超时";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 关闭连接.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "超时";
		}

		return msg;

	}








	/**
	 * 获取url链接
	 * @param url
	 * @param encoding
	 * @param sort  0不带ssl  1 带ssl  
	 * @return
	 */
	public static String getUrl(String url, String encoding,int sort) {
		String msg = "";
		try {
		
			HttpClient client = null;
			if(sort==0){
				client = ClientUtil.getHttpClient(encoding);
			}else{
				client = ClientUtil.getSSLHttpClient(encoding);
			}

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);

			// 设置为get取连接的方式.
			HttpGet get = new HttpGet(url);
			// 得到返回的response.
			HttpResponse response = client.execute(get);
			// 得到返回的client里面的实体对象信息.
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				 System.out.println("内容编码是：" + entity.getContentEncoding());
		         System.out.println("内容类型是：" + entity.getContentType());   
				// 得到返回的主体内容.
				InputStream instream = entity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, encoding));
					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null)
						sb.append(line);
					msg = sb.toString();
				} catch (Exception e) {
					msg = "超时";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 关闭连接.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "超时";
		}

		return msg;
	}

	/**
	 * 获取ssl url
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getSSLUrl(String url, String encoding) {
		return getUrl(url, encoding,1) ;
	}
	/**
	 * 获取url
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getUrl(String url, String encoding){
		return getUrl(url, encoding,0) ;
	}
	
	/**
	 * POST信息
	 * @param url      地址
	 * @param encoding 编码
	 * @param sort     0不带ssl  1 带ssl
	 * @param map      参数信息
	 * @param resStr   文本信息
	 * @return
	 */
	public static String postUrl(String url,String encoding,int sort,List map,String resStr){
		String msg = "";
		try {
		
			HttpClient client = null;
			if(sort==0){
				client = ClientUtil.getHttpClient(encoding);
			}else{
				client = ClientUtil.getSSLHttpClient(encoding);
			}
			// 设置为get取连接的方式.
			HttpPost post = new HttpPost(url);
			
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
			// 或者
			//HttpConnectionParams.setConnectionTimeout(params, 6000);
			
			HttpEntity entity = null;
			if(map!=null){
				entity = new UrlEncodedFormEntity(map, encoding);
			}else{
				if("".equals(CommonUtil.checkNull(resStr))){
					resStr="*";
				}
				entity = new StringEntity(resStr,encoding);
			}
			post.setEntity(entity);
			
			// 得到返回的response.
			HttpResponse response = client.execute(post);
			// 得到返回的client里面的实体对象信息.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				 System.out.println("内容编码是：" + resEntity.getContentEncoding());   
		         System.out.println("内容类型是：" + resEntity.getContentType());   
				// 得到返回的主体内容.
				InputStream instream = resEntity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, encoding));
					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null)
						sb.append(line);
					msg = sb.toString();
				} catch (Exception e) {
					msg = "超时";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 关闭连接.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "超时";
		}
		
		
		
		
		return msg;
	}
	//台州移动走代理
	public static String postUrlTzyd(String url,String encoding,int sort,List map,String resStr){
		String msg = "";
		try {
		
			HttpClient client = null;
			if(sort==0){
				client = ClientUtil.getHttpClientTzyd(encoding);
			}else{
				client = ClientUtil.getSSLHttpClient(encoding);
			}
			// 设置为get取连接的方式.
			HttpPost post = new HttpPost(url);
			
			HttpEntity entity = null;
			if(map!=null){
				entity = new UrlEncodedFormEntity(map, encoding);
			}else{
				if("".equals(CommonUtil.checkNull(resStr))){
					resStr="*";
				}
				entity = new StringEntity(resStr,encoding);
				System.out.println(resStr);
			}
			System.out.println("1111111="+entity);
			
			post.setEntity(entity);
			
			// 得到返回的response.
			HttpResponse response = client.execute(post);
			// 得到返回的client里面的实体对象信息.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println( "内容长度：" + resEntity.getContentLength() );
				 System.out.println("内容编码是：" + resEntity.getContentEncoding());   
		         System.out.println("内容类型是：" + resEntity.getContentType());   
				// 得到返回的主体内容.
				InputStream instream = resEntity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, encoding));
					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null)
						sb.append(line);
					msg = sb.toString();
				} catch (Exception e) {
					msg = "超时";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 关闭连接.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "超时";
		}
		
		return msg;
	}
	
	
	/**
	 * POST信息
	 * @param url      地址
	 * @param encoding 编码
	 * @param map      参数信息
	 * @param resStr   文本信息
	 * @return
	 */
	public static String postUrl(String url,String encoding,List map,String resStr){
		return postUrl(url,encoding,0,map,resStr);
	}	
	
	public static String postUrlTzyd(String url,String encoding,List map,String resStr){
		return postUrlTzyd(url,encoding,0,map,resStr);
	}	
	
	/**
	 * POST信息
	 * @param url      地址
	 * @param encoding 编码
	 * @param map      参数信息
	 * @param resStr   文本信息
	 * @return
	 */
	public static String postSSLUrl(String url,String encoding,List map,String resStr){
		return postUrl(url,encoding,1,map,resStr);
	}
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		// TODO Auto-generated method stub
		System.out.println(HttpUtil.postUrl("http://ewm.anycall.cn/ewm/manage/login_login.do", "utf-8", 0, null, "222222222222222222222"));
		

	}

	
	public static void download(String urlString, String filename) throws Exception {
	    // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    OutputStream os = new FileOutputStream(filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	} 
	
	/**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
	static int BUFFER_SIZE = 1024;
	
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
    	
        URL url = new URL(urlStr);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
                //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();  
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        //文件保存位置
        File saveDir = new File(savePath);
        
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);    
        FileOutputStream fos = new FileOutputStream(file);     
        fos.write(getData); 
        if(fos!=null){
            fos.close();  
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success"); 
/*
    		Reader in = null;
    		Writer out = null;
    		int totalCharsRead = 0;
    		try {
    			URLConnection urlConnection = new URL(urlStr).openConnection();
    			if (urlConnection instanceof HttpURLConnection) {
    				HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
    				in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
    				out = new BufferedWriter(new FileWriter(new File(savePath,fileName)));
    				char[] buffer = new char[BUFFER_SIZE]; 
    				int charsRead = 0;
    				while ( (charsRead = in.read(buffer, 0, BUFFER_SIZE)) != -1 ) {
    					out.write(buffer, 0, charsRead);
    					totalCharsRead += charsRead;
    				}
    			} else {
    				System.err.println("unhandled URL connection type, we currently only support HttpURLConnection. URL=" + urlStr);
    			}
    		} finally {
    			if (in != null) {
    				try {
    					in.close();
    				}
    				catch (IOException ex) {  }
    			}
    			if (out != null) {
    				try {
    					out.close();
    				}
    				catch (IOException ex) {  }
    			}
    		}
    		*/
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  
    
    
    public static String  requestWXUrl(String urlStr) throws IOException{
    	String content="";
     // Post请求的url，与get不同的是不需要带参数
        URL postUrl;
		try {
			postUrl = new URL(urlStr);
			// 打开连接
	        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
	      
	        // 设置是否向connection输出，因为这个是post请求，参数要放在
	        // http正文内，因此需要设为true
	        connection.setDoOutput(true);
	        // Read from the connection. Default is true.
	        connection.setDoInput(true);
	        // 默认是 GET方式
	        connection.setRequestMethod("POST");
	       
	        // Post 请求不能使用缓存
	        connection.setUseCaches(false);
	       
	        connection.setInstanceFollowRedirects(true);
	        
	     // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
	        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
	        // 进行编码
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setRequestProperty("User-Agent", "mozilla/5.0 (linux; u; android 4.1.2; zh-cn; gt-n7100 build/jzo54k) applewebkit/533.1 (khtml, like gecko)version/4.0 mqqbrowser/5.4 tbs/025478 mobile safari/533.1 micromessenger/6.2.5.51_rfe7d7c5.621 nettype/wifi language/zh_cn");

	        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
	        // 要注意的是connection.getOutputStream会隐含的进行connect。
	        connection.connect();
	        DataOutputStream out = new DataOutputStream(connection
	                .getOutputStream());
	        // The URL-encoded contend
	        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
	        /*
	        String content = "hd_id=" + URLEncoder.encode(hd_id, "UTF-8");
	        content +="&src_id="+URLEncoder.encode(src_id, "UTF-8");
	        content +="&mobile="+URLEncoder.encode(mobile, "UTF-8");
	        content +="&bon_amt="+URLEncoder.encode(bon_amt, "UTF-8");
	        content +="&mlog_no="+URLEncoder.encode(mlog_no, "UTF-8");*/
	        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
	        out.writeBytes(content);

	        out.flush();
	        out.close(); 
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        String return_str = "";
	        
	        while ((line = reader.readLine()) != null){
	            System.out.println(line);
	            return_str += line;
	        }
	      
	        reader.close();
	        connection.disconnect();
	        return return_str;
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		return "";
    }
    
}
