package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import br.com.petz.domain.Cliente;

public interface ClienteService {
	
	public Optional<Cliente> salvar(Cliente aluno);
	public Optional<Cliente> atualizar(Cliente aluno, String id);
	public Optional<Cliente> buscarClienteById(String id);
	public Optional<List<Cliente>> buscarClientes();
	public void excluirClienteById(String id);
}
