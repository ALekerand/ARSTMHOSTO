package com.sati.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sati.model.Caisse;
import com.sati.model.EtudiantArstm;
import com.sati.model.FacturePharmacie;
import com.sati.model.Filiere;
import com.sati.model.LigneAchat;
import com.sati.model.Medicament;
import com.sati.model.Patient;
import com.sati.model.UserAuthentication;
import com.sati.requêtes.RequetePatient;
import com.sati.requêtes.RequeteUtilisateur;
import com.sati.service.Iservice;

@Component
@Scope("session")
public class LigneAchatController {

	
	@Autowired
	Iservice service;
	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	private LigneAchat ligneAchat = new LigneAchat();
	private List<LigneAchat> listAchat = new ArrayList<LigneAchat>();
	private FacturePharmacie facturePharmacie = new FacturePharmacie();
	private List<FacturePharmacie> listObject = new ArrayList<FacturePharmacie>();
	private int idMedicament;
	private Integer quantiteMedicament;
	private Medicament selectedMedicament;
	private List<Medicament> listMedicament = new ArrayList<Medicament>();
	private Medicament medicament = new Medicament();
	private List<LigneAchat> listLigneAchat = new ArrayList<>();
	private Patient patient;
	private List<Patient> listPatient = new ArrayList<Patient>();
	private int idPatient;
	private String typePatient = "";
	private EtudiantArstm etudiant = new EtudiantArstm();
	private Filiere  filiere = new Filiere();
	private int idFiliere;
	private String libelleFiliere;
	private Long montantFacturePharmacie;
	private Caisse caisse = new Caisse();
	private UserAuthentication userAuthentication = new UserAuthentication();
	
	
	private CommandButton btnEnregistrer = new CommandButton();
	private CommandButton btnAjouter = new CommandButton();
	private CommandButton btnAnnuler = new CommandButton();
	
	@PostConstruct
	public void initialiser() {
		chagerUtilisateur();
		this.ligneAchat.setCodeAchat(genererCodeLigneAchat());
		this.facturePharmacie.setCodeFacturePharmacie(genererCodeFacturePharmacie());
		this.caisse.setCodeCaisse(genererCodeCaisee());
	}
	
	public String genererCodeLigneAchat() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("LigneAchat").size();
		if(nbEnregistrement < 10)
			prefix = "CLA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CLA0" ;
		if (nbEnregistrement > 100) 
			prefix = "CLA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public String genererCodeFacturePharmacie() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("FacturePharmacie").size();
		if(nbEnregistrement < 10)
			prefix = "CFP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CFP0" ;
		if (nbEnregistrement > 100) 
			prefix = "CFP" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public String genererCodeCaisee() {
		String prefix="";
		int nbEnregistrement = this.service.getObjects("Caisse").size();
		if(nbEnregistrement < 10)
			prefix = "CCA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "CCA0" ;
		if (nbEnregistrement > 100) 
			prefix = "CCA" ;
		return new String(prefix+(nbEnregistrement+1));
	}
	
	public UserAuthentication chagerUtilisateur() {
		return userAuthentication = requeteUtilisateur.recuperUser();
	}
	public void ajouter() {
		System.out.println("=====Lancement de la methode=======");
		LigneAchat ligneAchat = new LigneAchat();
		ligneAchat.setQuantiteMedicament(quantiteMedicament);
		ligneAchat.setMedicament(selectedMedicament);
		listAchat.add(ligneAchat);
		this.info("Ajout effectué avec succès!");
		annulerLigneAchat();
	
	}
	
	public void enregistrer() {
		// Calcule du montant de la factue
		montantFacturePharmacie = (long) 0.0;
		for(LigneAchat objetMedicament:listAchat) {
			montantFacturePharmacie += (objetMedicament.getQuantiteMedicament() * objetMedicament.getMedicament().getCoutMedicament());
			System.out.println("==========Montant des médicament est:"+montantFacturePharmacie+"FCFA");
		}
		
		// Enregistrement de la facture
		facturePharmacie.setCodeFacturePharmacie(genererCodeFacturePharmacie());
		facturePharmacie.setMontantFacturePharmacie(montantFacturePharmacie);
		service.addObject(facturePharmacie);
		
		caisse.setCodeCaisse(genererCodeCaisee());
		caisse.setDateEnregistrement(new Date());
		caisse.setMontantCaisse(getMontantFacturePharmacie());
		caisse.setFacturePharmacie(facturePharmacie);
		caisse.setUserAuthentication(userAuthentication);
		service.addObject(caisse);
		facturePharmacie.setCaisse(caisse);
		service.updateObject(facturePharmacie);
		
		// Enregistrement de la lignede commange
		for(LigneAchat objetMedicament:listAchat) {
			System.out.println("==========Quantité:"+objetMedicament.getQuantiteMedicament());
			System.out.println("==========Medicament:"+objetMedicament.getMedicament());
			objetMedicament.setCodeAchat(genererCodeLigneAchat());
			objetMedicament.setPatient(patient);
			objetMedicament.setFacturePharmacie(facturePharmacie);
			objetMedicament.setDateAchat(new Date());
			service.addObject(objetMedicament);
		}
		
		annuler();
		this.info("Enregistrement effectué avec succès!");

	}
	
