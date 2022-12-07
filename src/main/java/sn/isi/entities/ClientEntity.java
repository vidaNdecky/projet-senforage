package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String nom;

    @Column(length = 150, nullable = false)
    private String nomChefVillage;

    @Column(length = 150, nullable = false)
    private String village;

    @Column(length = 150, nullable = false)
    private String adresse;

    @Column(length = 150, nullable = false)
    private int numTelephone;

    @OneToMany(mappedBy = "client")
    private List<AbonnementEntity> abonnements = new ArrayList<>();

    @ManyToOne
    private UserEntity utilisateur;
}
