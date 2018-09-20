package com.mco;
import java.util.HashMap;
import java.util.Map;

public class Article implements GestionStock,Solde{
    String ref;
    String nomArticle;
    Magasin magasin;
    boolean estKilo;
    double prixAchat;
    double prixVente;
    double prixAvantSolde;
    String nomFournisseur;
    Map<String, Double> stock = new HashMap<>();
    double stockProduit;


    public Article(String ref, String nomArticle,Magasin magasin, boolean estKilo, double prixAchat, double prixVente,  String nomFournisseur, double stockProduit) {
        this.ref = ref;
        this.nomArticle = nomArticle;
        this.magasin=magasin;
        this.estKilo = estKilo;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.nomFournisseur = nomFournisseur;
        this.stockProduit = stockProduit;
        stock.put(ref,stockProduit);
    }

    public void achat(double quantite){
        if (prixAchat*quantite>magasin.getArgent())
            System.out.println("Votre magasin ne peut pas se permettre d'acheter autant de produits de ce type, il n'a pas assez d'argent !");
        else {
            if (stock.containsKey(ref))
                stock.replace(ref, stock.get(ref) + quantite);
            else
                stock.put(ref, quantite);
        }
    }

    public void vendre(double quantite) {
        if (stock.containsKey(ref) &&  stock.get(ref)-quantite>=0){
            stock.replace(ref,stock.get(ref)-quantite);
            magasin.setArgent(magasin.getArgent()+prixVente);
        }
        else if (stock.containsKey(ref) &&  stock.get(ref)-quantite<0){
            if (estKilo)
                System.out.println("Le produit n'est présent qu'en "+stock.get(ref)+" exemplaires dans le stock. Tu ne peux donc pas en vendre "+quantite+" kg.");
            else
                System.out.println("Le produit n'est présent qu'en "+stock.get(ref)+" exemplaires dans le stock. Tu ne peux donc pas en vendre "+quantite+" unités.");
        }
        else{
            System.out.println("Le produit n'est pas en stock, tu ne peux donc pas le vendre dans une si grande quantité.");
        }
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
