import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class Console {
	private static Box b2 = Box.createHorizontalBox();
	private static JButton rouge = new JButton("Rouge");
	private static JButton vert = new JButton("Vert");
	private static JButton orange = new JButton("Orange");
	private static JButton jaune = new JButton("Jaune");
	private static JButton violet = new JButton("Violet");
	private static JButton  bleu = new JButton("Bleu");
	private static JButton jouer = new JButton("Jugar");
	private static JButton retour = new JButton("Regresar al menú");
	private static Box boutoncouleur = Box.createVerticalBox(); // on crée La box qui contiendra tout les boutons	
	private static JPanel menumiddle = new JPanel();
	private static JPanel topmenu = new JPanel ();
	private static JPanel optionmenu = new JPanel ();
	private static JPanel coucou = new JPanel(); // On crée la Box qui contiendra la phrase informant le cours de la partie	     
	private static JPanel scoreleft = new JPanel ();
	private static JPanel scorerght = new JPanel ();
	private static JLabel nomjeu = new JLabel ("    Juego de los 6 colores  ");	
	public static boolean IA=false;
	public static Fenetre ftr = new Fenetre();
	public static Joueur J1 = new Joueur();
	public static Joueur J2 = new Joueur();
	public static IA Ordi = new IA();
	public static int ia = 0;
	public static JPanel fond = new AfficheImage("C:/Users/aymeric/workspace/6colores/imagefond.jpg");
	public static Font font = new Font("Calibri", Font.BOLD,20);
	public static JLabel victoire = new JLabel ();
	
	
	public static void main(String[] args) {		
		// TODO Auto-generated method stub
		menuJeu();
		ftr.playSound("musique.wav");
		
	}
	
	
	
	public static void menuJeu(){
		   ftr.setContentPane(fond); 
		   ftr.setLayout(new BorderLayout());
		   Font font = new Font("Optima", Font.BOLD, 50);
		   JButton options = new JButton("Opciones");
		   options.addActionListener(new BoutonListenerOptions());
		   JButton credits = new JButton("Créditos");
		   jouer.addActionListener(new BoutonListenerJouer());
		   nomjeu.setForeground(Color.WHITE);
		   nomjeu.setFont(font);		  	
		   menumiddle.add(nomjeu);
		   menumiddle.add(jouer);
		   menumiddle.add(options);
		   menumiddle.add(credits);
		   menumiddle.setOpaque(false);		   
		   topmenu.setPreferredSize(new Dimension(50*10+200, 250)); // ces dimmensions
	       topmenu.setOpaque(false);
		   ftr.add(topmenu, BorderLayout.NORTH);
		   ftr.add(menumiddle, BorderLayout.CENTER);		   
		   ftr.setVisible(true); 		   
	   }
	public static void menuChoix(){	
		JPanel contentGrille = new JPanel();
		ftr.setLigCol(10,10);
	    ftr.aprt = new int[10][10];
	    ftr.setCell(10, 10);
	    ftr.setColorCase(10,10);
	    ftr.setLayout(new BorderLayout()); 
	    contentGrille = ftr.getConGril();
	    ftr.creationGrille(contentGrille,J1,J2);	         
		b2.add(rouge);		
		b2.add(orange);
		b2.add(jaune);
		b2.add(violet);
		b2.add(vert);
		b2.add(bleu);      
	    rouge.addActionListener(new BoutonListenerRouge());
	    orange.addActionListener(new BoutonListenerOrange());
	    jaune.addActionListener(new BoutonListenerJaune());
	    violet.addActionListener(new BoutonListenerViolet());
	    vert.addActionListener(new BoutonListenerVert());
	    bleu.addActionListener(new BoutonListenerBleu()); 
       
	    boutoncouleur.add(b2);	    
	    coucou.setPreferredSize(new Dimension(50*10+200, 100)); // ces dimmensions
	    coucou.setOpaque(false);
	    scoreleft.setPreferredSize(new Dimension(100, 50*10+200));
	    scoreleft.setOpaque(false);
	    scorerght.setPreferredSize(new Dimension(100, 50*10+200));
	    scorerght.setOpaque(false);
        ftr.topphrase.setForeground(Color.WHITE);       
        Font font = new Font("Calibri", Font.BOLD,20);
		ftr.topphrase.setFont(font);
        coucou.add(ftr.topphrase); // on y ajoute la phrase
        ftr.validate();
	    ftr.setTourde(); // On set la 1ère phrase	        
	    ftr.getContentPane().add(ftr.contentGrille, BorderLayout.CENTER); //On AJOUTE LA GRILLE qu'on positionne au centre
	    ftr.getContentPane().add(boutoncouleur, BorderLayout.SOUTH);   // On la place au Sud     
	    ftr.getContentPane().add(scorerght, BorderLayout.EAST);
	    ftr.getContentPane().add(scoreleft, BorderLayout.WEST);    
	    ftr.getContentPane().add(coucou, BorderLayout.NORTH);       //ajoute la box en haut qui servira pour le titre	
	    ftr.validate();
	    ftr.repaint();
	    ftr.setVisible(true);  
	   }
	
	public static void menuOptions(){
		JRadioButton advIA = new JRadioButton("IA");
		advIA.setForeground(Color.WHITE);
		advIA.setOpaque(false);
		advIA.addActionListener(new BoutonListenerIA());
		JRadioButton advHumain = new JRadioButton("Humano");
		ButtonGroup group = new ButtonGroup(); // On groupe les boutons radio ensemble
		group.add(advIA);
		group.add(advHumain);
		advHumain.setForeground(Color.WHITE);
		advHumain.setOpaque(false);
		advHumain.addActionListener(new BoutonListenerHumain());
		if (ia==0)
		advHumain.setSelected(true);
		if (ia==1)
		advIA.setSelected(true);
		advIA.setSize(new Dimension(25,25));
		advHumain.setSize(25, 25);
		optionmenu.add(advIA);
		optionmenu.add(advHumain);
		optionmenu.setOpaque(false);		
		retour.addActionListener(new BoutonListenerRetour());
		ftr.add(optionmenu, BorderLayout.CENTER);
		ftr.add(retour, BorderLayout.SOUTH);
		ftr.setVisible(true);
	}
	public static void victoire(int J){
		ftr.remove(ftr.contentGrille);
		ftr.remove(boutoncouleur);
		ftr.remove(coucou);	
		System.out.println("El jugador 1 gana !!");	
		Font font = new Font("Optima", Font.BOLD, 50);
		victoire.setFont(font);
		victoire.setForeground(Color.WHITE);
		victoire.setText("El jugador "+J+" gagna !!");
		retour.addActionListener(new BoutonListenerRetour());
		ftr.add(victoire, BorderLayout.CENTER);
		ftr.add(retour, BorderLayout.SOUTH);
		ftr.validate();
		ftr.repaint();
		
		
	}
	 
	   static class AfficheImage extends JPanel 
	   { 
	   Image eau; 

	   AfficheImage(String s) 
	   { 
	   eau = getToolkit().getImage(s); 
	   } 

	   public void paintComponent(Graphics g) 
	   { 
	   super.paintComponent(g); 
	   g.drawImage(eau, 0, 0, getWidth(), getHeight(), this); 
	   } 
	   } 
	   
	   public static void ActionBouton(int color){
		   if (ftr.tour2==1 && J1.getColorJ()!=color && J2.getColorJ()!=color && Ordi.getColorJ()!=color ){ // si c'est le tour du Joueur 1 et qu'il a choisit une couleur différente de la sienne et du joueur 2
		       ftr.Tour(color,1,J1,J2);  // On execute le Tour du joueur 1 pour la couleur rouge
		       
		       if (ia==0){
		       ftr.tour2=2; // On prévient que le Joueur 1 a fini son tour
		       }
		       System.out.println("Le joueur 1 a un score de "+J1.getScore());
		       ftr.playSound("713.wav");
		       ftr.setTourde();
		       if(J1.getScore()>=50){
		    	   victoire(1);		    	   
		       }
		     }
		   if (ftr.tour2==2 && J1.getColorJ()!=color && J2.getColorJ()!=color && Ordi.getColorJ()!=color ){
		    	 ftr.Tour(color,2,J1,J2);
		    	 ftr.tour2=1;
		   		 System.out.println("Le joueur 2 a un score de "+J2.getScore());
		   		 ftr.playSound("713.wav");
		   		 ftr.setTourde();
		   		if(J1.getScore()>=50){
			    	   victoire(2);		    	   
			       }
		    	 } 
		   if(Ordi.getScore()>=50){
	    	   victoire(2);		    	   
	       }
	  		}
	   
	   
	   
	   
	   
	   //----------Classes qui écoutent les bouttons--------------
	   static class BoutonListenerRouge implements ActionListener{
	     //Redéfinition de la méthode actionPerformed()
	     public void actionPerformed(ActionEvent arg0) {
	    	ActionBouton(1);
	    	if (ia==1)
	    		ftr.TourIA(J1, Ordi);
	   }
	   }
	   static class BoutonListenerOrange implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ActionBouton(2);
		    	 if (ia==1)
			    		ftr.TourIA(J1, Ordi);	
		   }
	   }
	   static class BoutonListenerJaune implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ActionBouton(3);
		    	 if (ia==1)
			    		ftr.TourIA(J1, Ordi);
		     }
		   }
	   static class BoutonListenerVert implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ActionBouton(4);
		    	 if (ia==1)
			    		ftr.TourIA(J1, Ordi);			   
		     }
		   }
	   static class BoutonListenerBleu implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ActionBouton(5);
		    	 if (ia==1)
			    		ftr.TourIA(J1, Ordi);
		     }
		   }
	   static class BoutonListenerViolet implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ActionBouton(6);
		    	 if (ia==1)
			    	ftr.TourIA(J1, Ordi);			 
		     }
	   }
	
	   static class BoutonListenerJouer implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ftr.remove(menumiddle);
		    	 ftr.remove(topmenu);
		    	 menuChoix();
			   }
		     }	 	     
	   static class BoutonListenerOptions implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ftr.remove(menumiddle);
		    	 menuOptions();
		    	 ftr.repaint();
			   }
		     }
	   static class BoutonListenerIA implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ia=1;
			   }
		     }
	   static class BoutonListenerHumain implements ActionListener{
		     //Redéfinition de la méthode actionPerformed()
		     public void actionPerformed(ActionEvent arg0) {
		    	 ia=0;
			   }
		     }
	   static class BoutonListenerRetour implements ActionListener{
			     //Redéfinition de la méthode actionPerformed()
			     public void actionPerformed(ActionEvent arg0) {
			    	 ftr.remove(victoire);
			 		 ftr.remove(scoreleft);
					 ftr.remove(scorerght);
			    	 ftr.remove(optionmenu);
			    	 optionmenu.removeAll();
			    	 ftr.remove(retour);
			    	 ftr.add(topmenu, BorderLayout.NORTH);
			    	 ftr.add(menumiddle, BorderLayout.CENTER);
			    	 ftr.resetGame(J1,J2,Ordi); // Réinitalise tout le tableau
			    	 ftr.validate();
			    	 ftr.repaint();
				   }
			     }
	}
	
	
	
	
