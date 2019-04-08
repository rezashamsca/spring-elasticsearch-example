package com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.service;

import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.domain.Event;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.exception.EntityNotFoundException;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.repository.ElasticSearchEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ElasticSearchTransportServiceImpl {
    private final ElasticSearchEventRepository repository;

    @Autowired
    ElasticSearchTransportServiceImpl(ElasticSearchEventRepository repository) {
        Assert.notNull(repository, "Repository must not be null!");
        this.repository = repository;
    }

    public Event findEventById(String id) {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id + "not found`"));
    }
}
