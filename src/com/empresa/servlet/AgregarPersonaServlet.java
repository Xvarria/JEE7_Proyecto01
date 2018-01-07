package com.empresa.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.empresa.dao.ContactosDAO;
import com.empresa.model.Persona;
import com.empresa.model.Telefono;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/AgregarPersona")
public class AgregarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarPersonaServlet() {
		super();
	}

	// Se invoca como EJB
	@EJB
	ContactosDAO contactosDAO;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/personaForm.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean validEntry = true;
		String nombre = null;
		String apellido1 = null;
		String apellido2 = null;
		String email = null;
		Persona persona = new Persona();
		String mensaje = "";
		Set<Telefono> telefonos = null;
		try {
			//Get information from request
			nombre = request.getParameter("nombre");
			apellido1 = request.getParameter("apellido1");
			apellido2 = request.getParameter("apellido2");
			email = request.getParameter("email");
			telefonos = new HashSet<Telefono>();
			for (int index = 1; index <= 10; index ++){
				String telefonoStr = request.getParameter("telefono_"+index);
				if (StringUtils.isNotEmpty(telefonoStr)){
					Telefono telefono = new Telefono();
					telefono.setTelefono(telefonoStr);
					telefonos.add(telefono);
				}
			}
			
			if (StringUtils.isEmpty(nombre) 
					|| StringUtils.isEmpty(apellido1)
					|| StringUtils.isEmpty(apellido2)){
				mensaje = "Los campos nombre y apellido son obligatorios";
				validEntry = false;
			}
		} catch (Exception e) {
			mensaje = "Problema no indentificado";
			validEntry = false;
		}		

		if (validEntry){
			persona.setNombre(nombre);
			persona.setApellido1(apellido1);
			persona.setApellido2(apellido2);
			persona.setCorreo(email);
			persona.setTelefonos(telefonos);
			boolean existe = this.contactosDAO.existe(persona);
			if (!existe){
				try {
					this.contactosDAO.persist(persona);
					request.setAttribute("accion", "Agregar persona");
					request.setAttribute("mensaje", "Persona agregada correctamente");
					request.getRequestDispatcher("/exitoConMensaje.jsp").forward(request, response);						
				} catch (Exception e) {
					validEntry = false;
				}
			}
		
		}
		if (!validEntry){
			request.setAttribute("accion", "Agregar persona");
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}
	}
}