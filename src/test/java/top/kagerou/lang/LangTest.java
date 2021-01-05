package top.kagerou.lang;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class LangTest {

    public static RestHighLevelClient client = null;

    public static void main(String[] args) throws Exception {
        // 配置服务器相关信息
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("qibao", "xiaohuli"));

        RestClientBuilder builder = RestClient.builder(new HttpHost("101.33.116.193", 9200))
                .setHttpClientConfigCallback(
                        // httpClientBuilder ->
                        // httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
                        // 更改为异步请求方式
                        new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(
                                    HttpAsyncClientBuilder httpAsyncClientBuilder) {
                                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        });

        RestHighLevelClient client = new RestHighLevelClient(builder);

        // 配置查询 有点上头
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.indices("setu");
        // searchRequest.types() types已经被废弃了
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("tags", "影之诗").operator(Operator.AND);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        // 获取结果 不出意外应该是json
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
        // 关闭client
        client.close();
    }
}
