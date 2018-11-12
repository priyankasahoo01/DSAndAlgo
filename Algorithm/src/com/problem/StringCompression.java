package com.problem;

public class StringCompression {
    public static String stringCompression(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        int n = s.length();
        int count = 1;
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);
        boolean isDuplicate = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == prev) {
                isDuplicate = true;
                count++;
            } else {
                sb.append(String.valueOf(prev) + count);
                count = 1;
            }
            prev = s.charAt(i);

        }
        if (!isDuplicate) {
            return s;
        }
        sb.append(String.valueOf(prev) + count);
        return sb.toString();

    }
    
    public static void main(String[] args) {
        test("aabcccccaaa");
        test("ab");
        test("fsrejhktyioo");
        test("a");
    }

    private static void test(String s) {
        System.out.println(s +" --> "+stringCompression(s));
    }

}
