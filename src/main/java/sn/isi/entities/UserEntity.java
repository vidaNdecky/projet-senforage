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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String nom;

    @Column(length = 150, nullable = false)
    private String prenom;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 150, nullable = false)
    private String password;

    @Column(length = 150, nullable = false)
    private String role;

    @OneToMany(mappedBy = "utilisateur")
    private List<ClientEntity> clients = new ArrayList<>();
}
