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
import com.sati.service.Iservice;

@Component
@Scope("session")
public class GenreController {
	
	@Autowired
	Iservice service;
	private Genre genre = new Genre();
	private List<Genre> listGenre = new ArrayList<Genre>();
	private Genre selectedGenre ;

	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnModifier = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		this.btnModifier.setDisabled(true);
		genre.setCodeGenre(genererCodeGenre());
	}
	
	public String genererCodeGenre() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Genre").size();
		if(nbEnregistrement < 10)
			prefix = "GE00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "GE0" ;
		if (nbEnregistrement > 100) 
			prefix = "GE" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	public void enregistrer() {
		this.service.addObject(genre);
		annuler();
		this.info("Enregistrement effectué avec succès!");
		genre.setCodeGenre(genererCodeGenre());
		
	}
	
	public void modifier() {
		this.service.updateObject(genre);
		annuler();
		this.info("Modification effectuée avec succès!");
		
	}

	public void annuler() {
		genre.setCodeGenre(null);
		genre.setLibelleGenre(null);
	}
	
	public void selectionnerLigne() {
		this.genre = this.selectedGenre;
		this.btnEnregistrer.setDisabled(true);
		this.btnModifier.setDisabled(false);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null, new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage,null ));
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
		System.out.println("===========Taille de la liste est"+listGenre.size());
		return listGenre;
	}

	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}

	public Genre getSelectedGenre() {
		return selectedGenre;
	}

	public void setSelectedGenre(Genre selectedGenre) {
		this.selectedGenre = selectedGenre;
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
