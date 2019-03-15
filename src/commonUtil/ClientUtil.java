package commonUtil;

import java.security.KeyStore;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
public class ClientUtil {
	private static int REQUEST_TIMEOUT = 5 * 1000;// 设置请求超时3秒钟
	private static int SO_TIMEOUT = 5 * 1000; // 设置等待数据超时时间3秒钟
	private static String CHARSET=HTTP.UTF_8; //编码
	
	/**
	 * 获取sll链接
	 * @param request_timeout  设置请求超时
	 * @param so_timeout       设置等待数据超时时间
	 * @param charset          编码
	 * @return
	 */
	public static HttpClient getSSLHttpClient(int request_timeout,int so_timeout,String charset) {
		 try {  
		        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
		        trustStore.load(null, null);  
		  
		        SSLSocketFactory sf = new MySSLSocketFactory(trustStore);  
		        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
		  
		        HttpParams params = new BasicHttpParams();  
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
		        HttpProtocolParams.setContentCharset(params, charset);  
		        HttpConnectionParams.setConnectionTimeout(params,
		        		request_timeout);
				HttpConnectionParams.setSoTimeout(params, so_timeout);
		        SchemeRegistry registry = new SchemeRegistry();  
		        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));  
		        registry.register(new Scheme("https", sf, 443));  
		  
		        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);  
		  
		        return new DefaultHttpClient(ccm, params);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	
	/**
	 * 获取sll链接
	 * @return
	 */
	public static HttpClient getSSLHttpClient(String charset) {
		 try {  
		        return getSSLHttpClient(REQUEST_TIMEOUT,SO_TIMEOUT,charset);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	
	/**
	 * 获取sll链接
	 * @return
	 */
	public static HttpClient getSSLHttpClient() {
		 try {  
		        return getSSLHttpClient(REQUEST_TIMEOUT,SO_TIMEOUT,CHARSET);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	
	/**
	 * 获取链接
	 * @param request_timeout  设置请求超时
	 * @param so_timeout       设置等待数据超时时间
	 * @param charset          编码
	 * @return
	 */
	public static HttpClient getHttpClient(int request_timeout,int so_timeout,String charset) {
		 try { 
			 	HttpParams params = new BasicHttpParams();  
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
		        HttpProtocolParams.setContentCharset(params, charset);  
		        HttpConnectionParams.setConnectionTimeout(params,
		        		request_timeout);
				HttpConnectionParams.setSoTimeout(params, so_timeout);
		        return new DefaultHttpClient(params);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	//移动代理
	public static HttpClient getHttpClientTzyd(int request_timeout,int so_timeout,String charset) {
		 try { 
			 HttpHost proxy = new HttpHost("proxy-oa.zj.chinamobile.com", 8080);
			 
			 	HttpParams params = new BasicHttpParams();  
			 params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
		        HttpProtocolParams.setContentCharset(params, charset);  
		        HttpConnectionParams.setConnectionTimeout(params,
		        		request_timeout);
				HttpConnectionParams.setSoTimeout(params, so_timeout);
		        return new DefaultHttpClient(params);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	
	/**
	 * 获取链接
	 * @param request_timeout  设置请求超时
	 * @param so_timeout       设置等待数据超时时间
	 * @param charset          编码
	 * @return
	 */
	public static HttpClient getHttpClient(String charset) {
		 try { 
		        return getHttpClient(REQUEST_TIMEOUT,SO_TIMEOUT,charset);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	//移动代理
	public static HttpClient getHttpClientTzyd(String charset) {
		 try { 
		        return getHttpClientTzyd(REQUEST_TIMEOUT,SO_TIMEOUT,charset);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}
	/**
	 * 获取链接
	 * @param request_timeout  设置请求超时
	 * @param so_timeout       设置等待数据超时时间
	 * @param charset          编码
	 * @return
	 */
	public static HttpClient getHttpClient() {
		 try { 
		        return getHttpClient(REQUEST_TIMEOUT,SO_TIMEOUT,CHARSET);  
		    } catch (Exception e) {
		    	e.printStackTrace();
		        return new DefaultHttpClient();  
		    }  
	}

}
