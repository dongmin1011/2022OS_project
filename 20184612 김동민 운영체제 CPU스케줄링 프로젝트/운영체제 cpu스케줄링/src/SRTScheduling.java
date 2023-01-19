import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SRTScheduling extends SchedulingAlgorithm{
//	LinkedList<Process> queue = new LinkedList<>();
	PriorityQueue<Process> queue = new PriorityQueue<Process>(new Comparator<Process>() {

		@Override
		public int compare(Process o1, Process o2) {
			if(o1.getLeaveOperation()<o2.getLeaveOperation()) return -1;
			else if(o1.getLeaveOperation()==o2.getLeaveOperation()) return 0;
			else return 1;
		}
		
	});
	SRTScheduling(){
		frameNum = 5;
		ShowFrame();
		
		System.out.println("SRT ");
	
		SetGantt("SRT ");
		RunAlgorithm();

	}
	@Override
	public void RunAlgorithm() {
		// TODO Auto-generated method stub
		int s, end = 0, min;
		int time=0, Pcnt=0;
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
		
	for(time=0; Pcnt!=n;) {
	//	if(st==0)st=ReadyQ[0].getArrivalTime();
			min = 999;
			s = 0;
			for(int i=0; i<n; i++) {
				if(ReadyQ[i].getArrivalTime()<=time&&ReadyQ[i].getLeaveOperation()<=min&& ReadyQ[i].getLeaveOperation()>0) {
					s = i;
				//	System.out.println(ReadyQ[smallest].ProcessName+","+ReadyQ[smallest].getLeaveOperation()+"min: "+min);
					min = ReadyQ[s].getLeaveOperation();
					
				}
			}
		
			if(min==999) {
				 Process tmp = new Process("none");
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
				if(!ReadyQ[s].getDispatch()) {
					 ReadyQ[s].setResponseTime(time-ReadyQ[s].getArrivalTime());
				        //	 System.out.println(ReadyQ[smallest].ProcessName);
				     ReadyQ[s].setDispatch(true);
				}
				if(ReadyQ[s].getLeaveOperation()>TimeSlice) {
					ReadyQ[s].setLeaveOperation(ReadyQ[s].getLeaveOperation()-TimeSlice);
					list.add(new ProcessDispatch(ReadyQ[s],TimeSlice));
					time+=TimeSlice-1;
				}
				else {
					list.add(new ProcessDispatch(ReadyQ[s],ReadyQ[s].getLeaveOperation()));
					time+=ReadyQ[s].getLeaveOperation()-1;
					ReadyQ[s].setLeaveOperation(0);
					ReadyQ[s].setDispatch(true);
					
				}
				
				if(ReadyQ[s].getLeaveOperation()<=0) {
					Pcnt++;
					end = time+1;
					
					ReadyQ[s].setTurnaroundTime(end-ReadyQ[s].getArrivalTime());
					ReadyQ[s].setWaitingTime(ReadyQ[s].getTurnaroundTime()-ReadyQ[s].getOperationTime());
					
				}
				time++;
			}
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
	boolean isExist(String name) {
		int n = ReadyQ.length;
		int ary[] = new int [n];
		for(int i=0; i<n; i++) {
			ary [i] = 0;
		}
		for (Process value : queue) {
		//	System.out.println(value.ProcessName);
			
		    if(value.ProcessName.equals(name)) {
		    	int x = Integer.parseInt(value.ProcessName.substring(1, 2))-1;
		    	ary[x]++;
		    	for(int i=0; i<n; i++) {
					if(ary[i]== ReadyQ[i].getLeaveOperation()) return true;
					
				}
		    }
		}
		
		return false;
	}
}
