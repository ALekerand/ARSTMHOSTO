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

import com.sati.model.TypeConsultation;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class TypeConsultationController {

	
	@Autowired
	Iservice service;
	private TypeConsultation typeConsultation = new TypeConsultation();
	private List<TypeConsultation> listTypeConsultation = new ArrayList<TypeConsultation>();
	private TypeConsultation selectedTypeConsultation;
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		typeConsultation.setCodeTypeConsultation(genererCodeTypeConsultation());
	}
	
	public String genererCodeTypeConsultation() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("TypeConsultation").size();
		if(nbEnregistrement < 10)
			prefix = "TC00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "TC0" ;
		if (nbEnregistrement > 100) 
			prefix = "TC" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	public void enregistrer() {
		this.service.addObject(typeConsultation);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		typeConsultation.setCodeTypeConsultation(genererCodeTypeConsultation());
	}
	
	public void selectionnerLigne() {
		this.typeConsultation = selectedTypeConsultation;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public void modifier() {
		this.service.updateObject(typeConsultation);
		annuler();
		this.info("Modification effectuée avec succès!");
	}
	
	public void annuler() {
		typeConsultation.setCodeTypeConsultation(null);
		typeConsultation.setMontantTypeConsultation(null);
		typeConsultation.setLibelleTypeConsultation(null);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	public TypeConsultation getTypeConsultation() {
		return typeConsultation;
	}
	public void setTypeConsultation(TypeConsultation typeConsultation) {
		this.typeConsultation = typeConsultation;
	}
	
	@SuppressWarnings("unchecked")
	public List<TypeConsultation> getListTypeConsultation() {
		listTypeConsultation = service.getObjects("TypeConsultation");
		System.out.println("===========Taille de la liste:"+listTypeConsultation.size());
		return listTypeConsultation;
	}
	public void setListTypeConsultation(List<TypeConsultation> listTypeConsultation) {
		this.listTypeConsultation = listTypeConsultation;
	}
	public TypeConsultation getSelectedTypeConsultation() {
		return selectedTypeConsultation;
	}
	public void setSelectedTypeConsultation(TypeConsultation selectedTypeConsultation) {
		this.selectedTypeConsultation = selectedTypeConsultation;
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
