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
				/*
				 * When the algorithm encounters a zero in the matrix call the rate function to
				 * fill the cell with the value predicted by the k-NN method (approximated to the
				 * nearest integer).
				 */
				if (i.getRating()==0) {
					i.setRating(Math.round(rate(u,i, users, map,k)));
				}
			}
		}	
		return map;
		
	}
	
	public double rate(User u, Item item, double [] [] users, HashMap <User, LinkedList<Item>> map, int k) {
		
		int c=0;
		int row=0;
		Iterator<User> it = map.keySet().iterator();

		double [] rank = new double [users.length];
		
		/*
		 * The similarity values of all the users related to user u are encoded in the array rank
		 */
		while(it.hasNext()) {
			User u2=it.next();
			if (u2.getId()==u.getId()){
				/*
				 * The position of the user is saved in the int row
				 */
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

		/*
		 * The array is ordered with respect to the similarity values
		 */
		rank = bubbleSort(rank);

		double rate=0;
		
		for (int i=0; i<k; i++) {
			it = map.keySet().iterator();
			int cc=0;
			while(it.hasNext()) {
				/*
				 * Crescent order, the last value will be the greatest
				 */
				double max=rank[users.length-i-1];
				User u2=it.next();
				/*
				 * Finding the user in the keyset through its similarity value max
				 */
				if (users[row][cc]==max){
					for (Item ite : map.get(u2)) {
						/*
						 * Find the item equals to the item not yet rated by the user
						 */
						if (ite.getId()==item.getId()) {
							/*
							 * Compute the denominator of the formula used for the predicted rating
							 */
							rate+=max*ite.getRating();
							/*
							 * Saving the position of the user used in the last position of the
							 * array rank
							 */
							rank[users.length-i-1]=users[row][cc];
						}
					}
				}
				cc++;
			}
		}

		double den=0;
		/*
		 * Compute the denominator using the position of the user saved before in the 
		 * last position used of the array rank
		 */
		for (int i=0;i<k;i++) {
			den+=rank[users.length-i-1];
		}
		/*
		 * Control checks to avoid errors and impossible numbers int final matrix
		 */
		if (den<0) den=-den;
		if (den==0) den=1;
		if (rate/den <0) return 1.0;
		if (rate/den >5) return 5.0;
		return rate/den;
	}
	
	/*
	 * Algorithm for the ordering: https://www.javatpoint.com/bubble-sort-in-java
	 */
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
