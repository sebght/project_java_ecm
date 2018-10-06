package com.mco;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.JPanel;
import javax.xml.bind.Element;


public class Cartes extends JFrame implements ActionListener {

    public Cartes(String magasin,float arg) {
        super();
        JPanel card1 = new JPanel();
        JLabel label1 = new JLabel("Nb Articles");
        JLabel label2 = new JLabel(String.valueOf(0));
        JLabel label3 = new JLabel("Stock");
        JLabel label4 = new JLabel(String.valueOf(0));
        JLabel label5 = new JLabel("Argent");
        JLabel label6 = new JLabel(String.valueOf(arg)+" €");
        JButton button1 = new JButton("Acheter/Vendre");
        JButton button2 = new JButton("Bilan");
        JButton button3 = new JButton("Infos Article");
        JButton button4 = new JButton("Solder");
        Magasin m1 = new Magasin(magasin,arg);
        Article art1 = new Article("1pqf","Parapluie",false,20,25,"Umbrellaforlife","Oh il est beau le parapluie");
        Electromenager art2 = new Electromenager("9ekg","Aspirateur",false,120,210,"Aspi2000","Aspirateur de compét","Rowenta","toutes pièces");
        Habit art3 = new Habit("5hgl","Jupe",false,0,18.5,"Zara","Une jolie jupe pour l'été","Zara","S","Jaune");
        Primeur art4 = new Primeur("1ogg","Haricots verts",true,0,4.6,"Fermier","Des bons zaricots","Légumes","Légumes verts");
        m1.achat(art1,18);
        m1.vendre(art1,16);
        m1.achat(art2,3);
        m1.vendre(art2,2);
        m1.achat(art3,5);
        m1.achat(art4,3);
        setPreferredSize(new Dimension(300,350));
        setTitle("Gérer "+magasin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.setPreferredSize(new Dimension(125,50));
        button2.setPreferredSize(new Dimension(125,50));
        button3.setPreferredSize(new Dimension(125,50));
        button4.setPreferredSize(new Dimension(125,50));
        // Init grid
        setLayout(new BorderLayout());
        JPanel panecran = new JPanel();
        JPanel gauche = new JPanel();
        JPanel centre = new JPanel();
        JPanel droite = new JPanel();
        gauche.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        droite.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label1.setPreferredSize(new Dimension(90,45));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setPreferredSize(new Dimension(90,45));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label3.setPreferredSize(new Dimension(90,45));
        label3.setHorizontalAlignment(JLabel.CENTER);
        label4.setPreferredSize(new Dimension(90,45));
        label4.setHorizontalAlignment(JLabel.CENTER);
        label5.setPreferredSize(new Dimension(90,45));
        label5.setHorizontalAlignment(JLabel.CENTER);
        label6.setPreferredSize(new Dimension(90,45));
        label6.setHorizontalAlignment(JLabel.CENTER);
        gauche.add(label1,BorderLayout.NORTH);
        gauche.add(label2,BorderLayout.SOUTH);
        centre.add(label3,BorderLayout.NORTH);
        centre.add(label4,BorderLayout.SOUTH);
        droite.add(label5,BorderLayout.NORTH);
        droite.add(label6,BorderLayout.SOUTH);
        panecran.add(gauche,BorderLayout.WEST);
        panecran.add(centre,BorderLayout.CENTER);
        panecran.add(droite,BorderLayout.EAST);
        panecran.setPreferredSize(new Dimension(250,195));
        getContentPane().add(panecran,BorderLayout.NORTH);
        JPanel boutonssouth = new JPanel();
        JPanel boutonscenter = new JPanel();
//        boutons.setLayout(new FlowLayout());
//        boutons.setPreferredSize(new Dimension(200,50));
        boutonssouth.add(button3);
        boutonssouth.add(button4);
        boutonscenter.add(button1);
        boutonscenter.add(button2);
        getContentPane().add(boutonssouth,BorderLayout.SOUTH);
        getContentPane().add(boutonscenter,BorderLayout.CENTER);
        pack();
        final HashMap<String, Boolean> checkDispos = new HashMap<String, Boolean>();
        checkDispos.put("transac",true);
        checkDispos.put("bilan",true);
        checkDispos.put("infos_article",true);
        checkDispos.put("soldes",true);
        checkDispos.put("elec",false);
        checkDispos.put("habits",false);
        checkDispos.put("primeur",false);
        checkDispos.put("bilanArticle",false);
        checkDispos.put("getDescription",false);
        checkDispos.put("setDescription",false);
        final boolean[] checkSoldes = {false};
        final boolean[] checkSoldesMagasin = {false};
        final String[] choix_article = {new String()};
        final Article[] article = {new Article("x", "x", true, 1, 1, "x", "x")};
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDispos.get("elec")){
                    //faire apparaitre bilan de Electroménager
                    label2.setText(String.valueOf(m1.bilanElectromenager().get(0)));
                    label4.setText(String.valueOf(m1.bilanElectromenager().get(1)));
                    label6.setText(String.valueOf(m1.bilanElectromenager().get(2)));
                    setTitle("Gérer "+magasin);
                }
                else {
                    //faire apparaitre jDialog de transaction
                    JOptionPane jop1 = new JOptionPane();
                    String[] transaction = {"acheter","vendre"};
                    int rang = jop1.showOptionDialog(null,"Voulez-vous acheter ou vendre ?","Fenêtre de transaction",
                            JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,transaction,transaction[0]);
                    if (rang==0){
                        //effectuer l'achat
                        JOptionPane jop2 = new JOptionPane();
                        String[] categ = {"Electroménager","Habit","Primeur","Autre"};
                        int rang2 = jop2.showOptionDialog(null,"Quelle catégorie de produit voulez-vous acheter ?","Achat d'un produit",
                                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,categ,categ[0]);
                        String ref = jop2.showInputDialog(null,"Quel est la référence de l'article ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                        String nomArticle = jop2.showInputDialog(null,"Son nom ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                        double prixAchat = Double.valueOf(jop2.showInputDialog(null,"Son prix d'achat ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE));
                        double prixVente = Double.valueOf(jop2.showInputDialog(null,"Son prix de vente initial ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE));
                        int quant= Integer.valueOf(jop2.showInputDialog(null,"Combien voulez-vous en acheter ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE));
                        String nomFournisseur = jop2.showInputDialog(null,"Son fournisseur ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                        String description = jop2.showInputDialog(null,"Sa description ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                        if (rang2==0){
                            String marque = jop2.showInputDialog(null,"Sa marque ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                            String piece = jop2.showInputDialog(null,"La pièce dans laquelle il s'utilise ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                            try {
                                m1.achat(new Electromenager(ref,nomArticle,false,prixAchat,prixVente,nomFournisseur,description,marque,piece),quant);
                            } catch (Exception e1){
                                jop2.showMessageDialog(null,"Vous n'avez pas assez d'argent pour acheter ce produit ! ");
                            }
                        }
                        else if (rang2==1){
                            String marque = jop2.showInputDialog(null,"Sa marque ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                            String taille = jop2.showInputDialog(null,"Sa taille ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                            String couleur = jop2.showInputDialog(null,"Sa couleur ?","Achat d'un produit",JOptionPane.QUESTION_MESSAGE);
                            try {
                                m1.achat(new Habit(ref,nomArticle,false,prixAchat,prixVente,nomFournisseur,description,marque,taille,couleur),quant);
                            } catch (Exception e1){
                                jop2.showMessageDialog(null,"Vous n'avez pas assez d'argent pour acheter ce produit ! ");
                            }
                        }
                        else if (rang2==2){
                            String[] typeList = {"Fruit","Légume"};
                            String type = typeList[jop2.showOptionDialog(null,"Quelle catégorie de produit voulez-vous acheter ?","Achat d'un produit",
                                    JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,typeList,null)];
                            String[] isKilo = {"Kilo","Pièce"};
                            int kilo_piece = jop2.showOptionDialog(null,"Quelle catégorie de produit voulez-vous acheter ?","Achat d'un produit",
                                    JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,isKilo,null);
                            boolean check_kilo_piece;
                            if (kilo_piece==0)
                                check_kilo_piece=true;
                            else check_kilo_piece=false;
                            String sous_type;
                            if (type=="Fruit"){
                                String[] sous_typeList = {"Feuille","Tige","Fleur","Racine","Bulbe","Légume sec","Légume fruit","Tubercule"};
                                sous_type = (String)jop1.showInputDialog(null,"Quel type de "+type+" ?","Achat d'un produit"
                                        ,JOptionPane.QUESTION_MESSAGE,null, sous_typeList,null);
                            }
                            else {
                                String[] sous_typeList = {"Sec","Charnus","Multiple","Complexe","Composé"};
                                sous_type = (String)jop1.showInputDialog(null,"Quel type de "+type+" ?","Achat d'un produit"
                                        ,JOptionPane.QUESTION_MESSAGE,null, sous_typeList,null);
                            }
                            try {
                                m1.achat(new Primeur(ref,nomArticle,check_kilo_piece,prixAchat,prixVente,nomFournisseur,description,type,sous_type),quant);
                            } catch (Exception e1){
                                jop2.showMessageDialog(null,"Vous n'avez pas assez d'argent pour acheter ce produit ! ");
                            }
                        }
                    }

                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDispos.get("bilan")){
                    //faire apparaitre fenêtre de bilan
                    checkDispos.replace("bilan",false);
                    checkDispos.replace("infos_article",false);
                    checkDispos.replace("transac",false);
                    checkDispos.replace("soldes",false);
                    checkDispos.replace("elec",true);
                    checkDispos.replace("habits",true);
                    checkDispos.replace("primeur",true);
                    checkDispos.replace("bilanArticle",true);
                    button1.setText("Electroménager");
                    button2.setText("Habits");
                    button3.setText("Primeur");
                    button4.setText("Article");
                    label1.setText("Achats");
                    label2.setText(String.valueOf(m1.bilan().get(0)));
                    label3.setText("Ventes");
                    label4.setText(String.valueOf(m1.bilan().get(1)));
                    label5.setText("Marge");
                    label6.setText(String.valueOf(m1.bilan().get(2)));
                }
                else if (checkDispos.get("getDescription")){
                    //get description button
                    //faire le jDialog
                    JOptionPane jop1 = new JOptionPane();
                    jop1.showMessageDialog(null,"La description de l'article "+ article[0].getNomArticle() +" est : "+ article[0].getDescription());
                }
                else {
                    //afficher la vue bilan de Habits
                    label2.setText(String.valueOf(m1.bilanHabits().get(0)));
                    label4.setText(String.valueOf(m1.bilanHabits().get(1)));
                    label6.setText(String.valueOf(m1.bilanHabits().get(2)));
                    setTitle("Gérer "+magasin);
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDispos.get("infos_article")){
                    //faire apparaitre vue article
                    checkDispos.replace("bilan",false);
                    checkDispos.replace("infos_article",false);
                    checkDispos.replace("setDescription",true);
                    checkDispos.replace("getDescription",true);
                    button2.setText("Get Description");
                    button3.setText("Set Description");
                    label2.setText("0");
                    label4.setText("0");
                    label5.setText("Valeur théorique");
                    label6.setText("0");
                    JOptionPane jop1 = new JOptionPane();
                    String[] list_choix = new String[m1.getStock().keySet().size()];
                    int j=0;
                    for (String m:m1.getStock().keySet()){
                        list_choix[j]= m;
                        System.out.println(list_choix[j]);
                        j++;
                    }
                    String choix_article = (String)jop1.showInputDialog(null,"Quel article ?","Infos d'un article"
                            ,JOptionPane.QUESTION_MESSAGE,null, list_choix,null);
                    boolean a=false;
                    for (int i=0;i<m1.tableauArticles.size();i++){
                        if (a){}
                        else if (m1.tableauArticles.get(i).getRef()==choix_article){
                            article[0] =m1.tableauArticles.get(i);
                            a=true;
                        }
                    }
                    setTitle("Gestion Article : "+article[0].getNomArticle());
                }
                else if (checkDispos.get("primeur")){
                    //faire apparaitre bilan Primeur
                    label2.setText(String.valueOf(m1.bilanPrimeurs().get(0)));
                    label4.setText(String.valueOf(m1.bilanPrimeurs().get(1)));
                    label6.setText(String.valueOf(m1.bilanPrimeurs().get(2)));
                    setTitle("Gérer "+magasin);
                }
                else {
                    //set description button
                    //faire le jDialog
                    JOptionPane jop1 = new JOptionPane();
                    String new_description = jop1.showInputDialog(null,"Quel est la nouvelle description ?","Changer la description",JOptionPane.QUESTION_MESSAGE);
                    article[0].setDescription(new_description);
                    JOptionPane jop2 = new JOptionPane();
                    jop2.showMessageDialog(null,"La description de l'article "+ article[0].getNomArticle() +" est : "+ article[0].getDescription());
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDispos.get("setDescription")){
                    //activer/désactiver soldes
                    if (checkSoldes[0]){
                        article[0].arreterSoldes();
                        checkSoldes[0] =false;
                        button4.setText("Solder");
                    }
                    else {
                        JOptionPane jop1 = new JOptionPane();
                        double pourcentage = Float.valueOf(jop1.showInputDialog(null,"A combien de % voulez-vous solder votre article ?"));
                        article[0].debuterSoldes(pourcentage);
                        checkSoldes[0]=true;
                        button4.setText("Ne plus solder");
                    }
                }
                else if (checkDispos.get("infos_article")){
                    //activer/désactiver soldes du magasin entier
                    if (checkSoldesMagasin[0]){
                        m1.arreterSoldes();
                        checkSoldesMagasin[0]=false;
                        button4.setText("Solder");
                    }
                    else {
                        JOptionPane jop1 = new JOptionPane();
                        double pourcentage = Float.valueOf(jop1.showInputDialog(null,"A combien de % voulez-vous solder votre magasin ?"));
                        m1.debuterSoldes(pourcentage);
                        checkSoldesMagasin[0]=true;
                        button4.setText("Ne plus solder");
                    }
                }
                else {
                    //faire apparaitre le bilan d'un article précis
                    JOptionPane jop1 = new JOptionPane();
                    String[] list_choix = new String[m1.getStock().keySet().size()];
                    int j=0;
                    for (String m:m1.getStock().keySet()){
                        list_choix[j]= m;
                        System.out.println(list_choix[j]);
                        j++;
                    }
                    String choix_article = (String)jop1.showInputDialog(null,"Quel article ?","Bilan comptable d'un article"
                            ,JOptionPane.QUESTION_MESSAGE,null, list_choix,null);
                    boolean a=false;
                    for (int i=0;i<m1.tableauArticles.size();i++){
                        if (a){}
                        else if (m1.tableauArticles.get(i).getRef()==choix_article){
                            article[0] =m1.tableauArticles.get(i);
                            a=true;
                        }
                    }
                    label2.setText(String.valueOf(m1.bilanArticle(article[0]).get(0)));
                    label4.setText(String.valueOf(m1.bilanArticle(article[0]).get(1)));
                    label6.setText(String.valueOf(m1.bilanArticle(article[0]).get(2)));
                    setTitle("Bilan Article : "+article[0].getNomArticle());
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
class EssaiCartes {
    public static void main(String[] arg) {
        JOptionPane jop1 = new JOptionPane();
        JOptionPane jop2 = new JOptionPane();
        String nom = jop1.showInputDialog(null,"Quel est le nom de votre magasin ?","Initialisation magasin",JOptionPane.QUESTION_MESSAGE);
        Float x = Float.valueOf(jop2.showInputDialog(null,"Combien votre magasin possède-t-il d'argent ?","Argent du magasin",JOptionPane.QUESTION_MESSAGE));
        new Cartes(nom,x);
    }
}