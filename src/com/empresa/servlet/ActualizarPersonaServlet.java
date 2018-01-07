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
@WebServlet("/ActualizarPersona")
public class ActualizarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarPersonaServlet() {
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
			persona.getTelefonos().isEmpty();// Obliga la lectura de la lista
			request.setAttribute("persona", persona);
			request.getRequestDispatcher("/personaActualizarForm.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("accion", "Actualizar persona");
			request.setAttribute("mensaje", "Parámetro inválido o la persona no existe");
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean validEntry = true;
		int id;
		String nombre = null;
		String apellido1 = null;
		String apellido2 = null;
		String email = null;
		Persona persona = null;
		String mensaje = "";
		Set<Telefono> telefonos = null;
		try {
			//Get information from request
			mensaje = "Parámetro inválido";
			id = Integer.valueOf(request.getParameter("id"));
			persona = this.contactosDAO.getPersonaById(id);
			if (persona == null){
				mensaje = "Persona no existe";
				throw new Exception();
			}
			nombre = request.getParameter("nombre");
			apellido1 = request.getParameter("apellido1");
			apellido2 = request.getParameter("apellido2");
			email = request.getParameter("email");
			telefonos = new HashSet<Telefono>();
			for (int index = 1; index <= 10; index ++){
				String telefonoStr = request.getParameter("telefono_"+index);
				if (StringUtils.isNotEmpty(telefonoStr)){
					String idStr = request.getParameter("telefonoId_"+index);
					Telefono telefono = new Telefono();
					if (!StringUtils.isEmpty(idStr)){
						int telefonoId = Integer.valueOf(idStr);
						telefono.setId(telefonoId);
					}
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
			//Carga la persona serializada de la base de datos
			if (!persona.getNombre().equals(nombre)){
				persona.setNombre(nombre);
			}
			if (!persona.getApellido1().equals(apellido1)){
				persona.setApellido1(apellido1);
			}
			if (!persona.getApellido2().equals(apellido2)){
				persona.setApellido2(apellido2);
			}
			if (!persona.getCorreo().equals(email)){
				persona.setCorreo(email);
			}
			persona.setTelefonos(telefonos);
			try{
				this.contactosDAO.update(persona);
				request.setAttribute("accion", "Actualizar persona");
				request.setAttribute("mensaje", "Persona actualizada correctamente");
				request.getRequestDispatcher("/exitoConMensaje.jsp").forward(request, response);							
			} catch (Exception e) {
				mensaje = "Error al actualizar en la base de datos";
				validEntry = false;
			}
		}
		if (!validEntry){
			request.setAttribute("accion", "Actualizar persona");
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}		
	}
}