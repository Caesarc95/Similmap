package System;

import java.util.*;
import Algorithm.*;
import Entities.*;

public class Matrix {
	private HashMap<User, LinkedList<Item>> map;
	private LinkedList<User> simil;
	private RatingAlgorithm ratings= new RatingAlgorithm();
	private SimilarityAlgorithm similarities= new SimilarityAlgorithm();

	public Matrix(int i, int j, int emptyPerc, String simalg, String ratalg) {
		
		if ( emptyPerc<0 || emptyPerc>100) throw new RuntimeException("Number above 100");
		
		map = initialize(i,j, emptyPerc);
		
		simil = similarities.compute(map, simalg);
		
	}
	
	public HashMap <User, LinkedList<Item>> initialize(int u, int i, int emptyPerc){
		map = new HashMap <User, LinkedList<Item>>();
		
		for(; u>0; u--) {
			
			int rId = new Random().nextInt();
			LinkedList<Item> list = new LinkedList<Item>();
			for (int j=0; j<i; j++) {
				int percentage = new Random().nextInt(101);
				if (percentage>emptyPerc) {
					int rr = new Random().nextInt(4)+1;
					list.add(new Item(rr));
				}
				else list.add(new Item(0));
			}
			map.put(new User(rId), list);
		}
		
		return map;
		
	}

	@Override
	public String toString() {
		String text = "";
		for (User u : map.keySet()) {
			text = text + "\n" + u.toString();
			for (Item i : map.get(u)) {
				text = text  + i.toString();
			}
		};
		return text;
	}
	
	
	public static void main (String[] args){
		
		Matrix matrix= new Matrix(4,5,25, "", "");
		
		System.out.println(matrix.toString());
	}
}
