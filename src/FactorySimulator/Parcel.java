package FactorySimulator;

import java.awt.*;
import java.util.Random;

public class Parcel<E> implements Comparable<Parcel<?>> {
    E element;
    Color colour;
    int consumeTime;
    int priority;
    long timeStamp;

    public Parcel(E e, Color c, int ct, int p)
    {
        this.colour = c;
        this.consumeTime = ct;
        this.priority = p;
        this.element = e;
        this.timeStamp = System.nanoTime();
    }

    public void consume() throws InterruptedException {
    Thread.sleep(consumeTime);
    }

    public String toString(){
        return "("+element+","+priority+")";
    }


    public int compareTo(Parcel<?> p) {
    if(p.priority > priority){
        return 1;
    }
    if(p.priority == priority){
        return 0;
    }
    if(p.priority <priority){
        return -1;
    }
    else if (p.timeStamp > timeStamp)
    {
        return 1;
    }
    return -1;
    }


    public void drawBox(Graphics g, int x, int y, int width, int height){
        g.fillRect(x, y, width, height);
        g.setColor(colour);
    }


}
