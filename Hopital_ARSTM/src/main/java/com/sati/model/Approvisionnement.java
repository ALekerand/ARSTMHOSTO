package com.sati.model;
// Generated 22 juil. 2023, 19:05:53 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Approvisionnement generated by hbm2java
 */
@Entity
@Table(name = "approvisionnement", catalog = "hopitalarstm_bd")
public class Approvisionnement implements java.io.Serializable {

	private Integer idApprovisionnement;
	private Medicament medicament;
	private String codeApprovisionnement;
	private Integer quantiteApprovisionnement;
	private Date dateApprovisionnement;

	public Approvisionnement() {
	}

	public Approvisionnement(Medicament medicament) {
		this.medicament = medicament;
	}

	public Approvisionnement(Medicament medicament, String codeApprovisionnement, Integer quantiteApprovisionnement,
			Date dateApprovisionnement) {
		this.medicament = medicament;
		this.codeApprovisionnement = codeApprovisionnement;
		this.quantiteApprovisionnement = quantiteApprovisionnement;
		this.dateApprovisionnement = dateApprovisionnement;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_APPROVISIONNEMENT", unique = true, nullable = false)
	public Integer getIdApprovisionnement() {
		return this.idApprovisionnement;
	}

	public void setIdApprovisionnement(Integer idApprovisionnement) {
		this.idApprovisionnement = idApprovisionnement;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MEDICAMENT", nullable = false)
	public Medicament getMedicament() {
		return this.medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	@Column(name = "CODE_APPROVISIONNEMENT", length = 10)
	public String getCodeApprovisionnement() {
		return this.codeApprovisionnement;
	}

	public void setCodeApprovisionnement(String codeApprovisionnement) {
		this.codeApprovisionnement = codeApprovisionnement;
	}

	@Column(name = "QUANTITE_APPROVISIONNEMENT")
	public Integer getQuantiteApprovisionnement() {
		return this.quantiteApprovisionnement;
	}

	public void setQuantiteApprovisionnement(Integer quantiteApprovisionnement) {
		this.quantiteApprovisionnement = quantiteApprovisionnement;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_APPROVISIONNEMENT", length = 10)
	public Date getDateApprovisionnement() {
		return this.dateApprovisionnement;
	}

	public void setDateApprovisionnement(Date dateApprovisionnement) {
		this.dateApprovisionnement = dateApprovisionnement;
	}

}
