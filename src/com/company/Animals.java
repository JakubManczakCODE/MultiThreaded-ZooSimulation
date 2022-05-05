package com.company;

public class Animals {
    private double bodyWt;
    private final double brainWt;
    private final double nonDreaming;
    private final double dreaming;
    private final double totalSleep;
    private final double lifeSpan;
    private final double gestation;
    private final int predation;
    private final int exposure;
    private final int danger;

    Animals(String[] parameters) {

        bodyWt = Double.parseDouble(parameters[1]);
        brainWt = Double.parseDouble(parameters[2]);
        nonDreaming = Double.parseDouble(parameters[3]);
        dreaming = Double.parseDouble(parameters[4]);
        totalSleep = Double.parseDouble(parameters[5]);
        lifeSpan = Double.parseDouble(parameters[6]);
        gestation = Double.parseDouble(parameters[7]);
        predation = Integer.parseInt(parameters[8]);
        exposure = Integer.parseInt(parameters[9]);
        danger = Integer.parseInt(parameters[10]);


    }


    public synchronized double getBodyWtDouble() {
        return bodyWt;
    }

    public double getDreaming() {
        return dreaming;
    }

    public double getTotalSleep() {
        return totalSleep;
    }

    public double getLifeSpan() {
        return lifeSpan;
    }



    public synchronized void setBodyWt(double body_wt) {
        this.bodyWt = body_wt;
    }

    public String getValues() {


        return "Animal{" +
                "\u001B[34m"+" body_wt= "+"\u001B[0m"+(bodyWt ==0.0?"unknown": bodyWt)+
                "\u001B[34m"+" brain_wt= " +"\u001B[0m" +(brainWt ==0.0?"unknown": brainWt)+
                "\u001B[34m"+" non_dreaming= "+"\u001B[0m"+(nonDreaming ==0.0?"unknown": nonDreaming)+
                "\u001B[34m"+" dreaming= " +"\u001B[0m"+(dreaming==0.0?"unknown":dreaming)+
                "\u001B[34m"+" total_sleep= " + "\u001B[0m" +(totalSleep ==0.0?"unknown": totalSleep)+
                "\u001B[34m"+" life_span= " + "\u001B[0m" +(lifeSpan ==0.0?"unknown": lifeSpan)+
                "\u001B[34m"+" gestation= " +"\u001B[0m" + (gestation==0.0?"unknown":gestation)+
                "\u001B[34m"+" predation= " +"\u001B[0m" + (predation==0?"unknown":predation)+
                "\u001B[34m"+" exposure= " + "\u001B[0m" +(exposure==0?"unknown":exposure)+
                "\u001B[34m"+" danger= " +"\u001B[0m" +(danger==0?"unknown":danger) + "}";

    }

}

