package com.problem;

import com.stack.MyStack;
import com.stack.Stack;

public class Calculator {

    public static void main(String[] args) throws Exception{
        Calculator c = new Calculator();
        String[] ip= new String[]{"2","*","3","+","5","/","6","*","3","+","15"};
        String[] ip1= new String[]{"2","-","6","-","7","*","8","/","2","+","5"};
        double op = c.calculate(ip);
        System.out.println("op --> "+op);
        
        double op1 = c.calculate(ip1);
        System.out.println("op1 --> "+op1);
    }
    
    public double calculate(String[] ip) throws Exception{
        int n = ip.length;
        double res =0;
        Stack<String> stack = new MyStack<>(n);
        for(int i=0;i<n;i++) {
            if(isNumber(ip[i]) || isAddOrSub(ip[i])) {
                if("-".equals(ip[i])) {
                    stack.push("+");
                    stack.push("-"+ip[++i]);
                }
                else
                stack.push(ip[i]);
            }else if(isProdOrDiv(ip[i])) {
                String op = ip[i];
                double o1 = Double.parseDouble(stack.pop());
                double o2 = Double.parseDouble(ip[++i]);
                res = op(o1,o2,op);
                stack.push(String.valueOf(res));
            }
        }
        double a = 0 ;
        while(!stack.isEmpty()) {
            
            String x = stack.pop();
            if(isNumber(x)) {
                a = Double.parseDouble(x);
            }
            else {
                res = op(a,Double.parseDouble(stack.pop()),x);
                stack.push(String.valueOf(res));
            }
        }
        return res;
    }
    
    public double op(double a,double b, String op) throws Exception{
        switch(op) {
        case "+":
            return a+b;
        case "-":
            return a-b;
        case "*":
            return a*b;
        case "/":
            return a/b;
        }
        throw new Exception("Invalid operator");
    }
    public boolean isNumber(String ip) {
        //To do
        try {
            Double.parseDouble(ip);
        }catch(NumberFormatException e) {
            return false;
        }
         
        return true;
    }
    public boolean isAddOrSub(String ip) {
        //To do 
        return "+".equals(ip) || "-".equals(ip);
    }
    public boolean isProdOrDiv(String ip) {
        //To dp 
        return "*".equals(ip) || "/".equals(ip);
    }
}
