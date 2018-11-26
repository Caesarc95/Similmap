package Algorithm;

import java.util.*;
import Entities.Item;
import Entities.User;

public class RatingAlgorithm {

	public HashMap <User, LinkedList<Item>> compute(
			HashMap <User, LinkedList<Item>> map, 
			double [] [] users, int k){
		
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
		
		double rate=0;
		int c=0;
		int row=0;
		Iterator<User> it = map.keySet().iterator();
		
		double [] rank = new double [users.length];
		while(it.hasNext()) {
			User u2=it.next();
			if (u2.equals(u)){
				row=c;
				for (int i=0;i<users[c].length;i++) {
					rank[i] = users[c][i];
					if (c==i) {
						rank[i]=0;
					}
				}
			}
			c++;
		}
		
		rank = bubbleSort(rank);
		
		for (int i=0; i<k; i++) {
			double max=rank[users.length-i-1];
			Iterator<User> it2 = map.keySet().iterator();
			int cc=0;
			while(it2.hasNext()) {
				User u2=it.next();
				if (users[row][cc]==max){
					for (Item ite : map.get(u2)) {
						if (ite.getId()==item.getId()) {
							rate+=max*ite.getRating();
							rank[users.length-i-1]=users[row][cc];
						}
					}
				}
				cc++;
			}
		}
		double den=0;
		for (int i=0;i<k;i++) {
			den+=rank[users.length-i-1];
		}
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
