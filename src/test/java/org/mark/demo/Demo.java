package org.mark.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: huangzhiqiang
 * @create: 2022/01/20 14:56
 */
public class Demo {

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add(2);
        objects.forEach(System.out::println);

        List<Object> collect = objects.stream().filter(x -> x instanceof String).collect(Collectors.toList());
        objects.removeAll(collect);


        objects.forEach(System.out::println);
    }

}
