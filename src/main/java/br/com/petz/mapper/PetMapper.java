package br.com.petz.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.petz.domain.Pet;
import br.com.petz.dto.DadosPet;

@Component
public class PetMapper {
	
	public Pet toEntity(DadosPet dadosPet) {
		return Pet.builder()
				.id(dadosPet.getId())
				.nome(dadosPet.getNome())
				.idade(dadosPet.getIdade())
				.build();
	}
	
	public DadosPet toDto(Pet pet) {
		return DadosPet.builder()
				.id(pet.getId())
				.nome(pet.getNome())
				.idade(pet.getIdade())
				.build();
	}
	
	public List<DadosPet> toDtoList(List<Pet> pets) {
		return pets.stream().map(pet -> {
			return DadosPet.builder()
			.id(pet.getId())
			.nome(pet.getNome())
			.idade(pet.getIdade())
			.build();
		}).collect(Collectors.toList());
	}
}
