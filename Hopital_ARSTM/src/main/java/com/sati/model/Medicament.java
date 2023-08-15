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
 * Medicament generated by hbm2java
 */
@Entity
@Table(name = "medicament", catalog = "hopitalarstm_bd")
public class Medicament implements java.io.Serializable {

	private Integer idMedicament;
	private String codeMedicament;
	private String nomMedicament;
	private Integer stockActuel;
	private Integer stockAlerte;
	private Long coutMedicament;
	private Set<LigneAchat> ligneAchats = new HashSet<LigneAchat>(0);
	private Set<Approvisionnement> approvisionnements = new HashSet<Approvisionnement>(0);

	public Medicament() {
	}

	public Medicament(String codeMedicament, String nomMedicament, Integer stockActuel, Integer stockAlerte,
			Long coutMedicament, Set<LigneAchat> ligneAchats, Set<Approvisionnement> approvisionnements) {
		this.codeMedicament = codeMedicament;
		this.nomMedicament = nomMedicament;
		this.stockActuel = stockActuel;
		this.stockAlerte = stockAlerte;
		this.coutMedicament = coutMedicament;
		this.ligneAchats = ligneAchats;
		this.approvisionnements = approvisionnements;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_MEDICAMENT", unique = true, nullable = false)
	public Integer getIdMedicament() {
		return this.idMedicament;
	}

	public void setIdMedicament(Integer idMedicament) {
		this.idMedicament = idMedicament;
	}

	@Column(name = "CODE_MEDICAMENT", length = 10)
	public String getCodeMedicament() {
		return this.codeMedicament;
	}

	public void setCodeMedicament(String codeMedicament) {
		this.codeMedicament = codeMedicament;
	}

	@Column(name = "NOM_MEDICAMENT", length = 100)
	public String getNomMedicament() {
		return this.nomMedicament;
	}

	public void setNomMedicament(String nomMedicament) {
		this.nomMedicament = nomMedicament;
	}

	@Column(name = "STOCK_ACTUEL")
	public Integer getStockActuel() {
		return this.stockActuel;
	}

	public void setStockActuel(Integer stockActuel) {
		this.stockActuel = stockActuel;
	}

	@Column(name = "STOCK_ALERTE")
	public Integer getStockAlerte() {
		return this.stockAlerte;
	}

	public void setStockAlerte(Integer stockAlerte) {
		this.stockAlerte = stockAlerte;
	}

	@Column(name = "COUT_MEDICAMENT", precision = 12, scale = 0)
	public Long getCoutMedicament() {
		return this.coutMedicament;
	}

	public void setCoutMedicament(Long coutMedicament) {
		this.coutMedicament = coutMedicament;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicament")
	public Set<LigneAchat> getLigneAchats() {
		return this.ligneAchats;
	}

	public void setLigneAchats(Set<LigneAchat> ligneAchats) {
		this.ligneAchats = ligneAchats;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicament")
	public Set<Approvisionnement> getApprovisionnements() {
		return this.approvisionnements;
	}

	public void setApprovisionnements(Set<Approvisionnement> approvisionnements) {
		this.approvisionnements = approvisionnements;
	}

}
