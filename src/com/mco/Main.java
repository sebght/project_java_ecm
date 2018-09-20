package com.mco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
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
    }

}
