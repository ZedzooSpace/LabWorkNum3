package ru.zotov.bird;

import java.util.Random;

public class Cuckoo extends Bird {
    @Override
    public void sing() {
        Random random = new Random();
        int times = random.nextInt(10) + 1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append("ку-ку");
            if (i < times - 1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
