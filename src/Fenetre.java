import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
 
public class Fenetre extends JFrame implements ActionListener { 
	public static int nbLig;
	public static int nbCol;
	public static int aprt[][]= new int[nbLig][nbCol];
	public static int ColorCase[][]= new int[nbLig][nbCol];
	public static JPanel cell[][]= new JPanel[nbLig][nbCol]; 
	public static JPanel contentGrille = new JPanel();
	public Dimension dim = new Dimension(700,700);
	public static JLabel topphrase = new JLabel("C'est ton tour !");
	
	
	public int tour2 = 1;
	
	public Fenetre() // Construteur par défaut
	{
		this.setTitle("6 Couleurs");
		this.setSize(dim);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());     
		this.setVisible(true);
	}

	
    public Fenetre(int a, int b) { // constructeur de ma fenetre	
   
    }
 
     
    public void creationGrille(JPanel contentGrille, Joueur J1, Joueur J2){ // Création de la grille en fct de nos paramètres

          //On définit le layout manager
         contentGrille.setLayout(new GridLayout(nbCol, nbLig, 3, 3));//on définit la taille de la grille de 10 sur 10
    	 for(int i=0; i<cell.length; i++){
            for(int j=0; j<cell.length; j++){
                cell[i][j]= new JPanel();
                cell[i][j].setSize(new Dimension(50, 50));
                aprt[i][j]=0;
                int nombreAlea= 1 + (int)(Math.random()*((6))); // permet d'avoir un entier compris entre 1 et 6				
                cell[i][j].setBackground(giveC(nombreAlea));
                ColorCase[i][j]=nombreAlea;			    				
                contentGrille.add(cell[i][j]);         
            }
        }
	    JLabel  lab=new JLabel("1");  //créé un label valant 1 	 
        cell[0][0].add(lab);        
        JLabel  lab2=new JLabel("2");  //créé un label valant 2 	     
        cell[nbCol-1][nbLig-1].add(lab2);
        aprt[0][0]=1; // on précise que la premiere case appartient au joueur 1        
        ColorCase[0][0]=0;       
        aprt[nbCol-1][nbLig-1]=2; // on précise que la premiere case appartient au joueur 2       
        ColorCase[nbCol-1][nbLig-1]=0;
        setCouleur(J1,J2); 
    }
    
    public void Tour(int c, int a,Joueur J1,Joueur J2)
	 {
		 if (a==1) //si c'est le tour du joueur 1
		 { 
			for (int x=0; x<5; x++){
			for(int i=0; i<nbLig; i++){
		           for(int j=0; j<nbCol; j++){
		            if (aprt[i][j]==1) // On parcourt le tableau pour savoir les cases qui nous appartiennt
		            	{
		            		if (i!=nbLig-1 && ColorCase[i+1][j]==c && aprt[i+1][j]==0 && aprt[i+1][j] != 2 && aprt[i+1][j] != 1) // Si la case situé en dessous n'est pas a nous ni a J2 et de la couleur qu'on voulait
		            		{
		            			aprt[i+1][j]=1; // On dit que cette case est désormais au joueur 1 (=1)
		            			J1.setScore(1); // On ajoute +1 au score
		            			setChiffre(i+1,j,1);// On ajoute le label
		            		}
		            		if (j!= nbCol-1 && ColorCase[i][j+1]==c && aprt[i][j+1]==0 && aprt[i][j+1] != 2 && aprt[i][j+1] != 1) // Si la case situé a sa droite n'est pas a nous ni a J2 et de la couleur qu'on voulait
		            		{
		            			aprt[i][j+1]=1; // On dit que cette case est désormais au joueur 1 (=1)
		            			J1.setScore(1); // On ajoute +1 au score
		            			setChiffre(i,j+1,1);
		            		}
		            		if (i!=0){
		            		if (ColorCase[i-1][j]==c && aprt[i-1][j]==0)
		            		{
		            			aprt[i-1][j]=1; // On dit que cette case est désormais au joueur 1 (=1)
		            			J1.setScore(1); // On ajoute +1 au score
		            			setChiffre(i-1,j,1);
		            		}
		            		}
		            		if (j!=0){
		            		if (ColorCase[i][j-1]==c && aprt[i][j-1]==0)
		            		{
		            			aprt[i][j-1]=1; // On dit que cette case est désormais au joueur 1 (=1)
		            			J1.setScore(1); // On ajoute +1 au score
		            			setChiffre(i,j-1,1);
		            		}
		            		}
		            	J1.setColorJ(c);		            		
		            	}			
		            }
		            }
			}
			setCouleur(J1,J2);	
			validate();	
		 } 
		 if (a==2)// si c'est le tour du Joueur 2
		 { 
			 for (int x=0; x<5; x++){
			 for(int i=0; i<nbLig; i++){
		           for(int j=0; j<nbCol; j++){
		        	   if (aprt[i][j]==2) // On parcourt le tableau pour savoir les cases qui nous appartiennt
		            	{
		            		if (i!=nbLig-1 && ColorCase[i+1][j]==c && aprt[i+1][j]==0) // Si la case situé en dessous n'est pas a nous ni a J2 et de la couleur qu'on voulait
		            		{
		            			aprt[i+1][j]=2; // On dit que cette case est désormais au joueur 2 (=1)
		            			J2.setScore(1); // On ajoute +1 au score
		            			setChiffre(i+1,j,2);// On ajoute le label
		            		}
		            		if (j!= nbCol-1 && ColorCase[i][j+1]==c && aprt[i][j+1]==0) // Si la case situé a sa droite n'est pas a nous ni a J2 et de la couleur qu'on voulait
		            		{
		            			aprt[i][j+1]=2; // On dit que cette case est désormais au joueur 2 (=1)
		            			J2.setScore(1); // On ajoute +1 au score
		            			setChiffre(i,j+1,2);
		            		}
		            		if (i!=0){
		            		if (ColorCase[i-1][j]==c && aprt[i-1][j]==0)
		            		{
		            			aprt[i-1][j]=2; // On dit que cette case est désormais au joueur 1 (=1)
		            			J2.setScore(1); // On ajoute +1 au score
		            			setChiffre(i-1,j,2);
		            		}
		            		}
		            		if (j!=0){
		            		if (ColorCase[i][j-1]==c && aprt[i][j-1]==0)
		            		{
		            			aprt[i][j-1]=2; // On dit que cette case est désormais au joueur 1 (=1)
		            			J2.setScore(1); // On ajoute +1 au score
		            			setChiffre(i,j-1,2);
		            		}
		            		}			            	
		            	J2.setColorJ(c);			            		
		            	}				
		            }
		            }
			 }
			 setCouleur(J1,J2);
			 validate();
		 }
	 }
  
  public void TourIA(Joueur J1, IA Ordi){
		int c;
		int d[]= new int[7];
		c=Ordi.getColorJ();
		for (int x=1; x<7; x++){
		d[x]=BestChoice(x, Ordi);		// Le programme test le score remporté hypothétique pour chaque couleur
		}
		d[J1.getColorJ()]=0; // On dis à l'IA que le score pour choisir sa propre couleur est négatif, ainsi il ne fera jamais ce choix
		c=Ordi.MaxTab(d); // Le programme prend l'indice max du tableau, càd la couleur qui donnera le score le plus grand
		
		
		for (int x=0; x<3; x++){
			 for(int i=0; i<nbLig; i++){
		           for(int j=0; j<nbCol; j++){
		            if (aprt[i][j]==2) // On parcourt le tableau pour savoir les cases qui nous appartiennt
		            	{
		            		if (i!=0 && ColorCase[i-1][j]==c && aprt[i-1][j]==0 && aprt[i-1][j] != 1 ) // Si la case situé a sa droite n'est pas a nous ni a J1 et de la couleur qu'on voulait
		            		{
		            			aprt[i-1][j]=2; // On dit que cette case est désormais au joueur 2 (=1)
		            			Ordi.setScore(1); // On ajoute +1 au score
		            			setChiffre(i-1,j,2);
		            		}
		            		if (j!=0 && ColorCase[i][j-1]==c && aprt[i][j-1]==0 && aprt[i][j-1] != 1 ) // Si la case situé a sa droite n'est pas a nous ni a J1 et de la couleur qu'on voulait
		            		{
		            			aprt[i][j-1]=2; // On dit que cette case est désormais au joueur 2 (=1)
		            			Ordi.setScore(1); // On ajoute +1 au score
		            			setChiffre(i,j-1,2);
		            		}
		            		if (i!=nbCol-1){ 
			           		if (ColorCase[i+1][j]==c && aprt[i+1][j]==0)
			            		{		            			
			            		aprt[i+1][j]=2; // On dit que cette case est désormais au joueur 2 (=1)
			            		Ordi.setScore(1); // On ajoute +1 au score
			           			setChiffre(i+1,j,2);
			            		}
			            		}
			           		if (j!=nbLig-1){
			           		if (ColorCase[i][j+1]==c && aprt[i][j+1]==0)
			            		{
			           			aprt[i][j+1]=2; // On dit que cette case est désormais au joueur 2 (=1)
			           			Ordi.setScore(1); // On ajoute +1 au score
			           			setChiffre(i,j+1,2);
			            		}
			            		}
		            		
		            	Ordi.setColorJ(c);
		            	ColorCase[i][j]=c;
		            		
		            	}									
		            }
		            }
			 }
			 setCouleur(J1,Ordi);
			 validate();
			 tour2=1; // On prévient que l'IA a fini son tour et c'est au tour du joueur 1					
	}   
 
    
    
  //*************   ACCESSEURS *************
    
    //Retourne le tableau aprt
	  public int[][] getAprt()  {  
	    return aprt;
	  }
	  
	//Retourne les cellules
	  public JPanel[][] getCell()  {  
	    return cell;
	  }
	  //retourne le ColorCase
	  public int[][] getColorCase()  {  
		    return ColorCase;
		  }
	//Retourne le contentPannel
	  public JPanel getConGril()  {  
	    return contentGrille;
	  }
	 //Retourne une couleur
	  public static Color giveC(int a) {
		  switch (a){
		  	case 0:
				return Color.BLACK;
		  	case 1:
				return Color.RED;
			case 2:
				return Color.ORANGE;
			case 3:
				return Color.YELLOW;
			case 4:
				return Color.GREEN;
			case 5:
				return Color.BLUE;
			case 6:
				return Color.MAGENTA;						
			default:
				return Color.BLACK;
		  }
		
	  }
	  // Retourne le meilleur choix pour l'IA
	  
	  public int BestChoice(int color, IA Ordi){
			int scorehypt=0;
			int tabordi[][]= Ordi.CopieTab(aprt,nbLig,nbCol);
				for(int i=0; i<nbLig; i++){
			           for(int j=0; j<nbCol; j++){
			           if (tabordi[i][j]==2) // On parcourt le tableau pour savoir les cases qui nous appartiennt
			           {
		            		if (i!=0 && ColorCase[i-1][j]==color && tabordi[i-1][j]==0 && tabordi[i-1][j] != 1 && tabordi[i-1][j] != 2 ) // Si la case situé a sa droite n'est pas a nous ni a J1 et de la couleur qu'on voulait
		            		{
		            			tabordi[i-1][j]=2; // On dit que cette case est désormais au joueur 2 (=1)
		            			scorehypt++;// On ajoute +1 au score hypothètique	            			
		            		}
		            		if (j!=0 && ColorCase[i][j-1]==color && tabordi[i][j-1]==0 && tabordi[i][j-1] != 1 && tabordi[i][j-1] != 2) // Si la case situé a sa droite n'est pas a nous ni a J1 et de la couleur qu'on voulait
		            		{
		            			tabordi[i][j-1]=2; // On dit que cette case est désormais au joueur 2 (=1)
		            			scorehypt++; // On ajoute +1 au score hypothétique	            			
		            		}
			           		}
			           }
					}
				
			return (scorehypt);
		}
	  
	  
  //*************   MUTATEURS   *************
	  public void setCouleur(Joueur J1, Joueur J2){

	      
		   for(int i=0; i<cell.length; i++){
	            for(int j=0; j<cell.length; j++){
	            	if (aprt[i][j]==1){ // si la case appartient au joueur 1
	            		cell[i][j].setBackground(giveC(J1.getColorJ()));	            		
	            	}
	            	if (aprt[i][j]==2){ // si la case appartient au joueur 2
	            		cell[i][j].setBackground(giveC(J2.getColorJ()));
	            	} 
	            	

	            }
		   }
	  }

	   public void setLigCol(int a, int b){
		   nbLig=a;
		   nbCol=b;
	   }
	   public void setCell(int a, int b){
		  cell= new JPanel[a][b];
	   }
	   public void setAprt(int a, int b, int c){
			  aprt[a][b]=c;
		   }
	   public void setColorCase(int a, int b){
			  ColorCase= new int[a][b];
		   }
	   public void setChiffre(int i, int j, int d){
		   if (d==1){
			   JLabel  lab=new JLabel("1");  //créé un label valant 1 
			   getCell()[i][j].add(lab);
		   }
		   else{
			   JLabel  lab2=new JLabel("2");  //créé un label valant 2
			   getCell()[i][j].add(lab2);			   
		   }
	   }
	   
	   public void setTourde(){
		   topphrase.setAlignmentX(50);
		   topphrase.setAlignmentY(CENTER_ALIGNMENT);
		   if (tour2==1)
		   topphrase.setText("Es el turno del Jugador 1");
		   if (tour2==2)
			topphrase.setText("Es el turno del jugador 2");
	   }
	   
	   public void removeGrille(int x){ // Fonction qui enlève/ajoute la grille.
		   if (x==0)
			   remove(contentGrille);
		   else
			   add(contentGrille);
	   }
	   public void resetGame(Joueur J1, Joueur J2, IA Ordi){
		  aprt= new int[nbLig][nbCol];
		  ColorCase= new int[nbLig][nbCol];
		  cell= new JPanel[nbLig][nbCol]; 
		  contentGrille = new JPanel();
		  J1.score=0;
		  J2.score=0;
		  Ordi.score=0;
		  J1.color=0;
		  J2.color=0;
		  Ordi.color=0;
		  tour2=1;
	   }
	   
	   public static synchronized void playSound(final String url) {
		    new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
		      public void run() {
		        try {		          
		          Clip clip = AudioSystem.getClip();
		          AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
		          clip.open(inputStream);
		          if (url.equals("musique.wav"))
		          clip.loop(clip.LOOP_CONTINUOUSLY);
		          else
		        	  clip.start();
		        } catch (Exception e) {
		          System.err.println(e.getMessage());
		        }
		      }
		    }).start();
		  }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


