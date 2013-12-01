import java.util.ArrayList;

public class Main {

	public static void main (String[] args){
		int[] a = {5,6,8,1,2,5,3,5,5,2};
		int[] c = {4,6,6,2,9,7,7,5,6,2};
		TME1 tme1 = new TME1(a,c,10);
		int taille = 10;
		ArrayList<Integer> x = new ArrayList<Integer>(taille);
		for(int i=0 ; i<taille ; i++){
			x.add(i, 0);
		}
		
		System.out.println(tme1.branch_and_bound());
		System.out.println(tme1.branch_and_greed());
	}
}
