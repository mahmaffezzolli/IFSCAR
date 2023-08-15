package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Carona;
import modelo.IPessoaDAO;
import modelo.Pessoa;

public class PessoaDAO implements IPessoaDAO {

	private static PessoaDAO instancia;

	private PessoaDAO() {

	}

	public static PessoaDAO getInstancia() {
		if (instancia == null) {
			instancia = new PessoaDAO();
		}
		return instancia;
	}

	@Override
	public boolean cadastrarPessoa(Pessoa pessoa) {
		ConexaoBanco c = ConexaoBanco.getInstancia();

		Connection con = c.conectar();

		String query = "INSERT INTO pessoa " + "(nome, sobrenome, cpf, dataNasc, senha, email) " + "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getSobrenome());
			ps.setLong(3, pessoa.getCpf());
			ps.setDate(4, Date.valueOf(pessoa.getDataNasc()));
			ps.setString(5, pessoa.getSenha());
			ps.setString(6, pessoa.getEmail());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean alterarPessoa(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletarPessoa(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return false;
	}

    public ArrayList<Carona> listarPessoa(Pessoa pessoa) {

		
		ConexaoBanco c = ConexaoBanco.getInstancia();
		
		Connection con = c.conectar();
		
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		
		String query = "SELECT * FROM pessoa";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Long cpf = rs.getLong("cpf");
				String nome = rs.getString("nome");
				String sobrenome = rs.getString("sobrenome");
				String email = rs.getString("email");
     			//LocalDate dataNasc = rs.getLocalDate(Date.valueOf(pessoa.getDataNasc()));
				String senha = rs.getString("senha");
				
				
				Pessoa p = new Pessoa();
				p.setCpf(cpf);
				p.setNome(nome);
				p.setSobrenome(sobrenome);
				p.setEmail(email);
//				p.setDataNasc(dataNasc);
				p.setSenha(senha);
				
				pessoas.add(p);
			}
			
		}catch(SQLException e ) {
			
			e.printStackTrace();
		}
		
		c.fecharConexao();
		
		return null;
	}


}