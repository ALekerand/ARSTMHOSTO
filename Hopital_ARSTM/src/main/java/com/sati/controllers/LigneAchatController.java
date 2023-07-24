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

import com.sati.model.FacturePharmacie;
import com.sati.model.LigneAchat;
import com.sati.model.Medicament;
import com.sati.model.Patient;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class LigneAchatController {

	
	@Autowired
	Iservice service;
	private LigneAchat ligneAchat = new LigneAchat();
	private List<LigneAchat> listLigneAchat = new ArrayList<LigneAchat>();
	private LigneAchat selectedLigneAchat;
	private Patient patient = new Patient();
	private List<Patient> listPatient = new ArrayList<Patient>();
	private int idPatient;
	private Medicament medicament = new Medicament();
	private List<Medicament> listMedicament = new ArrayList<Medicament>();
	private int idMedicament;
	private FacturePharmacie facturePharmacie = new FacturePharmacie();
	private List<FacturePharmacie> listFacturePharmacie = new ArrayList<FacturePharmacie>();
	private int idFacturePharmacie;
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnSupprimer = new CommandButton();

	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		ligneAchat.setCodeAchat(genererCodeLigneAchat());
	}
	
	public String genererCodeLigneAchat() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("LigneAchat").size();
		if(nbEnregistrement < 10)
			prefix = "LA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "LA0" ;
		if (nbEnregistrement > 100) 
			prefix = "LA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer() {
		patient = (Patient) service.getObjectById(idPatient, "Patient");
		medicament = (Medicament) service.getObjectById(idMedicament, "Medicament");
		facturePharmacie = (FacturePharmacie) service.getObjectById(idFacturePharmacie, "FacturePharmacie");
		ligneAchat.setPatient(patient);
		ligneAchat.setMedicament(medicament);
		ligneAchat.setFacturePharmacie(facturePharmacie);
		ligneAchat.setDateAchat(new Date());
		this.service.addObject(ligneAchat);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		ligneAchat.setCodeAchat(genererCodeLigneAchat());
	}
	
	
	public void annuler() {
		ligneAchat.setCodeAchat(null);
		ligneAchat.setQuantiteMedicament(0);
		setIdPatient(0);
		setIdFacturePharmacie(0);
		setIdMedicament(0);
	}
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void selectionnerLigne() {
		ligneAchat = selectedLigneAchat;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public LigneAchat getLigneAchat() {
		return ligneAchat;
	}

	public void setLigneAchat(LigneAchat ligneAchat) {
		this.ligneAchat = ligneAchat;
	}

	@SuppressWarnings("unchecked")
	public List<LigneAchat> getListLigneAchat() {
		listLigneAchat = service.getObjects("LigneAchat");
		System.out.println("==========Taille de la liste est:"+listLigneAchat.size());
		return listLigneAchat;
	}

	public void setListLigneAchat(List<LigneAchat> listLigneAchat) {
		this.listLigneAchat = listLigneAchat;
	}

	public LigneAchat getSelectedLigneAchat() {
		return selectedLigneAchat;
	}

	public void setSelectedLigneAchat(LigneAchat selectedLigneAchat) {
		this.selectedLigneAchat = selectedLigneAchat;
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

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	public FacturePharmacie getFacturePharmacie() {
		return facturePharmacie;
	}

	public void setFacturePharmacie(FacturePharmacie facturePharmacie) {
		this.facturePharmacie = facturePharmacie;
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getListPatient() {
		listPatient = service.getObjects("Patient");
		System.out.println("==========Taille de la liste est:"+listPatient.size());
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	public int getIdMedicament() {
		return idMedicament;
	}

	public void setIdMedicament(int idMedicament) {
		this.idMedicament = idMedicament;
	}

	@SuppressWarnings("unchecked")
	public List<Medicament> getListMedicament() {
		listMedicament = service.getObjects("Medicament");
		System.out.println("==========Taille de la liste est:"+listMedicament);
		return listMedicament;
	}

	public void setListMedicament(List<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
	}

	@SuppressWarnings("unchecked")
	public List<FacturePharmacie> getListFacturePharmacie() {
		listFacturePharmacie = service.getObjects("FacturePharmacie");
		System.out.println("========Taille de la liste est:"+listFacturePharmacie.size());
		return listFacturePharmacie;
	}

	public void setListFacturePharmacie(List<FacturePharmacie> listFacturePharmacie) {
		this.listFacturePharmacie = listFacturePharmacie;
	}

	public int getIdFacturePharmacie() {
		return idFacturePharmacie;
	}

	public void setIdFacturePharmacie(int idFacturePharmacie) {
		this.idFacturePharmacie = idFacturePharmacie;
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
}
