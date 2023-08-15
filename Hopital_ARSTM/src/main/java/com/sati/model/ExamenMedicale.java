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
 * ExamenMedicale generated by hbm2java
 */
@Entity
@Table(name = "examen_medicale", catalog = "hopitalarstm_bd")
public class ExamenMedicale implements java.io.Serializable {

	private Integer idExamen;
	private String codeExamen;
	private String nomExamen;
	private Long cout;
	private Set<Realiser> realisers = new HashSet<Realiser>(0);

	public ExamenMedicale() {
	}

	public ExamenMedicale(String codeExamen, String nomExamen, Long cout, Set<Realiser> realisers) {
		this.codeExamen = codeExamen;
		this.nomExamen = nomExamen;
		this.cout = cout;
		this.realisers = realisers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_EXAMEN", unique = true, nullable = false)
	public Integer getIdExamen() {
		return this.idExamen;
	}

	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}

	@Column(name = "CODE_EXAMEN", length = 10)
	public String getCodeExamen() {
		return this.codeExamen;
	}

	public void setCodeExamen(String codeExamen) {
		this.codeExamen = codeExamen;
	}

	@Column(name = "NOM_EXAMEN", length = 25)
	public String getNomExamen() {
		return this.nomExamen;
	}

	public void setNomExamen(String nomExamen) {
		this.nomExamen = nomExamen;
	}

	@Column(name = "COUT", precision = 12, scale = 0)
	public Long getCout() {
		return this.cout;
	}

	public void setCout(Long cout) {
		this.cout = cout;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examenMedicale")
	public Set<Realiser> getRealisers() {
		return this.realisers;
	}

	public void setRealisers(Set<Realiser> realisers) {
		this.realisers = realisers;
	}

}
