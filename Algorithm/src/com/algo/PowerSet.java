package com.algo;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PowerSet set = new PowerSet();
		List<Integer> li = new ArrayList<>();
		li.add(1);
		li.add(2);
		li.add(3);
		List<List<Integer>>op = set.powerSet(li);
		printOp(op);
		
		
		
		li.add(4);
		li.add(5);
		op = set.powerSet(li);
		printOp(op);

	}
	

	List<List<Integer>> powerSet(List<Integer> li) {
		if (li == null || li.size() == 0) {
			return null;
		}
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> empty = new ArrayList<>();
		result.add(empty);

		for (Integer num : li) {
			result.addAll(getPowerSet(result, num));
		}
		return result;
	}

	List<List<Integer>> getPowerSet(List<List<Integer>> sets, int n) {
		List<List<Integer>> temp = new ArrayList<>();
		for (int i = 0; i < sets.size(); i++) {
			temp.add(clone(sets.get(i)));
			temp.get(i).add(n);
		}
		return temp;
	}
	
	private List<Integer> clone(List<Integer> li1) {
		List<Integer> li2 = new ArrayList<>();
		for(Integer i : li1) {
			li2.add(i);
		}
		return li2;
	}
	
	private static void printOp(List<List<Integer>> op) {
		//print
		for(List<Integer> temp : op) {
			System.out.print("{");
			for(Integer i : temp) {
				System.out.print(i+",");
			}
			System.out.print("},");
		}
		System.out.println("\n");
	}

}
