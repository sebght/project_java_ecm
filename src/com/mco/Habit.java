package com.mco;

import java.util.Map;

public class Habit extends Article{
    String marque;
    String taille;
    String color;

    public Habit(String ref, String nomArticle, boolean estKilo, double prixAchat, double prixVente, String nomFournisseur, String description, String marque, String taille, String color) {
        super(ref, nomArticle, estKilo, prixAchat, prixVente, nomFournisseur, description);
        this.marque = marque;
        this.taille = taille;
        this.color = color;
    }
}
