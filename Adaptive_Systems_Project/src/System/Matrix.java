package System;

import java.util.*;
import Algorithm.*;
import Entities.*;

public class Matrix {
	private HashMap<User, LinkedList<Item>> map;
	private double [] [] matrix;
	private RatingAlgorithm ratings= new RatingAlgorithm();
	private SimilarityAlgorithm similarities= new SimilarityAlgorithm();

	public Matrix(int i, int j, int emptyPerc, String simalg, String ratalg) {
		
		if ( emptyPerc<0 || emptyPerc>100) throw new RuntimeException("Number above 100");
		
		map = initialize(i,j, emptyPerc);
		
		matrix = similarities.compute(map, "PearsonCorrelation");
		
	}
	
	public HashMap <User, LinkedList<Item>> initialize(int u, int i, int emptyPerc){
		map = new HashMap <User, LinkedList<Item>>();
		

		
		LinkedList<Item> list = new LinkedList<Item>();
		
		for(int j=0;j<i;j++) {
			list.add(new Item());
		}
		
		for(; u>0; u--) {
			
			int rId = new Random().nextInt(1000);
			LinkedList<Item> userlist = new LinkedList<Item>();
			
			for(int j=0;j<i;j++) {
				userlist.add(new Item(list.get(j).getId()));
			}
			Iterator<Item> it= userlist.iterator();
			while(it.hasNext()) {
				Item item = it.next();
				int percentage = new Random().nextInt(101);
				if (percentage>emptyPerc) {
					int rr = new Random().nextInt(4)+1;
					item.setRating(rr);
				}
			}
			map.put(new User(rId), userlist);
		}
		
		return map;
		
	}

	public void printMatrix() {
		for (int i=0;i<matrix.length;i++) {
			for (int j=0;j<matrix.length;j++)
				System.out.print("\t" + matrix[i][j]);
			System.out.println();
		}
		
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
		
		matrix.printMatrix();
	}
}
