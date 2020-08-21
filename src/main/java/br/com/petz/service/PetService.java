package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import br.com.petz.domain.Pet;

public interface PetService {
	
	public Optional<Pet> salvar(Pet pet);
	public Optional<Pet> atualizar(Pet pet, String id);
	public Optional<Pet> buscarPetById(String id);
	public Optional<List<Pet>> buscarPets();
	public void excluirPetById(String id);
}
