package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @Column(name = "ID")
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

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Collection<RV> rv;

    public Client() {
    }

    public Client(Integer id, Integer version, String titre, String nom, String prenom) {
        this.id = id;
        this.version = version;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
    }

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
    public Collection<RV> getRv() {
        return rv;
    }

    public void setRv(Collection<RV> rv) {
        this.rv = rv;
    }
}
