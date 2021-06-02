package br.com.alura.escola.academico.infra.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.AlunoNaoEncontrado;
import br.com.alura.escola.academico.dominio.aluno.Email;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.academico.dominio.aluno.Telefone;
import br.com.alura.escola.shared.dominio.CPF;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAlunos {

	private final Connection connection;
	
	public RepositorioDeAlunosComJDBC(Connection connection) {
		this.connection =  connection;
	}
	
	@Override
	public void matricular(Aluno aluno) {
		try {
			String sql = "INSERT INTO ALUNO VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, aluno.getCpf().getNumero());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.execute();
			
			sql = "INSERT INTO TELEFONE VALUES (?,?)";
			ps = connection.prepareStatement(sql);
			for (Telefone telefone : aluno.getTelefones()) {
				ps.setString(1, telefone.getDdd());
				ps.setString(2, telefone.getNumero());
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Aluno buscarPorCPF(CPF cpf) {
		String sql = "SELECT id,nome,email FROM ALUNO WHERE cpf = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cpf.getNumero());
			
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				throw new AlunoNaoEncontrado(cpf);
			}
			Email email = new Email(rs.getString("email"));
			Aluno aluno = new Aluno(cpf, rs.getString("nome"), email); 
			
			Long id = rs.getLong("id");
			sql = "SELECT ddd,numero FROM TELEFONE WHERE aluno_id = ?";
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				String numero = rs.getString("numero");
				String ddd = rs.getString("ddd");
				aluno.adicionarTelefone(ddd, numero);
			}
			rs.close();
			ps.close();
			return aluno;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Aluno> listarTodosAlunosMatriculados() {
		try {
			String sql = "SELECT id,nome,email FROM ALUNO";
			PreparedStatement psAluno = connection.prepareStatement(sql);			
			ResultSet rsAluno = psAluno.executeQuery();
			List<Aluno> alunos = new ArrayList<>();
			while (rsAluno.next()) {
				CPF cpf = new CPF(rsAluno.getString("cpf"));
				Email email = new Email(rsAluno.getString("email"));
				Aluno aluno = new Aluno(cpf, rsAluno.getString("nome"), email);
				
				Long id = rsAluno.getLong("id");
				sql = "SELECT ddd,numero FROM TELEFONE WHERE aluno_id = ?";
				PreparedStatement psTelefone = connection.prepareStatement(sql);
				psTelefone.setLong(1, id);
				ResultSet rsTelefone = psTelefone.executeQuery();
				while(rsTelefone.next()) {
					String numero = rsTelefone.getString("numero");
					String ddd = rsTelefone.getString("ddd");
					aluno.adicionarTelefone(ddd, numero);
				}
				rsTelefone.close();
				psTelefone.close();
				alunos.add(aluno);
			}
			rsAluno.close();
			psAluno.close();
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
