
package bearbee;

import java.util.Random;

public class Bee implements Runnable{
    private static final String PREFIX_NAME = "Bee_";
    private int nHoneyPiece;
    private Pot potOfHoney;
    
    public Bee(Pot pot){
        this.nHoneyPiece = 0;
        this.potOfHoney = pot;
    }
    
    private String makeHoney(){
        String pieceOfHoney;
        pieceOfHoney = " pieza_"+String.valueOf(++this.nHoneyPiece)+" hecha por: "+Thread.currentThread().getName();
        return pieceOfHoney;
    }

    @Override
    public void run() {
        String honey;
        while(true){

            honey = this.makeHoney();  //fabrica miel
            try {
                this.potOfHoney.reciveAbeja(honey);  //lleva miel al bote         
                this.potOfHoney.despideAbeja(); //viene del boete despues de poner miel (y despertar al oso si ha sido la ultima                    
                Thread.sleep(Integer.toUnsignedLong(new Random().nextInt(5000))); //duerme para que se pueda ver mejor la ejecución
            } catch (InterruptedException ex) {
            }
        }
    }
    
}
