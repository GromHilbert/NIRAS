package com.w3dai.DataRetrieval.Config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.w3dai.DataRetrieval.Repositories")
@ComponentScan(basePackages = { "com.w3dai.DataRetrieval.Service" })
public class Config {
    @Bean
    public RestHighLevelClient client(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        return  client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchRestTemplate() {

        return new ElasticsearchRestTemplate(client());

    }
}
