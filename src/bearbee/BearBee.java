
package bearbee;

public class BearBee {

    protected static final int CAPACITY_OF_POT = 10;
    protected static final int TOTAL_BEE = 5;
    protected static final Thread BEES[] = new Thread[TOTAL_BEE];
    protected static Thread BEAR;
    
    
    
    public static void main(String[] args) {
        Pot pot = new Pot(CAPACITY_OF_POT);
        Bee bee = new Bee(pot);
        Bear bear = new Bear(pot);
        BEAR = new Thread(bear);
        //Create Bees
        for(int i=0; i<TOTAL_BEE;){
            BEES[i] = new Thread(bee);
            BEES[i].setName("bee_"+String.valueOf(++i));
        }
        
        for(int i=0; i<TOTAL_BEE; i++){
            BEES[i].start();
        }
        BEAR.start();
        for(int i=0; i<TOTAL_BEE; i++){
            try {
                BEES[i].join();
            } catch (InterruptedException ex) {
            }
        }
        try {
            BEAR.join();
        } catch (InterruptedException ex) {
        }
    }
    

    
    
}
