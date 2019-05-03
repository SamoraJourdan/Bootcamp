/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerleague;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author pc
 */
public class SoccerLeague {

    public static void main(String[] args) {
        String file = args[0];
        TreeMap<String, Integer> map = new TreeMap<>();
        SoccerLeague league = new SoccerLeague();
        league.loadFile(file, map);
        Map sortedMap = sortByValues(map);
        Set set = sortedMap.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
    }

    public void loadFile(String file, TreeMap map) {

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                split(line, map);
            }
            reader.close();

        } catch (IOException ex) {
            System.out.println("Error 404, file not found. Please check if the first command line argument is the correct filepath of the games' scores.");
            ex.printStackTrace();
        }
    }

    public void split(String lineSplit, TreeMap map) {
        String[] result3;
        String[] result2;
        String[] result = lineSplit.split(", ");
        result2 = result[0].split(" ");
        result3 = result[1].split(" ");
        int scoreA = Integer.parseInt(result2[1]);
        int scoreB = Integer.parseInt(result3[1]);
        if (scoreA > scoreB) {
            if (map.containsKey(result2[0])) {
                map.replace(result2[0], ((int) (map.get(result2[0])) + 3));
            } else {
                map.put(result2[0], 3);
            }
            if (!(map.containsKey(result3[0]))) {
                map.put(result3[0], 0);
            }

        } else if (scoreA == scoreB) {
            if (map.containsKey(result2[0])) {
                map.replace(result2[0], ((int) (map.get(result2[0])) + 1));
            } else {
                map.put(result2[0], 1);
            }
            if (map.containsKey(result3[0])) {
                map.replace(result3[0], ((int) (map.get(result3[0])) + 1));
            } else {
                map.put(result3[0], 1);
            }
        } else {
            if (map.containsKey(result3[0])) {
                map.replace(result3[0], ((int) (map.get(result3[0])) + 3));
            } else {
                map.put(result3[0], 3);
            }
            if (!(map.containsKey(result2[0]))) {
                map.put(result2[0], 0);
            }
        }

    }

    public static <K, V extends Comparable<V>> Map<K, V>
            sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator
                = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare
                        = map.get(k2).compareTo(map.get(k1));
                if (compare == 0) {
                    return 1;
                } else {
                    return compare;
                }
            }
        };

        Map<K, V> sortedByValues
                = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

}
