package com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.controller;

import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.domain.Event;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.service.ElasticSearchTransportServiceImpl;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.service.ElasticsearchRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ElasticSearchController {
    @Autowired
    private ElasticSearchTransportServiceImpl esService;

    @Autowired
    private ElasticsearchRestServiceImpl esRestService;

    @GetMapping("/v1/es/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable("id") String id) {
        // Write defensive code for argument
        Event event = esService.findEventById(id);
        return ResponseEntity.ok().body(event);
    }

    @PostMapping("/v1/es/event")
    public ResponseEntity<Event> newEvent(@RequestBody Event newEvent) {
        Event event = esRestService.newEvent(newEvent);
        return ResponseEntity.ok(event);
    }
}
