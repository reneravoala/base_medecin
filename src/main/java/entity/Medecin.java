package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "medecins")
public class Medecin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "version")
    private Integer version;

    @Column(name = "titre")
    private String titre;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @OneToMany(mappedBy = "medecin")
    @JsonIgnore
    private List<Creneau> creneaux;

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomComplet() {
        return titre + " " + nom + " " + prenom;
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(List<Creneau> creneaux) {
        this.creneaux = creneaux;
    }
}
