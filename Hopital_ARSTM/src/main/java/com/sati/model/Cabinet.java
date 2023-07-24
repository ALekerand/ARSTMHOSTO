package com.sati.model;
// Generated 22 juil. 2023, 19:05:53 by Hibernate Tools 4.3.6.Final

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
 * Cabinet generated by hbm2java
 */
@Entity
@Table(name = "cabinet", catalog = "hopitalarstm_bd")
public class Cabinet implements java.io.Serializable {

	private Integer idCabinet;
	private String codeCabinet;
	private String nomCabinet;
	private String telephoneCabinet;
	private String emailCabinet;
	private Set<Realiser> realisers = new HashSet<Realiser>(0);

	public Cabinet() {
	}

	public Cabinet(String codeCabinet, String nomCabinet, String telephoneCabinet, String emailCabinet,
			Set<Realiser> realisers) {
		this.codeCabinet = codeCabinet;
		this.nomCabinet = nomCabinet;
		this.telephoneCabinet = telephoneCabinet;
		this.emailCabinet = emailCabinet;
		this.realisers = realisers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_CABINET", unique = true, nullable = false)
	public Integer getIdCabinet() {
		return this.idCabinet;
	}

	public void setIdCabinet(Integer idCabinet) {
		this.idCabinet = idCabinet;
	}

	@Column(name = "CODE_CABINET", length = 10)
	public String getCodeCabinet() {
		return this.codeCabinet;
	}

	public void setCodeCabinet(String codeCabinet) {
		this.codeCabinet = codeCabinet;
	}

	@Column(name = "NOM_CABINET", length = 20)
	public String getNomCabinet() {
		return this.nomCabinet;
	}

	public void setNomCabinet(String nomCabinet) {
		this.nomCabinet = nomCabinet;
	}

	@Column(name = "TELEPHONE_CABINET", length = 15)
	public String getTelephoneCabinet() {
		return this.telephoneCabinet;
	}

	public void setTelephoneCabinet(String telephoneCabinet) {
		this.telephoneCabinet = telephoneCabinet;
	}

	@Column(name = "EMAIL_CABINET", length = 50)
	public String getEmailCabinet() {
		return this.emailCabinet;
	}

	public void setEmailCabinet(String emailCabinet) {
		this.emailCabinet = emailCabinet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cabinet")
	public Set<Realiser> getRealisers() {
		return this.realisers;
	}

	public void setRealisers(Set<Realiser> realisers) {
		this.realisers = realisers;
	}

}