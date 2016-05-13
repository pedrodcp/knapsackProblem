
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Pedro on 09/05/2016.
 */
public class Demo {

    public static void main(String[] args) {
        Knapsack teste = new Knapsack(200);

        teste.add("map", 9, 150);
        teste.add("compass", 13, 35);
        teste.add("water", 153, 200);
        teste.add("sandwich", 50, 160);
        teste.add("glucose", 15, 60);
        teste.add("tin", 68, 45);
        teste.add("banana", 27, 60);
        teste.add("apple", 39, 40);
        teste.add("cheese", 23, 30);
        teste.add("beer", 52, 10);
        teste.add("suntan cream", 11, 70);
        teste.add("camera", 32, 30);
        teste.add("t-shirt", 24, 15);
//        teste.add("trousers", 48, 10);
//        teste.add("umbrella", 73, 40);
//        teste.add("waterproof trousers", 42, 70);
//        teste.add("waterproof overclothes", 43, 75);
//        teste.add("note-case", 22, 80);
//        teste.add("sunglasses", 7, 20);
//        teste.add("towel", 18, 12);
//        teste.add("socks", 4, 50);
//        teste.add("book", 30, 10);
//        teste.add("book", 30, 10);

        List<Item> itemList = teste.calcSolution();

        if (teste.isCalculated()) {
            NumberFormat nf = NumberFormat.getInstance();

            System.out.println("Peso máximo           = " + nf.format(teste.getMaxWeight()) + " kg");
            System.out.println("Peso total da solução = " + nf.format(teste.getSolutionWeight()) + " kg");
            System.out.println("Valor Total           = " + teste.getProfit());
            System.out.println();
            System.out.println("Items a levar: ");
            for (Item item : itemList) {
                if (item.getInKnapsack() > 0) {
                    System.out.println(item.getInKnapsack() + " unidade(s) " + item.getName() + " " + (item.getInKnapsack() * item.getWeight()) + "kg  " + " (valor = " + item.getInKnapsack() * item.getValue() + ")");
                }
            }
        } else {
            System.out.println(
                    "Erro!"
            );
        }

    }
}
