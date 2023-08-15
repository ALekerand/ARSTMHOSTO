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

import com.sati.model.Cabinet;
import com.sati.model.Caisse;
import com.sati.model.ExamenMedicale;
import com.sati.model.Patient;
import com.sati.model.Realiser;
import com.sati.model.UserAuthentication;
import com.sati.requêtes.RequeteUtilisateur;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class ExamenController {

	
	@Autowired
	Iservice service;
	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	private ExamenMedicale examen = new ExamenMedicale();
	private List<ExamenMedicale> listExamen = new ArrayList<ExamenMedicale>();
	private ExamenMedicale selectedExamen;
	private Patient patient = new Patient();
	private int idPatient;
	private List<Patient> listPatient = new ArrayList<Patient>();
	private Cabinet cabinet = new Cabinet();
	private int idCabinet;
	private List<Cabinet> listCabinet = new ArrayList<Cabinet>();
	private Realiser realiser = new Realiser();
	private List<Realiser> listRealiser = new ArrayList<>();
	private Realiser selectedRealiser;
	private Caisse caisse = new Caisse();
	private UserAuthentication userAuthentication = new UserAuthentication();
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	private void initialiser() {
		this.btnModifier.setDisabled(true);
		examen.setCodeExamen(genererCodeExamenMedicale());
		realiser.setCodeRealiser(genererCodeRealiser());
		caisse.setCodeCaisse(genererCodeCaisse());
		chagerUtilisateur();
	}
	
	public String genererCodeExamenMedicale() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("ExamenMedicale").size();
		if(nbEnregistrement < 10)
			prefix = "EX00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "EX0" ;
		if (nbEnregistrement > 100) 
			prefix = "EX" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public String genererCodeRealiser() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Realiser").size();
		if(nbEnregistrement < 10)
			prefix = "CRA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CRA0" ;
		if (nbEnregistrement > 100) 
			prefix = "CRA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public String genererCodeCaisse() {
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
	
	public void enregistrer() {
		this.service.addObject(this.examen);
		cabinet = (Cabinet) service.getObjectById(idCabinet, "Cabinet");
		patient = (Patient) service.getObjectById(idPatient, "Patient");
		realiser.setCabinet(cabinet);
		realiser.setPatient(patient);
		realiser.setCodeRealiser(genererCodeRealiser());
		realiser.setDateRealiser(new Date());
		realiser.setExamenMedicale(examen);
		service.addObject(realiser);
		caisse.setRealiser(realiser);
		caisse.setCodeCaisse(genererCodeCaisse());
		caisse.setDateEnregistrement(new Date());
		caisse.setMontantCaisse(getRealiser().getExamenMedicale().getCout());
		caisse.setUserAuthentication(userAuthentication);
		service.addObject(caisse);
		realiser.setCaisse(caisse);
		service.updateObject(realiser);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		examen.setCodeExamen(genererCodeExamenMedicale());
		
		
	}
	
	public void modifier() {
		this.service.updateObject(examen);
		annuler();
		this.info("Modification effectuée avec succès!");
		
	}
	public void annuler() {
		examen.setCodeExamen(null);
		examen.setNomExamen(null);
		examen.setCout(null);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void selectionnerLigne() {
		this.realiser = selectedRealiser;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public ExamenMedicale getExamen() {
		return examen;
	}
	public void setExamen(ExamenMedicale examen) {
		this.examen = examen;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamenMedicale> getListExamen() {
		listExamen = service.getObjects("ExamenMedicale");
		System.out.println("==========Taille de la liste:"+listExamen.size());
		return listExamen;
	}
	public void setListExamen(List<ExamenMedicale> listExamen) {
		this.listExamen = listExamen;
	}
	public ExamenMedicale getSelectedExamen() {
		return selectedExamen;
	}
	public void setSelectedExamen(ExamenMedicale selectedExamen) {
		this.selectedExamen = selectedExamen;
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
	public CommandButton getBtnAnnuler() {
		return btnAnnuler;
	}
	public void setBtnAnnuler(CommandButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
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
		System.out.println("========Taille de la liste:"+listPatient.size());
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	public Cabinet getCabinet() {
		return cabinet;
	}

	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}

	public int getIdCabinet() {
		return idCabinet;
	}

	public void setIdCabinet(int idCabinet) {
		this.idCabinet = idCabinet;
	}

	@SuppressWarnings("unchecked")
	public List<Cabinet> getListCabinet() {
		listCabinet = service.getObjects("Cabinet");
		System.out.println("==========Taille de la liste:"+listCabinet.size());
		return listCabinet;
	}

	public void setListCabinet(List<Cabinet> listCabinet) {
		this.listCabinet = listCabinet;
	}

	public Realiser getRealiser() {
		return realiser;
	}

	public void setRealiser(Realiser realiser) {
		this.realiser = realiser;
	}

	@SuppressWarnings("unchecked")
	public List<Realiser> getListRealiser() {
		listRealiser = service.getObjects("Realiser");
		System.out.println("========Taille de la liste:"+listRealiser.size());
		return listRealiser;
	}

	public void setListRealiser(List<Realiser> listRealiser) {
		this.listRealiser = listRealiser;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	public Realiser getSelectedRealiser() {
		return selectedRealiser;
	}

	public void setSelectedRealiser(Realiser selectedRealiser) {
		this.selectedRealiser = selectedRealiser;
	}
}
