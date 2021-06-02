package br.com.alura.escola;

import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAluno;
import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAlunoDto;
import br.com.alura.escola.academico.dominio.aluno.LogDeAlunoMatriculado;
import br.com.alura.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import br.com.alura.escola.gamificacao.aplicacao.GeraSeloAlunoNovato;
import br.com.alura.escola.gamificacao.infra.selo.RepositorioDeSelosEmMemoria;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEvento;

public class MatricularAlunoPorLinhaDeComando {

	public static void main(String[] args) {
		String nome = "Neylan oliveira";
		String cpf = "85980554505";
		String email = "neylan@gmail.com";
		
		PublicadorDeEvento publicadorDeEvento = new PublicadorDeEvento();
		publicadorDeEvento.adicionar(new LogDeAlunoMatriculado());
		publicadorDeEvento.adicionar(new GeraSeloAlunoNovato(new RepositorioDeSelosEmMemoria()));
		
		MatricularAluno matricularAluno = new MatricularAluno(new RepositorioDeAlunosEmMemoria(), publicadorDeEvento);
		matricularAluno.executa(new MatricularAlunoDto(nome, cpf, email));
	}

}
