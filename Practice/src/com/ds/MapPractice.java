package com.ds;

import java.util.HashMap;
import java.util.Map;

public class MapPractice {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("S", null);
        System.out.println(map.keySet().size());
        System.out.println(map.size());
        map.remove("S");
        System.out.println(map.keySet().size());
        System.out.println(map.size());
        System.out.println(map.get("S"));
    }

}
