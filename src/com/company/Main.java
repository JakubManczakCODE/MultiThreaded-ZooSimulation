package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;



public class Main {

    public static void main(String[] args) throws IOException {


        Map<String, Animals> map = new HashMap<>();
        Stream<String> stream = Files.lines(Paths.get("mammals.txt"));

        map = stream
                .skip(1)
                .map(x -> x.replaceAll("NA", "0"))
                .map(x -> x.split(";"))
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> {
                            List<String> tmpList = new ArrayList<>();
                            tmpList.addAll(Arrays.asList(x));
                            Animals animals = new Animals(tmpList.toArray(new String[10]));
                            return animals;
                        }
                        )

                )

        ;

        stream.close();

        Map<String,Animals> treeMap = new TreeMap<String, Animals>(
                new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        if (s1.length() > s2.length()) {
                            return -1;
                        } else if (s1.length() < s2.length()) {
                            return 1;
                        } else {
                            return s1.compareTo(s2);
                        }
                    }
                }
        );
        treeMap.putAll(map);
        treeMap.entrySet().forEach(entery -> {

            System.out.println("\u001B[32m"+entery.getKey() + "\u001B[0m"+"  " + entery.getValue().getValues());
       });

      System.out.println();
        findMaxWeigth(map);
       System.out.println();
        findMaxSleepRatio(map);
       findLivingAboveAvg(map);


        List<Animals> zoo = new ArrayList<>(makeList(map));
        for (Animals a:zoo){
            System.out.println(a.getValues());
        }

        int numberOfEntitiesSimulated = 3;


        double animal1w=zoo.get(0).getBodyWtDouble();
        double animal2w=zoo.get(1).getBodyWtDouble();
        double animal3w=zoo.get(2).getBodyWtDouble();

       Thread main = new Thread(()->{
           for (int i=1;i<=365;i++) {
               System.out.println("\u001B[34m"+"Day "+ "\u001B[0m" +i);
               for(int x =0;x<numberOfEntitiesSimulated;x++){
                        for(int y=0;y<2;y++) {
                            System.out.println("\u001B[34m"+"Feeding no: "+ "\u001B[0m"+(y+1)+"\u001B[34m"+"   For animal: "+ (x+1)+ "\u001B[0m");
                            Simulation simulation = new Simulation(zoo, x);
                            while (simulation.isAlive()){
                                try {
                                    simulation.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                simulation.notify();
                            }
                            simulation.run();


                      }
               }
           }
       });
       main.start();
        try {
            main.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n"+"Animal 1 weight diffrence is: "+ (animal1w-(zoo.get(0).getBodyWtDouble())));
        System.out.println("Animal 2 weight diffrence is: "+ (animal2w-(zoo.get(1).getBodyWtDouble())));
        System.out.println("Animal 3 weight diffrence is: "+ (animal3w-(zoo.get(2).getBodyWtDouble())));

    }



    static void findMaxWeigth(Map<String, Animals> map) {

        double tmp = 0;
        String get = "";
        for (Map.Entry<String, Animals> entry : map.entrySet()) {
            if ((entry.getValue().getBodyWtDouble()) > tmp) {
                tmp = entry.getValue().getBodyWtDouble();
                get = "";
                get = ("\u001B[32m"+entry.getKey() + "\u001B[0m"+" = "   + entry.getValue().getValues());
            }
        }
        System.out.println("\u001B[31m" + "Największa waga to: " + "\u001B[0m" + tmp + "\u001b[31m" + " obiekt: " + "\u001B[0m" + get + "\u001B[31m");

    }

    static void findMaxSleepRatio(Map<String, Animals> map) {
        double first= 0;double second = 0;double third = 0;double ratio=0;String firstAnimal="";String secondAnimal="";String thirdAnimal="";
        for (Map.Entry<String, Animals> entry : map.entrySet()) {
           double dream = entry.getValue().getDreaming();
           double totalSleep = entry.getValue().getTotalSleep();
           ratio=dream/totalSleep;
           if(dream==0.0){

           }else if (totalSleep==0.0){

           }else {
               if(ratio>first){
                   first=ratio;
                   firstAnimal="";
                   firstAnimal=("\u001B[32m"+entry.getKey() + "\u001B[0m"+" = " + entry.getValue().getValues());
               }else if (ratio>second){
                   second=ratio;
                   secondAnimal="";
                   secondAnimal=("\u001B[32m"+entry.getKey() + "\u001B[0m"+" = "  + entry.getValue().getValues());

               }else if (ratio>third){
                   third=ratio;
                   thirdAnimal="";
                   thirdAnimal=("\u001B[32m"+entry.getKey() + "\u001B[0m"+" = "  + entry.getValue().getValues());

               } else {}
           }
        }
        System.out.println(
                        "\u001B[31m" + "Pierwszy: " + "\u001B[0m" + first +"%"+ "\u001b[31m" + " obiekt: " + "\u001B[0m" + firstAnimal + "\u001B[31m"+"\n"+
                        "\u001B[31m" + "Drugi: " + "\u001B[0m" + second +"%"+ "\u001b[31m" + " obiekt: " + "\u001B[0m" + secondAnimal + "\u001B[31m"+"\n"+
                        "\u001B[31m" + "Trzeci: " + "\u001B[0m" + third +"%"+ "\u001b[31m" + " obiekt: " + "\u001B[0m" + thirdAnimal + "\u001B[31m"+"\n"


        );



    }
    static void findLivingAboveAvg(Map<String, Animals> map) {
        double avg=0;
        int count=0;

        for (Map.Entry<String, Animals> entry : map.entrySet()) {
           avg+= entry.getValue().getLifeSpan();
           count++;

        }
        avg=avg/count;

        for (Map.Entry<String, Animals> entry : map.entrySet()){
            if (entry.getValue().getLifeSpan()>avg){
                System.out.println("\u001B[31m" + "Długość życia to: " + "\u001B[0m" + entry.getValue().getLifeSpan() +" lat"+ "\u001b[31m" + " dla obiektu: " + "\u001B[0m" +"\u001B[32m"+entry.getKey() + "\u001B[0m"+" = "  + entry.getValue().getValues() + "\u001B[31m"+"  ogólna średnia to:   "+"\u001B[0m"+avg);
            }

        }



    }
    static List<Animals> makeList(Map<String,Animals> map){
        List<Animals> zoo = new ArrayList<>(
        );
        List<Animals> animals = new ArrayList<>(map.values());
        Collections.sort(animals,Comparator.comparingDouble(Animals::getBodyWtDouble).reversed());
                zoo.add(animals.get(0));
                zoo.add(animals.get(1));
                zoo.add(animals.get(2));
                return zoo;
        }





    }
