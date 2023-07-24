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
 * Caisse generated by hbm2java
 */
@Entity
@Table(name = "caisse", catalog = "hopitalarstm_bd")
public class Caisse implements java.io.Serializable {

	private Integer idCaisse;
	private Consultation consultation;
	private FacturePharmacie facturePharmacie;
	private Realiser realiser;
	private UserAuthentication userAuthentication;
	private String codeCaisse;
	private Long montantCaisse;
	private Date dateEnregistrement;
	private Set<Consultation> consultations = new HashSet<Consultation>(0);
	private Set<FacturePharmacie> facturePharmacies = new HashSet<FacturePharmacie>(0);
	private Set<Realiser> realisers = new HashSet<Realiser>(0);

	public Caisse() {
	}

	public Caisse(Consultation consultation, FacturePharmacie facturePharmacie, Realiser realiser,
			UserAuthentication userAuthentication) {
		this.consultation = consultation;
		this.facturePharmacie = facturePharmacie;
		this.realiser = realiser;
		this.userAuthentication = userAuthentication;
	}

	public Caisse(Consultation consultation, FacturePharmacie facturePharmacie, Realiser realiser,
			UserAuthentication userAuthentication, String codeCaisse, Long montantCaisse, Date dateEnregistrement,
			Set<Consultation> consultations, Set<FacturePharmacie> facturePharmacies, Set<Realiser> realisers) {
		this.consultation = consultation;
		this.facturePharmacie = facturePharmacie;
		this.realiser = realiser;
		this.userAuthentication = userAuthentication;
		this.codeCaisse = codeCaisse;
		this.montantCaisse = montantCaisse;
		this.dateEnregistrement = dateEnregistrement;
		this.consultations = consultations;
		this.facturePharmacies = facturePharmacies;
		this.realisers = realisers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_CAISSE", unique = true, nullable = false)
	public Integer getIdCaisse() {
		return this.idCaisse;
	}

	public void setIdCaisse(Integer idCaisse) {
		this.idCaisse = idCaisse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONSULTATION", nullable = false)
	public Consultation getConsultation() {
		return this.consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FACTURE_PHARMACIE", nullable = false)
	public FacturePharmacie getFacturePharmacie() {
		return this.facturePharmacie;
	}

	public void setFacturePharmacie(FacturePharmacie facturePharmacie) {
		this.facturePharmacie = facturePharmacie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REALISER", nullable = false)
	public Realiser getRealiser() {
		return this.realiser;
	}

	public void setRealiser(Realiser realiser) {
		this.realiser = realiser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public UserAuthentication getUserAuthentication() {
		return this.userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	@Column(name = "CODE_CAISSE", length = 10)
	public String getCodeCaisse() {
		return this.codeCaisse;
	}

	public void setCodeCaisse(String codeCaisse) {
		this.codeCaisse = codeCaisse;
	}

	@Column(name = "MONTANT_CAISSE", precision = 12, scale = 0)
	public Long getMontantCaisse() {
		return this.montantCaisse;
	}

	public void setMontantCaisse(Long montantCaisse) {
		this.montantCaisse = montantCaisse;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ENREGISTREMENT", length = 19)
	public Date getDateEnregistrement() {
		return this.dateEnregistrement;
	}

	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caisse")
	public Set<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caisse")
	public Set<FacturePharmacie> getFacturePharmacies() {
		return this.facturePharmacies;
	}

	public void setFacturePharmacies(Set<FacturePharmacie> facturePharmacies) {
		this.facturePharmacies = facturePharmacies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caisse")
	public Set<Realiser> getRealisers() {
		return this.realisers;
	}

	public void setRealisers(Set<Realiser> realisers) {
		this.realisers = realisers;
	}

}
