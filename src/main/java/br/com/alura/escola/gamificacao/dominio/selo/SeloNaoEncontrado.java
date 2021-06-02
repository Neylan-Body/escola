package br.com.alura.escola.gamificacao.dominio.selo;

import br.com.alura.escola.shared.dominio.CPF;

public class SeloNaoEncontrado extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public SeloNaoEncontrado(CPF cpf) {
		super("Selo não encontrado com CPF: " + cpf.getNumero());
	}

}
