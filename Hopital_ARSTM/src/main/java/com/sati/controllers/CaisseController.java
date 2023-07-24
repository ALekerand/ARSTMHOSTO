package com.sati.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sati.model.Caisse;
import com.sati.model.Consultation;
import com.sati.model.FacturePharmacie;
import com.sati.model.UserAuthentication;
import com.sati.requêtes.RequeteUtilisateur;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class CaisseController {
	
	
	@Autowired
	Iservice service;
	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	private Caisse caisse = new Caisse();
	private List<Caisse> listCaisse = new ArrayList<Caisse>();
	private Caisse selectedCaisse;
	private UserAuthentication userAuthentication = new UserAuthentication();
	private Consultation consultation = new Consultation();
	private List<Consultation> listConsultation = new ArrayList<Consultation>();
	private FacturePharmacie facturePharmacie = new FacturePharmacie();
	private List<FacturePharmacie> listFacturePharmacie = new ArrayList<FacturePharmacie>();
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		chagerUtilisateur();
		this.caisse.setCodeCaisse(genererCodeCaisse());
	}
	
	public String genererCodeCaisse() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Caisse").size();
		if(nbEnregistrement < 10)
			prefix = "PA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "PA0" ;
		if (nbEnregistrement > 100) 
			prefix = "PA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	public UserAuthentication chagerUtilisateur() {
		return setUserAuthentication(requeteUtilisateur.recuperUser());
	}
	public Caisse getCaisse() {
		return caisse;
	}
	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	public List<Caisse> getListCaisse() {
		return listCaisse;
	}
	public void setListCaisse(List<Caisse> listCaisse) {
		this.listCaisse = listCaisse;
	}
	public Caisse getSelectedCaisse() {
		return selectedCaisse;
	}
	public void setSelectedCaisse(Caisse selectedCaisse) {
		this.selectedCaisse = selectedCaisse;
	}
	public Consultation getConsultation() {
		return consultation;
	}
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	public List<Consultation> getListConsultation() {
		return listConsultation;
	}
	public void setListConsultation(List<Consultation> listConsultation) {
		this.listConsultation = listConsultation;
	}
	public FacturePharmacie getFacturePharmacie() {
		return facturePharmacie;
	}
	public void setFacturePharmacie(FacturePharmacie facturePharmacie) {
		this.facturePharmacie = facturePharmacie;
	}
	public List<FacturePharmacie> getListFacturePharmacie() {
		return listFacturePharmacie;
	}
	public void setListFacturePharmacie(List<FacturePharmacie> listFacturePharmacie) {
		this.listFacturePharmacie = listFacturePharmacie;
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

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public UserAuthentication setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
		return userAuthentication;
	}

}
