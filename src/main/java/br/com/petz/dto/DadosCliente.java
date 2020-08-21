package br.com.petz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DadosCliente {
	
	private Long id;
	
	@NotNull
	private String nome;
	
	@Min(value = 8, message = "Idade nao pode ser menor que 8 anos")
    @Max(value = 150, message = "Idade nao pode ser maior que 80")
	private Integer idade;
	
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY) 
	private List<DadosPet> pets;
}
