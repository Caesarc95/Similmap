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
	
	public void fill(int k) {
		ratings.compute(map, matrix, k);
	}
	
	public HashMap <User, LinkedList<Item>> initialize(int u, int i, int emptyPerc){
		/*
		 * Testing example matrix
		 * 
		map = new HashMap <User, LinkedList<Item>>();
		LinkedList<Item> list = new LinkedList<Item>();
		int [] arr = new int[20];
		arr [0]=5;
		arr [1]=1;
		arr [2]=0;
		arr [3]=2;
		arr [4]=2;
		arr [5]=1;
		arr [6]=5;
		arr [7]=2;
		arr [8]=5;
		arr [9]=5;
		arr [10]=2;
		arr [11]=0;
		arr [12]=3;
		arr [13]=5;
		arr [14]=4;
		arr [15]=4;
		arr [16]=3;
		arr [17]=5;
		arr [18]=3;
		arr [19]=0;
		int c=0;
		for(int j=0;j<i;j++) {
			list.add(new Item());
		}
		for(int cc=0; cc<u; cc++) {

			LinkedList<Item> userlist = new LinkedList<Item>();
			
			for(int j=0;j<i;j++) {
				userlist.add(new Item(arr[c],list.get(j).getId()));
				c++;
			}
			
			map.put(new User(cc+1), userlist);
		}
		
		return map;*/
		map = new HashMap <User, LinkedList<Item>>();
		
		LinkedList<Item> list = new LinkedList<Item>();
		
		for(int j=0;j<i;j++) {
			list.add(new Item());
		}

		int cc=0;
		for(; u>0; u--) {
			LinkedList<Item> userlist = new LinkedList<Item>();
			
			for(int j=0;j<i;j++) {
				userlist.add(new Item(list.get(j).getId()));
			}
			Iterator<Item> it= userlist.iterator();
			while(it.hasNext()) {
				Item item = it.next();
				int percentage = new Random().nextInt(101);
				if (percentage>emptyPerc) {
					int rr = new Random().nextInt(5)+1;
					item.setRating(rr);
				}
			}
			cc++;
			map.put(new User(cc), userlist);
		}
		
		return map;
		
	}

	public void printMatrix() {

		System.out.println();
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
	
	/*
	 * Suggest the most suggested items for the user with this id.
	 */
	public void suggestedItems(int id) {

		LinkedList<Item> ordlist = new LinkedList<Item>();
		
		User user=null;
		for (User u : map.keySet()) {
			if (u.getId()==id) {
				user = u;
				LinkedList<Item> oldlist = map.get(u);
				LinkedList<Item> list = map.get(u);
				int size = list.size();
				for (int c = 0; c< size;c++) {
					Item first = list.getFirst();
					for (Item i : list) {
						if (i.getRating()>first.getRating()) {
							first = i;
						}
					}
					list.remove(first);
					ordlist.addLast(first);
				}
				map.put(user, ordlist);
			}
			
		}
		if (user==null) throw new RuntimeException("Unexpected error");
		String text = "\n" + user.toString();
		for (Item i : ordlist) {
			text = text  + i.toString();
		}
		System.out.println(text);
	}
	
	public static void main (String[] args){
		
		Scanner sc = new Scanner(System.in);
		int users;
		
		do {
			System.out.println("Please enter a legal users number!");
			while(!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next();
			}
			users = sc.nextInt();
		} while (users<=0);
		
		int items;
		do {
			System.out.println("Please enter a legal items number!");
			while(!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next();
			}
			items = sc.nextInt();
		} while (items<=0);
		int percentage;
		do {
			System.out.println("Please enter a legal percentage number!");
			while(!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next();
			}
			percentage = sc.nextInt();
		} while (percentage<0 || percentage >100);
		
		
		Matrix matrix= new Matrix(users,items,percentage, "", "");

		System.out.println(matrix.toString());
		
		matrix.printMatrix();
		int k;
		do {
			System.out.println("Please enter a legal k-NN number!");
			while(!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next();
			}
			k = sc.nextInt();
		} while (k<=0 || k >users);
		matrix.fill(k);
		System.out.println(matrix.toString());
		int user;
		do {
			System.out.println("Please enter a valid user!");
			while(!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next();
			}
			user = sc.nextInt();
		} while (user<0 || user > users );
		
		matrix.suggestedItems(user);
		
		sc.close();
	}
}
