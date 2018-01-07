package com.empresa.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.ContactosDAO;
import com.empresa.model.Persona;
/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/EliminarPersona")
public class EliminarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarPersonaServlet() {
		super();
		// this.vehiculoDAO = new VehiculoDAOImpl();
	}

	// Se invoca como EJB
	@EJB
	ContactosDAO contactosDAO;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		Persona persona = null;
		String mensaje="";
		boolean validEntry = true;
		try {
			//Get information from request
			mensaje = "Parámetro inválido";
			id = Integer.valueOf(request.getParameter("idPersona"));
			persona = this.contactosDAO.getPersonaById(id);
			if (persona == null){
				mensaje = "Persona no existe";
				throw new Exception();
			}
		} catch (Exception e) {
			mensaje = "Problema con el parámetro o al intentar eliminar el registro";
			validEntry = false;
		}
		
		if (validEntry){
			try{
				this.contactosDAO.remove(persona);
				request.setAttribute("accion", "Eliminar persona");
				request.setAttribute("mensaje", "Persona eliminada correctamente");
				request.getRequestDispatcher("/exitoConMensaje.jsp").forward(request, response);							
			} catch (Exception e) {
				mensaje = "Error al eliminar persona en la base de datos";
				validEntry = false;
			}
		}
		if (!validEntry){
			request.setAttribute("accion", "Eliminar persona");
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}		
	}
}