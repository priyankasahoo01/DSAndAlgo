package com.java8.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapEx {

    public static void main(String[] args) {
        String[][] data = new String[][] { { "a", "b" }, { "a", "d" }, { "e", "f" } };
        List<Integer> li = new ArrayList<>();
        li.add(1);
        li.add(2);
        li.add(3);
        li.add(4);
        findSumOfNumbers(li);
        System.out.println("------------------------------------");
        Stream<String[]> s = Arrays.stream(data);
        s.flatMap(s1 -> Arrays.stream(s1)).filter(a -> "a".equals(a)).forEach(System.out::println);
        System.out.println("------------------------------------");
        int[] intArray = { 1, 2, 3, 4, 5, 6 };
        Stream<int[]> stream = Stream.of(intArray);
        stream.flatMapToInt(x->Arrays.stream(x)).forEach(System.out::println);
        System.out.println("------------------------------------");
    }

    private static void findSumOfNumbers(List<Integer> li) {
        int sum = li.stream().filter(i -> i > 1).mapToInt(i -> i).sum();

        System.out.println("sum -> " + sum);
    }
    
    //1003 ---> 1111
    //7890 ---> 7997
    

}
