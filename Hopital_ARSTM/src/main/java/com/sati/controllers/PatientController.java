package com.sati.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sati.model.EtudiantArstm;
import com.sati.model.Filiere;
import com.sati.model.Genre;
import com.sati.model.Patient;
import com.sati.model.PersonnelArstm;
import com.sati.model.Service;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class PatientController {
	
	
	@Autowired
	Iservice service;
	private Patient patient = new Patient();
	private List<Patient> listPatient = new ArrayList<>();
	private Patient selectedPatient;
	private Filiere filiere = new Filiere();
	private Genre genre = new Genre();
	private int idGenre;
	private int idFiliere;
	private String codePatient;
	private String nomPatient;
	private String prenomPatient;
	private String telephonePatient;
	private EtudiantArstm etudiant = new EtudiantArstm();
	private PersonnelArstm personnel = new PersonnelArstm();
	private List<Genre> listGenre = new ArrayList<Genre>();
	private List<Filiere> listFiliere = new ArrayList<Filiere>();
	private Service services = new Service();
	private int idService;
	private List<Service> listService = new ArrayList<Service>();
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnSupprimer = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		patient.setCodePatient(genererCodePatient());
	}
	
	public String genererCodePatient() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Patient").size();
		if(nbEnregistrement < 10)
			prefix = "PA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "PA0" ;
		if (nbEnregistrement > 100) 
			prefix = "PA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer() {
		
		genre = (Genre) service.getObjectById(idGenre, "Genre");
		patient.setGenre(genre);;
		this.service.addObject(patient);
		filiere = (Filiere) service.getObjectById(idFiliere, "Filiere");
		etudiant.setPatient(patient);
		etudiant.setFiliere(filiere);
		etudiant.setIdGenre(idGenre);
		etudiant.setCodePatient(codePatient);
		etudiant.setNomPatient(nomPatient);
		etudiant.setPrenomPatient(prenomPatient);
		etudiant.setTelephonePatient(telephonePatient);
		this.service.addObject(etudiant);
		services = (Service) service.getObjectById(idService, "Service");
		personnel.setPatient(patient);
		personnel.setService(services);
		personnel.setIdGenre(idGenre);
		personnel.setCodePatient(codePatient);
		personnel.setNomPatient(nomPatient);
		personnel.setPrenomPatient(prenomPatient);
		personnel.setTelephonePatient(telephonePatient);
		this.service.addObject(personnel);
		annuler();
		info("Enregistrement effectué avec succès!");
		patient.setCodePatient(genererCodePatient());
		
		
		
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void annuler() {
		patient.setCodePatient(genererCodePatient());
		patient.setNomPatient(null);
		patient.setPrenomPatient(null);
		patient.setTelephonePatient(null);
	}
	
	public void modifier() {
		this.service.updateObject(patient);
		annuler();
		this.info("Modification effectuée avec succès!");
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patient> getListPatient() {
		listPatient = service.getObjects("Patient");
		System.out.println("===========Taille de la liste est:"+listPatient.size());
		return listPatient;
	}
	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}
	public Patient getSelectedPatient() {
		return selectedPatient;
	}
	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}
	public CommandButton getBtnEnregistrer() {
		return btnEnregistrer;
	}
	public void setBtnEnregistrer(CommandButton btnEnregistrer) {
		this.btnEnregistrer = btnEnregistrer;
	}
	public CommandButton getBtnSupprimer() {
		return btnSupprimer;
	}
	public void setBtnSupprimer(CommandButton btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}
	
	public CommandButton getBtnModifier() {
		return btnModifier;
	}
	public void setBtnModifier(CommandButton btnModifier) {
		this.btnModifier = btnModifier;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public int getIdFiliere() {
		return idFiliere;
	}

	public void setIdFiliere(int idFiliere) {
		this.idFiliere = idFiliere;
	}

	public String getCodePatient() {
		return codePatient;
	}

	public void setCodePatient(String codePatient) {
		this.codePatient = codePatient;
	}

	public String getNomPatient() {
		return nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	public String getPrenomPatient() {
		return prenomPatient;
	}

	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	public String getTelephonePatient() {
		return telephonePatient;
	}

	public void setTelephonePatient(String telephonePatient) {
		this.telephonePatient = telephonePatient;
	}

	public EtudiantArstm getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantArstm etudiant) {
		this.etudiant = etudiant;
	}

	public PersonnelArstm getPersonnel() {
		return personnel;
	}

	public void setPersonnel(PersonnelArstm personnel) {
		this.personnel = personnel;
	}

	@SuppressWarnings("unchecked")
	public List<Genre> getListGenre() {
		listGenre = service.getObjects("Genre");
		return listGenre;
	}

	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}

	@SuppressWarnings("unchecked")
	public List<Filiere> getListFiliere() {
		listFiliere = service.getObjects("Filiere");
		return listFiliere;
	}

	public void setListFiliere(List<Filiere> listFiliere) {
		this.listFiliere = listFiliere;
	}

	public Service getServices() {
		return services;
	}

	public void setServices(Service services) {
		this.services = services;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	@SuppressWarnings("unchecked")
	public List<Service> getListService() {
		listService = service.getObjects("Service");
		return listService;
	}

	public void setListService(List<Service> listService) {
		this.listService = listService;
	}

}
