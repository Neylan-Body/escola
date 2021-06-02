package br.com.alura.escola.gamificacao.infra.selo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelos;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;
import br.com.alura.escola.shared.dominio.CPF;

public class RepositorioDeSelosEmMemoria implements RepositorioDeSelos{

	private List<Selo> selos = new ArrayList<Selo>();

	@Override
	public void adicionar(Selo selo) {
		this.selos.add(selo);		
	}

	@Override
	public List<Selo> selosDoAlunoDeCPF(CPF cpf) {
		return this.selos.stream()
				.filter(s -> s.getCpf().getNumero().equals(cpf.getNumero()))
				.collect(Collectors.toList());
		
	}
}
