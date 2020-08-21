package br.com.petz.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.petz.domain.Pet;
import br.com.petz.dto.DadosPet;
import br.com.petz.dto.Response;
import br.com.petz.mapper.PetMapper;
import br.com.petz.service.PetService;

@Controller
@RequestMapping("/api/v1/pets")
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private PetMapper petMapper;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody ResponseEntity<Response<?>> save(@Valid @RequestBody DadosPet petDTO) {
		Optional<Pet> pet = petService.salvar(petMapper.toEntity(petDTO));
		
		if (!pet.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(Response.create("Erro ao salvar pet"));
		}
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Response.create(petMapper.toDto(pet.get()), "Pet salvo com sucesso."));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<?>> getOne(@PathVariable("id") String id) {
		Optional<Pet> pet = petService.buscarPetById(id);
		
		if (!pet.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Pet nao encontrado"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(petMapper.toDto(pet.get()), "Pet recuperado com sucesso."));
	}
	
	@GetMapping
	public ResponseEntity<Response<?>> getAll() {
		Optional<List<Pet>> pets = petService.buscarPets();
		
		if (!pets.isPresent() || pets.get().isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Nao existem pets cadastrados"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(petMapper.toDtoList(pets.get()), "Pets recuperados com sucesso."));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<?>> delete(@PathVariable("id") String id) {
		Optional<Pet> pet = petService.buscarPetById(id);
		
		if (!pet.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Pet nao encontrado"));
		}
		
		petService.excluirPetById(id);
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(Response.create("Pet excluido com sucesso."));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<?>> update(@RequestBody DadosPet petDTO, @PathVariable("id") String id) {
		Optional<Pet> pet = petService.atualizar(petMapper.toEntity(petDTO), id);
		
		if (!pet.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(Response.create("Erro ao atualizar pet"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(petMapper.toDto(pet.get()), "Cliente atualizado com sucesso."));
	}
}
