package com.sati.model;
// Generated 22 juil. 2023, 19:05:53 by Hibernate Tools 4.3.6.Final

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
 * Realiser generated by hbm2java
 */
@Entity
@Table(name = "realiser", catalog = "hopitalarstm_bd")
public class Realiser implements java.io.Serializable {

	private Integer idRealiser;
	private Cabinet cabinet;
	private Caisse caisse;
	private ExamenMedicale examenMedicale;
	private Patient patient;
	private String codeRealiser;
	private Date dateRealiser;
	private Set<Caisse> caisses = new HashSet<Caisse>(0);

	public Realiser() {
	}

	public Realiser(ExamenMedicale examenMedicale, Patient patient) {
		this.examenMedicale = examenMedicale;
		this.patient = patient;
	}

	public Realiser(Cabinet cabinet, Caisse caisse, ExamenMedicale examenMedicale, Patient patient, String codeRealiser,
			Date dateRealiser, Set<Caisse> caisses) {
		this.cabinet = cabinet;
		this.caisse = caisse;
		this.examenMedicale = examenMedicale;
		this.patient = patient;
		this.codeRealiser = codeRealiser;
		this.dateRealiser = dateRealiser;
		this.caisses = caisses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_REALISER", unique = true, nullable = false)
	public Integer getIdRealiser() {
		return this.idRealiser;
	}

	public void setIdRealiser(Integer idRealiser) {
		this.idRealiser = idRealiser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CABINET")
	public Cabinet getCabinet() {
		return this.cabinet;
	}

	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CAISSE")
	public Caisse getCaisse() {
		return this.caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EXAMEN", nullable = false)
	public ExamenMedicale getExamenMedicale() {
		return this.examenMedicale;
	}

	public void setExamenMedicale(ExamenMedicale examenMedicale) {
		this.examenMedicale = examenMedicale;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PATIENT", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "CODE_REALISER", length = 10)
	public String getCodeRealiser() {
		return this.codeRealiser;
	}

	public void setCodeRealiser(String codeRealiser) {
		this.codeRealiser = codeRealiser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_REALISER", length = 10)
	public Date getDateRealiser() {
		return this.dateRealiser;
	}

	public void setDateRealiser(Date dateRealiser) {
		this.dateRealiser = dateRealiser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "realiser")
	public Set<Caisse> getCaisses() {
		return this.caisses;
	}

	public void setCaisses(Set<Caisse> caisses) {
		this.caisses = caisses;
	}

}
