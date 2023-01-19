import java.awt.Color;

import javax.swing.JPanel;

public class SJFScheduling extends SchedulingAlgorithm{
	
	
	SJFScheduling(){
		frameNum = 1;
		ShowFrame();
		
		System.out.println("SJF");
	
		SetGantt("SJF");
		RunAlgorithm();
	}
	public void RunAlgorithm() {
	//	ReadyQ = ReadyQSort();
		int n = ReadyQ.length;
		int Pcnt = 0, time = 0;
	//	ReadyQ = process;
		
		while(true) {
			int c = n, min = 999;
			if(Pcnt == ReadyQ.length)
				break;
			for(int i=0; i<ReadyQ.length; i++) {
				if((ReadyQ[i].getArrivalTime()<=time)&& !ReadyQ[i].getDispatch()&&ReadyQ[i].getOperationTime()<min ) {
					min = ReadyQ[i].getOperationTime();
					System.out.println(ReadyQ[i].ProcessName);
					c = i;
				}
			}
			//if(tot==0) st = ReadyQ[c].getArrivalTime();
			if(c==n) {
				System.out.println(c+", "+ min);
				Process tmp = new Process("none");
				//	tmp.setColor(Color.gray);
				//	System.out.println(tmp.getColor());
					if(list.size()>0) {
						if(list.get(list.size()-1).getProcessName().equals("none")) {
							list.get(list.size()-1).runtime+=1;
						}
						else list.add(new ProcessDispatch(tmp, 1));
					}
				else list.add(new ProcessDispatch(tmp, 1));
					time++;
			}
			else {
				ReadyQ[c].setWaitingTime(time+ReadyQ[c].getOperationTime());
				time+=ReadyQ[c].getOperationTime();
			//	ReadyQ[tot].
				ReadyQ[c].setTurnaroundTime(ReadyQ[c].getWaitingTime()-ReadyQ[c].getArrivalTime());
				ReadyQ[c].setResponseTime(ReadyQ[c].getTurnaroundTime()-ReadyQ[c].getOperationTime());
				ReadyQ[c].setWaitingTime(ReadyQ[c].getTurnaroundTime()-ReadyQ[c].getOperationTime());
				ReadyQ[c].setDispatch(true);
				Pcnt++;
				
				list.add(new ProcessDispatch(ReadyQ[c], ReadyQ[c].getLeaveOperation()));
			}
			
			
		//	=process[i]
				
		}
		
		GanttColor();
		
		for(int i = 0; i<n; i++) {
			AvgWaiting += ReadyQ[i].getWaitingTime();
			AvgResponse +=ReadyQ[i].getResponseTime();
			AvgTurnAround+=ReadyQ[i].getTurnaroundTime();
		}
		
		
		PrintFrame();
	}
}
