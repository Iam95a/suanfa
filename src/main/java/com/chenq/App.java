package com.chenq;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Set<Integer> set =
                Stream.iterate(0, item -> (item + 1)).limit(10000000).collect(Collectors.<Integer>toSet());
        AvlNode top = new AvlNode(11);
        long startTime = System.currentTimeMillis();
        try {
            for (Integer i : set) {
                top = top.insert(i, top);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);
        System.out.println(top.height);
        TreeSet hashSet = new TreeSet<>();
        long startTime2 = System.currentTimeMillis();
        for (Integer i : set) {
            hashSet.add(i);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);
    }

}
