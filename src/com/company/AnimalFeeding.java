package com.company;


public class AnimalFeeding {
    private double weigth,burned,gained;

    AnimalFeeding(double weigth){
        this.weigth=weigth;

    }
    public synchronized void feed(){
        gained=weigth;
        weigth=weigth+(weigth*(Math.random()*0.015+0.005));
        gained=weigth-gained;
    }
    public synchronized void burn(){
        burned=(weigth*0.014);
        weigth=weigth-(weigth*0.014);
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public double getBurned() {
        return burned;
    }

    public double getGained() {
        return gained;
    }
}
