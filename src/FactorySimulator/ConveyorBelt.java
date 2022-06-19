package FactorySimulator;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConveyorBelt {
    private int maxCapacity;
    private Machine connectedMachine = null;
    private Dispatcher connectedDispatcher = null;
    Queue<Parcel<?>> theBelt;

    public ConveyorBelt(int maxCapacity){
    this.maxCapacity = maxCapacity;
    this.theBelt = new PriorityQueue<Parcel<?>>(this.maxCapacity);
    System.out.println("Belt has been created");
    }

    public ConveyorBelt(){
    this.maxCapacity = 10;
    this.theBelt = new PriorityQueue<Parcel<?>>(this.maxCapacity);
    }

    public synchronized boolean connectMachine(Machine machine){
        if(connectedMachine == null){
            this.connectedMachine = machine;
            return true;
        }
        System.out.println("There is already a machine connected");
        return false;

    }

    public synchronized boolean connectDispatcher(Dispatcher dispatcher){
        if(connectedDispatcher == null){
            this.connectedDispatcher = dispatcher;
            return true;
        }
        System.out.println("There is already a dispatcher connected");
        return false;

    }

    public synchronized boolean disconnectMachine(Machine machine){
        if(connectedMachine == machine){
            connectedMachine = null;
            return true;
        }

        return false;
    }

    public synchronized boolean disconnectDispatcher(Dispatcher dispatcher){
        if(connectedDispatcher == dispatcher){
            connectedDispatcher = null;
            return true;
        }
        return false;
    }

    public synchronized int size(){
        return theBelt.size();
    }

    public synchronized boolean isEmpty(){
        return (theBelt.size() == 0);
    }

    public synchronized boolean isFull(){
        return (theBelt.size() == maxCapacity);
    }

    public synchronized boolean postParcel(Parcel<?> p, Machine m){
        if(m == connectedMachine){
            theBelt.add(p);
            return true;
        }
        return false;
    }

    public synchronized Parcel<?> getFirstParcel(Dispatcher dispatcher){
        if(dispatcher == connectedDispatcher) {
            return theBelt.poll();
        }
        return null;
    }

    public synchronized Parcel<?> retrieveParcel(Dispatcher dispatcher){
        if(dispatcher == connectedDispatcher){
            return theBelt.peek();
        }
        return null;
    }

    public synchronized void drawBelt(Graphics g, int x, int y, int width, int height){
        g.drawRect(x,y,width,height);
        g.setColor(Color.WHITE);

    }

}
