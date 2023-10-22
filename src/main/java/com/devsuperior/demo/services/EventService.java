package com.devsuperior.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly=true)
	public EventDTO findById(Long id) {
		Optional<Event> obj = repository.findById(id);
		Event entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not founld"));
		
		return new EventDTO(entity);
	}
	
	
	
	
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
			try {
				Event entity = repository.getReferenceById(id);
				entity.setName(dto.getName());
				entity.setUrl(dto.getUrl());
				entity.setDate(dto.getDate());
				entity.setId(dto.getId());
		        entity.setCity(cityRepository.getReferenceById(dto.getCityId()));
		    
			    entity = repository.save(entity);
			    return  new EventDTO(entity);
			}
			catch(EntityNotFoundException e) {
				
				throw new ResourceNotFoundException("Id not fould");
				}		
	}
	
	

}
	
	

