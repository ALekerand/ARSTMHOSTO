package com.sati.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.selectonebutton.SelectOneButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sati.model.Caisse;
import com.sati.model.Consultation;
import com.sati.model.EtudiantArstm;
import com.sati.model.Externe;
import com.sati.model.Filiere;
import com.sati.model.Genre;
import com.sati.model.Medecin;
import com.sati.model.Patient;
import com.sati.model.PersonnelArstm;
import com.sati.model.Service;
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
	private List<Patient> listPatient = new ArrayList<Patient>();
	private TypeConsultation typeConsultation = new TypeConsultation();
	private int idTypeConsultation;
	private List<TypeConsultation> listTypeConsultation = new ArrayList<TypeConsultation>();
	private Genre genre = new Genre();
	private List<Genre> listGenre = new ArrayList<Genre>();
	private int idGenre;
	private Filiere filiere = new Filiere();
	private List<Filiere> listFiliere = new ArrayList<Filiere>();
	private int idFiliere;
	private Service services = new Service();
	private List<Service> listService = new ArrayList<Service>();
	private int idService;
	private EtudiantArstm etudiant = new EtudiantArstm();
	private PersonnelArstm personnel = new PersonnelArstm();
	private Externe externe = new Externe();
	private String codePatient;
	private String nomPatient;
	private String prenomPatient;
	private String telephonePatient;
	private String nature;
	private Medecin medecin = new Medecin();
	private List<Medecin> listMedecin = new ArrayList<Medecin>();
	private int idMedecin;
	private Caisse caisse = new Caisse();
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnSupprimer = new CommandButton();
	private SelectOneMenu cbService = new SelectOneMenu();
	private SelectOneMenu cbFiliere = new SelectOneMenu();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		this.cbFiliere.setDisabled(true);
		this.cbService.setDisabled(true);
		patient.setCodePatient(genererCodePatient());
		this.consultation.setCodeConsultation(genererCodeConsultation());
		this.caisse.setCodeCaisse(genererCodeCaisee());
		chagerUtilisateur();
	}
	
	public String genererCodeConsultation() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Consultation").size();
		if(nbEnregistrement < 10)
			prefix = "CON00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CON0" ;
		if (nbEnregistrement > 100) 
			prefix = "CON" ;
		return new String(prefix+(nbEnregistrement+1));
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
	
	public String genererCodeCaisee() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Caisse").size();
		if(nbEnregistrement < 10)
			prefix = "CCA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CCA0" ;
		if (nbEnregistrement > 100) 
			prefix = "CCA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	public UserAuthentication chagerUtilisateur() {
		return userAuthentication = requeteUtilisateur.recuperUser();
	}
	
	public void chargerPatient() {
		switch (nature){
		case "ETUDIANT":{
			this.cbFiliere.setDisabled(false);
			this.cbService.setDisabled(true);
			break;
		}
		case "PERSONNEL":{
			this.cbService.setDisabled(false);
			this.cbFiliere.setDisabled(true);
			break;
		}
		case "EXTERNE":{
			this.cbService.setDisabled(true);
			this.cbFiliere.setDisabled(true);
			break;
		}
		}
	
		
	}
	
	public void enregistrer() {
		genre = (Genre) service.getObjectById(idGenre, "Genre");
		patient.setGenre(genre);;
		this.service.addObject(patient);
		switch (nature) {
		case "ETUDIANT":{
			this.cbFiliere.setDisabled(false);
			filiere = (Filiere) service.getObjectById(idFiliere, "Filiere");
			etudiant.setPatient(patient);
			etudiant.setFiliere(filiere);
			etudiant.setIdGenre(idGenre);
			etudiant.setCodePatient(patient.getCodePatient());
			etudiant.setNomPatient(patient.getNomPatient());
			etudiant.setPrenomPatient(patient.getTelephonePatient());
			etudiant.setTelephonePatient(patient.getTelephonePatient());
			this.service.addObject(etudiant);
			break;
		}
			case "PERSONNEL":{
				this.cbService.setDisabled(false);
				services = (Service) service.getObjectById(idService, "Service");
				personnel.setPatient(patient);
				personnel.setService(services);
				personnel.setIdGenre(idGenre);
				personnel.setCodePatient(patient.getCodePatient());
				personnel.setNomPatient(patient.getNomPatient());
				personnel.setPrenomPatient(patient.getPrenomPatient());
				personnel.setTelephonePatient(patient.getTelephonePatient());
				this.service.addObject(personnel);
				break;
			}
			case "EXTERNE":{
				externe.setPatient(patient);
				externe.setIdGenre(idGenre);
				externe.setCodePatient(patient.getCodePatient());
				externe.setNomPatient(patient.getNomPatient());
				externe.setPrenomPatient(patient.getPrenomPatient());
				externe.setTelephonePatient(patient.getTelephonePatient());
				this.service.addObject(externe);
				break;
			}
		}
		medecin = (Medecin) service.getObjectById(idMedecin, "Medecin");
		typeConsultation = (TypeConsultation) service.getObjectById(idTypeConsultation, "TypeConsultation");
		consultation.setTypeConsultation(typeConsultation);
		consultation.setPatient(patient);
		consultation.setMedecin(medecin);
		consultation.setUserAuthentication(userAuthentication);
		consultation.setDateConsultation(new Date());
		this.service.addObject(consultation);
		caisse.setCodeCaisse(genererCodeCaisee());
		caisse.setConsultation(consultation);
		caisse.setDateEnregistrement(new Date());
		caisse.setMontantCaisse(getConsultation().getTypeConsultation().getMontantTypeConsultation());
		caisse.setUserAuthentication(userAuthentication);
		this.service.addObject(caisse);
		consultation.setCaisse(caisse);
		service.updateObject(consultation);
		annuler();
		this.info("Enregistrement effectué avec succès");
		consultation.setCodeConsultation(genererCodeConsultation());
	}
	
	public void annuler() {
		patient.setCodePatient(null);
		patient.setNomPatient(null);
		patient.setPrenomPatient(null);
		patient.setTelephonePatient(null);
		setIdFiliere(0);
		setIdGenre(idGenre);
		setIdService(idService);
		consultation.setCodeConsultation(null);
		consultation.setObservation(null);
		setIdTypeConsultation(0);
		setIdMedecin(0);
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Service getServices() {
		return services;
	}

	public void setServices(Service services) {
		this.services = services;
	}

	@SuppressWarnings("unchecked")
	public List<Genre> getListGenre() {
		listGenre = service.getObjects("Genre");
		System.out.println("=======Taille de la liste:"+listGenre.size());
		return listGenre;
	}

	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	@SuppressWarnings("unchecked")
	public List<Filiere> getListFiliere() {
		listFiliere = service.getObjects("Filiere");
		System.out.println("============Taille de la liste:"+listFiliere.size());
		return listFiliere;
	}

	public void setListFiliere(List<Filiere> listFiliere) {
		this.listFiliere = listFiliere;
	}

	public int getIdFiliere() {
		return idFiliere;
	}

	public void setIdFiliere(int idFiliere) {
		this.idFiliere = idFiliere;
	}

	@SuppressWarnings("unchecked")
	public List<Service> getListService() {
		listService = service.getObjects("Service");
		System.out.println("=========Taille de la liste:"+listService.size());
		return listService;
	}

	public void setListService(List<Service> listService) {
		this.listService = listService;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@SuppressWarnings("unchecked")
	public List<Medecin> getListMedecin() {
		listMedecin = service.getObjects("Medecin");
		System.out.println("==========Taille de la liste:"+listMedecin.size());
		return listMedecin;
	}

	public void setListMedecin(List<Medecin> listMedecin) {
		this.listMedecin = listMedecin;
	}

	public int getIdMedecin() {
		return idMedecin;
	}

	public void setIdMedecin(int idMedecin) {
		this.idMedecin = idMedecin;
	}

	public SelectOneMenu getCbService() {
		return cbService;
	}

	public void setCbService(SelectOneMenu cbService) {
		this.cbService = cbService;
	}

	public SelectOneMenu getCbFiliere() {
		return cbFiliere;
	}

	public void setCbFiliere(SelectOneMenu cbFiliere) {
		this.cbFiliere = cbFiliere;
	}

	public Externe getExterne() {
		return externe;
	}

	public void setExterne(Externe externe) {
		this.externe = externe;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	

}
