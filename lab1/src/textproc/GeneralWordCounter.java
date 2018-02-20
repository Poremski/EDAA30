package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    private Set<String> exceptions = new HashSet<>();
    private Map<String, Integer> list = new HashMap<>();
    private int min_amount;

    public GeneralWordCounter(Scanner scanner) throws FileNotFoundException {
        this(scanner, 200);
    }

    public GeneralWordCounter(Scanner scanner, int min_amount) {
        while (scanner.hasNext()) {
            exceptions.add(scanner.next().toLowerCase());
        }
        scanner.close();

        this.min_amount = min_amount;
    }

    @Override
    public void process(String w) {
        if (!exceptions.contains(w)) {
            if (list.containsKey(w)) {
                list.computeIfPresent(w, (k, v) -> v + 1);
            } else {
                list.put(w, 1);
            }
        }
    }

    @Override
    public void report() {
        list.entrySet().stream().forEach(
            pair -> {
                if (pair.getValue() >= min_amount) {
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }
            }
        );
    }

}
