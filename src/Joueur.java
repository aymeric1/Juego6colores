import java.util.Scanner;

public class Joueur {
	protected String name;
	public int score;
	public int color;
	public int humain;

	// Constructeur par défaut
	 public Joueur()
	  {
		 name="Joueur 1";
		 score=1;
		 color=0;
		 humain=1;		 
	  }
	 
	 // Constructeur avec paramètres
	 
	 public Joueur(String nom)
	 {
		 	name = nom;		 
	        score=1;
	        color=1 + (int)(Math.random()*((6)));
	        humain=1;
	 }
	 
	 

	 
	 //Retourne le numero de la couleur du joueur
	  public int getColorJ()
	  {
	    return color;
	  }
	  public int getScore()
	  {
	    return score;
	  }
	  
	  public String getName()
	  {
	    return name;
	  }
	  
	  
	  // Set la couleur du joueur 
	  public void setColorJ(int a)
	  {
	    color = a;
	  }
	  
	  //Set le score +1
	  public void setScore(int a)
	  {
	    score = score +a;
	  }
}