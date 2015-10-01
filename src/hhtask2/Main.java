package hhtask2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**
 * точка входа в программу
 */
public class Main {

    /**
     * ввод данных пользователя и вывод результатов
     * обработка ошибок генерируемых классом Fraction
     * ловит также исключения, возникающие при несоответствии числителя или знаменателя
     * базе системы счисления
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] userInput;
        String str;
        while (true) {
            System.out.println("Enter your fraction and it's base (type \"exit\" to exit):");
            str = sc.nextLine();
            if (str.equalsIgnoreCase("exit"))
                break;
            userInput = str.split(" ");
            try{
                Fraction dr = new Fraction(userInput[0], userInput[1], parseInt(userInput[2]));
                System.out.println(dr.getPeriod());
                System.out.println("---------------");
            }catch (IllegalArgumentException e){
                String errorMsg[];
                errorMsg = e.toString().split(":", 2);
                System.out.println("Incorrect data,"+errorMsg[1]+", Try again");
            }
        }
        System.out.println("Bye...");
    }
}
