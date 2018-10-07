package com.mco;

import java.util.Map;

public interface GestionStock {
    public void achat(Article article, double quantite);
    public void vendre(Article article, double quantite);
}
