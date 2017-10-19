
package bearbee;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Katia
 */
public class Pot {
    private final int capacityOfPot;
    private final Semaphore despertador; 
    private final Semaphore mutex; 
    private final Semaphore blockBee; 
    private Deque<String> honeyPot; 
    
    public Pot(int CapacityOfPot){
        this.despertador = new Semaphore(0, true);
        this.mutex = new Semaphore(1, true);
        this.blockBee = new Semaphore(CapacityOfPot-1,true);
        this.honeyPot = new ArrayDeque(CapacityOfPot);
        this.capacityOfPot = CapacityOfPot;
    }
    
    //la abeja pone la pieza de miel en el bote de miel
    public void reciveAbeja(String piceOfHoney) throws InterruptedException{
        this.mutex.acquire(1);
        this.honeyPot.add(piceOfHoney);
        System.out.println("Se ha añadido al bote de miel: "+piceOfHoney);
        this.despertador.release(1);
    }
    
    //la abeja vuelve a la colmena despues de poner la pieza de miel en el pote
    public void despideAbeja() throws InterruptedException{
        this.blockBee.acquire(1); //si es la ultima abeja se bloqueada, por lo que bloquea a todas las demas en el mutex
        this.mutex.release(1); //da paso a que otra abeja ponga miel en el bote
    }
    
    //el oso come las piezas de miel del bote
    public String vaciar(){
        String comida= "\nEL OSO COME:\n";
        do{
        comida += this.honeyPot.poll()+"\n";
        }while(!this.honeyPot.isEmpty());
        return comida;
    }
    
    //el bote está vacío así que la última abeja se desbloquea para volver a la colmena y que continuen la produccion de miel
    public void setVacio(){
        this.blockBee.release(this.capacityOfPot); //desbloquea a la ultima abeja que llenó el pote 
    }
    
    //el oso se hecha a dormir
    public void llenandose() throws InterruptedException{
        this.despertador.acquire(this.capacityOfPot);
    }
}
