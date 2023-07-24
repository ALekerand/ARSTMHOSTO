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

import com.sati.model.Specialite;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class SpecialiteController {
	
	@Autowired
	Iservice service;
	private Specialite specialite = new  Specialite();
	private List<Specialite> listSpecialite = new ArrayList<Specialite>();
	private Specialite selectedSpecialite;
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		specialite.setCodeSpecialite(genererCodeSpecialite());
		
	}
	
	public String genererCodeSpecialite() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Specialite").size();
		if(nbEnregistrement < 10)
			prefix = "SP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "SP0" ;
		if (nbEnregistrement > 100) 
			prefix = "SP" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer() {
		this.service.addObject(specialite);
		this.info("Enregistrement effectué avec succès!");
		annuler();
		specialite.setCodeSpecialite(genererCodeSpecialite());
	}
	
	public void modifier() {
		this.service.updateObject(specialite);
		annuler();
		this.info("Modification effectuée avec succès!");
		
	}
	
	public void annuler() {
		specialite.setCodeSpecialite(null);
		specialite.setLibelleSpecialite(null);
		this.info("Annulation effectuée avec succès!");
	}
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	public Specialite getSpecialite() {
		return specialite;
	}
	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}
	
	@SuppressWarnings("unchecked")
	public List<Specialite> getListSpecialite() {
		listSpecialite = service.getObjects("Specialite");
		System.out.println("=========Taille de la liste:"+listSpecialite.size());
		return listSpecialite;
	}
	public void setListSpecialite(List<Specialite> listSpecialite) {
		this.listSpecialite = listSpecialite;
	}
	public Specialite getSelectedSpecialite() {
		return selectedSpecialite;
	}
	public void setSelectedSpecialite(Specialite selectedSpecialite) {
		this.selectedSpecialite = selectedSpecialite;
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
