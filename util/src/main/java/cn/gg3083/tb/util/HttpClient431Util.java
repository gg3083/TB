package cn.gg3083.tb.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpClient431Util {
    protected static final Logger log = LoggerFactory.getLogger(HttpClient431Util.class);
    //超时时间
    protected static final int TIME_OUT = 30 * 1000;

    private static final RequestConfig config;

    public static final String DEFAULT_SEND_CHARSET = "UTF-8";

    public static final String DEFAULT_RES_CHARSET = "UTF-8";

    static {
        config = RequestConfig.custom().setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
    }

    public static String doGet( Map<String, String> params,String url,String cookie) throws Exception{
        return doGet(params,url,DEFAULT_SEND_CHARSET,DEFAULT_RES_CHARSET,cookie);
    }

    public static String doGet( Map<String, String> params,String url) throws Exception{
        return doGet(params,url,DEFAULT_SEND_CHARSET,DEFAULT_RES_CHARSET,"");
    }
    public static String doPost(Map<String, String> params,String url) throws Exception{
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        headers.put("Connection" , "close");
        return doPost(params,url,DEFAULT_SEND_CHARSET,DEFAULT_RES_CHARSET , null );
    }

    public static String doPost(Map<String, String> params,String url , Map<String,String> headers) throws Exception{
        headers.put("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        headers.put("Connection" , "close");
        return doPost(params,url,DEFAULT_SEND_CHARSET,DEFAULT_RES_CHARSET , headers );
    }

    public static byte[] doDualSSLGet(Map<String, String> params,String url,String keyStorePath,String keyStorePass){
        return doDualSSLGet(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET, keyStorePath, keyStorePass);
    }

    public static String doDualSSLPost(Map<String, String> params,String url,String keyStorePath,String keyStorePass){
        return doDualSSLPost(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET, keyStorePath, keyStorePass);
    }

//    public static String doDualSSLMultipartPost(Map<String, Object> params,String url,String keyStorePath,String keyStorePass){
//        return doDualSSLMultipartPost(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET, keyStorePath, keyStorePass);
//    }

    public static String doPost(String dataContent,String url, Map<String, String> headers){
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection" , "close");
            if(headers != null && !headers.isEmpty()){
                for(Map.Entry<String,String> entry : headers.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        httpPost.addHeader( entry.getKey() , entry.getValue() );
                    }
                }
            }

            HttpEntity reqentity = new StringEntity(dataContent, DEFAULT_SEND_CHARSET);
            httpPost.setEntity(reqentity);

            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, DEFAULT_RES_CHARSET);
            }
            EntityUtils.consume(entity);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }
    /**
     * HTTP Get 获取内容
     * @param params 请求的参数
     * @param url  请求的url地址 ?之前的地址
     * @param reqCharset    编码格式
     * @param resCharset    编码格式
     * @return    页面内容
     */
    public static String doGet(Map<String,String> params,String url,String reqCharset,String resCharset,String cookie){
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, reqCharset==null?DEFAULT_SEND_CHARSET:reqCharset));
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpGet.addHeader("Connection" , "close");
            if (!Strings.isNotEmpty(cookie)){
                httpGet.setHeader("Cookie", cookie);
            }

            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, resCharset==null ? DEFAULT_RES_CHARSET:resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * HTTP Post 获取内容
     * @param params 请求的参数
     * @param url  请求的url地址 ?之前的地址
     * @param reqCharset    编码格式
     * @param resCharset    编码格式
     * @return    页面内容
     */
    public static String doPost(Map<String,String> params,String url,String reqCharset,String resCharset , Map<String,String> headers) throws Exception{
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection" , "close");
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,reqCharset==null?DEFAULT_SEND_CHARSET:reqCharset));
            }

            if(headers != null && !headers.isEmpty()){
                for(Map.Entry<String,String> entry : headers.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        httpPost.addHeader( entry.getKey() , entry.getValue() );
                    }
                }
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, resCharset==null?DEFAULT_RES_CHARSET:resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        }
        catch (Exception e) {
            throw e;
        }finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * HTTP Post 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param resCharset    编码格式
     * @return    页面内容

    public static String doPost(Map<String,String> params,String url,String reqCharset,String resCharset,String contentType) throws Exception{
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Content-Type",contentType);
            httpPost.addHeader("Connection" , "close");
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,reqCharset==null?DEFAULT_SEND_CHARSET:reqCharset));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new AppException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, resCharset==null?DEFAULT_RES_CHARSET:resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new AppException(e);
        } finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }*/

    public static String doPost(JSONObject jsonObject, String url, String resCharset) throws Exception{
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Content-Type","JSON");
            httpPost.addHeader("Connection" , "close");
            StringEntity myEntity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
            httpPost.setEntity(myEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, resCharset==null?DEFAULT_RES_CHARSET:resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    public static byte[] doDualSSLGet(Map<String,String> params,String url,String reqCharset,String resCharset,String keyStorePath,String keyStorePass){
        CloseableHttpClient httpClient = getDualSSLConnection(keyStorePath,keyStorePass);
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, reqCharset==null?DEFAULT_SEND_CHARSET:reqCharset));
            }

            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpGet.addHeader("Connection" , "close");

            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            byte[] result = null;
            if (entity != null){
                result = EntityUtils.toByteArray(entity);
            }
            EntityUtils.consume(entity);
            response.close();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException(String.format("status code:%s result:%s", statusCode, result));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }


    /**
     * HTTP Post 获取内容
     * @param params 请求的参数
     * @param url  请求的url地址 ?之前的地址
     * @param reqCharset    编码格式
     * @param resCharset    编码格式
     * @return    页面内容
     */
    public static String doDualSSLPost(Map<String,String> params,String url,String reqCharset,String resCharset,String keyStorePath,String keyStorePass){
        CloseableHttpClient httpClient = getDualSSLConnection(keyStorePath,keyStorePass);
        CloseableHttpResponse response = null;
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection" , "close");
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,reqCharset==null?DEFAULT_SEND_CHARSET:reqCharset));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, resCharset==null?DEFAULT_RES_CHARSET:resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            //有些接口错误信息返回的http status不是200
//            if (statusCode != 200) {
//                httpPost.abort();
//                throw new RuntimeException(String.format("status code:%s result:%s", statusCode, result));
//            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if(response!=null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }


    /**
     * 发送带文件的表单请求
     * @param params
     * @param url
     * @param reqCharset
     * @param resCharset
     * @param keyStorePath
     * @param keyStorePass
     * @return
     */