	public void annulerLigneAchat() {
		medicament.setCodeMedicament(null);
		medicament.setCoutMedicament(null);
		medicament.setNomMedicament(null);
		setSelectedMedicament(null);
		setQuantiteMedicament(null);
		
	}
	
	public void annuler() {
		setListAchat(null);
		patient.setCodePatient(null);
		patient.setNomPatient(null);
		patient.setPrenomPatient(null);
		patient.setTelephonePatient(null);
	}
	public void info(String monMessage) {
		FacesContext.getCurrentInstance().addMessage((String) null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, monMessage, null));
	}
	
	public void chargerPatient() {
		patient = new Patient();
		patient = (Patient) service.getObjectById(idPatient, "Patient");
	}
	public void choisirLigne() {
		this.medicament = this.selectedMedicament;
	}
	public LigneAchat getLigneAchat() {
		return ligneAchat;
	}
	public void setLigneAchat(LigneAchat ligneAchat) {
		this.ligneAchat = ligneAchat;
	}
	
	
	public List<LigneAchat> getListAchat() {
		return listAchat;
	}
	public void setListAchat(List<LigneAchat> listAchat) {
		this.listAchat = listAchat;
	}
	public FacturePharmacie getFacturePharmacie() {
		return facturePharmacie;
	}
	public void setFacturePharmacie(FacturePharmacie facturePharmacie) {
		this.facturePharmacie = facturePharmacie;
	}
	public CommandButton getBtnEnregistrer() {
		return btnEnregistrer;
	}
	public void setBtnEnregistrer(CommandButton btnEnregistrer) {
		this.btnEnregistrer = btnEnregistrer;
	}
	public CommandButton getBtnAjouter() {
		return btnAjouter;
	}
	public void setBtnAjouter(CommandButton btnAjouter) {
		this.btnAjouter = btnAjouter;
	}
	public List<FacturePharmacie> getListObject() {
		return listObject;
	}
	public void setListObject(List<FacturePharmacie> listObject) {
		this.listObject = listObject;
	}
	public int getIdMedicament() {
		return idMedicament;
	}
	public void setIdMedicament(int idMedicament) {
		this.idMedicament = idMedicament;
	}
	public Medicament getSelectedMedicament() {
		return selectedMedicament;
	}
	public void setSelectedMedicament(Medicament selectedMedicament) {
		this.selectedMedicament = selectedMedicament;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medicament> getListMedicament() {
		listMedicament = service.getObjects("Medicament");
		return listMedicament;
	}
	public void setListMedicament(List<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public int getIdFiliere() {
		return idFiliere;
	}

	public void setIdFiliere(int idFiliere) {
		this.idFiliere = idFiliere;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	public EtudiantArstm getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantArstm etudiant) {
		this.etudiant = etudiant;
	}

	@SuppressWarnings("unchecked")
	public List<LigneAchat> getListLigneAchat() {
		listLigneAchat = service.getObjects("LigneAchat");
		System.out.println("========Taille de la liste est:"+listLigneAchat.size());
		return listLigneAchat;
	}

	public void setListLigneAchat(List<LigneAchat> listLigneAchat) {
		this.listLigneAchat = listLigneAchat;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@SuppressWarnings("unchecked")
	public List<Patient> getListPatient() {
		listPatient = service.getObjects("Patient");
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public String getTypePatient() {
		return typePatient;
	}

	public void setTypePatient(String typePatient) {
		this.typePatient = typePatient;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public String getLibelleFiliere() {
		return libelleFiliere;
	}

	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}

	public Integer getQuantiteMedicament() {
		return quantiteMedicament;
	}

	public void setQuantiteMedicament(Integer quantiteMedicament) {
		this.quantiteMedicament = quantiteMedicament;
	}

	public Long getMontantFacturePharmacie() {
		return montantFacturePharmacie;
	}

	public void setMontantFacturePharmacie(Long montantFacturePharmacie) {
		this.montantFacturePharmacie = montantFacturePharmacie;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public CommandButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public void setBtnAnnuler(CommandButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	
	
}
