package com.sati.model;
// Generated 14 ao�t 2023, 17:26:29 by Hibernate Tools 4.3.6.Final

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Consultation generated by hbm2java
 */
@Entity
@Table(name = "consultation", catalog = "hopitalarstm_bd")
public class Consultation implements java.io.Serializable {

	private Integer idConsultation;
	private Caisse caisse;
	private Medecin medecin;
	private Patient patient;
	private TypeConsultation typeConsultation;
	private UserAuthentication userAuthentication;
	private String codeConsultation;
	private Date dateConsultation;
	private String observation;
	private Set<Caisse> caisses = new HashSet<Caisse>(0);

	public Consultation() {
	}

	public Consultation(Patient patient, TypeConsultation typeConsultation, UserAuthentication userAuthentication) {
		this.patient = patient;
		this.typeConsultation = typeConsultation;
		this.userAuthentication = userAuthentication;
	}

	public Consultation(Caisse caisse, Medecin medecin, Patient patient, TypeConsultation typeConsultation,
			UserAuthentication userAuthentication, String codeConsultation, Date dateConsultation, String observation,
			Set<Caisse> caisses) {
		this.caisse = caisse;
		this.medecin = medecin;
		this.patient = patient;
		this.typeConsultation = typeConsultation;
		this.userAuthentication = userAuthentication;
		this.codeConsultation = codeConsultation;
		this.dateConsultation = dateConsultation;
		this.observation = observation;
		this.caisses = caisses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_CONSULTATION", unique = true, nullable = false)
	public Integer getIdConsultation() {
		return this.idConsultation;
	}

	public void setIdConsultation(Integer idConsultation) {
		this.idConsultation = idConsultation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CAISSE")
	public Caisse getCaisse() {
		return this.caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MEDECIN")
	public Medecin getMedecin() {
		return this.medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PATIENT", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_CONSULTATION", nullable = false)
	public TypeConsultation getTypeConsultation() {
		return this.typeConsultation;
	}

	public void setTypeConsultation(TypeConsultation typeConsultation) {
		this.typeConsultation = typeConsultation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false)
	public UserAuthentication getUserAuthentication() {
		return this.userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	@Column(name = "CODE_CONSULTATION", length = 10)
	public String getCodeConsultation() {
		return this.codeConsultation;
	}

	public void setCodeConsultation(String codeConsultation) {
		this.codeConsultation = codeConsultation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CONSULTATION", length = 19)
	public Date getDateConsultation() {
		return this.dateConsultation;
	}

	public void setDateConsultation(Date dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	@Column(name = "OBSERVATION")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "consultation")
	public Set<Caisse> getCaisses() {
		return this.caisses;
	}

	public void setCaisses(Set<Caisse> caisses) {
		this.caisses = caisses;
	}

}
