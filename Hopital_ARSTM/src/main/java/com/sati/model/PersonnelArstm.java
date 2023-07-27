package com.sati.model;
// Generated 22 juil. 2023, 19:05:53 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PersonnelArstm generated by hbm2java
 */
@Entity
@Table(name = "personnel_arstm", catalog = "hopitalarstm_bd")
public class PersonnelArstm implements java.io.Serializable {

	private int idPatient;
	private Patient patient;
	private Service service;
	private Integer idGenre;
	private String codePatient;
	private String nomPatient;
	private String prenomPatient;
	private String telephonePatient;

	public PersonnelArstm() {
	}

	public PersonnelArstm(Patient patient, Service service) {
		this.patient = patient;
		this.service = service;
	}

	public PersonnelArstm(Patient patient, Service service, Integer idGenre, String codePatient, String nomPatient,
			String prenomPatient, String telephonePatient) {
		this.patient = patient;
		this.service = service;
		this.idGenre = idGenre;
		this.codePatient = codePatient;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.telephonePatient = telephonePatient;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "patient"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "ID_PATIENT", unique = true, nullable = false)
	public int getIdPatient() {
		return this.idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SERVICE", nullable = false)
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Column(name = "ID_GENRE")
	public Integer getIdGenre() {
		return this.idGenre;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
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

}
