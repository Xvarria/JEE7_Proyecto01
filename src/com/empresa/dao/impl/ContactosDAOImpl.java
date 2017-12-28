package com.empresa.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.empresa.dao.ContactosDAO;
import com.empresa.model.Persona;
import com.empresa.model.Telefono;

//En la implementacion se define el tipo 
@Stateless
@LocalBean
public class ContactosDAOImpl implements ContactosDAO {

	@PersistenceContext(name="default")
	private EntityManager em;

	@Override
	public void persist(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persist(Telefono telefono) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Persona> getAllPersona() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Telefono telefono) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Telefono telefono) {
		// TODO Auto-generated method stub
		
	}
	

}
