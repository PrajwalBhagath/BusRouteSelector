package FactorySimulator;

import java.awt.*;
import java.util.Random;

public class Machine implements Runnable{
    public static int MIN_CONSUMPTION_TIME;
    public static int MAX_CONSUMPTION_TIME;
    public static int MAX_PRODUCTION_TIME;
    public static int MIN_PRODUCTION_TIME;
    ConveyorBelt[] conveyorBelts;

    public Machine(ConveyorBelt[] conveyorBelts) throws InterruptedException {
        System.out.println("Machine has been created");
        MIN_CONSUMPTION_TIME = 200;
        MAX_CONSUMPTION_TIME = 500;
        MIN_PRODUCTION_TIME = 200;
        MAX_PRODUCTION_TIME = 700;
        this.conveyorBelts = conveyorBelts;


    }


    @Override
    public void run() {
        // looping through the conveyer belt
        //checking if it is full or not and try
        //connect to the one that is not full
        //if the machine is already connected or
        //the belt is full then it will just continue
        //if successful connected then it will start posting parcels
        //the machine must sleep for a random amount of time between min and max production times

        //the machine produces a parcel w a random priority between 1-3 and a random letter and a random consume time
        //once a conveyor belt is full then ut disconnects from the belt and repeats the process
        int counter = 0;
        boolean s = true;
        Random rand = new Random();
        Color colour;

        while (s){
            if(conveyorBelts[counter].isFull() == false && conveyorBelts[counter].connectMachine(this)){
                System.out.println("Machine has been connected to belt number"+ counter);
                boolean r = true;
                while (r){
                    char randomChar = (char) ('a'+rand.nextInt(26));
                    colour = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
                    int randomConsumption = rand.nextInt((MAX_CONSUMPTION_TIME - MIN_CONSUMPTION_TIME)+1)+MIN_CONSUMPTION_TIME;
                    int randomPriority = rand.nextInt(3);

                    Parcel parcel = new Parcel(randomChar,colour,randomConsumption,randomPriority);
                    conveyorBelts[counter].postParcel(parcel, this);
                    System.out.println("Parcel has been posted");

                    int randomSleepTime = rand.nextInt((MAX_PRODUCTION_TIME-MIN_PRODUCTION_TIME)+1)+MIN_PRODUCTION_TIME;
                    try {
                        Thread.sleep(randomSleepTime);
                    } catch (InterruptedException e) {

                    }

                    if(conveyorBelts[counter].isFull()){
                        r =false;
                        conveyorBelts[counter].disconnectMachine(this);
                        System.out.println("Machine has disconnected from belt "+ counter);
                        break;
                    }
                }
            }
            counter++;
            if(counter == conveyorBelts.length-1){
                counter = 0;
            }
        }
    }
}
