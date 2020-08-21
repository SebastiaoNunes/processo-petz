package br.com.petz.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.petz.domain.Cliente;
import br.com.petz.domain.Pet;
import br.com.petz.dto.DadosCliente;
import br.com.petz.dto.DadosPet;

@Component
public class ClienteMapper {
	
	public Cliente toEntity(DadosCliente dadosCliente) {
		return Cliente.builder()
				.id(dadosCliente.getId())
				.nome(dadosCliente.getNome())
				.idade(dadosCliente.getIdade())
				.pets(this.toEntityPets(dadosCliente.getPets()))
				.build();
	}
	
	public DadosCliente toDto(Cliente cliente) {
		return DadosCliente.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.idade(cliente.getIdade())
				.pets(toPets(cliente.getPets()))
				.build();
	}
	
	public List<DadosCliente> toDtoList(List<Cliente> clientes) {
		return clientes.stream().map(cliente -> {
			return DadosCliente.builder()
			.id(cliente.getId())
			.nome(cliente.getNome())
			.idade(cliente.getIdade())
			.build();
		}).collect(Collectors.toList());
	}
	
	private List<DadosPet> toPets(List<Pet> pets) {
		if (pets == null || pets.isEmpty()) {
			return Collections.emptyList();
		}
		
		return pets.stream().map(pet -> DadosPet.builder()
				.id(pet.getId())
				.idade(pet.getIdade())
				.nome(pet.getNome())
				.build())
				.collect(Collectors.toList());
	}
	
	private List<Pet> toEntityPets(List<DadosPet> pets) {
		if (pets == null || pets.isEmpty()) {
			return Collections.emptyList();
		}
		
		return pets.stream().map(pet -> Pet.builder()
				.id(pet.getId())
				.nome(pet.getNome())
				.idade(pet.getIdade())
				.build())
				.collect(Collectors.toList());
	}
}
