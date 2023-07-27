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

import com.sati.model.Medicament;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class MedicamentController {

	
	@Autowired
	Iservice service;
	private Medicament medicament = new Medicament();
	private List<Medicament> listMedicament = new ArrayList<Medicament>();
	private Medicament selectedMedicament;
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		medicament.setCodeMedicament(genererCodeMedicament());
	}
	
	public String genererCodeMedicament() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Medicament").size();
		if(nbEnregistrement < 10)
			prefix = "ME00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "ME0" ;
		if (nbEnregistrement > 100) 
			prefix = "ME" ;
		return new String(prefix+(nbEnregistrement+1));

	}
	
	public void enregistrer() {
		this.service.addObject(medicament);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		medicament.setCodeMedicament(genererCodeMedicament());
		
		
	}
	
	public void modifier() {
		this.service.updateObject(medicament);
		annuler();
		this.info("Modification effectuée avec succès!");
		
	}
	
	public void annuler() {
		medicament.setCodeMedicament(null);
		medicament.setCoutMedicament(null);
		medicament.setNomMedicament(null);
		medicament.setStockActuel(0);
		medicament.setStockAlerte(0);
	}
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage ,null ));
	}
	
	public void selectionnerLigne() {
		this.medicament = this.selectedMedicament;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
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
		return listMedicament;
	}
	public void setListMedicament(List<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
	}
	public Medicament getSelectedMedicament() {
		return selectedMedicament;
	}
	public void setSelectedMedicament(Medicament selectedMedicament) {
		this.selectedMedicament = selectedMedicament;
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

}
