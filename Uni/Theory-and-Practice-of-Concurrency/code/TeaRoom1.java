import java.util.LinkedList;

class Coin { }
class Tea { }

// *****************************************************************************

class Machine {

    public int supply;
    public int profit;

    public Machine(int supply) {
        this.supply = supply;
        this.profit = 0;
    }

    public Tea vend(Coin c, String drinkerName) {
        if (this.supply > 0) {
            // Accounting
            this.profit++;
            this.supply--;
            // You get tea
            System.out.println("Machine is dispensing for " + drinkerName);
            return new Tea();
        } else {
            // I'm bad
            return null;
        }
    }
}

// *****************************************************************************

class Drinker {

    private Machine machine;
    private int walletSize;
    private String name;

    public Drinker(Machine machine, int walletSize, String name) {
        this.machine = machine;
        this.walletSize = walletSize;
        this.name = name;
    }

    public void run() {
        while (this.walletSize > 0) {
            // Take out a coin and try to get tea
            this.walletSize--;
            Tea tea = this.machine.vend(new Coin(), this.name);

            if (tea != null) {
                // Yay we got tea!
                try {
                    System.out.println(this.name + " is drinking some tea.");
                    // Drinking tea takes exactly 500ms (top scientists confirm)
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // Someone stopped the process
                }
            } else
                System.out.println(this.name + " was robbed");
        }

        System.out.println(this.name + ": all out of cash.");
    }
}

// *****************************************************************************

public class TeaRoom {

    public static int numberOfCustomers = 3;

    public static void main(String args[]) {
        // TeaMate 1000 - Holds 1000 teas
        Machine machine = new Machine(1000);

        for (int i = 0; i < numberOfCustomers; i++) {
            Drinker d = new Drinker(machine, 5, "Customer " + i);
            d.run();
        }

        // ....
    }

}
