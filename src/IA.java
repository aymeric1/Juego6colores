import java.util.*;
public class IA extends Joueur{

	
	public IA(){
		name = "Ordinateur";
        score=1;
        color=0;
        humain=0;
	}	

	public int MaxTab(int montab[]){ // donne le maximum d'un tableau d'entier
		int mavaleur = 0;
		int indicemax =0;
		for (int i = 0; i < montab.length; i++) {
		if (montab[i] > mavaleur){		
		mavaleur = montab[i];	
		indicemax=i;
		}
	    }
		return indicemax ;
		}
	
	public int[][] CopieTab(int montab[][], int a, int b){
		int copie[][]= new int [a][b];
		for(int i=0; i<a; i++){
	           for(int j=0; j<b; j++){
	        	   copie[i][j]=montab[i][j];
	           }
		}	
		return copie;
	}
}
	