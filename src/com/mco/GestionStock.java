package com.mco;

import java.util.Map;

public interface GestionStock {
    public void achat(Article article, int quantite);
    public void vendre(Article article, int quantite);
}
