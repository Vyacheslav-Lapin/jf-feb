package com.epam.jf.feb;

import lombok.experimental.FieldDefaults;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class Main {

    public static void main(String... args) {
//        Map<String, String> hashMap = new HashMap<>();

//        Map<String, String> map = Map.of(
//                "key", "Value for key",
//                "key2", "Value for key2");
//
////        hashMap.put("key", "Value for key");
//        System.out.println(map.get("key"));

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(
                16,
                0.75f,
                true);

        linkedHashMap.put("Smith", 30);
        linkedHashMap.put("Anderson", 31);
        linkedHashMap.put("Lewis", 29);
        linkedHashMap.put("Cook", 29);
        System.out.println("\nThe age for Lewis is " + linkedHashMap.get("Lewis"));

        System.out.println(linkedHashMap);
    }
}
