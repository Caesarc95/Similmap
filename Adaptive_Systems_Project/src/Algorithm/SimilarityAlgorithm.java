package Algorithm;

import java.util.*;

import Entities.*;

public class SimilarityAlgorithm {
	
	
	public int average(LinkedList<Item> list) {
		int avg=0;
		int c=0;
		for (int i=0; i<list.size();i++) {
			if (list.get(i).getRating()!=0) {
				c+=1;
				avg+=list.get(i).getRating();
			}
		}
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
				
				while(it1.hasNext()) {
					User u1=it1.next();
					Iterator<User> it2 = map.keySet().iterator();

					int c=0;
					while(it2.hasNext()) {
						
						User u2=it2.next();
						LinkedList<Item> ll = new LinkedList<Item>();
						
						LinkedList<Item> ll1 = map.get(u1);
						int avg1=average(ll1);
						LinkedList<Item> ll2 = map.get(u2);
						int avg2=average(ll2);
						
						for (int i=0; i<ll1.size();i++) {
							if (ll1.get(i).getRating()!=0 && ll2.get(i).getRating()!=0) {
								ll.add(ll1.get(i));
							}
						}
						int num=0;
						int den1=0;
						int den2=0;
						for (int i=0; i<ll.size();i++) {
							num+=((ll.get(i).getRating()-avg1)*(ll.get(i).getRating()-avg2));
							den1+=((ll.get(i).getRating()-avg1)*(ll.get(i).getRating()-avg1));
							den2+=((ll.get(i).getRating()-avg2)*(ll.get(i).getRating()-avg2));
						}
						double pc = num/Math.sqrt((den1*den2));
						matrix[r][c]=pc;
						c+=1;
					}
						
						
					r+=1;	
				}
				return matrix;
			}
				
			default: return null;
			
		}
		
		
		
	}
}
