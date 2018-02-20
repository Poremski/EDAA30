package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {

        long t0 = System.nanoTime();

        List<TextProcessor> pList = new ArrayList<>();
        pList.add(new SingleWordCounter("nils"));
        pList.add(new SingleWordCounter("norge"));
        pList.add(new MultiWordCounter(REGIONS));
        pList.add(new GeneralWordCounter(new Scanner(new File("./lab1/undantagsord.txt")),210));

		Scanner s = new Scanner(new File("./lab1/nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			pList.forEach((TextProcessor p) -> p.process(word));
		}

		s.close();
		pList.forEach(TextProcessor::report);

        long t1 = System.nanoTime();
        System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms"); // HashMap: 996 ms TreeMap: 1047 ms

	}
}
