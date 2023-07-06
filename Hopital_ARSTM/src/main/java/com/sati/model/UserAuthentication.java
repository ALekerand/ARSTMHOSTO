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
 * UserAuthentication generated by hbm2java
 */
@Entity
@Table(name = "user_authentication", catalog = "hopitalarstm_bd")
public class UserAuthentication implements java.io.Serializable {

	private Integer userId;
	private String username;
	private String password;
	private Boolean enabled;
	private String nom;
	private String prenoms;
	private String email;
	private String telephone;
	private Set<Caisse> caisses = new HashSet<Caisse>(0);
	private Set<Consultation> consultations = new HashSet<Consultation>(0);
	private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);

	public UserAuthentication() {
	}

	public UserAuthentication(String username, String password, Boolean enabled, String nom, String prenoms,
			String email, String telephone, Set<Caisse> caisses, Set<Consultation> consultations,
			Set<UserAuthorization> userAuthorizations) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.telephone = telephone;
		this.caisses = caisses;
		this.consultations = consultations;
		this.userAuthorizations = userAuthorizations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "USER_ID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "USERNAME", length = 15)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 15)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ENABLED")
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "NOM", length = 25)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "PRENOMS", length = 50)
	public String getPrenoms() {
		return this.prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "TELEPHONE", length = 15)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAuthentication")
	public Set<Caisse> getCaisses() {
		return this.caisses;
	}

	public void setCaisses(Set<Caisse> caisses) {
		this.caisses = caisses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAuthentication")
	public Set<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAuthentication")
	public Set<UserAuthorization> getUserAuthorizations() {
		return this.userAuthorizations;
	}

	public void setUserAuthorizations(Set<UserAuthorization> userAuthorizations) {
		this.userAuthorizations = userAuthorizations;
	}

}