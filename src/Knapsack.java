import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pedro on 09/05/2016.
 * Class geral. Cada item só pode existir uma vez é ou é adicionado à mochila ou não
 */
public class Knapsack {

    protected List<Item> itemList = new ArrayList<Item>();
    protected int maxWeight = 0;
    protected int solutionWeight = 0;
    protected int profit = 0;
    protected boolean calculated = false;

    public Knapsack() {
    }

    /**
     * Contrutor que define o peso máximo
     *
     * @param maxWeight
     */
    public Knapsack(int maxWeight) {
        setMaxWeight(maxWeight);
    }

    /**
     * Construtor que recebe os itens para entrar na mochila
     *
     * @param itemList
     */
    public Knapsack(List<Item> itemList) {
        setItemList(itemList);
    }

    /**
     * Construtor que recebe a lista de itens e o peso máximo
     *
     * @param itemList
     * @param maxWeight
     */
    public Knapsack(List<Item> itemList, int maxWeight) {
        setItemList(itemList);
        setMaxWeight(maxWeight);
    }

    /**
     * Algoritmo que calcula os itens que podem entrar na mochila que é definido no construtor {@link #Knapsack(int)}
     * Respeita a capacidade da mochila @maxWeight e maximiza o valor total dos itens(@profit), que são todos distintos.
     *
     * Solução usando programação dinâmica para o problem 0/1 knapsack que executa em tempo pseudo-polinomial
     *
     * Time complexity - O(nW)
     * n - numero de items distintos
     * W - Capacidade máxima da mochila
     * @return
     */
    public List<Item> calcSolution() {
        double initTime = System.currentTimeMillis();
        int n = itemList.size(); // numero de items

        setInitialStateForCalculation();
        if (n > 0 && maxWeight > 0) {                                  //1
            List<List<Integer>> c = new ArrayList<List<Integer>>();
            List<Integer> curr = new ArrayList<Integer>();

            c.add(curr);
            for (int j = 0; j <= maxWeight; j++)                     //2 - O(n)
                curr.add(0);
            for (int i = 1; i <= n; i++) {                           //3 - O(n)
                List<Integer> prev = curr;
                c.add(curr = new ArrayList<Integer>());
                for (int j = 0; j <= maxWeight; j++) {               //4 - O(n)
                    if (j > 0) {
                        int wH = itemList.get(i - 1).getWeight();
                        curr.add((wH > j) ? prev.get(j) : Math.max(prev.get(j), itemList.get(i - 1).getValue() + prev.get(j - wH)));
                    } else {
                        curr.add(0);
                    }
                } // fim do for j
            } // fim do fim i
            profit = curr.get(maxWeight);

            for (int i = n, j = maxWeight; i > 0 && j >= 0; --i) {
                int tempI = c.get(i).get(j);
                int tempI_1 = c.get(i - 1).get(j);
                if ((i == 0 && tempI > 0) || (i > 0 && tempI != tempI_1)) {
                    Item iH = itemList.get(i - 1);
                    int wH = iH.getWeight(); // Peso do item especifico
                    iH.setInKnapsack(1);
                    j -= wH;
                    solutionWeight += wH;
                }
            } // fim do for
            calculated = true;
        } // fim do if
        double endTime = System.currentTimeMillis();
        System.out.println("Algorithm execution time: " + (endTime - initTime)/1000 + " seconds");
        return itemList;
    }


    public void add(String name, int weight, int value) {
        if (name.equals(""))
            name = "" + (itemList.size() + 1);
        itemList.add(new Item(name, weight, value));
        setInitialStateForCalculation();
    }


    public void add(int weight, int value) {
        add("", weight, value);
    }


    public void remove(String name) {
        for (Iterator<Item> it = itemList.iterator(); it.hasNext(); ) {
            if (name.equals(it.next().getName())) {
                it.remove();
            }
        }
        setInitialStateForCalculation();
    }

    public void removeAllItems() {
        itemList.clear();
        setInitialStateForCalculation();
    }

    public int getProfit() {
        if (!calculated)
            calcSolution();
        return profit;
    }

    public int getSolutionWeight() {
        return solutionWeight;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int _maxWeight) {
        maxWeight = Math.max(_maxWeight, 0);
    }

    public void setItemList(List<Item> _itemList) {
        if (_itemList != null) {
            itemList = _itemList;
            for (Item item : _itemList) {
                item.checkMembers();
            }
        }
    }

    /**
     * Diz que todos os atributos já estão na mochila
     * inKnapsack = 1 para todos os items
     * @param inKnapsack
     */
    private void setInKnapsackByAll(int inKnapsack) {
        for (Item item : itemList)
            if (inKnapsack > 0)
                item.setInKnapsack(1);
            else
                item.setInKnapsack(0);
    }

    /**
     * atribui os valores default às variaveis de instancia da class
     */
    protected void setInitialStateForCalculation() {
        setInKnapsackByAll(0);
        calculated = false;
        profit = 0;
        solutionWeight = 0;
    }

}
