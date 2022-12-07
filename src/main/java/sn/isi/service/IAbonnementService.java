package sn.isi.service;

import sn.isi.dto.Abonnement;

import java.util.List;

public interface IAbonnementService {
    public Abonnement createAbonnement(Abonnement abonnement);
    public Abonnement updateAbonnement(int id, Abonnement abonnement);
    public void deleteAbonnement(int id);
    public Abonnement getAbonnement(int id);
    public List<Abonnement> getAbonnements();
}
