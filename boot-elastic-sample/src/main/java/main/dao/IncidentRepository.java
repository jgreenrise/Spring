package main.dao;

import main.model.Incident;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface IncidentRepository extends ElasticsearchRepository<Incident, Long> {
}
