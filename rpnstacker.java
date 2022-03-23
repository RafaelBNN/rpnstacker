import java.util.Scanner;
import java.util.Stack;
import java.io.*;

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
            "E:/2021.2/Compiladoes/Calc1.stk");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        Stack stk = new Stack();

        String operand1 = "";
        String operand2 = "";

        while ((st = br.readLine()) != null){
            if(isNumeric(st)){
                stk.push((st));
            }
            else{
                operand1 = stk.pop().toString();
                operand2 = stk.pop().toString();

                switch(st){
                    case "+": stk.push(String.valueOf(Integer.parseInt(operand2) + Integer.parseInt(operand1))); break;
                    case "-": stk.push(String.valueOf(Integer.parseInt(operand2) - Integer.parseInt(operand1))); break;
                    case "*": stk.push(String.valueOf(Integer.parseInt(operand2) * Integer.parseInt(operand1))); break;
                    case "/": stk.push(String.valueOf(Integer.parseInt(operand2) / Integer.parseInt(operand1))); break;
                }
            }

        }

        br.close();

        System.out.println(stk.peek());

    }

}