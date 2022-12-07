package sn.isi.service;

import sn.isi.dto.Facture;

import java.util.List;

public interface IFactureService {
    public Facture createFacture(Facture facture);
    public Facture updateFacture(int id, Facture facture);
    public void deleteFacture(int id);
    public Facture getFacture(int id);
    public List<Facture> getFactures();
}
