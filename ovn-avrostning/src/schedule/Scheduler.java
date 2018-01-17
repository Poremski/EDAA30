package schedule;

import java.util.ArrayList;

public class Scheduler {

    private Machine[] machines;

	/** Skapar ett schemaläggare för maskinerna
		i vektorn machines. */
	public Scheduler(Machine[] machines) {
	    this.machines = machines;
	}

	/** Fördelar jobben i listan jobs på maskinerna.
	 	Jobben är sortrade är sorterad efter avtagande tidsåtgång. */
	public void makeSchedule(ArrayList<Job> jobs) {
	    for (Job job : jobs) {
	        int t0 = machines[0].getTotalTime();
	        int t1 = machines[1].getTotalTime();
	        int t2 = machines[2].getTotalTime();

	        if (t0 <= t1 && t0 <= t2) machines[0].assignJob(job);
	        else if (t1 <= t0 && t1 <= t2) machines[1].assignJob(job);
	        else machines[2].assignJob(job);
        }
	}

	/** Skriver ut maskinernas scheman. */
	public void printSchedule() {
	    for (Machine machine : machines) {
            System.out.println(machine);
        }
	}
}
