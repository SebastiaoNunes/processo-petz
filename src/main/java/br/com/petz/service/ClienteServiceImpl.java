package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.domain.Cliente;
import br.com.petz.exception.AppMessage;
import br.com.petz.exception.GenericException;
import br.com.petz.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Optional<Cliente> salvar(Cliente cliente) {
		return Optional.ofNullable(clienteRepository.save(cliente));
	}
	
	@Override
	public Optional<Cliente> atualizar(Cliente aluno, String id) {
		if (!aluno.getId().equals(Long.valueOf(id))) {
			throw new GenericException(AppMessage.builder()
					.message("Cliente com id divergentes.")
					.build());
		}
		
		return Optional.ofNullable(clienteRepository.save(aluno));
	}

	@Override
	public Optional<Cliente> buscarClienteById(String id) {
		return clienteRepository.findById(Long.valueOf(id));
	}

	@Override
	public Optional<List<Cliente>> buscarClientes() {
		return Optional.ofNullable(clienteRepository.findAll());
	}

	@Override
	public void excluirClienteById(String id) {
		clienteRepository.deleteById(Long.valueOf(id));
	}
}
