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

	static final String[] NOMBRE_LIST = {"Santiago","Mateo","Sebastian","Alejandro","Matias","Diego","Samuel","Nicolas","Daniel","Martin","Benjamin","Emiliano","Leonardo","Joaquin","Lucas","Iker","Gabriel","Thiago","Adrian","Bruno","Dylan","Tomas","David","Agustin","Ian","Ethan","Felipe","Maximiliano","Eric","Hugo","Pablo","Luca","Rodrigo","Ignacio","Simon","Carlos","Javier","Juan Pablo","Isaac","Santino","Manuel","Jeronimo","Emmanuel","Aaron","angel","Dante","Gael","Vicente","Juan Sebastian","Liam","Damian","Leo","Francisco","Alonso","Christopher","alvaro","Bautista","Miguel angel","Valentino","Rafael","Andres","Franco","Fernando","Leon","Oliver","Emilio","Marcos","Julian","Juan Jose","Pedro","Alexander","Lorenzo","Mario","Sergio","Maximo","Cristian","Esteban","Elias","Antonio","Luciano","Noah","Jorge","Enzo","Axel","Salvador","Marc","Derek","Juan Martin","Joel","Juan Diego","Gonzalo","Kevin","Alan","Eduardo","Miguel","Ivan","Josue","Cristobal","Ciro","Juan David","Sofia","Isabella","Valentina","Emma","Martina","Lucia","Victoria","Luciana","Valeria","Camila","Julieta","Ximena","Sara","Daniela","Emilia","Renata","Mia","Catalina","Julia","Elena","Olivia","Regina","Paula","Natalia","Mariana","Samantha","Maria","Antonella","Gabriela","Emily","Maria Jose","Zoe","Alma","Alejandra","Andrea","Noa","Alba","Aitana","Amanda","Abril","Miranda","Salome","Ana Sofia","Carla","Alexa","Juana","Ivanna","Allison","Guadalupe","Antonia","Fernanda","Delfina","Romina","Luna","Lola","Paulina","Josefina","Ana","Carmen","Maria Fernanda","Agustina","Abigail","Ana Paula","Maite","Amelia","Brianna","Nicole","Francesca","Chloe","Clara","Constanza","Isabel","Claudia","Laura","Adriana","Maia","Josefa","Ariana","Alicia","Danna","Elisa","Melissa","Leire","Maria Paz","Ainhoa","Violeta","Ariadna","Laia","Carolina","Juliana","Rafaela","Valerie","Trinidad","Aurora","Elizabeth","Pilar","Ashley","Carlota","Candela","Maria Victoria"};//200
	static final String[] APPELLIDO_LIST = {"Garcia","Rodriguez","Martinez","Hernandez","Lopez","Gonzalez","Perez","Sanchez","Ramirez","Torres","Flores","Rivera","Gomez","Diaz","Reyes","Morales","Cruz","Ortiz","Gutierrez","Chavez","Ramos","Gonzales","Ruiz","Alvarez","Mendoza","Vasquez","Castillo","Jimenez","Moreno","Romero","Herrera","Medina","Aguilar","Garza","Castro","Vargas","Fernandez","Guzman","Munoz","Mendez","Salazar","Soto","Delgado","Pena","Rios","Alvarado","Sandoval","Contreras","Valdez","Guerrero","Ortega","Estrada","Nunez","Maldonado","Vega","Vazquez","Santiago","Dominguez","Espinoza","Silva","Padilla","Marquez","Cortez","Rojas","Acosta","Figueroa","Luna","Juarez","Navarro","Campos","Molina","Avila","Ayala","Mejia","Carrillo","Duran","Santos","Salinas","Robles","Solis","Lara","Cervantes","Aguirre","Deleon","Ochoa","Miranda","Cardenas","Trujillo","Velasquez","Fuentes","Cabrera","Leon","Rivas","Montoya","Calderon","Colon","Serrano","Gallegos","Rosales","Castaneda","Villarreal","Guerra","Trevino","Pacheco","Ibarra","Valencia","Macias","Camacho","Salas","Orozco","Roman","Zamora","Suarez","Franco","Barrera","Mercado","Santana","Valenzuela","Escobar","Rangel","Melendez","Velez","Lozano","Velazquez","Smith","Arias","Mora","Delacruz","Galvan","Zuniga","Cantu","Villanueva","Meza","Acevedo","Cisneros","Arroyo","Pineda","Andrade","Tapia","Sosa","Villa","Rocha","Rubio","Zavala","Montes","Ponce","Bonilla","Arellano","Rosario","Davila","Villegas","Huerta","Mata","Beltran","Barajas","Benitez","Esparza","Cordova","Murillo","Salgado","Rosas","Cuevas","Palacios","Guevara","Quintero","Johnson","Lucero","Medrano","Bautista","Martin","Lugo","Dejesus","Espinosa","Marin","Cortes","Magana","Quintana","Corona","Trejo","Bernal","Nieves","Villalobos","Enriquez","Reyna","Jaramillo","Saenz","Quinones","Delarosa","Avalos","Esquivel","Williams","Nava","Cano","Bravo","Duarte","Galindo","Correa","Parra","Rodriquez","Saldana"};//200

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Persona> personas = this.contactosDAO.getAllPersona(null);
		int maxRecord = 100 - personas.size();
		//Carga un maximo de 100 si ya hay 100 o mas no agrega nada
		for (int index=1; index <= maxRecord;){
			int nIndex = ThreadLocalRandom.current().nextInt(0, 200);
			int a1Index = ThreadLocalRandom.current().nextInt(0, 200);
			int a2Index = ThreadLocalRandom.current().nextInt(0, 200);
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
		this.contactosDAO.flush();
		request.setAttribute("accion", "Cargar persona");
		request.setAttribute("mensaje", "Persona cargadas correctamente");
		request.getRequestDispatcher("/exitoConMensaje.jsp").forward(request, response);		
	}
}