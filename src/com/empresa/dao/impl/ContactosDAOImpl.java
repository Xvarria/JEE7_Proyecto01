package com.empresa.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.empresa.dao.ContactosDAO;
import com.empresa.model.Persona;


@Stateless
public class ContactosDAOImpl implements ContactosDAO {

	@PersistenceContext(name="default")
	private EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void persist(Persona persona) {
		em.persist(persona);
		//throw new RuntimeException("Error rollback");//To test transacctions
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> getAllPersona(String criterio) {
	    String queryString = "Select p from Persona p ";
	    if (StringUtils.isNotEmpty(criterio)){
	    	queryString += " where lower(p.nombre) like :criterio or lower(p.apellido1) like :criterio or lower(p.apellido2) like :criterio ";
	    }
	    queryString += " order by  p.apellido1, p.apellido2, p.nombre";
	    Query query = em.createQuery(queryString);
	    if (StringUtils.isNotEmpty(criterio)){
	    	query.setParameter("criterio", "%"+criterio.toLowerCase()+"%");
	    }
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> getAllPersona(String criterio, int pagina, int regPorPagina) {
	    String queryString = "Select p from Persona p ";
	    if (StringUtils.isNotEmpty(criterio)){
	    	queryString += " where lower(p.nombre) like :criterio or lower(p.apellido1) like :criterio or lower(p.apellido2) like :criterio";
	    }
	    Query query = em.createQuery(queryString);
	    queryString += " order by  p.apellido1, p.apellido2, p.nombre";
	    if (StringUtils.isNotEmpty(criterio)){
	    	query.setParameter("criterio", "%"+criterio.toLowerCase()+"%");
	    }
	    
	    int primer = ((pagina - 1) * regPorPagina);
	    query.setFirstResult(primer);
	    query.setMaxResults(regPorPagina);
		return query.getResultList();
		
	}	

	@Override
	public void remove(Persona persona) {
		if (!em.contains(persona)) {
			persona = em.merge(persona);
		}	
		em.remove(persona);
	}

	@Override
	public void update(Persona persona) {
		em.merge(persona);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean existe(Persona persona) {
	    String queryString = "Select p from Persona p where lower(p.nombre) = :nombre and lower(p.apellido1) = :apellido1 and lower(p.apellido2) = :apellido2";
	    Query query = em.createQuery(queryString);
	    query.setParameter("nombre", persona.getNombre().toLowerCase());
	    query.setParameter("apellido1", persona.getApellido1().toLowerCase());
	    query.setParameter("apellido2", persona.getApellido2().toLowerCase());
	    List<Persona> personas = query.getResultList();
		return personas.size() > 0;
	}

	@Override
	public Persona getPersonaById(int id) {
	    String queryString = "Select p from Persona p where p.id = :id";
	    Query query = em.createQuery(queryString);
	    query.setParameter("id", id);
	    Persona result = (Persona)query.getSingleResult();
		return result;
	}

	@Override
	public int cantidadRegistros(String criterio) {
	    String queryString = "Select count(p) from Persona p ";
	    if (StringUtils.isNotEmpty(criterio)){
	    	queryString += " where lower(p.nombre) like :criterio or lower(p.apellido1) like :criterio or lower(p.apellido2) like :criterio";
	    }
	    Query query = em.createQuery(queryString);
	    if (StringUtils.isNotEmpty(criterio)){
	    	query.setParameter("criterio", "%"+criterio.toLowerCase()+"%");
	    }
	    Long registros = (Long)query.getSingleResult(); 
		return registros.intValue();
	}

	@Override
	public void flush() {
		em.flush();
	}
	
	
}
