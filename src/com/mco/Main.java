package com.mco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
    /*
	// write your code here
        ArrayList<Article> tableArticles = new ArrayList<>();
        Article article = new Article("46h","Buie",true,120,150,"Orange",0);
        tableArticles.add(article);
        Article article2 = new Article("47h","Buie",true,120,140,"Orange",0);
        tableArticles.add(article2);
        Magasin magasin = new Magasin("Auchan",tableArticles,30000);
        article.vendre(1);
        article.achat(28);
        article2.achat(27);
//        for (int i=0;i<tableArticles.size();i++)
//            System.out.println(tableArticles.get(i).getStock());
//        System.out.println(article.getPrixVente());
//        article.debuterSoldes(50);
//        System.out.println(article.getPrixVente());
//        article.arreterSoldes();
        System.out.println(article.getPrixVente());
        System.out.println(magasin);
        magasin.debuterSoldes(50);
        System.out.println(article.getPrixVente());
        System.out.println(article2.getPrixVente());
        magasin.arreterSoldes();
        System.out.println(article.getPrixVente());
        System.out.println(article2.getPrixVente());
        */

        //Je créé un magasin
        Magasin magasin1=new Magasin("Auchan Vieux Port",30000);
        System.out.println(magasin1.getArgent());
        System.out.println(magasin1.getTableauArticles());
        //Je créé un article
        Article article1= new Article("0000","Parapluie",false,4,10,"myUmbrella.com","Ceci est un parapluie");
        Primeur orange= new Primeur("0001","Parapluie",false,2,7,"myUmbrella.com","Ceci est un parapluie","Fruit","Agrume");
        Primeur orange2= new Primeur("0002","Parapluie",false,5,7,"myUmbrella.com","Ceci est un parapluie","Fruit","Agrume");
        //J'en achete 10:
        magasin1.achat(article1,10);
        magasin1.achat(orange,25);
        magasin1.achat(orange2,25);
        System.out.println(magasin1.getArgent());
        System.out.println(magasin1.getTableauArticles());
        //J'en vend 5
        magasin1.vendre(article1,5);
        magasin1.vendre(orange,10);
        magasin1.vendre(orange2,12);
        System.out.println(magasin1.getArgent());
        System.out.println(magasin1.getTableauArticles());
        System.out.println(magasin1.bilan());
        //Je fais -50% sur tout le magasin
        magasin1.debuterSoldes(50);
        //J'en revend un
        magasin1.vendre(article1,1);
        System.out.println(magasin1.getArgent());
        //j'arrête les soldes
        magasin1.arreterSoldes();
        System.out.println(magasin1.bilanArticle(article1));
        System.out.println(magasin1.bilanArticle(orange));
        System.out.println("-------------------");
        System.out.println(magasin1.bilanElectromenager());
    }

}
