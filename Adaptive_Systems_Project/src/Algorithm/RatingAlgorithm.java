package Algorithm;

import java.util.*;
import Entities.Item;
import Entities.User;

public class RatingAlgorithm {

	public HashMap <User, LinkedList<Item>> compute(
			HashMap <User, LinkedList<Item>> map, 
			double [] [] users, int k){
		
		if (k>users.length)throw new RuntimeException();
		
		for (User u : map.keySet()) {
			LinkedList<Item> list=map.get(u);
			for (Item i : list) {
				if (i.getRating()==0) {
					i.setRating(rate(u,i, users, map,k));
				}
			}
		}	
		return map;
		
	}
	
	public double rate(User u, Item item, double [] [] users, HashMap <User, LinkedList<Item>> map, int k) {
		
		int c=0;
		int row=0;
		Iterator<User> it = map.keySet().iterator();

		System.out.println();
		double [] rank = new double [users.length];
		while(it.hasNext()) {
			User u2=it.next();
			if (u2.getId()==u.getId()){
				row=c;
				for (int i=0;i<users[c].length;i++) {
					rank[i] = users[c][i];
					if (c==i) {
						rank[i]=-1;
					}
				}
			}
			c++;
		}

		System.out.println();
		rank = bubbleSort(rank);

		for (int i=0; i<users.length; i++) {
			System.out.print("\t"+rank[users.length-i-1]);
		}
		

		double rate=0;
		
		for (int i=0; i<k; i++) {
			System.out.print("\t"+rank[users.length-i-1]);
			it = map.keySet().iterator();
			int cc=0;
			while(it.hasNext()) {
				double max=rank[users.length-i-1];
				User u2=it.next();
				if (users[row][cc]==max){
					System.out.print("\t"+users[row][cc]);
					System.out.print("\t"+max);
					System.out.print("\t"+rate);
					for (Item ite : map.get(u2)) {
						if (ite.getId()==item.getId()) {

							System.out.print("\t"+ite.getRating());
							rate+=max*ite.getRating();
							rank[users.length-i-1]=users[row][cc];
							System.out.print("\t"+rate);
						}
					}
				}
				cc++;
				System.out.println();
			}
		}

		System.out.println();
		double den=0;
		for (int i=0;i<k;i++) {
			System.out.print("\t"+den);
			den+=rank[users.length-i-1];
		}
		System.out.print("\t"+rate);
		System.out.print("\t"+den);
		System.out.println("\t"+rate/den);
		if (den==0) return 0;
		if (rate/den <0) return 0.1;
		if (rate/den >5) return 5.0;
		return rate/den;
	}
	
	
	public double[] bubbleSort(double[] arr) {  
	        double n = arr.length;  
	        double temp = 0;  
	         for(int i=0; i < n; i++){  
	                 for(int j=1; j < (n-i); j++){  
	                          if(arr[j-1] > arr[j]){  
	                                 //swap elements  
	                                 temp = arr[j-1];  
	                                 arr[j-1] = arr[j];  
	                                 arr[j] = temp;  
	                         }  
	                          
	                 }  
	         }
	         return arr;
	  
	    }
}
