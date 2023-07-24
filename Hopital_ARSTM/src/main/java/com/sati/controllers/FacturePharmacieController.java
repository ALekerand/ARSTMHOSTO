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

import com.sati.model.FacturePharmacie;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class FacturePharmacieController {
	
	@Autowired
	Iservice service;
	private FacturePharmacie facturePharmacie = new FacturePharmacie();
	private List<FacturePharmacie> listFacturePharmacie = new ArrayList<FacturePharmacie>();
	private FacturePharmacie selectedFacturePharmacie;
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnSupprimer = new CommandButton();
	

	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		facturePharmacie.setCodeFacturePharmacie(genererCodeFacturePharmacie());
	}
	
	public String genererCodeFacturePharmacie() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("FacturePharmacie").size();
		if(nbEnregistrement < 10)
			prefix = "FP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "FP0" ;
		if (nbEnregistrement > 100) 
			prefix = "FP" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	
	public void enregistrer() {
		
		this.service.addObject(facturePharmacie);
		this.info("Enregistrement effectué avec succès!");
		annuler();
		facturePharmacie.setCodeFacturePharmacie(genererCodeFacturePharmacie());
		
	}
	
	public void modifier() {
		this.service.updateObject(facturePharmacie);
		this.info("Modification effectuée avec succès!");
		annuler();
	}
	
	public void supprimer() {
		
		this.service.deleteObject(facturePharmacie);
		this.info("Suppression effectuée avec succès!");
		annuler();
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void annuler() {
		facturePharmacie.setCodeFacturePharmacie(null);
		facturePharmacie.setMontantFacturePharmacie(null);
	}
	
	public void selectionnerLigne() {
		this.facturePharmacie = this.selectedFacturePharmacie;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public FacturePharmacie getFacturePharmacie() {
		return facturePharmacie;
	}
	public void setFacturePharmacie(FacturePharmacie facturePharmacie) {
		this.facturePharmacie = facturePharmacie;
	}
	
	@SuppressWarnings("unchecked")
	public List<FacturePharmacie> getListFacturePharmacie() {
		listFacturePharmacie = service.getObjects("FacturePharmacie");
		System.out.println("===========Taille de la liste:"+listFacturePharmacie.size());
		return listFacturePharmacie;
	}
	public void setListFacturePharmacie(List<FacturePharmacie> listFacturePharmacie) {
		this.listFacturePharmacie = listFacturePharmacie;
	}
	public FacturePharmacie getSelectedFacturePharmacie() {
		return selectedFacturePharmacie;
	}
	public void setSelectedFacturePharmacie(FacturePharmacie selectedFacturePharmacie) {
		this.selectedFacturePharmacie = selectedFacturePharmacie;
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
