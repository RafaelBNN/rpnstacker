import java.util.Scanner;
import java.util.Stack;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import token.*;

public class rpnstacker {
 
    static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception
    {

        File file = new File(
            "./Calc1.stk");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        List<Token> listaTokens = new ArrayList<Token>();

        while ((st = br.readLine()) != null){
            if(isNumeric(st)){
                listaTokens.add(new Token(TokenType.NUM, st)); // adicionando o token a lista
            }
            else{

                switch(st){
                    case "+": listaTokens.add(new Token(TokenType.PLUS, st)); break;
                    case "-": listaTokens.add(new Token(TokenType.MINUS, st)); break;
                    case "*": listaTokens.add(new Token(TokenType.STAR, st)); break;
                    case "/": listaTokens.add(new Token(TokenType.SLASH, st)); break;
                    default: throw new Exception("Unexpected character: " + st);
                }

            }

        }
        
        // Printando a lista de tokens
        System.out.println(listaTokens);

        // Criando a stack para calcularmos o resultado
        Stack stk = new Stack();

        String operand1 = "";
        String operand2 = "";

        while(!listaTokens.isEmpty()){
            Token aux = listaTokens.remove(0);

            if(aux.type == TokenType.NUM) stk.push(aux.lexeme);
            else{
                operand1 = stk.pop().toString();
                operand2 = stk.pop().toString();

                if(aux.type == TokenType.PLUS) stk.push((String.valueOf(Integer.parseInt(operand2) + Integer.parseInt(operand1))));
                else if(aux.type == TokenType.MINUS) stk.push((String.valueOf(Integer.parseInt(operand2) - Integer.parseInt(operand1))));
                else if(aux.type == TokenType.STAR) stk.push((String.valueOf(Integer.parseInt(operand2) * Integer.parseInt(operand1))));
                else if(aux.type == TokenType.SLASH) stk.push((String.valueOf(Integer.parseInt(operand2) / Integer.parseInt(operand1))));
                
            }
        }

        br.close();

        System.out.println(stk.peek());

    }

}