package com.sulongx.util.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wang.jingyu
 * @date 2018年10月20日下午1:17:47
 */
public class HttpClientUtils {

    static PoolingHttpClientConnectionManager manager = null;

    static CloseableHttpClient httpClient = null;

    public static synchronized CloseableHttpClient getHttpClient() {
        if(httpClient == null) {
            //注册访问协议相关的Socket工厂
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                    .build();
            //HttpConnnection工厂:配置写请求/解析响应处理器
            HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory =  new ManagedHttpClientConnectionFactory(
                    DefaultHttpRequestWriterFactory.INSTANCE,
                    DefaultHttpResponseParserFactory.INSTANCE);
            //DNS解析器
            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
            //创建池化连接管理器
            manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory, dnsResolver);
            //默认Socket配置
            SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            manager.setDefaultSocketConfig(defaultSocketConfig);
            //设置整个连接池的最大数
            manager.setMaxTotal(1000);
            //每个路由的默认最大连接，每个路由实际最大连接数默认为DafaultMaxPerRoute控制，而MaxRoute是控制整个池子最大数
            //设置过小无法支持大并发(ConnectionPoolTimeoutException:Timeout waiting for connection from pool)
            //路由是对maxTotal的细分
            manager.setDefaultMaxPerRoute(1000);
            //在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
            manager.setValidateAfterInactivity(5000);
            //默认请求配置
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    //设置连接超时时间
                    .setConnectionRequestTimeout(2000)
                    //设置等待数据超时时间
                    .setSocketTimeout(5000)
                    //设置从连接池获取连接的等待超时时间
                    .setConnectionRequestTimeout(2000)
                    .build();
            //创建HttpClient
            httpClient = HttpClients.custom()
                    .setConnectionManager(manager)
                    //连接池不是共享模式
                    .setConnectionManagerShared(false)
                    //定期回收空闲连接
                    .evictIdleConnections(60,  TimeUnit.SECONDS)
                    //定期回收过期连接
                    .evictExpiredConnections()
                    //连接存活时间，如果不设置，则根据长连接信息决定
                    .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                    //设置默认请求配置
                    .setDefaultRequestConfig(defaultRequestConfig)
                    //连接重用策略,即是否能keepAlive
                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                    //长连接配置,即获取长连接生产多长时间
                    .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                    //设置重试次数,默认是3次;当前是禁用掉,可根据需要开启
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                    .build();
            //JVM停止或重启时,关闭连接池释放掉连接
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return httpClient;
    }

    public static JSONObject invokePostMethodWithMap(String url,Map<String, String> paramsMap) {
        JSONObject json = new JSONObject();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : paramsMap.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = getHttpClient().execute(post);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                EntityUtils.consume(response.getEntity());
            }else {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                json = JSONObject.parseObject(result);
            }
        } catch (Exception ex) {
            if(response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }

    public static String invokePostMethodWithString(String url, String paramsString) {
        String result = null;
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            StringEntity stringEntity = new StringEntity(paramsString,"UTF-8");
            post.setEntity(stringEntity);
            response = getHttpClient().execute(post);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                EntityUtils.consume(response.getEntity());
            }else {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception ex) {
            if(response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static JSONObject invokeGetMethodWithMap(String url,Map<String,String> paramsMap){
        JSONObject json = new JSONObject();
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : paramsMap.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            HttpGet get = new HttpGet(builder.build());
            response = getHttpClient().execute(get);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                EntityUtils.consume(response.getEntity());
            }else {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                json = JSONObject.parseObject(result);
            }
        } catch (Exception ex) {
            if(response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }

}
