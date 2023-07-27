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

import com.sati.model.Approvisionnement;
import com.sati.model.Medicament;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class ApprovisionnementController {
	
	
	@Autowired
	Iservice service;
	private Approvisionnement approvisionnement = new Approvisionnement();
	private List<Approvisionnement> listApprovisionnement = new ArrayList<Approvisionnement>();
	private Approvisionnement selectedApprovisionnement;
	private int idMedicament;
	private Medicament medicament;
	private List<Medicament> listMedicament = new ArrayList<Medicament>();
	private int stockActuel;
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		approvisionnement.setCodeApprovisionnement(genererCodeApprovisionnement());
	}
	
	public String genererCodeApprovisionnement() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Approvisionnement").size();
		if(nbEnregistrement < 10)
			prefix = "AP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "AP0" ;
		if (nbEnregistrement > 100) 
			prefix = "AP" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public void enregistre() {
		approvisionnement.setMedicament(medicament);
		approvisionnement.setDateApprovisionnement(new Date());
		this.service.addObject(approvisionnement);
		approvisionnement.setCodeApprovisionnement(genererCodeApprovisionnement());

		medicament.setStockActuel(this.medicament.getStockActuel() + this.approvisionnement.getQuantiteApprovisionnement());
		service.updateObject(medicament);
		this.info("Enregistrement effectué avec succès!");
		annuler();
	}
	
	public void modifier() {
		this.service.updateObject(approvisionnement);
		this.info("Modification effectuée avec succès!");
		annuler();
	}
	
	public void annuler() {
		approvisionnement.setCodeApprovisionnement(null);
		approvisionnement.setQuantiteApprovisionnement(null);
		setIdMedicament(0);
		
	}
	
	public void selectionnerLigne() {
		this.approvisionnement = selectedApprovisionnement;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public void chargerMedicament() {
		medicament = new Medicament();
		medicament = (Medicament) service.getObjectById(idMedicament, "Medicament");
		setStockActuel(getStockActuel());
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage, null));
	}
	public Approvisionnement getApprovisionnement() {
		return approvisionnement;
	}
	public void setApprovisionnement(Approvisionnement approvisionnement) {
		this.approvisionnement = approvisionnement;
	}
	
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getListApprovisionnement() {
		listApprovisionnement = service.getObjects("Approvisionnement");
		System.out.println("========Taille de la liste:"+listApprovisionnement.size());
		return listApprovisionnement;
	}
	public void setListApprovisionnement(List<Approvisionnement> listApprovisionnement) {
		this.listApprovisionnement = listApprovisionnement;
	}
	public Approvisionnement getSelectedApprovisionnement() {
		return selectedApprovisionnement;
	}
	public void setSelectedApprovisionnement(Approvisionnement selectedApprovisionnement) {
		this.selectedApprovisionnement = selectedApprovisionnement;
	}
	public int getIdMedicament() {
		return idMedicament;
	}
	public void setIdMedicament(int idMedicament) {
		this.idMedicament = idMedicament;
	}
	public Medicament getMedicament() {
		return medicament;
	}
	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medicament> getListMedicament() {
		listMedicament = service.getObjects("Medicament");
		System.out.println("=========Taille de la liste:"+listMedicament.size());
		return listMedicament;
	}
	public void setListMedicament(List<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
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

	public int getStockActuel() {
		return stockActuel;
	}

	public void setStockActuel(int stockActuel) {
		this.stockActuel = stockActuel;
	}

}
