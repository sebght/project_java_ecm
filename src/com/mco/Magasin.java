package com.mco;

import java.util.ArrayList;

public class Magasin implements Solde{
    String magasin;
    ArrayList<Article> tableauArticles;
    ArrayList<Double> tableauPrixAvantSoldes=new ArrayList<>();
    double argent=0;

    public Magasin(String magasin, ArrayList<Article> tableauArticles,float argent) {
        this.magasin = magasin;
        this.tableauArticles = tableauArticles;
        this.argent=argent;
    }

    @Override
    public void debuterSoldes(double percentage) {
        for (int i=0;i<tableauArticles.size();i++){
            tableauPrixAvantSoldes.add(tableauArticles.get(i).getPrixVente());
            tableauArticles.get(i).setPrixVente(tableauArticles.get(i).getPrixVente()*(100-percentage)/100);
        }
    }

    @Override
    public void arreterSoldes() {
        for (int i=0;i<tableauArticles.size();i++){
            tableauArticles.get(i).setPrixVente(tableauPrixAvantSoldes.get(i));
        }
    }

    public double getArgent() {
        return argent;
    }

    public void setArgent(double argent) {
        this.argent = argent;
    }

    @Override
    public String toString() {
        return "Magasin{" +
                "magasin='" + magasin + '\'' +
                ", tableauArticles=" + tableauArticles +
                ", argent=" + argent +
                '}';
    }
}
