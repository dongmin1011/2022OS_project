import java.awt.Color;

public class ProcessDispatch {
//	String processName;
	Process process;
//	int inout;
	int runtime;
	int endtime;
	
	
	
	   
	int responseTime; 
	Color color;
	ProcessDispatch(){}
	ProcessDispatch(Process p){
		process = p;
		this.runtime =p.getOperationTime();
	}
	ProcessDispatch(Process p, int runtime){
	//	inout = n;
		process = p;
		this.runtime =runtime;
	}
	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}

	
	public int getEndtime() {
		return endtime;
	}


	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}


	public String getProcessName() {
		return process.ProcessName;
	}
	
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public int getArrivalTime() {
		return process.getArrivalTime();
	}
}
