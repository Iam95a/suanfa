package com.chenq;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Set<Integer> set =
                Stream.iterate(0, item -> (item + 1)).limit(10).collect(Collectors.<Integer>toSet());
        AvlNode top = new AvlNode(11);
        try {
            for (Integer i : set) {
                top = top.insert(i, top);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(top);
    }

}
