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

import com.sati.model.Filiere;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class FiliereController {

	@Autowired
	Iservice service;
	private Filiere filiere = new Filiere();
	private List<Filiere> listFiliere = new ArrayList<Filiere>();
	private Filiere selectedObject;
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		filiere.setCodeFiliere(genererCodeFiliere());
	}
	public String genererCodeFiliere() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Filiere").size();
		if(nbEnregistrement < 10)
			prefix = "FI00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "FI0" ;
		if (nbEnregistrement > 100) 
			prefix = "FI" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer() {
		this.service.addObject(this.filiere);
		info("Enregistrement effectué avec succès!");
		annuler();
		filiere.setCodeFiliere(genererCodeFiliere());
		
	}
	
	public void selectionnerLigne() {
		setFiliere(selectedObject);
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public void modifier() {
		this.service.updateObject(this.filiere);
		info("Modification effectuée avec succès!");
		annuler();
	}
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void annuler() {
		filiere.setCodeFiliere(null);
		filiere.setLibelleFiliere(null);
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
	@SuppressWarnings("unchecked")
	public List<Filiere> getListFiliere() {
		listFiliere = service.getObjects("Filiere");
		System.out.println("=========Taille de la liste est:"+listFiliere.size());
		return listFiliere;
	}
	public void setListFiliere(List<Filiere> listFiliere) {
		this.listFiliere = listFiliere;
	}
	public Filiere getSelectedObject() {
		return selectedObject;
	}
	public void setSelectedObject(Filiere selectedObject) {
		this.selectedObject = selectedObject;
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
