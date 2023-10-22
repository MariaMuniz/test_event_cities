package com.devsuperior.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;


@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	public EventService service;
	
	@GetMapping(value= "/{id}")	
    public ResponseEntity<EventDTO>findById(@PathVariable Long id){
		EventDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	
	
	@PutMapping(value= "/{id}")	
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
 dto = service.update(id, dto);
 return ResponseEntity.ok().body(dto);
}

}