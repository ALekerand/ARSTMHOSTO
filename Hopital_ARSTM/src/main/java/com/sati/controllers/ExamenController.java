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

import com.sati.model.ExamenMedicale;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class ExamenController {

	
	@Autowired
	Iservice service;
	private ExamenMedicale examen = new ExamenMedicale();
	private List<ExamenMedicale> listExamen = new ArrayList<ExamenMedicale>();
	private ExamenMedicale selectedExamen;
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	private void initialiser() {
		this.btnModifier.setDisabled(true);
		examen.setCodeExamen(genererCodeExamenMedicale());
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
	
	public void enregistrer() {
		this.service.addObject(this.examen);
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
		this.examen = this.selectedExamen;
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
}
