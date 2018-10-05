package com.mco;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Magasin implements GestionStock, Solde{
    String magasin;
    ArrayList<Article> tableauArticles=new ArrayList<>();
    ArrayList<Double> tableauPrixAvantSoldes=new ArrayList<>();
    Map<String, Integer> stock = new HashMap<>();
    Map<String,Double> achats = new HashMap<>();
    Map<String,Double> ventes = new HashMap<>();
    double argent=0;

    //On créé un magasin défini uniquement par son nom et son capital d'argent initial
    public Magasin(String magasin, float argent) {
        this.magasin = magasin;
        this.argent=argent;
    }

    public void achat(Article article , int quantite){
        //Si pas assez d'argent dans le magasin pour acheter le produit:
        if (article.getPrixAchat()*quantite>argent)
            System.out.println("Votre magasin ne peut pas se permettre d'acheter autant de produits de ce type, il n'a pas assez d'argent !");
        else {
            //On retire l'argent et on ajoute l'article le nombre de fois adéquat dans le tableau des articles
            argent-=article.getPrixAchat()*quantite;
            for (int i=0; i<quantite;i++) {
                tableauArticles.add(article);
            }
            //Si le magasin possède déjà au moins un exemplaire de l'article
            if (stock.containsKey(article.getRef()))
                stock.replace(article.getRef(), stock.get(article.getRef()) + quantite);
            //Si le magasin ne possède pas l'article
            else
                stock.put(article.getRef(), quantite);
            // ajout de l'article dans la liste achats avec son prix d'achat
            if (achats.containsKey(article.getRef())){
                achats.replace(article.getRef(), achats.get(article.getRef()) + quantite*article.getPrixAchat());
            }
            else {
                achats.put(article.getRef(),quantite*article.getPrixAchat());
            }

        }
    }

    public void vendre(Article article, int quantite) {
        if (stock.containsKey(article.getRef()) &&  stock.get(article.getRef())-quantite>=0){
            stock.replace(article.getRef(),stock.get(article.getRef())-quantite);
            argent+=article.getPrixVente()*quantite;
            for (int i=0; i<quantite;i++) {
                tableauArticles.remove(article);
            }
            if (ventes.containsKey(article.getRef())){
                ventes.replace(article.getRef(), ventes.get(article.getRef()) + quantite*article.getPrixVente());
            }
            else {
                ventes.put(article.getRef(),quantite*article.getPrixVente());
            }
        }
        else if (stock.containsKey(article.getRef()) &&  stock.get(article.getRef())-quantite<0){
            if (article.isEstKilo())
                System.out.println("Le produit n'est présent qu'en "+stock.get(article.getRef())+" exemplaires dans le stock. Tu ne peux donc pas en vendre "+quantite+" kg.");
            else
                System.out.println("Le produit n'est présent qu'en "+stock.get(article.getRef())+" exemplaires dans le stock. Tu ne peux donc pas en vendre "+quantite+" unités.");
        }
        else{
            System.out.println("Le produit n'est pas en stock, tu ne peux donc pas le vendre dans une si grande quantité.");
        }
    }

    @Override
    public void debuterSoldes(double percentage) {
        for (int i=0;i<tableauArticles.size();i++){
            if (i==0){
                tableauPrixAvantSoldes.add(tableauArticles.get(i).getPrixVente());
                tableauArticles.get(i).setPrixVente(tableauArticles.get(i).getPrixVente()*(100-percentage)/100);
            }
            else {
                //On veut modifier le prix de l'article une seule fois si j'ai plusieurs itérations du meme article
                //Si l'article i est différent de l'article i-1
                if (tableauArticles.get(i).getRef()!=tableauArticles.get(i-1).getRef()){
                    tableauPrixAvantSoldes.add(tableauArticles.get(i).getPrixVente());
                    tableauArticles.get(i).setPrixVente(tableauArticles.get(i).getPrixVente()*(100-percentage)/100);
                }
                else{
                    //Sinon j'ajoute dans mon tableau de prix avant les soldes la valeurs i-1
                    tableauPrixAvantSoldes.add(tableauPrixAvantSoldes.get(i-1));
                }
            }

        }
    }

    @Override
    public void arreterSoldes() {
        System.out.println(tableauPrixAvantSoldes);
        for (int i=0;i<tableauArticles.size();i++){
            tableauArticles.get(i).setPrixVente(tableauPrixAvantSoldes.get(i));
        }
    }

    public ArrayList<Double> bilan(){
        double sommeAchats=0;
        double sommeVentes=0;
        double marge;
        //On somme les prix d'achats puis de vente de tous les articles
        for (Article article : tableauArticles){
            sommeAchats+=achats.get(article.getRef());
            sommeVentes+=ventes.get(article.getRef());
        }
        //On fait la différence pour obtenir le bilan comptable du magasin:
        marge=sommeVentes-sommeAchats;
        //On retourne tout dans une arrayList de type double. On accède donc au résultat comme ceci:
        //mesResultats[0]=sommeAchats
        //mesResultats[0]=sommeVentes
        //mesResultats[0]=marge
        ArrayList<Double> mesResultats=new ArrayList<>();
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(marge);
        return mesResultats;
    }

    public ArrayList<Double> bilanArticle(Article article){
        double sommeAchats=0;
        double sommeVentes=0;
        double marge;
        for (Article art : tableauArticles){
            if (art.getRef()==article.getRef()){
                sommeAchats+=achats.get(article.getRef());
                sommeVentes+=ventes.get(article.getRef());
            }
        }
        marge=sommeVentes-sommeAchats;
        ArrayList<Double> mesResultats= new ArrayList<>();
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(marge);
        return mesResultats;
    }

    public double getArgent() {
        return argent;
    }

    public ArrayList<Article> getTableauArticles() {
        return tableauArticles;
    }

    public void setArgent(double argent) {
        this.argent = argent;
    }
    public void deleteArticle(Article article){
        tableauArticles.remove(article);
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
