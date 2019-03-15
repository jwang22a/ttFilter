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
	 * ��ȡssl url
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String postSSLUrlForFile(String url,String encoding,String filePath, Map<String,String> map) {
		return postSSLUrlForFile( url,encoding,1, filePath,  map) ;
	}

	/**
	 * POST��Ϣ ���󲿷��ϴ�ͼƬ
	 * @param url      ��ַ
	 * @param encoding ����
	 * @param sort     0����ssl  1 ��ssl
	 * @param map      ������Ϣ
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
			// ����Ϊgetȡ���ӵķ�ʽ.
			HttpPost post = new HttpPost(url);

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
			// ����
			//HttpConnectionParams.setConnectionTimeout(params, 6000);


			MultipartEntity entity3 = new MultipartEntity();

			//�ļ���
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


			// �õ����ص�response.
			HttpResponse response = client.execute(post);
			// �õ����ص�client�����ʵ�������Ϣ.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println("���ݱ����ǣ�" + resEntity.getContentEncoding());
				System.out.println("���������ǣ�" + resEntity.getContentType());
				// �õ����ص���������.
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
					msg = "��ʱ";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �ر�����.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "��ʱ";
		}

		return msg;

	}



	/**
	 *  ΢��
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
	 * POST��Ϣ �ϴ�ͼƬ ΢��
 	 * @param url      ��ַ
	 * @param encoding ����
	 * @param sort     0����ssl  1 ��ssl
	 * @param map      ������Ϣ
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
			// ����Ϊgetȡ���ӵķ�ʽ.
			HttpPost post = new HttpPost(url);

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
			// ����
			//HttpConnectionParams.setConnectionTimeout(params, 6000);


			MultipartEntity entity3 = new MultipartEntity();

			//�ļ���
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


			// �õ����ص�response.
			HttpResponse response = client.execute(post);
			// �õ����ص�client�����ʵ�������Ϣ.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println("���ݱ����ǣ�" + resEntity.getContentEncoding());
				System.out.println("���������ǣ�" + resEntity.getContentType());
				// �õ����ص���������.
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
					msg = "��ʱ";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �ر�����.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "��ʱ";
		}

		return msg;

	}








	/**
	 * ��ȡurl����
	 * @param url
	 * @param encoding
	 * @param sort  0����ssl  1 ��ssl  
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

			// ����Ϊgetȡ���ӵķ�ʽ.
			HttpGet get = new HttpGet(url);
			// �õ����ص�response.
			HttpResponse response = client.execute(get);
			// �õ����ص�client�����ʵ�������Ϣ.
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				 System.out.println("���ݱ����ǣ�" + entity.getContentEncoding());
		         System.out.println("���������ǣ�" + entity.getContentType());   
				// �õ����ص���������.
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
					msg = "��ʱ";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �ر�����.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "��ʱ";
		}

		return msg;
	}

	/**
	 * ��ȡssl url
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getSSLUrl(String url, String encoding) {
		return getUrl(url, encoding,1) ;
	}
	/**
	 * ��ȡurl
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getUrl(String url, String encoding){
		return getUrl(url, encoding,0) ;
	}
	
	/**
	 * POST��Ϣ
	 * @param url      ��ַ
	 * @param encoding ����
	 * @param sort     0����ssl  1 ��ssl
	 * @param map      ������Ϣ
	 * @param resStr   �ı���Ϣ
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
			// ����Ϊgetȡ���ӵķ�ʽ.
			HttpPost post = new HttpPost(url);
			
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
			// ����
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
			
			// �õ����ص�response.
			HttpResponse response = client.execute(post);
			// �õ����ص�client�����ʵ�������Ϣ.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				 System.out.println("���ݱ����ǣ�" + resEntity.getContentEncoding());   
		         System.out.println("���������ǣ�" + resEntity.getContentType());   
				// �õ����ص���������.
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
					msg = "��ʱ";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �ر�����.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "��ʱ";
		}
		
		
		
		
		return msg;
	}
	//̨���ƶ��ߴ���
	public static String postUrlTzyd(String url,String encoding,int sort,List map,String resStr){
		String msg = "";
		try {
		
			HttpClient client = null;
			if(sort==0){
				client = ClientUtil.getHttpClientTzyd(encoding);
			}else{
				client = ClientUtil.getSSLHttpClient(encoding);
			}
			// ����Ϊgetȡ���ӵķ�ʽ.
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
			
			// �õ����ص�response.
			HttpResponse response = client.execute(post);
			// �õ����ص�client�����ʵ�������Ϣ.
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println( "���ݳ��ȣ�" + resEntity.getContentLength() );
				 System.out.println("���ݱ����ǣ�" + resEntity.getContentEncoding());   
		         System.out.println("���������ǣ�" + resEntity.getContentType());   
				// �õ����ص���������.
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
					msg = "��ʱ";
					System.out.println(e.getMessage());
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �ر�����.
					client.getConnectionManager().shutdown();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "��ʱ";
		}
		
		return msg;
	}
	
	
	/**
	 * POST��Ϣ
	 * @param url      ��ַ
	 * @param encoding ����
	 * @param map      ������Ϣ
	 * @param resStr   �ı���Ϣ
	 * @return
	 */
	public static String postUrl(String url,String encoding,List map,String resStr){
		return postUrl(url,encoding,0,map,resStr);
	}	
	
	public static String postUrlTzyd(String url,String encoding,List map,String resStr){
		return postUrlTzyd(url,encoding,0,map,resStr);
	}	
	
	/**
	 * POST��Ϣ
	 * @param url      ��ַ
	 * @param encoding ����
	 * @param map      ������Ϣ
	 * @param resStr   �ı���Ϣ
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
	    // ����URL
	    URL url = new URL(urlString);
	    // ������
	    URLConnection con = url.openConnection();
	    // ������
	    InputStream is = con.getInputStream();
	    // 1K�����ݻ���
	    byte[] bs = new byte[1024];
	    // ��ȡ�������ݳ���
	    int len;
	    // ������ļ���
	    OutputStream os = new FileOutputStream(filename);
	    // ��ʼ��ȡ
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // ��ϣ��ر���������
	    os.close();
	    is.close();
	} 
	
	/**
     * ������Url�������ļ�
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
	static int BUFFER_SIZE = 1024;
	
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
    	
        URL url = new URL(urlStr);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
                //���ó�ʱ��Ϊ3��
        conn.setConnectTimeout(3*1000);
        //��ֹ���γ���ץȡ������403����
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //�õ�������
        InputStream inputStream = conn.getInputStream();  
        //��ȡ�Լ�����
        byte[] getData = readInputStream(inputStream);
        //�ļ�����λ��
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
     * ���������л�ȡ�ֽ�����
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
     // Post�����url����get��ͬ���ǲ���Ҫ������
        URL postUrl;
		try {
			postUrl = new URL(urlStr);
			// ������
	        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
	      
	        // �����Ƿ���connection�������Ϊ�����post���󣬲���Ҫ����
	        // http�����ڣ������Ҫ��Ϊtrue
	        connection.setDoOutput(true);
	        // Read from the connection. Default is true.
	        connection.setDoInput(true);
	        // Ĭ���� GET��ʽ
	        connection.setRequestMethod("POST");
	       
	        // Post ������ʹ�û���
	        connection.setUseCaches(false);
	       
	        connection.setInstanceFollowRedirects(true);
	        
	     // ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
	        // ��˼��������urlencoded�������form�������������ǿ��Կ������Ƕ���������ʹ��URLEncoder.encode
	        // ���б���
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setRequestProperty("User-Agent", "mozilla/5.0 (linux; u; android 4.1.2; zh-cn; gt-n7100 build/jzo54k) applewebkit/533.1 (khtml, like gecko)version/4.0 mqqbrowser/5.4 tbs/025478 mobile safari/533.1 micromessenger/6.2.5.51_rfe7d7c5.621 nettype/wifi language/zh_cn");

	        // ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
	        // Ҫע�����connection.getOutputStream�������Ľ���connect��
	        connection.connect();
	        DataOutputStream out = new DataOutputStream(connection
	                .getOutputStream());
	        // The URL-encoded contend
	        // ���ģ�����������ʵ��get��URL�� '? '��Ĳ����ַ���һ��
	        /*
	        String content = "hd_id=" + URLEncoder.encode(hd_id, "UTF-8");
	        content +="&src_id="+URLEncoder.encode(src_id, "UTF-8");
	        content +="&mobile="+URLEncoder.encode(mobile, "UTF-8");
	        content +="&bon_amt="+URLEncoder.encode(bon_amt, "UTF-8");
	        content +="&mlog_no="+URLEncoder.encode(mlog_no, "UTF-8");*/
	        // DataOutputStream.writeBytes���ַ����е�16λ��unicode�ַ���8λ���ַ���ʽд��������
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
