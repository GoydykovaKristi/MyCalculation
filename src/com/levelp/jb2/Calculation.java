package com.levelp.jb2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculation {

    public static void main(String[] args){
        System.out.println("Введите пример, используя числа от 1 до 10 (включительно) " +
                "только в виде арабских чисел, либо только в виде римских (I - X). " +
                "Для выхода из программы - напишите \"end\"!");
        //позволяем пользователю ввести пример в консоль
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String vvodStroki = reader.readLine();
            //вводим цикл while, что бы пользователь мог вводить примеры, не выходя из программы
            while (!vvodStroki.equals("end")) {
                /*
                разделяем введенную строку по разделителю " " и вносим в массив с помощью статического метода
                splitStr класса Calculation
                 */
                String[] primerSimvols = splitStr(vvodStroki);

                // Если первое число является Римским числом, начинаем обрабатывать римские числа
                if (primerSimvols[0].contains("I") || primerSimvols[0].contains("V") || primerSimvols[0].contains("X")) {
                    // Проверяем, что и второе число римское
                    if (primerSimvols[2].contains("I") || primerSimvols[2].contains("V") || primerSimvols[2].contains("X")){

                        CalcRim rim1 = CalcRim.valueOf(primerSimvols[0]); // создаем объект 1-го римского числа
                        if (rim1.getValue() <= 0 || rim1.getValue() > 10) // проверяем значение 1-го числа
                            throw new IllegalArgumentException("Вы ввели недопустимое ПЕРВОЕ число, введите число от 1 до 10");

                        String operation = primerSimvols[1]; // сохраняем знак между числами

                        CalcRim rim2 = CalcRim.valueOf(primerSimvols[2]); // создаем объект 2-го римского числа
                        if (rim2.getValue() <= 0 || rim2.getValue() > 10) // проверяем значение 2-го числа
                            throw new IllegalArgumentException("Вы ввели недопустимое ВТОРОЕ число, введите число от 1 до 10");

                        /*
                         Расчет результата с помощью статического метода raschetResult класса Calculation
                         */
                        int result = raschetResult(rim1.getValue(), operation, rim2.getValue());

                        // проверяем, что число результата >= 0
                        if (result >= 0){
                            CalcRim rim = CalcRim.fromInt(result);
                            System.out.println(rim);
                        }else{
                            System.out.println("Ваше число меньше нуля. " +
                                    "Римская система счисления является непозиционной системой счисления и в ней отсутствуют отрицательные числа");
                        }

                    }else
                        try {
                            //Исключение, если пользователь вввел римское число  и потом арабское в одном примере
                            throw new MultiNumberException("Калькулятор умеет работать либо только с арабскими, " +
                                    "либо только с римскими цифрами одновременно");
                        }catch (MultiNumberException e) {
                            e.printStackTrace();
                        }
                    // Если первое число является Арабским числом - начинаем обрабатывать арабские числа
                }else {
                    int num1 = privodimKInt(primerSimvols[0]); // сохраняем первое число
                    if (num1 <= 0 || num1 > 10) // проверяем значение 1-го числа
                        throw new IllegalArgumentException("Вы ввели недопустимое ПЕРВОЕ число, введите число от 1 до 10");

                    String operation = primerSimvols[1]; // сохраняем знак между числами

                    if (primerSimvols[2].contains("I") || primerSimvols[2].contains("V") || primerSimvols[2].contains("X")) {
                        try {
                            //Исключение, если пользователь вввел арабское число и потом римское в одном примере
                            throw new MultiNumberException("Калькулятор умеет работать либо только с арабскими, либо только с римскими цифрами одновременно");
                        } catch (MultiNumberException e) {
                            e.printStackTrace();
                        }
                    }else {
                        int num2 = privodimKInt(primerSimvols[2]); // сохраняем второе число
                        if (num2 <= 0 || num2 > 10) // проверяем значение 2-го числа
                            throw new IllegalArgumentException("Вы ввели недопустимое ВТОРОЕ число, введите число от 1 до 10");
                        int result = raschetResult(num1, operation, num2); // Расчет результата
                        System.out.println(result);
                    }
                }

                System.out.println("Введите пример. Для выхода из программы - напишите \"end\"!");
                reader = new BufferedReader(new InputStreamReader(System.in));
                vvodStroki = reader.readLine();
            }

        }catch (IOException e){
            System.out.println("Вы ничего не ввели!");
        }catch (ArithmeticException e){
            System.out.println("На ноль делить нельзя!");
        }catch (OperationException e){
            System.out.println("Вы ввели неверный знак между числами. Калькулятор умеет складывать '+', вычитать '-', умножать '*', делить '/'.");
        }catch (NumberFormatException e){
            System.out.println("Вы ввели некорректные данные для начала работы калькулятора");
        }
        finally {
            try {
                reader.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] splitStr(String str) throws IOException {
        if (str.isEmpty()) throw new IOException("Вы ничего не ввели!");
        String[] strPoSimvolam = str.split(" ");
        if(strPoSimvolam.length != 3) throw new IndexOutOfBoundsException("Для примера нужны 3 составляющих: " +
                "первое число, знак, второе число " +
                "(каждый элемент пишем через пробел)");
        return strPoSimvolam;
    }

    // обработка строки с числом (приведение к типу int)
    public static int privodimKInt(String sNum1) {
        return Integer.parseInt(sNum1);
    }

    // Вывод результата исходя из знака между числами
    public static int raschetResult(int number1, String operation, int number2) throws OperationException {
        int a;
        switch (operation) {
            case "+":
                a = number1 + number2;
                break;
            case "-":
                a = number1 - number2;
                break;
            case "*":
                a = number1 * number2;
                break;
            case "/":
                a = number1 / number2;
                break;
            default:
                throw new OperationException();
        }
        return a;
    }
}
