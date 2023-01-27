package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rv")
public class RV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "jour")
    private Date jour;

    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "creneaux_id")
    private Creneau creneau;

}
