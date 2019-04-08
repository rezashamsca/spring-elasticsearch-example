package com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.repository;

import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.domain.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticSearchEventRepository extends ElasticsearchRepository<Event, String> {
}
