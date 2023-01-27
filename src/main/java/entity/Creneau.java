package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "creneaux")
public class Creneau implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "version")
    private int version;
    
    @Column(name = "hd_debut")
    private int hdDebut;
    
    @Column(name = "md_debut")
    private int mdDebut;
    
    @Column(name = "h_fin")
    private int hFin;
    
    @Column(name = "m_fin")
    private int mFin;

    @OneToMany(mappedBy = "creneau")
    private List<RV> rv;

    @ManyToOne
    @JoinColumn(name = "medecins_id")
    private Medecin medecin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getHdDebut() {
        return hdDebut;
    }

    public void setHdDebut(int hdDebut) {
        this.hdDebut = hdDebut;
    }

    public int getMdDebut() {
        return mdDebut;
    }

    public void setMdDebut(int mdDebut) {
        this.mdDebut = mdDebut;
    }

    public int gethFin() {
        return hFin;
    }

    public void sethFin(int hFin) {
        this.hFin = hFin;
    }

    public int getmFin() {
        return mFin;
    }

    public void setmFin(int mFin) {
        this.mFin = mFin;
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
