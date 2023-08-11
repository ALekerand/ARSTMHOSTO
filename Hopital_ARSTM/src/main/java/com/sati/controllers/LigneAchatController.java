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
import com.sati.model.FacturePharmacie;
import com.sati.model.Filiere;
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
	private List<LigneAchat> listAchat = new ArrayList<LigneAchat>();
	private FacturePharmacie facturePharmacie;
	private List<FacturePharmacie> listObject = new ArrayList<FacturePharmacie>();
	private int idMedicament;
	private int quantiteMedicament;
	private Medicament selectedMedicament;
	private List<Medicament> listMedicament = new ArrayList<Medicament>();
	private Medicament medicament = new Medicament();
	private List<LigneAchat> listLigneAchat = new ArrayList<>();
	private Patient patient;
	private List<Patient> listPatient = new ArrayList<Patient>();
	private int idPatient;
	private String typePatient;
	private EtudiantArstm etudiant = new EtudiantArstm();
	private Filiere  filiere = new Filiere();
	private String libelleFiliere;
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnAjouter = new CommandButton();
	
	
	public void ajouter() {
		System.out.println("=====Lancement de la methode=======");
		LigneAchat ligneAchat = new LigneAchat();
		ligneAchat.setQuantiteMedicament(quantiteMedicament);
		ligneAchat.setMedicament(selectedMedicament);
		listAchat.add(ligneAchat);
		this.info("Ajout effectué avec succès!");
		annulerLigneAchat();
	}
	
	public void annulerLigneAchat() {
		setQuantiteMedicament(0);
		medicament.setCodeMedicament(null);
		medicament.setCoutMedicament(null);
		medicament.setNomMedicament(null);
		setSelectedMedicament(null);
		
	}
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage, null));
	}
	
	public void chargerPatient() {
		patient = new Patient();
		patient = (Patient) service.getObjectById(idPatient, "Patient");
	}
	public void choisirLigne() {
		this.medicament = this.selectedMedicament;
	}
	public LigneAchat getLigneAchat() {
		return ligneAchat;
	}
	public void setLigneAchat(LigneAchat ligneAchat) {
		this.ligneAchat = ligneAchat;
	}
	
	
	public List<LigneAchat> getListAchat() {
		return listAchat;
	}
	public void setListAchat(List<LigneAchat> listAchat) {
		this.listAchat = listAchat;
	}
	public FacturePharmacie getFacturePharmacie() {
		return facturePharmacie;
	}
	public void setFacturePharmacie(FacturePharmacie facturePharmacie) {
		this.facturePharmacie = facturePharmacie;
	}
	public CommandButton getBtnEnregistrer() {
		return btnEnregistrer;
	}
	public void setBtnEnregistrer(CommandButton btnEnregistrer) {
		this.btnEnregistrer = btnEnregistrer;
	}
	public CommandButton getBtnAjouter() {
		return btnAjouter;
	}
	public void setBtnAjouter(CommandButton btnAjouter) {
		this.btnAjouter = btnAjouter;
	}
	public List<FacturePharmacie> getListObject() {
		return listObject;
	}
	public void setListObject(List<FacturePharmacie> listObject) {
		this.listObject = listObject;
	}
	public int getIdMedicament() {
		return idMedicament;
	}
	public void setIdMedicament(int idMedicament) {
		this.idMedicament = idMedicament;
	}
	public int getQuantiteMedicament() {
		return quantiteMedicament;
	}
	public void setQuantiteMedicament(int quantiteMedicament) {
		this.quantiteMedicament = quantiteMedicament;
	}
	public Medicament getSelectedMedicament() {
		return selectedMedicament;
	}
	public void setSelectedMedicament(Medicament selectedMedicament) {
		this.selectedMedicament = selectedMedicament;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medicament> getListMedicament() {
		listMedicament = service.getObjects("Medicament");
		return listMedicament;
	}
	public void setListMedicament(List<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	public EtudiantArstm getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantArstm etudiant) {
		this.etudiant = etudiant;
	}

	public List<LigneAchat> getListLigneAchat() {
		return listLigneAchat;
	}

	public void setListLigneAchat(List<LigneAchat> listLigneAchat) {
		this.listLigneAchat = listLigneAchat;
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
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public String getTypePatient() {
		return typePatient;
	}

	public void setTypePatient(String typePatient) {
		this.typePatient = typePatient;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public String getLibelleFiliere() {
		return libelleFiliere;
	}

	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}
	
}
