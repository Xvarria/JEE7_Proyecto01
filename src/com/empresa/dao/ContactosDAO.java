package com.empresa.dao;

import java.util.List;

import javax.ejb.LocalBean;

import com.empresa.model.Persona;

@LocalBean
public interface ContactosDAO {

	public void persist(Persona persona);
	
	public List<Persona> getAllPersona(String criterio);
	
	public List<Persona> getAllPersona(String criterio, int pagina, int regPorPagina); 
	
	public void remove(Persona persona);
	
	public void update(Persona persona);
	
	public boolean existe(Persona persona);
	
	public Persona getPersonaById(int id);
	
	public int cantidadRegistros(String criterio);
	
	public void flush();
}
