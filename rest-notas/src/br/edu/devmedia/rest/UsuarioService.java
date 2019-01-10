package br.edu.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.devmedia.dao.UsuarioDAO;
import br.edu.devmedia.entidade.Usuario;

@Path("/usuario")
public class UsuarioService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private UsuarioDAO usuarioDAO;

	@PostConstruct
	private void init() {
		usuarioDAO = new UsuarioDAO();

	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuario() {
		List<Usuario> lista = null;
		try {
			lista = usuarioDAO.listarUsuario();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUsuario(Usuario usuario) {
		String msg = "";

		System.out.println(usuario.getNome());

		try {
			int idGerado = usuarioDAO.addUsuario(usuario);
			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao add usuário";
			e.printStackTrace();
		}
		return msg;
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario buscarPorId(@PathParam("id") int idUsuario) {
		Usuario usuario = null;

		try {
			usuario = usuarioDAO.buscarUsuarioPorId(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;

	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarUsuario(Usuario usuario, @PathParam("id") int idUsuario) {
		String msg = "";

		System.out.println(usuario.getNome());

		try {
			usuarioDAO.editarUsuario(usuario, idUsuario);
			msg = "Nome editado com sucesso!";

		} catch (Exception e) {
			msg = "Erro ao editar nome";
			e.printStackTrace();
		}
		return msg;
	}

	public String removerUsuario(@PathParam("id") int idUsuario) {

		String msg = "";

		try {
			usuarioDAO.removerUsuario(idUsuario);
			msg = "Usuario removido com sucesso";
		} catch (Exception e) {
			msg = "Erro ao remover com sucesso";
			e.printStackTrace();
		}
		return msg;
	}

}
