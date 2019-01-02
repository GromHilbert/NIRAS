package com.w3dai.DataImport;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CreateIndexFromData {
    public void CreateIndex() throws IOException {
        //create the high level client of the localhost
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest("articles");

        request.settings(Settings.builder() // <1>
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );

        //please edit the following mapping on your needs.
        request.mapping("article", // <1>
                "{\n" +
                        "  \"article\": {\n" +
                        "    \"properties\": {\n" +
                        "      \"kicker\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-index\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      },\n" +
                        "      \"headline\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-index\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      },\n" +
                        "      \"subHeadline\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-index\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      },\n" +
                        "      \"authors\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-smart\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      },\n" +
                        "      \"editors\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-smart\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      },\n" +
                        "      \"sectionName\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\"\n"+
                        "      },\n" +
                        "      \"columnName\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\"\n"+
                        "      },\n" +
                        "      \"category\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-smart\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      },\n" +
                        "      \"type\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\"\n"+
                        "      },\n" +
                        "      \"publicationDate\": {\n" +
                        "        \"type\": \"date\",\n" +
                        "        \"format\":\"yyyyMMdd\"\n"+
                        "      },\n" +
                        "      \"body\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"true\",\n"+
                        "        \"fielddata\":\"true\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"hanlp-index\",\n" +
                        "         \"search_analyzer\": \"hanlp-smart\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);

        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isAcknowledged();

        System.out.println(acknowledged + "##" + shardsAcknowledged);

    }
}
