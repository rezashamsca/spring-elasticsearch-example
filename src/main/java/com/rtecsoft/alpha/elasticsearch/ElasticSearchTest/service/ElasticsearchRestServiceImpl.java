package com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.service;

import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.domain.Event;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.exception.EntityNotFoundException;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.util.JsonSerializerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchRestServiceImpl {
    private final RestHighLevelClient client;

    public Event newEvent(Event newEventName)  {
        IndexRequest indexRequest = new IndexRequest("event", "_doc");
        XContentBuilder builder;
        try {
            builder = jsonBuilder()
                    .startObject()
                    .field("name", newEventName.getName())
                    .field("startDate", JsonSerializerUtil.jsonDateTime(newEventName.getStartDate()))
                    .endObject();
            log.info(Strings.toString(builder));
            indexRequest.source(builder);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            if (indexResponse.status().getStatus() == 201) {
                newEventName.set_id(indexResponse.getId());
                return newEventName;
            }
            else {
                throw new EntityNotFoundException(newEventName);
            }
        }
        catch (IOException e) {
            throw new EntityNotFoundException(newEventName);
        }
    }
}
