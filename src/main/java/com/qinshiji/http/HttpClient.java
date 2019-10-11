package com.qinshiji.http;

import com.alibaba.fastjson.JSON;
import com.qinshiji.entity.Invocation;
import com.qinshiji.protocol.ProtocolClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;


/**
 * @author qinshiji
 * @date 2019/10/10 17:38
 */
public class HttpClient implements ProtocolClient {
    /**
     * 发送接口信息
     *
     * @param hostname
     * @param port
     * @param invocation
     * @return
     */
    @Override
    public String send(String hostname, Integer port, Invocation invocation) {
//        try {
//            HttpRequest http = HttpRequest.newBuilder().uri(new URI("http", null, hostname, port, "/", null, null))
//                    .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(invocation))).build();
//            java.net.http.HttpClient httpClient = java.net.http.HttpClient.newHttpClient();
//            HttpResponse<String> response = httpClient.send(http, HttpResponse.BodyHandlers.ofString());
//            return response.body();
//        } catch (URISyntaxException | InterruptedException | IOException e) {
//            e.printStackTrace();
//        }

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(new URI("http", null, hostname, port, "/", null, null));
            httpPost.setEntity(new StringEntity(JSON.toJSONString(invocation),"UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
