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

import com.sati.model.Genre;
import com.sati.model.Medecin;
import com.sati.model.Specialite;
import com.sati.model.UserAuthentication;
import com.sati.requêtes.RequeteUtilisateur;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class MedecinController {

	@Autowired
	Iservice service;
	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	private Medecin medecin = new Medecin();
	private List<Medecin> listMedecin = new ArrayList<Medecin>();
	private Medecin selectedMedecin;
	private Specialite specialite = new Specialite();
	private int idSpecialite;
	private List<Specialite> listSpecialite;
	private UserAuthentication userAuthentication = new UserAuthentication();
	private Genre genre = new Genre();
	private List<Genre> listGenre = new ArrayList<Genre>();
	private int idGenre;

	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		medecin.setCodeMedecin(genererCodeMedecin());
		chagerUtilisateur();
	}
	
	public String genererCodeMedecin() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Medecin").size();
		if(nbEnregistrement < 10)
			prefix = "ME00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "ME0" ;
		if (nbEnregistrement > 100) 
			prefix = "ME" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public UserAuthentication chagerUtilisateur() {
		return setUserAuthentication(requeteUtilisateur.recuperUser());
	}
	public void enregistrer() {

		specialite = (Specialite) service.getObjectById(idSpecialite, "Specialite");
		medecin.setSpecialite(specialite);
		genre = (Genre) service.getObjectById(idGenre, "Genre");
		medecin.setGenre(genre);
		medecin.setUserAuthentication(userAuthentication);
		this.service.addObject(medecin);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		medecin.setCodeMedecin(genererCodeMedecin());
	}
	
	public void modifier() {
		this.service.updateObject(medecin);
		this.info("Modification effectuée avec succès!");
	}
	
	public void annuler() {
		medecin.setCodeMedecin(null);
		medecin.setEmailMedecin(null);
		medecin.setNomMedecin(null);
		medecin.setPrenomsMedecin(null);
		medecin.setTelephoneMedecin(null);
		setIdSpecialite(0);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medecin> getListMedecin() {
		listMedecin = service.getObjects("Medecin");
		System.out.println("=======Taille de la liste:"+listMedecin.size());
		return listMedecin;
	}
	public void setListMedecin(List<Medecin> listMedecin) {
		this.listMedecin = listMedecin;
	}
	public Medecin getSelectedMedecin() {
		return selectedMedecin;
	}
	public void setSelectedMedecin(Medecin selectedMedecin) {
		this.selectedMedecin = selectedMedecin;
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

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public int getIdSpecialite() {
		return idSpecialite;
	}

	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
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

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public UserAuthentication setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
		return userAuthentication;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@SuppressWarnings("unchecked")
	public List<Genre> getListGenre() {
		listGenre = service.getObjects("Genre");
		System.out.println("========Taille de la liste:"+listGenre.size());
		return listGenre;
	}

	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}
}
