package com.accenture.java.desafio.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import javax.ws.rs.core.MediaType;

import com.accenture.java.desafio.dao.NotaDAO;
import com.accenture.java.desafio.entidade.Nota;

@Path("/notas")
public class NotasService {

	private NotaDAO notaDAO;

	@PostConstruct
	private void init() {
		notaDAO = new NotaDAO();
	}

	@GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Nota> listarNotas() {
        List<Nota> lista = null;
        try {
            lista = notaDAO.listarNotas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

	
	@POST
	@Path ("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addNota (Nota nota) {
		String msg = "";
		System.out.println(nota.getTitulo());
		
		try {
			int idGerado = notaDAO.addNota(nota);
			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao add nota!";
			e.printStackTrace();
		}
		return msg;
	}
}
