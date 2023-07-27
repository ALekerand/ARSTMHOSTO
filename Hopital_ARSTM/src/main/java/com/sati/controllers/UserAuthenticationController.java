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

import com.sati.model.UserAuthentication;
import com.sati.model.UserAuthorization;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class UserAuthenticationController {
	
	@Autowired
	Iservice service;
	private String role;
	private UserAuthentication userAuthentication = new UserAuthentication();
	private List<UserAuthentication> listUserAuthentication = new ArrayList<UserAuthentication>();
	private UserAuthentication selectedUserAuthentication;
	private UserAuthorization userAuthorization = new UserAuthorization();
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
	}
	
	public void enregistrer() {
		userAuthentication.setEnabled(true);
		service.addObject(userAuthentication);
		userAuthorization.setUserAuthentication(userAuthentication);
		userAuthorization.setRole(role);
		service.addObject(userAuthorization);
		annuler();
		this.info("Enregistrement effectué avec succès!");
	}
	
	
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	
	public void annuler() {
		userAuthentication.setUsername(null);
		userAuthentication.setPassword(null);
		userAuthentication.setNom(null);
		userAuthentication.setEmail(null);
		userAuthentication.setPrenoms(null);
		userAuthentication.setTelephone(null);
		userAuthorization.setRole(null);
	}
	
	public void modifier() {
		service.updateObject(userAuthentication);
		annuler();
		this.info("Modification effectuée avec succès!");
	}
	
	public void selectionnerLigne() {
		this.userAuthentication = this.selectedUserAuthentication;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}
	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAuthentication> getListUserAuthentication() {
		listUserAuthentication = service.getObjects("UserAuthentication");
		System.out.println("==========Taille de la liste:"+listUserAuthentication.size());
		return listUserAuthentication;
	}
	public void setListUserAuthentication(List<UserAuthentication> listUserAuthentication) {
		this.listUserAuthentication = listUserAuthentication;
	}
	public UserAuthentication getSelectedUserAuthentication() {
		return selectedUserAuthentication;
	}
	public void setSelectedUserAuthentication(UserAuthentication selectedUserAuthentication) {
		this.selectedUserAuthentication = selectedUserAuthentication;
	}
	public UserAuthorization getUserAuthorization() {
		return userAuthorization;
	}
	public void setUserAuthorization(UserAuthorization userAuthorization) {
		this.userAuthorization = userAuthorization;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CommandButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public void setBtnAnnuler(CommandButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}

}
