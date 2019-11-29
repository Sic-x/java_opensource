import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 *  编写工具
 */
public class ESClientUtil {

    public static TransportClient getClient(){
        //非集群此处不要 settings改为Settings.EMPTY
        Settings settings = Settings.builder()
        .put("cluster.name","my-ealsticsearch")
        .put("client.transport.sniff", true).build();
        
        TransportClient client = null;
        //--------------------------------------------------
        try {
            //非集群传入Settings.EMPTY
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(
                    		new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9303));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
}