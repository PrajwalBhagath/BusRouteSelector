package FactorySimulator;

import java.awt.*;
import java.util.Random;

public class Dispatcher implements Runnable{
    private ConveyorBelt[] conveyorBelts;

    public Dispatcher(ConveyorBelt[] conveyorBelts) throws InterruptedException {
        System.out.println("Dispatcher has been created");
        this.conveyorBelts = conveyorBelts;
    }

    @Override
    public void run() {
         /*
        looping through the conveyerbel array until it can
        it will try connecting to a conveyer belt if its not empty
        and doesnt have a dispatcher connected

        it will call the consume method from parcel. it then removes the parcel from the
        conveyor belt and disconnects from the belt and repeats process. the dispatcher only removes one
        parcel and disconnects.
         */

        int counter = 0;
        boolean s = true;
        Random rand = new Random();


        while (s){
            if(conveyorBelts[counter].isEmpty() == false && conveyorBelts[counter].connectDispatcher(this)){
                System.out.println("Dispatcher has been connected to belt "+counter);
                Parcel parcel = conveyorBelts[counter].retrieveParcel(this);

                try {
                    parcel.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                conveyorBelts[counter].getFirstParcel(this);
                System.out.println("parcel has been dispatched in belt "+counter);
                conveyorBelts[counter].disconnectDispatcher(this);
                System.out.println("Dispatcher has been disconnected from belt"+ counter);


                try {
                    Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            counter++;
            if(counter == conveyorBelts.length-1){
                counter = 0;
            }
        }

    }
}
