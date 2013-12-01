import java.util.ArrayList;
import java.util.Collections;


public class TME1 {
	public int[] a;
	public int[] c;
	public int taille;
	public int b;
	public double best_borne_inf;
	public double best_borne_sup;
	
	public TME1(int[] a, int[] c, int b){
		this.a = a;
		this.c = c;
		this.b = b;
		taille = a.length;
		best_borne_inf = 0;
		best_borne_sup = 0;
		for(int i=0 ; i<taille ; i++){
			best_borne_sup = best_borne_sup + c[i];
		}
	}
	
	public double glouton(ArrayList<Integer> x, int profondeur){
		ArrayList<Tab> tab = new ArrayList<Tab>(taille);
		int i;
		double tmp_cout,tmp_poids;
		for(i=0 ; i<taille ; i++){
			if(i>=profondeur){
				tab.add(i, new Tab(i,(double)c[i]/(double)a[i]));
			}else {
				tab.add(i,new Tab(i,-1));
			}
		}
		
		Collections.sort(tab);
		
		tmp_cout=0;tmp_poids=0;
		for(i=0 ; i<profondeur ; i++){
			tmp_poids = tmp_poids + a[i]*x.get(i);
			tmp_cout= tmp_cout+ c[i]*x.get(i);
		}

		i=0;
		while(i < taille && (tmp_poids+a[tab.get(i).indice]) <=b){
			if(tab.get(i).valeur != -1){
				tmp_poids = tmp_poids + a[tab.get(i).indice];
				tmp_cout = tmp_cout + c[tab.get(i).indice];
			}
			i++;
		}
		
		System.out.println("Glouton");
		System.out.println("Poids : "+tmp_poids);
		System.out.println("Couts : "+(tmp_poids <= b?tmp_cout:-1));
		System.out.println(x);
		
		return (tmp_poids <= b?tmp_cout:-1);
	}
	
	public double relaxation_continue(ArrayList<Integer> x, int profondeur){
		ArrayList<Tab> tab = new ArrayList<Tab>(taille);
		int i;
		double tmp_cout,tmp_poids;
		
		for(i=0 ; i<taille ; i++){
			if(i>=profondeur){
				tab.add(i, new Tab(i,(double)c[i]/(double)a[i]));
			}else {
				tab.add(i,new Tab(i,-1));
			}
		}
		
		Collections.sort(tab);
		
		tmp_cout=0;tmp_poids=0;
		for(i=0 ; i<profondeur ; i++){
			tmp_poids = tmp_poids + a[i]*x.get(i);
			tmp_cout= tmp_cout+ c[i]*x.get(i);
		}

		i=0;
		while(i < taille && (tmp_poids+a[tab.get(i).indice]) <=b){
			if(tab.get(i).valeur != -1){
				tmp_poids = tmp_poids + a[tab.get(i).indice];
				tmp_cout = tmp_cout + c[tab.get(i).indice];
			}
			i++;
		}
		
		if(i<taille){tmp_cout = tmp_cout + ((double)(b - tmp_poids)/(double)a[i])*c[i];}
		
		System.out.println("Relaxation continue");
		System.out.println("Poids : "+tmp_poids);
		System.out.println("Couts : "+(tmp_poids <= b?tmp_cout:-1));
		System.out.println(x);
		
		return (tmp_poids <= b?tmp_cout:-1);
	}
	
	public double branch_and_bound(){
		ArrayList<Integer> x = new ArrayList<Integer>(taille);
		for(int i=0 ; i<taille ; i++){
			x.add(i, 0);
		}
		return branch_and_bound_recu(x,0);
	}
	
	public double calculer(ArrayList<Integer> x){
		int tmp=0;
		for(int i=0 ; i<taille ; i++){
			tmp = tmp + c[i]*x.get(i);
		}
		return tmp;
	}
	
	public double calculer_verif(ArrayList<Integer> x){
		int tmp_cout=0, tmp_poids =0;
		for(int i=0 ; i<taille ; i++){
			tmp_cout = tmp_cout + c[i]*x.get(i);
			tmp_poids = tmp_poids + a[i]*x.get(i);
		}
		return (tmp_poids <= b?tmp_cout:-1);
	}
	
	public double branch_and_bound_recu(ArrayList<Integer> x, int profondeur){
		if(profondeur == taille){
			System.out.println("fin");
			System.out.println(x);
			return calculer(x);
		}
		
		double borne_inf, borne_sup;
		borne_inf = glouton(x,profondeur);
		borne_sup = relaxation_continue(x,profondeur);
		System.out.print("Borne inf "+borne_inf);
		System.out.print(" Borne sup "+borne_sup);
		System.out.print(" Borne inf best "+best_borne_inf);
		System.out.println(" Borne sup best "+best_borne_sup);
		System.out.println(x+" profondeur : "+profondeur);
		
		if(borne_sup < best_borne_inf || borne_inf < best_borne_inf){
			System.out.println("if");
			return -1;
		}else{
			best_borne_inf = borne_inf;
			System.out.println("else");
			ArrayList<Integer> x_0;
			ArrayList<Integer> x_1;
			x_0 = new ArrayList<Integer>(x);
			x_0.set(profondeur, 0);
			x_1 = new ArrayList<Integer>(x);
			x_1.set(profondeur, 1);
			double res_0 = branch_and_bound_recu(x_0, profondeur+1);
			double res_1 = branch_and_bound_recu(x_1, profondeur+1);
			return (res_0>res_1?res_0:res_1);
		}
	}
	
	public double branch_and_greed(){
		ArrayList<Integer> x = new ArrayList<Integer>(taille);
		double x_0,x_1;
		
		for(int i=0 ; i<taille ; i++){
			x.add(i, 0);
		}
		
		for(int i=0 ; i<taille ; i++){
			x_0 = calculer_verif(x);
			x.set(i, 1);
			x_1 = calculer_verif(x);
			if(x_0 > x_1){x.set(i, 0);}
		}
		return calculer(x);		
	}
}
