package com.tree;

import java.util.*;
public class Main{
    public static final int BASE = 16;
    public static final Map<Character,Integer> map = new HashMap<Character,Integer>(){{
        put('0',0);
        put('1',1);
        put('2',2);
        put('3',3);
        put('4',4);
        put('5',5);
        put('6',6);
        put('7',7);
        put('8',8);
        put('9',9);

        put('A',10);
        put('B',11);
        put('C',12);
        put('D',13);
        put('E',14);
        put('F',15);
        put('a',10);
        put('b',11);
        put('c',12);
        put('d',13);
        put('e',14);
        put('f',15);
    }};

    public static int getNumber(String number){
        int res = 0;
        for(char ch: number.toCharArray()){
            res = res*BASE + map.get(ch);
        }
        return res;
    }
    public static void main(String[] args){
//        Scanner scn = new Scanner(System.in);
//
//        while(scn.hasNext()){
//            String number = scn.nextLine();
//            System.out.println(getNumber(number));
//        }
//        try{
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("start");
//                    throw new RuntimeException("aasdasdasd");
//                }
//            });
//            thread.start();
//        }catch (Exception e){
//            System.out.println("捕获线程异常"+e.getMessage());
//        }


//       thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//           @Override
//           public void uncaughtException(Thread t, Throwable e) {
//               System.out.println(e.toString());
//           }
//       });


        String fileName ="JkafkaSenderFailMsg-202204271657.txt";


        int pos = fileName.indexOf("-");
        System.out.println(fileName.substring(pos+1,pos+13));



    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int start = 0; start < s.length()-10; start++) {
            int end = start + 10;
            String str = s.substring(start, end);
            if (map.containsKey(str) && (map.get(str) - start) >= 10) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}