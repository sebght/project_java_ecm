package com.mco;

import java.util.Map;

public class Primeur extends Article {
    String type;
    String sous_type;

    public Primeur(String ref, String nomArticle, boolean estKilo, double prixAchat, double prixVente, String nomFournisseur, String description, String type, String sous_type) {
        super(ref, nomArticle, estKilo, prixAchat, prixVente, nomFournisseur, description);
        this.type = type;
        this.sous_type = sous_type;
    }
}
