package br.com.alura.escola.shared.dominio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.shared.dominio.CPF;

class CPFTest {

	@Test
	void naoDeveriaCriarCpfsInvalidos() {
		assertThrows(IllegalArgumentException.class, ()-> new CPF(null));
		assertThrows(IllegalArgumentException.class, ()-> new CPF(""));
		assertThrows(IllegalArgumentException.class, ()-> new CPF("123.456.789-00"));
		assertThrows(IllegalArgumentException.class, ()-> new CPF("123456789"));
		assertThrows(IllegalArgumentException.class, ()-> new CPF("12345678900"));
	}
	
	@Test
	void deveriaCriarCpfsValidos() {
		String numero = "85980554505";
		CPF cpf = new CPF(numero);		
		assertEquals(numero, cpf.getNumero());
	}

}
