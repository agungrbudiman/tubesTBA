/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programtba;

import java.util.Stack;

/**
 *
 * @author agungrb
 */
public class State {   
    private Stack<Character> stack;
    private String in;
    private int i;
    private char head;
    private boolean habis;
    public String hasil,transisi;

    public State(String in) {
        this.in = in;
        stack = new Stack();
        i = 0;
        habis = false;
        hasil = "";transisi="";
        stack.push('#');
        q0(); 
    }
    
    private void error() {
        hasil = "TIDAK VALID";
    }
    
    private void finish() {
        hasil = "VALID";
    }
    
    private void nextChar() {
        if(i<in.length()) {
           //if(in.length()>=1) {
               head = in.charAt(i);
               i++;
               if(Character.isWhitespace(head)) {
                  nextChar();
               }
           //}
        }
        else {
            habis=true;
        }      
    }
    
    private void q0() {
        nextChar();
        if(!habis) {
            transisi = transisi + "q0-" + head + System.lineSeparator();
            switch(head) {
                case '1' : stack.push('x');q1();break;
                case '2' : q2();break;
                case '6' : stack.push('y');q3();break;
                case '9' : stack.push('(');q4();break;
                default : error();
            }
        }
        else {
            transisi = transisi + "q0-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q1() {
        nextChar();
        if(!habis) {
            transisi = transisi + "q1-" + head + System.lineSeparator();
           if(head == '1') {
               if(stack.lastElement().equals('x')) {
                   stack.pop();
                   q7();
               }
               else {
                   error();
               }
           }
           else if (head == '7') {
               if(stack.lastElement().equals('y')) {
                   stack.pop();
                   q5();
               }
               else {
                   error();
               }
           }
           else if(head == '3' || head == '4' || 
                   head == '5' || head == '8') {
               if(stack.lastElement().equals('x')) {
                   stack.pop();
                   q6();
               }
               else {
                   error();
               }
           }
           else {
               error();
           }
        }
        else {
            transisi = transisi + "q1-kosong" + System.lineSeparator();
            if(stack.lastElement().equals('x')) {
                stack.pop();
                q10();
            }
            else {
                error();
            }
        }
    }
    
    private void q2() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q2-" + head + System.lineSeparator();
           switch(head) {
               case '1' : stack.push('x');q1();break;
               case '9' : stack.push('(');q4();break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q2-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q3() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q3-" + head + System.lineSeparator();
           switch(head) {
               case '1' : q1();break;
               case '9' : stack.push('(');q4();break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q3-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q4() {
        transisi = transisi + "q4-ga baca" + System.lineSeparator();
        q0();
    }
    
    private void q5() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q5-" + head + System.lineSeparator();
           switch(head) {
               case '1' : q10();break;
               case '2' : q2();break;
               case '9' : stack.push('(');q4();break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q5-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q6() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q6-" + head + System.lineSeparator();
           switch(head) {
               case '1' : stack.push('x');q1();break;
               case '2' : q2();break;
               case '9' : stack.push('(');q4();break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q6-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q7() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q7-" + head + System.lineSeparator();
           switch(head) {
               case '0' :
                   if(stack.lastElement().equals('(')) {
                       stack.pop();
                       q8();
                   }
                   else {
                       error();
                   }
                   break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q7-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q8() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q8-" + head + System.lineSeparator();
           switch(head) {
               case '1' : q9();break;
               case '7' :
                   if(stack.lastElement().equals('y')) {
                       stack.pop();
                       q5();
                   }
                   else {
                       error();
                   }
                   break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q8-kosong" + System.lineSeparator();
            if(stack.lastElement().equals('#')) {
                stack.pop();
                finish();
            }
            else {
                error();
            }
        }
    }
    
    private void q9() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q9-" + head + System.lineSeparator();
           switch(head) {
               case '0' :
                   if(stack.lastElement().equals('(')) {
                       stack.pop();
                       q8();
                   }
                   else {
                       error();
                   }
                   break;
               default : error();
            } 
        }
        else {
            transisi = transisi + "q9-kosong" + System.lineSeparator();
            error();
        }
    }
    
    private void q10() {
        nextChar();
        if(!habis) {
        transisi = transisi + "q10-" + head + System.lineSeparator();
           switch(head) {
               case '1' : q7();break;
               default : error();
           }
        }
        else {
            transisi = transisi + "q10-kosong" + System.lineSeparator();
            if(stack.lastElement().equals('#')) {
                stack.pop();
                finish();
            }
            else {
                error();
            }
        }
    }
}


