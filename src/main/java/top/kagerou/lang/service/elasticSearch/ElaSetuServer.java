package top.kagerou.lang.service.elasticSearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElaSetuServer {

    private static final String INDEX = "setu";

    private RestHighLevelClient client;

    @Autowired
    public ElaSetuServer(RestHighLevelClient client) {
        this.client = client;
    }

    public SearchResponse searchImagByTags(String tags) {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.indices(INDEX);
        // searchRequest.types() types已经被废弃了
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("tags", tags).operator(Operator.AND);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        // 获取结果 不出意外应该是json
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (Exception e) {
            log.error("这里出错了！" + e.getMessage(), 0);
            return null;
        }
    }
}
