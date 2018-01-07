package com.empresa.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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
@WebServlet("/CargarPersonas")
public class CargarPersonasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CargarPersonasServlet() {
		super();
	}

	// Se invoca como EJB
	@EJB
	ContactosDAO contactosDAO;

	static final String[] NOMBRE_LIST = {"Abelardo","Alejandra","Bruno","Bruna","Carloto","Carmela","Danila","Dinio","Estefanio","Erminia","Federica","Farabunda","Gonzala","Gabriel"};//12
	static final String[] APPELLIDO_LIST = {"Alvarado","Brenes","Castro","Donoso","Echeverria","Fernandez","Gonzales","Hernandez", "Lios","Marquez","Nunes", "Rios"};//12

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Persona> personas = this.contactosDAO.getAllPersona(null);
		int maxRecord = 100 - personas.size();
		//Carga un máximo de 100 si ya hay 100 o mas no agrega nada
		for (int index=1; index <= maxRecord;){
			int nIndex = ThreadLocalRandom.current().nextInt(0, 12);
			int a1Index = ThreadLocalRandom.current().nextInt(0, 12);
			int a2Index = ThreadLocalRandom.current().nextInt(0, 12);
			Persona persona = new Persona();
			persona.setNombre(NOMBRE_LIST[nIndex]);
			persona.setApellido1(APPELLIDO_LIST[a1Index]);
			persona.setApellido2(APPELLIDO_LIST[a2Index]);
			persona.setCorreo(NOMBRE_LIST[nIndex]+APPELLIDO_LIST[a1Index]+"@mail.com");
			int sizeSeed = ThreadLocalRandom.current().nextInt(0, 11);
			int telefonoSize = 0;
			if (sizeSeed <= 8){
				telefonoSize = ThreadLocalRandom.current().nextInt(1, 4);
			}else{
				telefonoSize = ThreadLocalRandom.current().nextInt(4, 10);
			}
			Set<Telefono> telefonos = new HashSet<Telefono>();
			for(int telIndex=1; telIndex <= telefonoSize; telIndex++){
				int telTipo = ThreadLocalRandom.current().nextInt(0, 100);
				String prefix = "";
				if (telTipo >= 50){
					if (telTipo >= 75){
						prefix = "883";
					}else{
						prefix = "835";
					}
				}else{
					if (telTipo >= 25){
						prefix = "225";
					}else{
						prefix = "222";
					}					
				}
				
				String numero = prefix + StringUtils.leftPad(String.valueOf(ThreadLocalRandom.current().nextInt(0, 99999)), 5, "0") ;
				Telefono telefono = new Telefono();
				telefono.setTelefono(numero);
				telefonos.add(telefono);
			}
			persona.setTelefonos(telefonos);
			boolean existe = this.contactosDAO.existe(persona);
			if (!existe){
				try {
					this.contactosDAO.persist(persona);
					index++;
				} catch (Exception e) {
				}
			}
		}
		request.setAttribute("accion", "Cargar persona");
		request.setAttribute("mensaje", "Persona cargadas correctamente");
		request.getRequestDispatcher("/exitoConMensaje.jsp").forward(request, response);		
	}
}