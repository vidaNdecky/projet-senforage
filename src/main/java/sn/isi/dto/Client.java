package sn.isi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private int id;
    @NotNull
    private String nom;
    @NotNull
    private String nomChefVillage;
    @NotNull
    private String village;
    @NotNull
    private String adresse;
    @NotNull
    private int numTelephone;
}
