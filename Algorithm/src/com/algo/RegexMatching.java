package com.algo;

public class RegexMatching {

//	public static void main(String[] args) {
//
//		RegexMatching sol  = new RegexMatching();
//		int op = sol.isMatch("abaa", "a.*a");
//		System.out.println("output --> "+op);
//	}

	static boolean isMatch(String text, String pattern) {
		return isMatch(text, pattern, 0, 0);
	}

	static boolean isMatch(String text, String pattern, int i, int j) {
		if (j > (pattern.length() - 1)) {
			return i > (text.length() - 1);
		}

		if (i > (text.length() - 1)) {
			if (j + 1 <= pattern.length() - 1 && pattern.charAt(j + 1) == '*') {
				return isMatch(text, pattern, i, j + 2);
			}
			return false;
		}

		boolean isStar = false;
		if (j <= pattern.length() - 2 && pattern.charAt(j + 1) == '*') {
			isStar = true;
		}

		if (!isStar) {
			if (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.') {
				return isMatch(text, pattern, i + 1, j + 1);
			}
			return false;
		}

		char ch = pattern.charAt(j);
		if (ch != '.') {
			while (text.charAt(i) == ch) {
				boolean isMatch = isMatch(text, pattern, i, j + 2);
				if (isMatch) {
					return true;
				}
				i++;
			}
			return isMatch(text, pattern, i, j + 2);
		} else {
			while (i <= text.length() - 1) {
				boolean isMatch = isMatch(text, pattern, i, j + 2);
				if (isMatch) {
					return true;
				}
				i++;
			}
			return isMatch(text, pattern, i, j + 2);
		}
	}

	public static void main(String[] args) {
		boolean op = isMatch("abaa", "a.*a");
		System.out.println("op --> "+op);
	}

//	
//	
//	public int isMatch(final String A, final String B) {
//        return valid(A, B, 0, 0) ? 1 : 0;
//    }
//    
//    private boolean valid(String str, String pat, int si , int pi){
//        
//        if(pi>pat.length()-1){
//            return si>str.length()-1;
//        }
//        
//        if(pat.substring(pi).equals(".*")){return true;}
//        boolean isStar = (pi+1 < pat.length() && pat.charAt(pi+1)=='*');
//        if(!isStar){
//            if(pat.charAt(pi) == str.charAt(si) || pat.charAt(pi) == '.' ){
//                return valid(str, pat, si+1, pi+1);
//            }else
//                return false;
//        }else{//b*c c
//            char ch = pat.charAt(pi);
//            if(si > str.length()-1){return valid(str, pat, si, pi+2);}
//            if(ch == '.'){
//                int  k = si;
//                for( k = si ; k <= str.length(); k++){
//                    if(valid(str, pat, k, pi+2)){
//                        return true;
//                    }
//                }
//                
//                return valid(str, pat, k, pi+2);
//            }else{
//            	int k = si;
//                for(k = si ; k < str.length(); k++){
//                    if(str.charAt(k) == ch){
//                        if(valid(str, pat, k, pi+2)){
//                            return true;
//                        }
//                    }else{
//                        return valid(str, pat, k, pi+2);
//                    }
//                    
//                }
//                return valid(str, pat, k, pi+2);
//            }
//            
//            
//            
//            
//        }
	/*
	 * if(pat.charAt(pi) == str.charAt[si] || pat.charAt[pi] == '.' && (pi+1 <
	 * pat.length() && pat[pi+1]!='*')){ return valid(str, pat, si+1, pi+1); }
	 */

}
