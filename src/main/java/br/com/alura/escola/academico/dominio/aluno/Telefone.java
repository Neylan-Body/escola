package br.com.alura.escola.academico.dominio.aluno;

public class Telefone {
	
	private String ddd;
	private String numero;
	
	
	public Telefone(String ddd, String numero) {
		if(ddd == null || numero == null ||
		!ddd.matches("\\d{2}") || 
		!numero.matches("\\d{8}|\\d{9}")) {
			throw new IllegalArgumentException("Telefone Invalido!");
		}
		this.ddd = ddd;
		this.numero = numero;
	}


	public String getDdd() {
		return ddd;
	}


	public String getNumero() {
		return numero;
	}
}
