package sn.isi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {
    private int numeroAbonnement;
    @NotNull
    private String dateAbonnement;
    @NotNull
    private String description;
    @NotNull
    private int numCompteur;
    @NotNull
    private double cumulConsomation;
}
