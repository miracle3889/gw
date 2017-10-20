package oracle_arena;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by xudabiao on 2017/2/24.
 */
public class HttpClientTutorial {

    private static String url = "http://www.baidu.com/";

    public static void main(String[] args) {
        try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            try(CloseableHttpResponse response = httpclient.execute(httpget)){
                //��ȡ��Ӧʵ��
                HttpEntity entity = response.getEntity();
                //��Ӧ״̬
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    //���ݳ���
                    System.out.println("Response content length: " + entity.getContentLength());
                    //��Ӧ����
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
