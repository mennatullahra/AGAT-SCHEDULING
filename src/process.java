
public class process {
	int Quantum;
	String processName;
	int brustTime;
	int ArrivalTime;

	process(int Quantum, String processName, int brustTime, int ArrivalTime){
		this.Quantum    =Quantum; 
		this.processName=processName; 
		this.brustTime  =brustTime;   
		this.ArrivalTime=ArrivalTime; 
		}
	process(){
		this.Quantum    =0; 
		this.processName=""; 
		this.brustTime  =0;   
		this.ArrivalTime=0; 
		}
	public process editBrustTime(int time) {
		brustTime = time;
		return this;
	}
	public String toString(){//overriding the toString() method  
		  return "\t"+processName+"\t"+Quantum+"\t   "+brustTime
				  +"\t\t"+ ArrivalTime+"\n";  
		 }  
}
