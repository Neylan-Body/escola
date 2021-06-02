package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAluno;
import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAlunoDto;
import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.LogDeAlunoMatriculado;
import br.com.alura.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEvento;

class MatricularAlunoTest {

	@Test
	void alunoDeveriaSerPersistido() {
		RepositorioDeAlunosEmMemoria repositorioDeAlunosEmMemoria = new RepositorioDeAlunosEmMemoria();
		
		PublicadorDeEvento publicadorDeEvento = new PublicadorDeEvento();
		publicadorDeEvento.adicionar(new LogDeAlunoMatriculado());
		
		MatricularAluno useCase = new MatricularAluno(repositorioDeAlunosEmMemoria, publicadorDeEvento);
		MatricularAlunoDto matricularAlunoDto = new MatricularAlunoDto("Fulano", "85980554505", "neylan@gmail.com");
		useCase.executa(matricularAlunoDto);
		
		Aluno encontrado = repositorioDeAlunosEmMemoria.buscarPorCPF(new CPF("85980554505"));
		assertEquals("Fulano", encontrado.getNome());
		assertEquals("85980554505", encontrado.getCpf().getNumero());
		assertEquals("neylan@gmail.com", encontrado.getEmail());
	}

}
