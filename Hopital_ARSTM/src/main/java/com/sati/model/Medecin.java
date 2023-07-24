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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Medecin generated by hbm2java
 */
@Entity
@Table(name = "medecin", catalog = "hopitalarstm_bd")
public class Medecin implements java.io.Serializable {

	private Integer idMedecin;
	private Genre genre;
	private Specialite specialite;
	private UserAuthentication userAuthentication;
	private String codeMedecin;
	private String nomMedecin;
	private String prenomsMedecin;
	private String telephoneMedecin;
	private String emailMedecin;
	private Set<Consultation> consultations = new HashSet<Consultation>(0);
	private Set<UserAuthentication> userAuthentications = new HashSet<UserAuthentication>(0);

	public Medecin() {
	}

	public Medecin(Genre genre, Specialite specialite, UserAuthentication userAuthentication) {
		this.genre = genre;
		this.specialite = specialite;
		this.userAuthentication = userAuthentication;
	}

	public Medecin(Genre genre, Specialite specialite, UserAuthentication userAuthentication, String codeMedecin,
			String nomMedecin, String prenomsMedecin, String telephoneMedecin, String emailMedecin,
			Set<Consultation> consultations, Set<UserAuthentication> userAuthentications) {
		this.genre = genre;
		this.specialite = specialite;
		this.userAuthentication = userAuthentication;
		this.codeMedecin = codeMedecin;
		this.nomMedecin = nomMedecin;
		this.prenomsMedecin = prenomsMedecin;
		this.telephoneMedecin = telephoneMedecin;
		this.emailMedecin = emailMedecin;
		this.consultations = consultations;
		this.userAuthentications = userAuthentications;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_MEDECIN", unique = true, nullable = false)
	public Integer getIdMedecin() {
		return this.idMedecin;
	}

	public void setIdMedecin(Integer idMedecin) {
		this.idMedecin = idMedecin;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_GENRE", nullable = false)
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SPECIALITE", nullable = false)
	public Specialite getSpecialite() {
		return this.specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false)
	public UserAuthentication getUserAuthentication() {
		return this.userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	@Column(name = "CODE_MEDECIN", length = 10)
	public String getCodeMedecin() {
		return this.codeMedecin;
	}

	public void setCodeMedecin(String codeMedecin) {
		this.codeMedecin = codeMedecin;
	}

	@Column(name = "NOM_MEDECIN", length = 25)
	public String getNomMedecin() {
		return this.nomMedecin;
	}

	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	@Column(name = "PRENOMS_MEDECIN", length = 50)
	public String getPrenomsMedecin() {
		return this.prenomsMedecin;
	}

	public void setPrenomsMedecin(String prenomsMedecin) {
		this.prenomsMedecin = prenomsMedecin;
	}

	@Column(name = "TELEPHONE_MEDECIN", length = 15)
	public String getTelephoneMedecin() {
		return this.telephoneMedecin;
	}

	public void setTelephoneMedecin(String telephoneMedecin) {
		this.telephoneMedecin = telephoneMedecin;
	}

	@Column(name = "EMAIL_MEDECIN", length = 50)
	public String getEmailMedecin() {
		return this.emailMedecin;
	}

	public void setEmailMedecin(String emailMedecin) {
		this.emailMedecin = emailMedecin;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medecin")
	public Set<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medecin")
	public Set<UserAuthentication> getUserAuthentications() {
		return this.userAuthentications;
	}

	public void setUserAuthentications(Set<UserAuthentication> userAuthentications) {
		this.userAuthentications = userAuthentications;
	}

}
