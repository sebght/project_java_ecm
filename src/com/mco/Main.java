package com.mco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //voir la classe EssaiCartes dans Cartes.java pour l'affichage
        Magasin m1 = new Magasin("Vieux Port", 3000);
        Article art1 = new Article("1pqf","Parapluie",false,20,25,"Umbrellaforlife","Oh il est beau le parapluie");
        Primeur art4 = new Primeur("1ogg","Haricots verts",true,3,5,"Fermier","Des bons zaricots","Légumes","Légumes verts");
        m1.achat(art4,10);
        m1.achat(art1,10);
        m1.achat(art4,2);
        m1.achat(art1,2);
        m1.vendre(art1,5 );
        m1.vendre(art4,5 );
        System.out.println(m1.bilan());
        System.out.println(m1.bilanArticle(art4));
        System.out.println(m1.bilanArticle(art1));
        System.out.println(m1.bilanPrimeurs());
        System.out.println(m1.bilanElectromenager());
        System.out.println(m1.bilanHabits());
        System.out.println(m1.getStock());
    }

}
