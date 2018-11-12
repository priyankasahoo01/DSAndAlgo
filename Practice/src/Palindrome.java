
public class Palindrome {
    public static void main(String[] args) {
//        isPalindrome("1234321");
//        isPalindrome("1221");
//        isPalindrome("12345");
//        System.out.println("12345678".substring(0, 2));
//        System.out.println(isPalindrome("34534"));
//        System.out.println(isPalindrome("345345"));
//        System.out.println(isPalindrome("34534785"));
//        System.out.println(lengthofPalindrome("12345354987"));
        System.out.println(lengthofPalindrome("2233"));
    }

    public static int lengthofPalindrome(String input1) {
        if (input1 == null || "".equals(input1.trim())) {
            return 0;
        }
        int count =0;
        int len = input1.length();
        for (int i = 0; i < input1.length(); i++) {
            for (int j = len; j > i; j--) {
                if(count > j-i){
                    break;
                }
                if(isPalindrome(input1.substring(i, j))){
                    count = j-i;
                }
            }
        }
        return count;
    }

    public static boolean isPalindrome(String ip) {
        int len = ip.length();
        
        char[] arr = ip.toCharArray();
        for(int i = 0; i< len; i++){
            boolean isFound = false;
            for(int k = i+1; k<len ; k++){
                if(arr[i] == arr[k]){
                    //swap(k,len);
                    char temp = arr[len-1];
                    arr[len-1] = arr[k];
                    arr[k]=temp;
                    len --;
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                return false;
            }
        }
//        if(len==1){
//            return true;
//        }
        return true;
        
        
        ////
//        int j = len - 1;
//        for (int i = 0; i < len / 2; i++) {
//            if (ip.charAt(i) != ip.charAt(j--)) {
//                return false;
//            }
//        }
//        return true;
    }
}
