package textproc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {

    private Map<String, Integer> list = new HashMap<>();

    public MultiWordCounter(String[] provinces) {
        for (String p : provinces) {
            list.put(p, 0);
        }
    }

    @Override
    public void process(String w) {
        list.computeIfPresent(w, (k, v) -> v + 1);
    }

    @Override
    public void report() {
        list.keySet().forEach(
            (String key) -> System.out.println(key + " : " + list.get(key))
        );
    }
}
