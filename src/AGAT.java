import java.util.*;

public class AGAT {
	int numOfProcesses;
	public Vector<process> Processes = new Vector<process>();
	public Vector<process> myQueue = new Vector<process>();
	
	AGAT(int numOfProcesses){
		this.numOfProcesses = numOfProcesses;
		process intialProcess= new process(0, "P0", 0, 0);
		for (int i = 0; i < numOfProcesses; i++) {
			Processes.add(intialProcess);
		}
	}
	
	AGAT(Vector<process> processData) {
		this.numOfProcesses = processData.size();
		for (int i = 0; i < processData.size(); i++) {
			Processes.add(processData.get(i));
		}
		sortProcesses();
		for (int i = 0; i < Processes.size(); i++) {
			myQueue.add(Processes.get(i));
		}
	}
	
	public Vector<process> sortProcesses() {
		int n = Processes.size();
        for (int i = 1; i < n; ++i) {
            int keyArrivalTime = Processes.get(i).ArrivalTime;
            String tempName = Processes.get(i).processName;
            int tempQ = Processes.get(i).Quantum;
            int tempB = Processes.get(i).brustTime;
            int j = i - 1;
 
            while (j >= 0 && (Processes.get(j).ArrivalTime) > keyArrivalTime) {
            	Processes.get(j+1).ArrivalTime = Processes.get(j).ArrivalTime;
            	Processes.get(j+1).brustTime = Processes.get(j).brustTime;
            	Processes.get(j+1).processName = Processes.get(j).processName;
            	Processes.get(j+1).Quantum = Processes.get(j).Quantum;
                j = j - 1;
            }
            Processes.get(j + 1).ArrivalTime = keyArrivalTime;
            Processes.get(j + 1).processName = tempName;
            Processes.get(j + 1).brustTime = tempB;
            Processes.get(j + 1).Quantum = tempQ;
        }
		return Processes;
		
	}
	
	public void run() {
		int reminder = 0 ;
		while(myQueue.size() != 0) {
			if (myQueue.get(0).brustTime == (myQueue.get(0).Quantum + reminder)) {
				
				System.out.println("Process : "+ myQueue.get(0).processName + " is finished.\n");
				
				myQueue.remove(0);
				printSchedule();
			}
			else if (myQueue.get(0).brustTime <= (myQueue.get(0).Quantum+ reminder)) {
				
				System.out.println("Process : "+ myQueue.get(0).processName + " is finished.\n");
				
				//EDIT REMINDER TIME 
				reminder = myQueue.get(0).Quantum - myQueue.get(0).brustTime;
				
				myQueue.remove(0);
				printSchedule();
			}
			else if (myQueue.get(0).brustTime >= (myQueue.get(0).Quantum+ reminder)){
				
				System.out.println("Process : "+ myQueue.get(0).processName + " is working...\n");
				
				//EDIT BRUST TIME FORM THIS PROCESS
				myQueue.add(myQueue.get(0).editBrustTime(myQueue.get(0).brustTime - (myQueue.get(0).Quantum+ reminder)));
				
				//BECAUSE BRUST TIME NOT SMALLER THAN QUANTUM, SO THERE'S NO REMINDER IN THE QUANTUM
				reminder = 0;
				
				myQueue.remove(0);
				printSchedule();
			}
		}
		
	}
	
	public void printSchedule() {
		if (myQueue.size() == 0 ) {
			System.out.println("ALL PROCESSES FINISHED.");
			return;
		}
		System.out.println(" __________________NEW Schedule___________________");
		System.out.println("Process name | Quantum | BrustTime | Arrival Time\n------------------"
				+ "-------------------------------");
		for (int i = 0; i < myQueue.size(); i++) {
			System.out.println(myQueue.get(i));
		}
		System.out.println(" _________________________________________________");
	}
	public static void main(String args[]) {
		int num;
		String data;
		Vector<process> myProcesses = new Vector<process>();
		System.out.println("Enter number of processes : ");
		Scanner input = new Scanner(System.in);
		num = input.nextInt();
		input.nextLine();
		System.out.println("Enter processes' data (Quantum, ProcessName, BrustTime, ArrivalTime"
				+ ".) Respectivly, separated with spaces : ");
		for (int i = 0; i < num; i++) {
			data = input.nextLine();

			String[] splitStr = data.split("\\s+");
			int Q = Integer.parseInt(splitStr[0]);
			String PN = splitStr[1];
			int BT =  Integer.parseInt(splitStr[2]);
			int AT =  Integer.parseInt(splitStr[3]);
			process newProcess = new process(Q, PN, BT, AT);
			myProcesses.add(newProcess);
		}
		
		AGAT NEW = new AGAT(myProcesses);
		NEW.run();
		
		
	}
}
