package com.sati.requêtes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sati.model.LigneAchat;

@Transactional
@Component
@Scope("session")
public class RequetePatient {
	
	@Autowired
	
	private SessionFactory sessionFactory;
	
	
	public List rechercheParCodePatient(String codePatient) {
		
		String query = "SELECT * FROM `ligne_achat`,`patient` WHERE ligne_achat.ID_PATIENT = patient.ID_PATIENT AND patient.CODE_PATIENT = '"+codePatient+"'";
		List list = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(LigneAchat.class).list();
		
		return list;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
