package br.com.alura.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.Email;
import br.com.alura.escola.academico.dominio.aluno.TelefoneMaximoException;
import br.com.alura.escola.shared.dominio.CPF;

class AlunoTest {

	private Aluno aluno;
	
	@BeforeEach
	void beforeEach() {
		String numero = "62495120040";
		CPF cpf = new CPF(numero);
		String endereco = "teste@gmail.com";
		Email email = new Email(endereco);
		this.aluno = new Aluno(cpf, "teste", email);
	}
	
	@Test 
	void deveriaAceitarUmTelefone() {
		String ddd = "11";
		String numero = "123456789";
		this.aluno.adicionarTelefone(ddd, numero);
		assertEquals(1, this.aluno.getTelefones().size());
	}
	
	@Test 
	void deveriaAceitarDoisTelefones() {
		String ddd1 = "11";
		String numero1 = "123456789";
		String ddd2 = "12";
		String numero2 = "123456780";
		this.aluno.adicionarTelefone(ddd1, numero1);
		this.aluno.adicionarTelefone(ddd2, numero2);
		assertEquals(2, this.aluno.getTelefones().size());
	}
	
	@Test 
	void naoDeveriaAceitarTresTelefones() {
		String ddd1 = "11";
		String numero1 = "123456789";
		String ddd2 = "12";
		String numero2 = "123456780";
		String ddd3 = "13";
		String numero3 = "123456781";
		assertThrows(TelefoneMaximoException.class, ()->{
			this.aluno.adicionarTelefone(ddd1, numero1);
			this.aluno.adicionarTelefone(ddd2, numero2);
			this.aluno.adicionarTelefone(ddd3, numero3);
		});
	}

}
