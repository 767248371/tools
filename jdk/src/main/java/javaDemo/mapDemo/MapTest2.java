package javaDemo.mapDemo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest2 {

    private static Map<String, Object> map1 = new HashMap<String, Object>();
    private static Map<String, Object> map2 = new Hashtable<String, Object>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<>());

    private static Map<String, Object> map = map4;//Exception in thread "Thread-0" java.util.ConcurrentModificationException
//    private static Map<String, Object> map = map3;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (map.size() > 0) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
                    }
                    map.clear();
                }
                try {
                    Thread.sleep((new Random().nextInt(10) + 1) * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                map.put("key" + i, "value" + i);
                try {
                    Thread.sleep((new Random().nextInt(10) + 1) * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
