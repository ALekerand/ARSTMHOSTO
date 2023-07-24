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

import com.sati.model.Service;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class ServiceController {
	
	@Autowired
	Iservice service;
	private Service services = new Service();
	private List<Service> listService = new ArrayList<Service>();
	private Service selectedService;
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnSupprimer = new CommandButton();
	
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		services.setCodeService(genererCodeService());
	}
	
	public String genererCodeService() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Service").size();
		if(nbEnregistrement < 10)
			prefix = "SE00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "SE0" ;
		if (nbEnregistrement > 100) 
			prefix = "SE" ;
		return new String(prefix+(nbEnregistrement+1));

	}
	
	public void enregistrer() {
		this.service.addObject(services);
		annuler();
		info("Enregistrement effectué avec succès!");
		services.setCodeService(genererCodeService());
	}
	
	
	public void modifier() {
		this.service.updateObject(services);
		annuler();
		info("Modification effectuée avrc succès!");
	}
	
	public void supprimer() {
		this.service.deleteObject(services);
		annuler();
		this.info("Suppression effectuée avec succès!");
	}
	
	public void annuler() {
		services.setCodeService(null);
		services.setLibelleService(null);
		
	}
	
	public void selectionnerLigne() {
		this.services = this.selectedService;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
		
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage ,null ));
	}
	
	public Service getServices() {
		return services;
	}
	public void setServices(Service services) {
		this.services = services;
	}
	
	@SuppressWarnings("unchecked")
	public List<Service> getListService() {
		listService = service.getObjects("Service");
		return listService;
	}
	public void setListService(List<Service> listService) {
		this.listService = listService;
	}
	public Service getSelectedService() {
		return selectedService;
	}
	public void setSelectedService(Service selectedService) {
		this.selectedService = selectedService;
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
