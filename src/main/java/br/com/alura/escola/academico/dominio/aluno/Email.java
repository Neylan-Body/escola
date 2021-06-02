package br.com.alura.escola.academico.dominio.aluno;

public class Email {
	
	private String endereco;
	
	public Email(String endereco) {
		if(endereco == null || !endereco.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			throw new IllegalArgumentException("E-mail Invalido!");
		}
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}
	
	
}
