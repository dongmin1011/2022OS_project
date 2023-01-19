import java.util.Arrays;
import java.util.Comparator;

public class HRNScheduling extends SchedulingAlgorithm{
	
	HRNScheduling(){
		frameNum = 6;
		ShowFrame();
		
		System.out.println("HRN");

		SetGantt("HRN");
		RunAlgorithm();

	}
	public void RunAlgorithm() {
		int n = ReadyQ.length;
		
		Arrays.sort(ReadyQ, new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) {
				// TODO Auto-generated method stub
				if(o1.getArrivalTime()<o2.getArrivalTime()) return -1;
				else if (o1.getArrivalTime() == o2.getArrivalTime()) return 0;
				else return 1;
			}
			
		});
		float priority = 0;
		int c = 0;
		int Pcnt = 0;
		for(int t = 0; Pcnt<n;) {
			float hrr = -999;
			for(int i=0; i<n; i++) {
				if(ReadyQ[i].getArrivalTime()<=t && !ReadyQ[i].getDispatch()) {
					priority = ((float)ReadyQ[i].getOperationTime()+(float)(t-ReadyQ[i].getArrivalTime()))/(float)ReadyQ[i].getOperationTime();
					if(hrr<priority) {
						hrr = priority;
						c = i;
					}
				}
			}
			if (hrr==-999) {
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
               t++;
			 }
			else {
			t+=ReadyQ[c].getOperationTime();
			ReadyQ[c].setDispatch(true);
			list.add(new ProcessDispatch(ReadyQ[c]));
			ReadyQ[c].setTurnaroundTime(t-ReadyQ[c].getArrivalTime());
			ReadyQ[c].setResponseTime(t-ReadyQ[c].getArrivalTime()-ReadyQ[c].getOperationTime());
			ReadyQ[c].setWaitingTime(t-ReadyQ[c].getArrivalTime()-ReadyQ[c].getOperationTime());
			Pcnt++;
			}
		//	ReadyQ[loc]
		
		}
		Arrays.sort(ReadyQ, new Comparator<Process>() {
			@Override
			public int compare(Process o1, Process o2) {
				// TODO Auto-generated method stub
				return o1.ProcessName.compareTo(o2.ProcessName);
			}
			
		});
		
		
		GanttColor();
		
		for(int i = 0; i<n; i++) {
			AvgWaiting += ReadyQ[i].getWaitingTime();
			AvgResponse += ReadyQ[i].getResponseTime();
			AvgTurnAround+=ReadyQ[i].getTurnaroundTime();
		}
		PrintFrame();
	}
	
}
