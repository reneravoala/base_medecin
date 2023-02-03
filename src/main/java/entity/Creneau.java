package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "creneaux")
public class Creneau implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "version")
    private Integer version;
    
    @Column(name = "hd_debut")
    private Integer hdDebut;
    
    @Column(name = "md_debut")
    private Integer mdDebut;
    
    @Column(name = "h_fin")
    private Integer hFin;
    
    @Column(name = "m_fin")
    private Integer mFin;

    @OneToMany(mappedBy = "creneau")
    @JsonIgnore
    private List<RV> rv;

    @ManyToOne
    @JoinColumn(name = "medecins_id")
    private Medecin medecin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getHdDebut() {
        return hdDebut;
    }

    public void setHdDebut(Integer hdDebut) {
        this.hdDebut = hdDebut;
    }

    public Integer getMdDebut() {
        return mdDebut;
    }

    public void setMdDebut(Integer mdDebut) {
        this.mdDebut = mdDebut;
    }

    public Integer gethFin() {
        return hFin;
    }

    public void sethFin(Integer hFin) {
        this.hFin = hFin;
    }

    public Integer getmFin() {
        return mFin;
    }

    public void setmFin(Integer mFin) {
        this.mFin = mFin;
    }

    public String getHeureDebut() {
        return hdDebut + ":" + mdDebut;
    }

    public String getHeureFin() {
        return hFin + ":" + mFin;
    }

    public List<RV> getRv() {
        return rv;
    }

    public void setRv(List<RV> rv) {
        this.rv = rv;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
}
