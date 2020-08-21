package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.domain.Pet;
import br.com.petz.exception.AppMessage;
import br.com.petz.exception.GenericException;
import br.com.petz.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	@Override
	public Optional<Pet> salvar(Pet pet) {
		return Optional.ofNullable(petRepository.save(pet));
	}
	
	@Override
	public Optional<Pet> atualizar(Pet pet, String id) {
		if (!pet.getId().equals(Long.valueOf(id))) {
			throw new GenericException(AppMessage.builder()
					.message("ids divergentes.")
					.build());
		}
		
		return Optional.ofNullable(petRepository.save(pet));
	}

	@Override
	public Optional<Pet> buscarPetById(String id) {
		return petRepository.findById(Long.valueOf(id));
	}

	@Override
	public Optional<List<Pet>> buscarPets() {
		return Optional.ofNullable(petRepository.findAll());
	}

	@Override
	public void excluirPetById(String id) {
		petRepository.deleteById(Long.valueOf(id));
	}
}
