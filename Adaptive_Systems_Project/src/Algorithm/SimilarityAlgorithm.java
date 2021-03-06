package Algorithm;

import java.util.*;

import Entities.*;

public class SimilarityAlgorithm {
	
	/*
	 * Method to calculate the average rating of a user.
	 */
	public double average(LinkedList<Item> list) {
		double avg=0;
		int c=0;
		for (int i=0; i<list.size();i++) {
			if (list.get(i).getRating()!=0) {
				c+=1;
				avg+=list.get(i).getRating();
			}
		}
		if (c==0) return 0;
		return avg/c;
	}

	public double[][] compute(
			HashMap <User, LinkedList<Item>> map, String alg)
	{
		switch(alg) { 
			case "PearsonCorrelation": {
				
				int k = map.keySet().size();
				
				double [][] matrix = new double [k] [k];
				int r=0;
				Iterator<User> it1 = map.keySet().iterator();
				/*
				 * Implementation of the Pearson-Correlation formula: scanning the users.
				 */
				while(it1.hasNext()) {
					User u1=it1.next();
					Iterator<User> it2 = map.keySet().iterator();

					int c=0;
					while(it2.hasNext()) {
						
						User u2=it2.next();
						LinkedList<Item> ll = new LinkedList<Item>();
						

						LinkedList<Item> ll1 = map.get(u1);
						double avg1=average(ll1);
						LinkedList<Item> ll2 = map.get(u2);
						double avg2=average(ll2);
						
						
						/*
						 * Finding commom rated items (rated by both users).
						 */
						for (int i=0; i<ll1.size();i++) {
							if (ll1.get(i).getRating()!=0 && ll2.get(i).getRating()!=0) {
								ll.add(ll1.get(i));
							}
						}
						double num=0;
						double den1=0;
						double den2=0;
						for (int i=0; i<ll.size();i++) {
							
							double rate1 = 0;
							double rate2 = 0;
							Item item = ll.get(i);
							for (int j=0; j<ll1.size();j++) {
								/*
								 * Find the ratings of the items rated by the user
								 */
								if (ll1.get(j).getId() == item.getId()) {
									rate1=ll1.get(j).getRating();
								}
							}
							for (int j=0; j<ll1.size();j++) {
								/*
								 * Find the ratings of the items rated by the other user
								 */
								if (ll2.get(j).getId() == item.getId()) {
									rate2=ll2.get(j).getRating();
								}
							}
							/*
							 * formula
							 */
							num+=((rate1-avg1)*(rate2-avg2));
							den1+=((rate1-avg1)*(rate1-avg1));
							den2+=((rate2-avg2)*(rate2-avg2));
						}
						if (num==0 || den1*den2==0) {
							matrix[r][c]=0;			
							
							/*when the informations given by the ratings of other users is not enough
							and the formula is impossible to compute to avoid error, a zero is 
							inserted in the corrisponding cell of the matrix
							*/
						}
						else{
							double pc = num/Math.sqrt((den1*den2));
						
							matrix[r][c]=pc;
							c+=1;
						}
					}
						
						
					r+=1;	
				}
				return matrix;
			}
				
			default: return null;
			
		}
		
		
		
	}
}
