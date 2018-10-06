package com.mco;

import java.util.*;

public class Magasin implements GestionStock, Solde{
    String magasin;
    ArrayList<Article> tableauArticles=new ArrayList<>();
    ArrayList<Double> tableauPrixAvantSoldes=new ArrayList<>();
    Map<String, Integer> stock = new HashMap<>();
    Map<String,Double> achats = new HashMap<>();
    Map<String,Double> ventes = new HashMap<>();
    double nbAchats = 0;
    double nbVentes = 0;
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
            nbAchats+=quantite;
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
            nbVentes+=quantite;
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
//        System.out.println(tableauPrixAvantSoldes);
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
            try {
                sommeAchats+=achats.get(article.getRef());
            } catch (Exception e){
                sommeAchats+=0;
                System.out.println("Aucun achat de cet article n'a été répertoriée");
            }

            try {
                sommeVentes+=ventes.get(article.getRef());
            } catch (Exception e) {
                sommeVentes+=0;
                System.out.println("Aucune vente de cet article n'a été répertoriée");
            }
        }
        //On fait la différence pour obtenir le bilan comptable du magasin:
        marge=sommeVentes-sommeAchats;
        //On retourne tout dans une arrayList de type double. On accède donc au résultat comme ceci:
        ArrayList<Double> mesResultats=new ArrayList<>();
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(marge);
        return mesResultats;
    }

    public ArrayList<Double> bilanArticle(Article article){
        ArrayList<Double> mesResultats= new ArrayList<>();
        double sommeAchats=0;
        double sommeVentes=0;
        try {
            sommeAchats+=achats.get(article.getRef());
        } catch (Exception e){
            sommeAchats+=0;
            System.out.println("Aucun achat de cet article n'a été répertoriée");
        }

        try {
            sommeVentes+=ventes.get(article.getRef());
        } catch (Exception e) {
            sommeVentes+=0;
            System.out.println("Aucune vente de cet article n'a été répertoriée");
        }
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(sommeVentes - sommeAchats);
        return mesResultats;
    }

    public ArrayList<Double> bilanElectromenager(){
        double sommeAchats=0;
        double sommeVentes=0;
        double marge;
        //Je créée un set de strings dans laquelle je mettrai toutes les references de produits electromenagers du magasin
        //On créée un set car le set n'acceptera pas l'ajout d'un duplicata
        Set<String> mesReferences=new HashSet<>();
        for (Article art : tableauArticles){
            if (art instanceof Electromenager){
                mesReferences.add(art.getRef());
            }
        }
        // On parcourt le set et on cherche les achats et ventes pour chaque référence pour les sommer et faire le bilan
        for (String ref : mesReferences){
            try {
                sommeAchats+=achats.get(ref);
            } catch (Exception e){
                sommeAchats+=0;
                System.out.println("Aucun achat de cet article n'a été répertoriée");
            }
            try {
                sommeVentes+=ventes.get(ref);
            } catch (Exception e) {
                sommeVentes += 0;
                System.out.println("Aucune vente de cet article n'a été répertoriée");
            }
        }
        marge=sommeVentes-sommeAchats;
        ArrayList<Double> mesResultats= new ArrayList<>();
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(marge);
        return mesResultats;
    }

    public ArrayList<Double> bilanPrimeurs(){
        double sommeAchats=0;
        double sommeVentes=0;
        double marge;
        //Je créé un set de strings dans laquelle je mettrai toutes les references de Primeurs du magasin
        //On créé un set car le set n'acceptera pas l'ajout d'un duplicata
        Set<String> mesReferences=new HashSet<>();
        for (Article art : tableauArticles){
            if (art instanceof Primeur){
                mesReferences.add(art.getRef());
            }
        }
        // On parcourt le set et on cherche les achats et ventes pour chaque référence pour les sommer et faire le bilan
        for (String ref : mesReferences){
            try {
                sommeAchats+=achats.get(ref);
            } catch (Exception e){
                sommeAchats+=0;
                System.out.println("Aucun achat de cet article n'a été répertoriée");
            }
            try {
                sommeVentes+=ventes.get(ref);
            } catch (Exception e) {
                sommeVentes += 0;
                System.out.println("Aucune vente de cet article n'a été répertoriée");
            }
        }
        marge=sommeVentes-sommeAchats;
        ArrayList<Double> mesResultats= new ArrayList<>();
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(marge);
        return mesResultats;
    }

    public ArrayList<Double> bilanHabits(){
        double sommeAchats=0;
        double sommeVentes=0;
        double marge;
        //Je créé un set de strings dans laquelle je mettrai toutes les references d'habits du magasin
        //On créé un set car le set n'acceptera pas l'ajout d'un duplicata
        Set<String> mesReferences=new HashSet<>();
        for (Article art : tableauArticles){
            if (art instanceof Habit){
                mesReferences.add(art.getRef());
            }
        }
        // On parcourt le set et on cherche les achats et ventes pour chaque référence pour les sommer et faire le bilan
        for (String ref : mesReferences){
            try {
                sommeAchats+=achats.get(ref);
            } catch (Exception e){
                sommeAchats+=0;
                System.out.println("Aucun achat de cet article n'a été répertoriée");
            }
            try {
                sommeVentes+=ventes.get(ref);
            } catch (Exception e) {
                sommeVentes += 0;
                System.out.println("Aucune vente de cet article n'a été répertoriée");
            }
        }
        marge=sommeVentes-sommeAchats;
        ArrayList<Double> mesResultats= new ArrayList<>();
        mesResultats.add(sommeAchats);
        mesResultats.add(sommeVentes);
        mesResultats.add(marge);
        return mesResultats;
    }

    public void setTableauArticles(ArrayList<Article> tableauArticles) {
        this.tableauArticles = tableauArticles;
    }

    public Map<String, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<String, Integer> stock) {
        this.stock = stock;
    }

    public Map<String, Double> getAchats() {
        return achats;
    }

    public void setAchats(Map<String, Double> achats) {
        this.achats = achats;
    }

    public Map<String, Double> getVentes() {
        return ventes;
    }

    public void setVentes(Map<String, Double> ventes) {
        this.ventes = ventes;
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
