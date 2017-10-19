
package bearbee;

public class Bear implements Runnable{
    private final Pot potOfHoney;
    
    public Bear(Pot potOfHoney){
        this.potOfHoney = potOfHoney;
    }

    @Override
    public void run() {
        String piezasComidas;
        while(true){
            try {
                this.potOfHoney.llenandose(); //el oso duerme mientras las abejas no hayan puesto la última pieza (el bote no esta llenao)
            } catch (InterruptedException ex) {
            }
            piezasComidas = this.potOfHoney.vaciar(); //el oso se despierta y se come la miel del bote
            System.out.println(piezasComidas);
            this.potOfHoney.setVacio();
        }
    }
    
}
