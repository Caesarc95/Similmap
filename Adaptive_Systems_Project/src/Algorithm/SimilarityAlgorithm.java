package Algorithm;

import java.util.*;

import Entities.*;

public class SimilarityAlgorithm {

	public LinkedList<User> compute(
			HashMap <User, LinkedList<Item>> map, String alg)
	{
		switch(alg) { 
			case "PearsonCorrelation": {
				
				int k = map.keySet().size();
				
				int [][] matrix = new int [k] [k];
				
				for (int i=0;i<k;i++) {
					map.keySet().
					for (int j=0;j<k;j++) {
						if (i==j) matrix [i][j]=1;
						
						else {
							matrix [i][j] = ()
						}
					}
				}
				
			}
			default: return null;
			
		}
		
		
		
	}
}
