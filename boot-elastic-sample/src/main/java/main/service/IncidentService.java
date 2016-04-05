package main.service;

import java.util.Collection;

import main.dao.IncidentRepository;
import main.model.Incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IncidentService {

	@Autowired
	private IncidentRepository repository;

	public void addIncidents(Collection<Incident> incidents) {
		repository.save(incidents);
	}
}
