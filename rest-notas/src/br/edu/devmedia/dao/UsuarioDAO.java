package br.edu.devmedia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.devmedia.config.BDConfig;
import br.edu.devmedia.entidade.Usuario;

public class UsuarioDAO {

	public List<Usuario> listarUsuario() throws Exception {
		List<Usuario> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM USUARIO";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Usuario usuario = new Usuario();

			usuario.setId(rs.getInt("ID_USUARIO"));
			usuario.setNome(rs.getString("NOME_USUARIO"));

			lista.add(usuario);
		}
		return lista;
	}

	public Usuario buscarUsuarioPorId(int idUsuario) throws Exception {
		Usuario usuario = null;

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			usuario = new Usuario();
			usuario.setId(rs.getInt("ID_USUARIO"));
			usuario.setNome(rs.getString("NOME_USUARIO"));

		}
		return usuario;
	}

	public int addUsuario(Usuario usuario) throws Exception {
		int idGerado = 0;
		Connection conexao = BDConfig.getConnection();

		String sql = "INSERT INTO USUARIO (NOME_USUARIO) VALUES (?)";

		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, usuario.getNome());
		statement.executeUpdate();

		ResultSet rs = statement.getGeneratedKeys();

		if (rs.next()) {
			idGerado = rs.getInt(1);

		}
		return idGerado;

	}
	
	public void editarUsuario (Usuario usuario, int idUsuario) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "UPDATE USUARIO SET NOME_USUARIO = ? WHERE ID_USUARIO = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, usuario.getNome());
		statement.setInt(2, idUsuario);
		statement.executeUpdate();
		
	}
	public void removerUsuario (int idUsuario) throws Exception{
		Connection conexao = BDConfig.getConnection();
		
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setInt(1, idUsuario);
		statement.execute();
		
	}
}
