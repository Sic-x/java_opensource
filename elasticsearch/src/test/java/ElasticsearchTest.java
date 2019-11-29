import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class ElasticsearchTest {

    /**
     * 添加文档
     * @throws Exception
     */
    @Test
    public void testAdd() throws Exception {
        //获取客户端对象
        TransportClient client = ESClientUtil.getClient();
        //创建索引
        IndexRequestBuilder index = client.prepareIndex("class0704", "students", "1");
        Map<String, String> map = new HashMap<>();
        map.put("username","勇勇");
        map.put("id","1");
        map.put("age","21");
        //获取响应
        System.out.println(index.setSource(map).get());
        client.close();
    }


    /**
     * client.prepareGet("class0704","students", "1").get()不能够直接打印
     * GetResponse getFields
     * 需要使用getFields.getSource()获取数据源方能打印
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        //获取客户端对象
        TransportClient client = ESClientUtil.getClient();
        System.out.println(client.prepareGet("class0704","students", "1").get().getSource());
        client.close();
    }


    @Test
    public void testDelete() throws Exception {
        //获取客户端对象
        TransportClient client = ESClientUtil.getClient();
        DeleteResponse response = client.prepareDelete("class0704", "students", "2").get();
        System.out.println(response);
        client.close();
    }


    @Test
    public void testBulkAdd() throws Exception {
        //获取客户端对象
        TransportClient client = ESClientUtil.getClient();
        BulkRequestBuilder bulk = client.prepareBulk();

        Map<String,String> map = new HashMap<>();
        map.put("id","3");
        map.put("username","伟伟");
        map.put("age","29");
        bulk.add(client.prepareIndex("class0704", "students", "3").setSource(map));

        Map<String,String> map1 = new HashMap<>();
        map.put("id","4");
        map.put("username","天天");
        map.put("age","19");
        bulk.add(client.prepareIndex("class0704", "students", "4").setSource(map1));

        BulkResponse response = bulk.get();
        //获取迭代器
        Iterator<BulkItemResponse> iterator = response.iterator();
        while(iterator.hasNext()){
            BulkItemResponse next = iterator.next();
            System.out.println(next.getResponse());
        }
        client.close();
    }




}