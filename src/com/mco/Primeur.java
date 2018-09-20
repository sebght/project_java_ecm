package com.mco;

import java.util.Map;

public class Primeur extends Article {
    String type;
    String sous_type;

    public Primeur(String ref, String nomArticle, boolean estKilo, double prixAchat, double prixVente, String nomFournisseur, double stockProduit, String type, String sous_type) {
        super(ref, nomArticle, estKilo, prixAchat, prixVente, nomFournisseur, stockProduit);
        this.type = type;
        this.sous_type = sous_type;
    }
}
