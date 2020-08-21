package br.com.petz.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DadosPet {
	
	private Long id;
	
	@NotNull
	private String nome;
	
	@Min(value = 8, message = "Idade nao pode ser menor que 8 anos")
    @Max(value = 150, message = "Idade nao pode ser maior que 80")
	private Integer idade;
}
