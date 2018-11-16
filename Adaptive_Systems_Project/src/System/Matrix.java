package System;

import java.util.*;
import Algorithm.*;
import Entities.*;

public class Matrix {
	private HashMap<User, LinkedList<Item>> map;
	private LinkedList<User> simil;
	private RatingAlgorithm ratings= new RatingAlgorithm();
	private SimilarityAlgorithm similarities= new SimilarityAlgorithm();

	public Matrix(int i, int j, String simalg, String ratalg) {
		
		map = initialize(i,j);
		
		simil = similarities.compute(map, simalg);
		
		map = ratings.compute(map, simil, ratalg);
		
	}
	
	public HashMap <User, LinkedList<Item>> initialize(int u, int i){
		map = new HashMap <User, LinkedList<Item>>();
		
		for(; i<5; i++) {
			User = new User();
		}
		
		for (User u : map.keySet()) {
			
		}
		
		
		
		return map;
		
	}
}
