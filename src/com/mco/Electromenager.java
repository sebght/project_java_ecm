package com.mco;

import java.util.Map;

public class Electromenager extends Article {
    String brand;
    String use;

    public Electromenager(String ref, String nomArticle, boolean estKilo, double prixAchat, double prixVente, String nomFournisseur, String description, String brand, String use) {
        super(ref, nomArticle, estKilo, prixAchat, prixVente, nomFournisseur, description);
        this.brand = brand;
        this.use = use;
    }
}
