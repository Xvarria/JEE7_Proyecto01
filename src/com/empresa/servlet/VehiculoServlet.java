package com.empresa.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.ContactosDAO;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/ListaContactos")
public class VehiculoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehiculoServlet() {
		super();
		// this.vehiculoDAO = new VehiculoDAOImpl();
	}

	// Se invoca como EJB
	@EJB
	ContactosDAO vehiculoDAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Usuarios:
		
		//request.setAttribute("propietarios", propietarios);
		request.getRequestDispatcher("/vehiculos.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Crea un motor
		
		
		doGet(request, response);
	}
}