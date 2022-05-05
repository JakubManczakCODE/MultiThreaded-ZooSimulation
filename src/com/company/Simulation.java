package com.company;
import java.util.ArrayList;
import java.util.List;

public class Simulation extends Thread {
    private List<Animals> zoo = new ArrayList<>();
    private final int animalindex;

    Simulation(List<Animals> zoo,int animalindex){
        this.zoo=zoo;
        this.animalindex=animalindex;

    }


    public void run(){
        AnimalFeeding animalFeeding = new AnimalFeeding(zoo.get(animalindex).getBodyWtDouble());
        animalFeeding.feed();
        System.out.println("animal "+(animalindex+1)+" gained: "+"\u001B[31m"+animalFeeding.getGained()+"\u001B[0m");
        try {
            Thread.sleep(1 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        animalFeeding.burn();
        System.out.println("animal "+(animalindex+1)+" burned: "+"\u001B[31m"+animalFeeding.getBurned()+"\u001B[0m");
        zoo.get(animalindex).setBodyWt(animalFeeding.getWeigth());
    }


}
