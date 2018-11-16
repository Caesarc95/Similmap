package Algorithm;

import java.util.HashMap;
import java.util.LinkedList;

import Entities.Item;
import Entities.User;

public class RatingAlgorithm {

	public HashMap <User, LinkedList<Item>> compute(
			HashMap <User, LinkedList<Item>> map, 
			LinkedList<User> users, String alg)
	{
		
		switch(alg) { 
			case "kNN": return null;
			default: return null;
		
		}
	}
}
