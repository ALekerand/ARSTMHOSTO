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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Genre generated by hbm2java
 */
@Entity
@Table(name = "genre", catalog = "hopitalarstm_bd")
public class Genre implements java.io.Serializable {

	private Integer idGenre;
	private String codeGenre;
	private String libelleGenre;
	private Set<Patient> patients = new HashSet<Patient>(0);

	public Genre() {
	}

	public Genre(String codeGenre, String libelleGenre, Set<Patient> patients) {
		this.codeGenre = codeGenre;
		this.libelleGenre = libelleGenre;
		this.patients = patients;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_GENRE", unique = true, nullable = false)
	public Integer getIdGenre() {
		return this.idGenre;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	@Column(name = "CODE_GENRE", length = 10)
	public String getCodeGenre() {
		return this.codeGenre;
	}

	public void setCodeGenre(String codeGenre) {
		this.codeGenre = codeGenre;
	}

	@Column(name = "LIBELLE_GENRE", length = 10)
	public String getLibelleGenre() {
		return this.libelleGenre;
	}

	public void setLibelleGenre(String libelleGenre) {
		this.libelleGenre = libelleGenre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
	public Set<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

}
