import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagram {

    public static boolean isAnagram(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i =0; i < str1.length();i++) {
            char ch = str1.charAt(i);
            
            Integer count = map.get(ch);
            if(count == null) {
                map.put(ch, 1);
            }else {
                map.put(ch, map.get(ch)+1);
            }
        }
        
        for(int i =0; i < str2.length();i++) {
            char ch = str2.charAt(i);
            Integer count = map.get(ch);
            if(count == null || count == 0) {
                return false;
            }
            map.put(ch, count -1);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        for (int i = 0; i < num; i++) {
            String str1 = s.next();
            String str2 = s.next();
            if(isAnagram(str1, str2)){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
            
        }

    }

}
