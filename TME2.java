import java.util.ArrayList;
import java.util.Collections;

public class TME2 {
	public int[][] a;
	public int[] b;
	public int[][] c;
	public int taille;
	public double best_borne_inf;
	public double best_borne_sup;
	
	public TME2(int[][] a, int[][] c, int[] b){
		this.a = a;
		this.c = c;
		this.b = b;
		taille = a.length;
		best_borne_inf = 0;
	}
	
	public double glouton(){
		ArrayList<Tab> tab = new ArrayList<Tab>();
		ArrayList<Integer> tab_j = new ArrayList<Integer>();
		int i,j,j_ca,k;
		double tmp_ca,tmp_cout,tmp_poids;
		
		for(i=0 ; i<taille ; i++){
			tab_j.add(i);
		}
		
		for(i=0 ; i<taille ; i++){
			System.out.println("Itération "+i);
			tmp_ca = 10000000;
			j_ca = 0;
			k = 0 ;
			while(k < tab_j.size()){
				j = tab_j.get(k);
				System.out.println("k = "+k+" j = "+j+" taille = "+taille);
				if((double)c[i][j]/(double)c[i][j] < tmp_ca){
					tmp_ca = (double)c[i][j]/(double)c[i][j];
					j_ca = j;
				}
				k++;
			}
			System.out.println(tab_j);
			System.out.println(" j_ca = "+j_ca);
			tab_j.remove((Integer)j_ca);
			tab.add(i, new Tab(i,(double)c[i][j_ca]/(double)a[i][j_ca]));
		}
		
		System.out.println(tab);
		Collections.sort(tab);
		
		i=0;
		while(i < taille && (tmp_poids+a[tab.get(i).indice]) <=b){
			if(tab.get(i).valeur != -1){
				tmp_poids = tmp_poids + a[tab.get(i).indice];
				tmp_cout = tmp_cout + c[tab.get(i).indice];
			}
			i++;
		}
		
		return 0;
	}

}
