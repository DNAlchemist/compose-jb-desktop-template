package sample.entity.tools;

import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Set;

public class PrintMultiMap {
    public static <K, V> String multiMapToString(Multimap<K, V> map) {
        StringBuilder str = new StringBuilder();
        Set<K> keys = map.keySet();
        for (K key : keys) {
            Collection<V> values = map.get(key);
            for (V v : values) {
                str.append(v);
            }
        }
        return String.valueOf(str);
    }
}