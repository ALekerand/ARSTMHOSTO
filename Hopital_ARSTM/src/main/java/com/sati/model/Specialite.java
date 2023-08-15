package com.sati.model;
// Generated 14 ao�t 2023, 17:26:29 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Specialite generated by hbm2java
 */
@Entity
@Table(name = "specialite", catalog = "hopitalarstm_bd")
public class Specialite implements java.io.Serializable {

	private Integer idSpecialite;
	private String codeSpecialite;
	private String libelleSpecialite;
	private Set<Medecin> medecins = new HashSet<Medecin>(0);

	public Specialite() {
	}

	public Specialite(String codeSpecialite, String libelleSpecialite, Set<Medecin> medecins) {
		this.codeSpecialite = codeSpecialite;
		this.libelleSpecialite = libelleSpecialite;
		this.medecins = medecins;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_SPECIALITE", unique = true, nullable = false)
	public Integer getIdSpecialite() {
		return this.idSpecialite;
	}

	public void setIdSpecialite(Integer idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	@Column(name = "CODE_SPECIALITE", length = 10)
	public String getCodeSpecialite() {
		return this.codeSpecialite;
	}

	public void setCodeSpecialite(String codeSpecialite) {
		this.codeSpecialite = codeSpecialite;
	}

	@Column(name = "LIBELLE_SPECIALITE", length = 20)
	public String getLibelleSpecialite() {
		return this.libelleSpecialite;
	}

	public void setLibelleSpecialite(String libelleSpecialite) {
		this.libelleSpecialite = libelleSpecialite;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialite")
	public Set<Medecin> getMedecins() {
		return this.medecins;
	}

	public void setMedecins(Set<Medecin> medecins) {
		this.medecins = medecins;
	}

}
