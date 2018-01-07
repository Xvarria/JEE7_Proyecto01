package com.empresa.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.empresa.dao.ContactosDAO;
import com.empresa.model.Persona;
/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/ListarPersonas")
public class ListarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarPersonaServlet() {
		super();
	}

	// Se invoca como EJB
	@EJB
	ContactosDAO contactosDAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int pagina = 1;
			int regPorPagina = 10;
			int registros = this.contactosDAO.cantidadRegistros(null);
			int paginas = (registros/regPorPagina) + (registros%regPorPagina == 0 ? 0 : 1);
			paginas = paginas == 0 ? 1 : paginas;
			List<Persona> personas = this.contactosDAO.getAllPersona(null,pagina,regPorPagina);
			request.setAttribute("personas", personas);
			request.setAttribute("pagina", pagina);
			request.setAttribute("regPorPagina", regPorPagina);
			request.setAttribute("paginas", paginas);
			request.setAttribute("registros", registros);
			request.getRequestDispatcher("/listarPersonas.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("accion", "Listar personas");
			request.setAttribute("mensaje", "Problemas al Listar personas");
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String criterio = request.getParameter("criterio");
		String nuevaBusqueda = request.getParameter("nuevaBusqueda");
		int pagina = Integer.valueOf(request.getParameter("pagina"));
		if (StringUtils.isNotEmpty(nuevaBusqueda) && nuevaBusqueda.equalsIgnoreCase("SI")){
			pagina = 1;
		}
		try {
			int regPorPagina = 10;
			int registros = this.contactosDAO.cantidadRegistros(criterio);
			int paginas = (registros/regPorPagina) + (registros%regPorPagina == 0 ? 0 : 1);
			List<Persona> personas = this.contactosDAO.getAllPersona(criterio,pagina,regPorPagina);
			request.setAttribute("criterio", criterio);
			request.setAttribute("personas", personas);
			request.setAttribute("pagina", pagina);
			request.setAttribute("regPorPagina", regPorPagina);
			request.setAttribute("paginas", paginas);
			request.setAttribute("registros", registros);
			request.getRequestDispatcher("/listarPersonas.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("accion", "Listar personas");
			request.setAttribute("mensaje", "Problemas al Listar personas");
			request.getRequestDispatcher("/errorConMensaje.jsp").forward(request, response);
		}
	}	
}