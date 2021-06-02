package br.com.alura.escola.academico.dominio.aluno;

public class TelefoneMaximoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public TelefoneMaximoException() {
		super("Aluno só pode ter no maximo 2 Telefones");
	}
}
