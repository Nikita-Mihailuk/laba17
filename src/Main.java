import java.io.*;
import java.util.Scanner;

class Equation implements Serializable {
    private double x;
    private double y = 0;

    Equation(double x){
        this.x = x;
    }
    public double CalculateY(double x) { return x - Math.sin(x);}

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner in1 = new Scanner(System.in);
        System.out.print("Введите x: ");
        double x = in.nextDouble();

        while (true) {
            System.out.print("Введите команду (save/upload): ");
            String input = in1.nextLine();

            if (input.equals("save")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saveObject.txt"))) {

                    Equation equation = new Equation(x);
                    equation.setY(equation.CalculateY(x));

                    oos.writeObject(equation);
                    System.out.println("Объект сохранён");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (input.equals("upload")) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saveObject.txt"))) {
                    Equation newEquation = (Equation) ois.readObject();
                    System.out.println("Объект востановлен");
                    System.out.printf("x: %f \ny: %f \n", newEquation.getX(), newEquation.getY());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Неверная команда");
                break;
            }
        }
    }
}