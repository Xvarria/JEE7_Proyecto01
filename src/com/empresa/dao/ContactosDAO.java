package com.empresa.dao;

import java.util.List;

import javax.ejb.Remote;

import com.empresa.model.Persona;
import com.empresa.model.Telefono;

@Remote //EN la interface se define el tipo de acceso local vs public
public interface ContactosDAO {

	public void persist(Persona persona);
	
	public void persist(Telefono telefono);
	
	public List<Persona> getAllPersona(); 
	
	public void remove(Telefono telefono);
	
	public void remove(Persona persona);
	
	public void update(Telefono telefono);
	
}