//    public static String doDualSSLMultipartPost(Map<String,Object> params,String url,String reqCharset,String resCharset,String keyStorePath,String keyStorePass){
////        reqCharset=resCharset==null?DEFAULT_RES_CHARSET:resCharset;
////        CloseableHttpClient httpClient = getDualSSLConnection(keyStorePath,keyStorePass);
////        CloseableHttpResponse response = null;
////        if(StringUtils.isBlank(url)){
////            return null;
////        }
////        try {
////            HttpPost httpPost = new HttpPost(url);
////            httpPost.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
////            httpPost.addHeader("Connection" , "close");
////
////            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create().setCharset(Charset.forName(reqCharset));
////            for (Map.Entry<String, Object> entry : params.entrySet()) {
////                if (entry.getValue() instanceof String) {
////                    entityBuilder.addBinaryBody(entry.getKey(), ((String) entry.getValue()).getBytes());
////                } else if (entry.getValue() instanceof File) {
////                    entityBuilder.addBinaryBody(entry.getKey(), (File) entry.getValue());
////                } else if (entry.getValue() instanceof byte[]) {
////                    entityBuilder.addBinaryBody(entry.getKey(), (byte[]) entry.getValue());
////                }
////            }
////
////            httpPost.setEntity(entityBuilder.build());
////            response = httpClient.execute(httpPost);
////            int statusCode = response.getStatusLine().getStatusCode();
////
////            HttpEntity entity = response.getEntity();
////            String result = null;
////            if (entity != null){
////                result = EntityUtils.toString(entity, resCharset==null?DEFAULT_RES_CHARSET:resCharset);
////            }
////            EntityUtils.consume(entity);
////            response.close();
////            //有些接口错误信息返回的http status不是200
//////            if (statusCode != 200) {
//////                httpPost.abort();
//////                throw new RuntimeException(String.format("status code:%s result:%s", statusCode, result));
//////            }
////            return result;
////        } catch (Exception e) {
////            throw new RuntimeException(e);
////        } finally{
////            if(response!=null)
////                try {
////                    response.close();
////                } catch (IOException e) {
////                }
////        }
////    }




    /**
     * 创建双向ssl的连接
     * @param keyStorePath
     * @param keyStorePass
     * @return
     * @throws RuntimeException
     */
    private static CloseableHttpClient getDualSSLConnection(String keyStorePath,String keyStorePass) throws RuntimeException{
        CloseableHttpClient httpClient = null;
        try {
            File file = new File(keyStorePath);
            URL sslJksUrl = file.toURI().toURL();
            KeyStore keyStore  = KeyStore.getInstance("jks");
            InputStream is = null;
            try {
                is = sslJksUrl.openStream();
                keyStore.load(is, keyStorePass != null ? keyStorePass.toCharArray(): null);
            } finally {
                if (is != null) is.close();
            }
            SSLContext sslContext = new SSLContextBuilder().loadKeyMaterial(keyStore, keyStorePass != null ? keyStorePass.toCharArray(): null)
                    .loadTrustMaterial(null,new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate,
                                                 String paramString) throws CertificateException {
                            return true;
                        }
                    })
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClient =  HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
            return httpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * 创建单向ssl的连接
     * @return
     * @throws RuntimeException
     */
    private static CloseableHttpClient getSingleSSLConnection() throws RuntimeException{
        CloseableHttpClient httpClient = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate,
                                         String paramString) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            httpClient =  HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
            return httpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String sendXmlData(String url,Map<String,String> params) throws Exception{
        HttpClient client = HttpClients.createDefault();
//        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);
        List<NameValuePair> pairs = null;
        if(params != null && !params.isEmpty()){
            pairs = new ArrayList<NameValuePair>(params.size());
            for(Map.Entry<String,String> entry : params.entrySet()){
                String value = entry.getValue();
                if(value != null){
                    pairs.add(new BasicNameValuePair(entry.getKey(),value));
                }
            }
        }
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type","text/xml;charset=GB2312");
        post.setEntity(new UrlEncodedFormEntity(pairs,"GB2312"));
        HttpResponse response = client.execute(post);
        Integer code = response.getStatusLine().getStatusCode();
        if(code == 200){
            String result = EntityUtils.toString(response.getEntity(), "GB18030");
            EntityUtils.consume(response.getEntity());
            post.abort();
            return result;
        }
        return "";
    }

    public static void main(String[] args) {
        testMsg();
//        JSONObject jsonObject = new JSONObject();
//        Map<String, String> params = new HashMap<String,String>();
////        params.put("access_token","tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVoyQpdTeMp8AgYVPcAHACZT");
//        jsonObject.put("type","news");
//        jsonObject.put("offset","0");
//        jsonObject.put("count","20");
//        try {
//            String result = HttpClient431Util.doPost(jsonObject,"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+
//                    "tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVoyQpdTeMp8AgYVPcAHACZT","UTF-8");
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void testMsg(){
        JSONObject jsonObject = new JSONObject();
        Map<String, String> params = new HashMap<String,String>();
//        params.put("access_token","tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVoyQpdTeMp8AgYVPcAHACZT");
        jsonObject.put("media_id","OlgPH4Gv2U2fjFhsG70xXxP9-mwILh0HAAkzAWQrQiQ");
        try {
            String result = HttpClient431Util.doPost(jsonObject,"https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+
                    "tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVoyQpdTeMp8AgYVPcAHACZT","UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
