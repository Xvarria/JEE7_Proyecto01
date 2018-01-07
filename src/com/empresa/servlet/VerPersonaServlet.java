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
@WebServlet("/VerPersona")
public class VerPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerPersonaServlet() {
		super();
		// this.vehiculoDAO = new VehiculoDAOImpl();
	}

	// Se invoca como EJB
	@EJB
	ContactosDAO contactosDAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idParam = Integer.parseInt(request.getParameter("idPersona"));
			Persona persona = this.contactosDAO.getPersonaById(idParam);
			// Set attributes
			if (persona == null){
				throw new Exception();
			}
			request.setAttribute("persona", persona);
			request.getRequestDispatcher("/verPersona.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("accion", "Ver persona");
			request.setAttribute("mensaje", "Parámetro inválido o la persona no existe");
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}
	}
}