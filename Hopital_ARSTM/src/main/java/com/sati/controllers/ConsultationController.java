package com.sati.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sati.model.Consultation;
import com.sati.model.Patient;
import com.sati.model.TypeConsultation;
import com.sati.model.UserAuthentication;
import com.sati.requêtes.RequeteUtilisateur;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class ConsultationController {
	
	
	@Autowired
	Iservice service;
	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	private Consultation consultation = new Consultation();
	private List<Consultation> listConsultation = new ArrayList<Consultation>();
	private Consultation selectedConsultation;
	private UserAuthentication userAuthentication = new UserAuthentication();
	private Patient patient = new Patient();
	private int idPatient;
	private List<Patient> listPatient = new ArrayList<Patient>();
	private TypeConsultation typeConsultation = new TypeConsultation();
	private int idTypeConsultation;
	private List<TypeConsultation> listTypeConsultation = new ArrayList<TypeConsultation>();
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnSupprimer = new CommandButton();
	
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		this.consultation.setCodeConsultation(genererCodeConsultation());
		chagerUtilisateur();
	}
	
	public String genererCodeConsultation() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Consultation").size();
		if(nbEnregistrement < 10)
			prefix = "PA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "PA0" ;
		if (nbEnregistrement > 100) 
			prefix = "PA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public UserAuthentication chagerUtilisateur() {
		return userAuthentication = requeteUtilisateur.recuperUser();
	}
	
	public void enregistrer() {
		typeConsultation = (TypeConsultation) service.getObjectById(idTypeConsultation, "TypeConsultation");
		patient = (Patient) service.getObjectById(idPatient, "Patient");
		consultation.setTypeConsultation(typeConsultation);
		consultation.setPatient(patient);
		consultation.setUserAuthentication(userAuthentication);
		consultation.setDateConsultation(new Date());
		this.service.addObject(consultation);
		this.info("Enregistrement effectué avec succès");
		annuler();
		consultation.setCodeConsultation(genererCodeConsultation());
	}
	
	public void annuler() {
		consultation.setCodeConsultation(null);
		consultation.setObservation(null);
		setIdPatient(0);
		setIdTypeConsultation(0);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void selectionnerLigne() {
		this.consultation = this.selectedConsultation;
	}
	public Consultation getConsultation() {
		return consultation;
	}
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	@SuppressWarnings("unchecked")
	public List<Consultation> getListConsultation() {
		listConsultation = service.getObjects("Consultation");
		System.out.println("========Taille de la liste:"+listConsultation.size());
		return listConsultation;
	}
	public void setListConsultation(List<Consultation> listConsultation) {
		this.listConsultation = listConsultation;
	}
	public Consultation getSelectedConsultation() {
		return selectedConsultation;
	}
	public void setSelectedConsultation(Consultation selectedConsultation) {
		this.selectedConsultation = selectedConsultation;
	}
	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}
	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
	public CommandButton getBtnEnregistrer() {
		return btnEnregistrer;
	}
	public void setBtnEnregistrer(CommandButton btnEnregistrer) {
		this.btnEnregistrer = btnEnregistrer;
	}
	public CommandButton getBtnModifier() {
		return btnModifier;
	}
	public void setBtnModifier(CommandButton btnModifier) {
		this.btnModifier = btnModifier;
	}
	public CommandButton getBtnSupprimer() {
		return btnSupprimer;
	}
	public void setBtnSupprimer(CommandButton btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getListPatient() {
		listPatient = service.getObjects("Patient");
		System.out.println("============Taille de la liste:"+listPatient.size());
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	public TypeConsultation getTypeConsultation() {
		return typeConsultation;
	}

	public void setTypeConsultation(TypeConsultation typeConsultation) {
		this.typeConsultation = typeConsultation;
	}

	public int getIdTypeConsultation() {
		return idTypeConsultation;
	}

	public void setIdTypeConsultation(int idTypeConsultation) {
		this.idTypeConsultation = idTypeConsultation;
	}

	@SuppressWarnings("unchecked")
	public List<TypeConsultation> getListTypeConsultation() {
		listTypeConsultation = service.getObjects("TypeConsultation");
		System.out.println("=========Taille de la liste:"+listTypeConsultation.size());
		return listTypeConsultation;
	}

	public void setListTypeConsultation(List<TypeConsultation> listTypeConsultation) {
		this.listTypeConsultation = listTypeConsultation;
	}
	

}
