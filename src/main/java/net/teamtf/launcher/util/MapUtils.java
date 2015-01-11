package net.teamtf.launcher.util;

import java.util.Iterator;
import java.util.Map;

public class MapUtils {
    public static <K, V> void addMapToMap(Map<K, V> source, Map<K, V> target) {
        Iterator<K> iteratorK = source.keySet().iterator();
        Iterator<V> iteratorV = source.values().iterator();
        while (iteratorK.hasNext()) {
            target.put(iteratorK.next(), iteratorV.next());
        }
    }
}
