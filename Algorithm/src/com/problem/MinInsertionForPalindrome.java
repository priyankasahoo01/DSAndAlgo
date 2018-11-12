package com.problem;

public class MinInsertionForPalindrome {

    public static int minInsert(String ip) {
        int n = ip.length();
        if (n <= 1) {
            return 0;
        }
        String last = ip.substring(n - 1);
        String first = ip.substring(0, 1);
        if (first.equals(last)) {
            if (n - 2 > 1) {
                String secondlast = ip.substring(1, n - 1);
                return minInsert(secondlast);
            }
            return 0;
        }
        String exceptFirst = ip.substring(1);
        String exceptLast = ip.substring(0, n - 1);
        return 1 + Math.min(minInsert(exceptFirst), minInsert(exceptLast));
    }

    public static String stringInsert(String ip) {
        int n = ip.length();
        if (n <= 1) {
            return ip;
        }
        String last = ip.substring(n - 1);
        String first = ip.substring(0, 1);
        if (first.equals(last)) {
            if (n - 2 > 1) {
                String secondlast = ip.substring(1, n - 1);
                return first + stringInsert(secondlast) + last;
            }
            return first + last;
        }
        String exceptFirst = ip.substring(1);
        String exceptLast = ip.substring(0, n - 1);
        String firstSkipped = stringInsert(exceptFirst);
        String lastSkipped = stringInsert(exceptLast);
        if (firstSkipped.length() < lastSkipped.length()) {
            return ip.substring(0, 1) + firstSkipped + ip.substring(0, 1);
        }
        return ip.substring(n - 1) + lastSkipped + ip.substring(n - 1);
        // return 1+ Math.min(firstSkipped, lastSkipped);
    }

    public static void main(String[] args) {
        test("abc");
        test("aa");
        test("ab");
        test("abcd");
        test("abcda");
        test("abcde");

    }

    private static void test(String ip) {
        int count = minInsert(ip);
        System.out.println(ip + " --> " + count);

        String op = stringInsert(ip);
        System.out.println(ip + " --> " + op);
    }

}
