package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.AlunoMatriculado;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEvento;

public class MatricularAluno {

	private final RepositorioDeAlunos repositorioDeAlunos;
	private final PublicadorDeEvento publicadorDeEvento;
	
	public MatricularAluno(RepositorioDeAlunos repositorioDeAlunos, PublicadorDeEvento publicadorDeEvento) {
		this.repositorioDeAlunos = repositorioDeAlunos;
		this.publicadorDeEvento = publicadorDeEvento;
	}
	
	public void executa(MatricularAlunoDto dados) {
		Aluno novo = dados.criarAluno();
		repositorioDeAlunos.matricular(novo);
		AlunoMatriculado alunoMatriculado = new AlunoMatriculado(novo.getCpf());
		publicadorDeEvento.publicar(alunoMatriculado);
	}
}
