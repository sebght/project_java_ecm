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
        Article art1 = new Article("1pqf","Parapluie",false,0,25,"Umbreallaforlife","Oh il est beau le parapluie");
        Electromenager art2 = new Electromenager("9ekg","Aspirateur",false,0,210,"Aspi2000","Aspirateur de compét","Rowenta","toutes pièces");
        Habit art3 = new Habit("5hgl","Jupe",false,0,18.5,"Zara","Une jolie jupe pour l'été","Zara","S","Jaune");
        Primeur art4 = new Primeur("1ogg","Haricots verts",true,0,4.6,"Fermier","Des bons zaricots","Légumes","Légumes verts");
        m1.achat(art1,18);
        m1.achat(art2,3);
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
                }
                else {
                    //faire apparaitre jDialog de transaction
//                    JOptionPane jop3 = new JOptionPane();
//                    String[] transaction = {"acheter","vendre"};
//                    int rang = jop3.showOptionDialog(null,"Voulez-vous acheter ou vendre ?","Fenêtre de transaction",
//                            JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,transaction,transaction[0]);
//                    JOptionPane jop4 = new JOptionPane();
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
                    label3.setText("Dépenses");
                    label4.setText(String.valueOf(m1.bilan().get(1)));
                    label5.setText("Marge");
                    label6.setText(String.valueOf(m1.bilan().get(2)));
                }
                else if (checkDispos.get("getDescription")){
                    //get description button
                    //faire le jDialog
                    JOptionPane jop6 = new JOptionPane();
                    jop6.showMessageDialog(null,"La description de l'article "+ article[0].getNomArticle() +" est : "+ article[0].getDescription());
                }
                else {
                    //afficher la vue bilan de Habits
                    label2.setText(String.valueOf(m1.bilanHabits().get(0)));
                    label4.setText(String.valueOf(m1.bilanHabits().get(1)));
                    label6.setText(String.valueOf(m1.bilanHabits().get(2)));
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
                    JOptionPane jop5 = new JOptionPane();
                    String[] list_choix = new String[m1.getStock().keySet().size()];
                    int j=0;
                    for (String m:m1.getStock().keySet()){
                        list_choix[j]= m;
                        System.out.println(list_choix[j]);
                        j++;
                    }
                    String choix_article = (String)jop5.showInputDialog(null,"Quel article ?","Infos d'un article"
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
                else if (checkDispos.get("elec")){
                    //faire apparaitre bilan Primeur
                    label2.setText(String.valueOf(m1.bilanHabits().get(0)));
                    label4.setText(String.valueOf(m1.bilanHabits().get(1)));
                    label6.setText(String.valueOf(m1.bilanHabits().get(2)));
                }
                else {
                    //set description button
                    //faire le jDialog
                    JOptionPane jop8 = new JOptionPane();
                    String new_description = jop8.showInputDialog(null,"Quel est la nouvelle description ?","Changer la description",JOptionPane.QUESTION_MESSAGE);
                    article[0].setDescription(new_description);
                    JOptionPane jop9 = new JOptionPane();
                    jop9.showMessageDialog(null,"La description de l'article "+ article[0].getNomArticle() +" est : "+ article[0].getDescription());
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDispos.get("setDescription")){
                    //activer/désactiver soldes
                    
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
        JOptionPane jop = new JOptionPane();
        JOptionPane jop2 = new JOptionPane();
        String nom = jop.showInputDialog(null,"Quel est le nom de votre magasin ?","Initialisation magasin",JOptionPane.QUESTION_MESSAGE);
        Float x = Float.valueOf(jop.showInputDialog(null,"Combien votre magasin possède-t-il d'argent ?"));
        new Cartes(nom,x);
    }
}