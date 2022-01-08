package org.mark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: huangzhiqiang
 * @create: 2022/01/07 15:29
 */
public class Demo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            System.out.println(list.remove(random.nextInt(list.size())));

        }
    }

}
