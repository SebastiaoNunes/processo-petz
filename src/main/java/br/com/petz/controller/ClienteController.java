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

import br.com.petz.domain.Cliente;
import br.com.petz.dto.DadosCliente;
import br.com.petz.dto.Response;
import br.com.petz.mapper.ClienteMapper;
import br.com.petz.service.ClienteService;

@Controller
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<DadosCliente>> save(@Valid @RequestBody DadosCliente clienteDTO) {
		Optional<Cliente> cliente = clienteService.salvar(clienteMapper.toEntity(clienteDTO));
		
		if (!cliente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(Response.create("Erro ao salvar cliente"));
		}
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Response.create(clienteMapper.toDto(cliente.get()), "Cliente salvo com sucesso."));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<?>> getOne(@PathVariable("id") String id) {
		Optional<Cliente> cliente = clienteService.buscarClienteById(id);
		
		if (!cliente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Cliente nao encontrado"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(clienteMapper.toDto(cliente.get()), "Cliente recuperado com sucesso."));
	}
	
	@GetMapping
	public ResponseEntity<Response<?>> getAll() {
		Optional<List<Cliente>> clientes = clienteService.buscarClientes();
		
		if (!clientes.isPresent() || clientes.get().isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Nao existem clientes cadastrados"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(clienteMapper.toDtoList(clientes.get()), "Cliente recuperados com sucesso."));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<?>> delete(@PathVariable("id") String id) {
		Optional<Cliente> cliente = clienteService.buscarClienteById(id);
		
		if (!cliente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Cliente nao encontrado"));
		}
		
		clienteService.excluirClienteById(id);
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(Response.create("Cliente excluido com sucesso."));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<?>> update(@RequestBody DadosCliente clienteDTO, @PathVariable("id") String id) {
		Optional<Cliente> cliente = clienteService.atualizar(clienteMapper.toEntity(clienteDTO), id);
		
		if (!cliente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(Response.create("Erro ao atualizar cliente"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(clienteMapper.toDto(cliente.get()), "Cliente atualizado com sucesso."));
	}
}
