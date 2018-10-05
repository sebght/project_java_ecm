package com.mco;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Article implements Solde{
    String ref;
    String nomArticle;
    boolean estKilo;
    double prixAchat;
    double prixVente;
    double prixAvantSolde;
    String nomFournisseur;
    Map<String, Double> stock = new HashMap<>();
    Map<String,Double> achats = new HashMap<>();
    Map<String,Double> ventes = new HashMap<>();
    String description;



    public Article(String ref, String nomArticle, boolean estKilo, double prixAchat, double prixVente,  String nomFournisseur,  String description) {
        this.ref = ref;
        this.nomArticle = nomArticle;
        this.estKilo = estKilo;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.nomFournisseur = nomFournisseur;
        this.description=description;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public boolean isEstKilo() {
        return estKilo;
    }

    public void setEstKilo(boolean estKilo) {
        this.estKilo = estKilo;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getPrixAvantSolde() {
        return prixAvantSolde;
    }

    public void setPrixAvantSolde(double prixAvantSolde) {
        this.prixAvantSolde = prixAvantSolde;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public Map<String, Double> getStock() {
        return stock;
    }

    public void setStock(Map<String, Double> stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Double> getAchats() {
        return achats;
    }

    public Map<String, Double> getVentes() {
        return ventes;
    }



    @Override
    public void debuterSoldes(double percentage) {
        prixAvantSolde=prixVente;
        prixVente=prixVente*(100-percentage)/100;
    }


    @Override
    public void arreterSoldes() {
        prixVente=prixAvantSolde;
    }


}
