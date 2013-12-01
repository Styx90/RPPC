class Tab implements Comparable<Tab> {
	public int indice;
	public double valeur;
	
	public Tab(int indice, double valeur){
		this.indice = indice;
		this.valeur = valeur;
	}

	@Override
	public int compareTo(Tab arg0) {
		if(valeur > arg0.valeur){
			return -1;
		} else if (valeur < arg0.valeur){
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public String toString(){
		return indice + " "+ valeur;
	}
}