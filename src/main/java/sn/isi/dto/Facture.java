package sn.isi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {
    private int numFacture;
    @NotNull
    private String consoLettre;
    @NotNull
    private double consoChiffre;
    @NotNull
    private double prix;
}
