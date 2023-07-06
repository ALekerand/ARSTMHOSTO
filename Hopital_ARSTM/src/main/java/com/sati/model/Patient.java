package com.sati.model;
// Generated 4 juil. 2023, 21:54:33 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "patient", catalog = "hopitalarstm_bd")
public class Patient implements java.io.Serializable {

	private Integer idPatient;
	private Genre genre;
	private String codePatient;
	private String nomPatient;
	private String prenomPatient;
	private String telephonePatient;
	private PersonnelArstm personnelArstm;
	private Set<LigneAchat> ligneAchats = new HashSet<LigneAchat>(0);
	private EtudiantArstm etudiantArstm;
	private Set<Consultation> consultations = new HashSet<Consultation>(0);

	public Patient() {
	}

	public Patient(Genre genre) {
		this.genre = genre;
	}

	public Patient(Genre genre, String codePatient, String nomPatient, String prenomPatient, String telephonePatient,
			PersonnelArstm personnelArstm, Set<LigneAchat> ligneAchats, EtudiantArstm etudiantArstm,
			Set<Consultation> consultations) {
		this.genre = genre;
		this.codePatient = codePatient;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.telephonePatient = telephonePatient;
		this.personnelArstm = personnelArstm;
		this.ligneAchats = ligneAchats;
		this.etudiantArstm = etudiantArstm;
		this.consultations = consultations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_PATIENT", unique = true, nullable = false)
	public Integer getIdPatient() {
		return this.idPatient;
	}

	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_GENRE", nullable = false)
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Column(name = "CODE_PATIENT", length = 10)
	public String getCodePatient() {
		return this.codePatient;
	}

	public void setCodePatient(String codePatient) {
		this.codePatient = codePatient;
	}

	@Column(name = "NOM_PATIENT", length = 50)
	public String getNomPatient() {
		return this.nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	@Column(name = "PRENOM_PATIENT", length = 100)
	public String getPrenomPatient() {
		return this.prenomPatient;
	}

	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	@Column(name = "TELEPHONE_PATIENT", length = 15)
	public String getTelephonePatient() {
		return this.telephonePatient;
	}

	public void setTelephonePatient(String telephonePatient) {
		this.telephonePatient = telephonePatient;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "patient")
	public PersonnelArstm getPersonnelArstm() {
		return this.personnelArstm;
	}

	public void setPersonnelArstm(PersonnelArstm personnelArstm) {
		this.personnelArstm = personnelArstm;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<LigneAchat> getLigneAchats() {
		return this.ligneAchats;
	}

	public void setLigneAchats(Set<LigneAchat> ligneAchats) {
		this.ligneAchats = ligneAchats;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "patient")
	public EtudiantArstm getEtudiantArstm() {
		return this.etudiantArstm;
	}

	public void setEtudiantArstm(EtudiantArstm etudiantArstm) {
		this.etudiantArstm = etudiantArstm;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

}
