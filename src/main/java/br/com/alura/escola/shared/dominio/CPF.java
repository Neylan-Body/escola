package br.com.alura.escola.shared.dominio;

public class CPF extends ValidaCPF{
	private String numero;

	public CPF(String numero) {
		if(numero ==  null || !isCPF(numero)) {
			throw new IllegalArgumentException("CPF Invalido!");
		}
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return numero;
	}
	
}
