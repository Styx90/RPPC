import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lecturee {
	public int[] a_1;
	public int[] c_1;
	public int b_1;
	public int[][] a_2;
	public int[][] c_2;
	public int[] b_2;
	
	public void lire_tme1(String nom_fichier){
		try{
			InputStream ips=new FileInputStream(nom_fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String mot_lu;
			int i,cpt;
			
			ligne=br.readLine();i=0;mot_lu = "";
			while(i < ligne.length() && ligne.charAt(i) != ' ' && ligne.charAt(i) != '\n'){
				mot_lu = mot_lu + ligne.charAt(i);
				i++;
			}
			
			b_1 = Integer.parseInt(mot_lu);
			
			a_1 = new int[b_1];
			c_1 = new int[b_1];
					
			cpt=0;
			while (cpt != b_1 && (ligne=br.readLine())!=null){
				i=0;
				for(int j=0 ; j<3 ; j++){
					mot_lu = "";
					while(ligne.charAt(i) == ' '){
						i++;
					}
					while(i < ligne.length() && ligne.charAt(i) != ' ' && ligne.charAt(i) != '\n'){
						mot_lu = mot_lu + ligne.charAt(i);
						i++;
					}
					if(j==1){c_1[cpt] = Integer.parseInt(mot_lu);}
					if(j==2){a_1[cpt] = Integer.parseInt(mot_lu);}
				}
				cpt++;
			}
			
			ligne=br.readLine();i=0;mot_lu = "";
			while(i < ligne.length() && ligne.charAt(i) != ' ' && ligne.charAt(i) != '\n'){
				mot_lu = mot_lu + ligne.charAt(i);
				i++;
			}
			
			b_1 = Integer.parseInt(mot_lu);
			
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void lire_tme2(String nom_fichier){
		
	}
}
