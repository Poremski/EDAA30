package schedule;

import java.util.ArrayList;

public class Machine {
    private int nbr;
    private ArrayList<Job> jobs;

	/** Skapar maskin nr nbr. */
	public Machine(int nbr) {
	    this.nbr = nbr;
        jobs = new ArrayList<>();
	}

	/** Tar reda på maskinens nr. */
	public int getNbr() {
		return nbr;
	}

	/** Tilldelar maskinen jobbet j. */
	public void assignJob(Job j) {
	    jobs.add(j);
	}

	/** Tar bort alla jobb från maskinen. */
	public void clearJobs() {
        jobs.clear();
	}

	/** Tar bort och returnerar nästa jobb som maskinen ska utföra.
	 	Returnerar null om maskinen inte har några jobb. */
	public Job getNextJob() {
	    if (jobs.isEmpty()) return null;
	    return jobs.remove(0);
	}

	/** Tar reda på den totala tiden för maskinens jobb. */
	public int getTotalTime() {
	    int time = 0;
	    for (Job job : jobs) {
	        time += job.getTime();
        }
		return time;
	}

	/** Returnerar en sträng som innehåller maskinens nr samt maskinens
    schemalagda jobb inom [] med kommatecken mellan. */
	public String toString() {
	    StringBuilder j = new StringBuilder();
	    for (Job job : jobs) {
	        j.append(job.toString());
	        if (jobs.indexOf(job) < jobs.size()-1) {
	            j.append(", ");
            }
        }
		return "M" + nbr + " [" + j + "]";
	}

}
