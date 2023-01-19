import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FCFSScheduling extends SchedulingAlgorithm{

	FCFSScheduling(){
		frameNum = 0;
		ShowFrame();
		
		System.out.println("FCFS");
		
		
		
		SetGantt("FCFS");
		RunAlgorithm();
		
		
		
	}
	public void RunAlgorithm() {
		System.out.println("Running");
	//	ReadyQ = ReadyQSort();
	//	int sum = 0;
	//	ReadyQ = process;
		
		int n = ReadyQ.length;
		int Pcnt = 0, time = 0;		//Pcnt는 실행한 프로세스 개수 time는 c가 프로세스 개수가 되면 증가
		
		while(true) {
			int c = n, min = 999;
			if(Pcnt == n)
				break;
			for(int i=0; i<n; i++) {
				if((ReadyQ[i].getArrivalTime()<=time)&& (!ReadyQ[i].getDispatch())&&(ReadyQ[i].getArrivalTime()<min) ) {
					min = ReadyQ[i].getArrivalTime();
					
					c = i;
				}
			}
			
		//	if(tot==0) st = ReadyQ[c].getArrivalTime();
			if(c==n) {
			//	System.out.println(c+", "+ min);
				System.out.println("none");
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
				
				ReadyQ[c].setTurnaroundTime(time+ReadyQ[c].getOperationTime() - ReadyQ[c].getArrivalTime());
				ReadyQ[c].setResponseTime(ReadyQ[c].getTurnaroundTime()-ReadyQ[c].OperationTime);
				ReadyQ[c].setWaitingTime(ReadyQ[c].getTurnaroundTime()-ReadyQ[c].OperationTime);
				ReadyQ[c].setDispatch(true);
			//	ReadyQ[tot[]
				System.out.println(ReadyQ[c].ProcessName);
			//	System.out.println(ReadyQ[c].getTurnaroundTime());
				time+=ReadyQ[c].getOperationTime();
				list.add(new ProcessDispatch(ReadyQ[c], ReadyQ[c].getLeaveOperation()));
				
			//	System.out.println(tot);
				
				Pcnt++;
				
			}
			
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
