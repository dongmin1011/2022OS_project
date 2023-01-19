import java.awt.Color;

public class Process implements Comparable<Process>{
	String ProcessName;
	int ArrivalTime;	//�����ð�
	int OperationTime;	//�۾��ð�
	int LeaveOperation;
	
	int Priority;		//�켱����
	int WaitingTime;	//���ð�
	int ResponseTime; 	//����ð�
	
	int TurnaroundTime; //��ȯ�ð�
	boolean Dispatch;
	
	Color color;
	
	Process(){
		ArrivalTime = OperationTime = Priority = WaitingTime = TurnaroundTime = 0;
	}
	
	Process(Object name, Object arrivaltime, Object operationtime, Object priority){
		ProcessName = (String)name;
		ArrivalTime = Integer.parseInt((String)arrivaltime);
		OperationTime = Integer.parseInt((String)operationtime);
		Priority = Integer.parseInt((String)priority);
		WaitingTime = TurnaroundTime = 0;
		Dispatch = false;
	}
	Process(String str){
		ProcessName = str;
	}
	public int getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public int getOperationTime() {
		return OperationTime;
	}
	public void setOperationTime(int operationTime) {
		OperationTime = operationTime;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
	}
	
	public int getWaitingTime() {
		return WaitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		WaitingTime = waitingTime;
	}
	public int getTurnaroundTime() {
		return TurnaroundTime;
	}
	public void setTurnaroundTime(int turnaroundTime) {
		TurnaroundTime = turnaroundTime;
	}
	public void setDispatch(boolean TF) {
		Dispatch = TF;
	}
	public boolean getDispatch() {
		return Dispatch;
	}
	public int getResponseTime() {
		return ResponseTime;
	}
	public int getLeaveOperation() {
		return LeaveOperation;
	}

	public void setLeaveOperation(int leaveOperation) {
		LeaveOperation = leaveOperation;
	}
	public void setResponseTime(int responseTime) {
		ResponseTime = responseTime;
	}
	public void setColor(Color c) {
		color = c;
	}
	public Color getColor() {
		return color;
	}

	@Override
	public int compareTo(Process o) {
		
		if (this.Priority > o.getPriority())
            return 1;
        else if (this.Priority < o.getPriority())
            return -1;
        return 0;
		
		// TODO Auto-generated method stub
	//	return 0;
	}
}
