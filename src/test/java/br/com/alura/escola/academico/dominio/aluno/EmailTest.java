package br.com.alura.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailTest {

	@Test
	void naoDeveriaCriarEmailComEnderecosInvalidos() {
		assertThrows(IllegalArgumentException.class, ()-> new Email(null));
		assertThrows(IllegalArgumentException.class, ()-> new Email(""));
		assertThrows(IllegalArgumentException.class, ()-> new Email("emailInvalido"));
		assertThrows(IllegalArgumentException.class, ()-> new Email("@gmail.com"));
		assertThrows(IllegalArgumentException.class, ()-> new Email("@gmail"));
		assertThrows(IllegalArgumentException.class, ()-> new Email("teste@gmail"));
	}
	
	@Test
	void deveriaCriarEmailComEnderecosValidos() {
		String endereco = "neylan@gmail.com";
		Email email = new Email(endereco);
		assertEquals(endereco, email.getEndereco());
	}

}
