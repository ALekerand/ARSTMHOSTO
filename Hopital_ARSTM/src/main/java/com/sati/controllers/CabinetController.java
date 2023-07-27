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

import com.sati.model.Cabinet;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class CabinetController {

	
	@Autowired
	Iservice service;
	private Cabinet cabinet = new Cabinet();
	private List<Cabinet> listCabinet = new ArrayList<Cabinet>();
	private Cabinet selectedCabinet;
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		cabinet.setCodeCabinet(genererCodeCabinet());
		
	}
	
	public String genererCodeCabinet() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Cabinet").size();
		if(nbEnregistrement < 10)
			prefix = "CA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CA0" ;
		if (nbEnregistrement > 100) 
			prefix = "CA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer() {
		this.service.addObject(cabinet);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		
	}
	
	public void modifier() {
		this.service.updateObject(cabinet);
		annuler();
		this.info("Modification effectuée avec succès!");
		
	}
	
	public void annuler() {
		cabinet.setCodeCabinet(null);
		cabinet.setEmailCabinet(null);
		cabinet.setNomCabinet(null);
		cabinet.setTelephoneCabinet(null);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public Cabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cabinet> getListCabinet() {
		listCabinet = service.getObjects("Cabinet");
		return listCabinet;
	}
	public void setListCabinet(List<Cabinet> listCabinet) {
		this.listCabinet = listCabinet;
	}
	public Cabinet getSelectedCabinet() {
		return selectedCabinet;
	}
	public void setSelectedCabinet(Cabinet selectedCabinet) {
		this.selectedCabinet = selectedCabinet;
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
