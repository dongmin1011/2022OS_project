import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RoundRobinScheduling extends SchedulingAlgorithm{
	LinkedList<Process> queue = new LinkedList<>();
	RoundRobinScheduling(){
		frameNum = 4;
		ShowFrame();
		
		System.out.println("Round Robin ");
		
		SetGantt("Round Robin ");
		RunAlgorithm();

	}

	@Override
	public void RunAlgorithm() {
		// TODO Auto-generated method stub
		int n = ReadyQ.length;
	//	ReadyQ = ReadyQSort();
	//	LinkedList<ProcessDispatch> pid = new LinkedList<>();
		LinkedList<ProcessDispatch> ReadyQueue = new LinkedList<>();
		int s = 0;
		
		
	//	int tot = 0;
	//	int time = 0;
	//	queue.add(ReadyQ[0]);
	//	System.out.println(queue.poll().getOperationTime());
	/*	while(true) {
		//	queue.offer(ReadyQ[s]);
			
		//	System.out.println(pop);
			if(tot == n) break;
			
			
			
			Process pop = queue.removeFirst();
			
				if(pop.getLeaveOperation()>TimeSlice &&!pop.getDispatch()) {
					ReadyQ[s].setLeaveOperation(ReadyQ[s].getLeaveOperation()- TimeSlice);
				//	System.out.println(ReadyQ[s].ProcessName);
					time+=TimeSlice;
				//	queue.add(ReadyQ[s]);
					
					for(int i=0; i<n; i++) {
						if(time>=ReadyQ[i].getArrivalTime()&& ReadyQ[i].getLeaveOperation()>0 && !isExist(ReadyQ[i].ProcessName)) {
						//	System.out.println(ReadyQ[i].ProcessName+", "+ReadyQ[i].getLeaveOperation());
							queue.add(ReadyQ[i]);
						}
					}
					System.out.println("--");
					
				}
				else {
					time+=ReadyQ[s].getLeaveOperation();
					ReadyQ[s].setLeaveOperation(0);
					ReadyQ[s].setDispatch(true);
					
				//	System.out.println(ReadyQ[s].ProcessName);
					tot++;
				}
			
			s++;
			if(s==n)s=0;
		}*/
	//	Process temp1 = null;
	/*	for(int i=0; i<n; i++) {
			queue.add(ReadyQ[i]);
		}
		int time = queue.get(0).getArrivalTime();
		Process temp1 = null;
		for(int i=0; i<n-1; i++) {
			for(int j = i+1; j<n; j++) {
				if(ReadyQ[i].getArrivalTime()>ReadyQ[j].getArrivalTime()) {
					temp1 = ReadyQ[i];
					ReadyQ[i] = ReadyQ[j];
					ReadyQ[j] = temp1;
				}
			}
		}
		while(!queue.isEmpty()) {
			Process process = queue.getFirst();
			int operationTime = (process.getOperationTime() < TimeSlice ? process.getOperationTime() : TimeSlice);
			
			ProcessDispatch temp = new ProcessDispatch();
			temp.process = process;
			temp.startTime = time;
			temp.totalTime = time + operationTime;
			temp.runtime = operationTime;
			
			if(!queue.get(0).getDispatch()) temp.pid_run = time;
			ReadyQueue.add(temp);
			time+=operationTime;
			queue.remove(0);
			
			if(process.getOperationTime()>TimeSlice) {
				process.setOperationTime(process.getOperationTime()-TimeSlice);
				for(int i=0; i<queue.size(); i++) {
					if(queue.get(i).getArrivalTime()>time) {
						process.setDispatch(true);
						queue.add(i, process);
						break;
					}
					else if(i==queue.size()-1) {
						process.setDispatch(true);
						queue.add(process);
						break;
					}
				}
			}
			
		}
		Map map = new HashMap();
	      for(Process pro : ReadyQ) {
	    	  map.clear();
	    	  
	    	  for(ProcessDispatch ppid : ReadyQueue) {
	    		  if(ppid.getProcessName().equals(pro.ProcessName)) {
	    			  int a = ppid.pid_run;
	    			  if(map.containsKey(ppid.getProcessName())) {
	    				  int w = ppid.startTime-(int)map.get(ppid.getProcessName());
	    				  pro.WaitingTime=pro.WaitingTime+w;
	    			  }
	    			  else {
	    				  pro.ResponseTime=(a+pro.ResponseTime)-pro.ArrivalTime;
	    				  System.out.println("map 1Â÷ "+ppid.getProcessName()+" : "+pro.ResponseTime);
	    				  pro.WaitingTime=ppid.startTime-pro.ArrivalTime;
	    			  }
	    			  map.put(ppid.getProcessName(), ppid.totalTime);
	    		  }
	    	  }
	    	  pro.TurnaroundTime=pro.WaitingTime+pro.OperationTime;
	      }*/
	/*	
		Process temp;
		for(int i=0; i<operationSum; i++) {
			
			if(tot == n)break;
			for(int j=0; j<n; j++) {
				if(ReadyQ[j].getArrivalTime() == i) {
					if(!isExist(ReadyQ[j].ProcessName)) {
						queue.add(ReadyQ[j]);
				//		System.out.println(ReadyQ[j].ProcessName+"add");
					}
						
				}	
			}
			
			if(queue.size()>0) {
				temp = queue.removeFirst();
			//	System.out.println(temp.ProcessName+"out");
				
				if(temp.getLeaveOperation()>TimeSlice && !temp.getDispatch()) {
					time += TimeSlice;
					temp.setLeaveOperation(temp.getLeaveOperation()- TimeSlice);
				//	System.out.println(i+","+temp.ProcessName);
				//	System.out.println(t.ProcessName+"add");
					queue.add(temp);
				}
				else {
					time += temp.getLeaveOperation();
					System.out.println(i+","+temp.ProcessName);
					temp.setLeaveOperation(0);
					temp.setDispatch(true);
					tot++;
					
				}
			}
			
	
			
		}
			*/
	//	System.out.println(time);
		
		int cur_time = 0;
		
		int []remain_burst = new int[n];
		boolean [] calc_response = new boolean[n];
		for(int i=0; i<n; i++) {
			remain_burst[i] = ReadyQ[i].getOperationTime();
			calc_response[i] = false;
		}
		Process temp = null;
		Arrays.sort(ReadyQ, new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) {
				// TODO Auto-generated method stub
				if(o1.getArrivalTime()<o2.getArrivalTime()) return -1;
				else if (o1.getArrivalTime() == o2.getArrivalTime()) return 0;
				else return 1;
			}
			
		});
		int Pcnt = 0;
	//	queue.add(ReadyQ[0]);
		while(true) {
			int min = 999;
			int c = -1;
			if(Pcnt==n)break;
			
		//	if(cur_time==0) cur_time=ReadyQ[0].getArrivalTime();
			for(int i=0; i<n; i++) {
				if((ReadyQ[i].getArrivalTime()<=cur_time)&& (!ReadyQ[i].getDispatch())&&(ReadyQ[i].getArrivalTime()<min)  ) {
					min = ReadyQ[i].getArrivalTime();
					
					c = i;
				}
			}
			System.out.println(min);
		//	if(tot==0) st = ReadyQ[c].getArrivalTime();
			
			if(min==999) {
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
				cur_time++;
			//	st++;
			}
			else {
				if(!isExist(ReadyQ[c].ProcessName)) queue.add(ReadyQ[c]);
				if(queue.size()>0) temp = queue.get(0);
				for(int i=0; i<n; i++) {
					if(temp.ProcessName.equals(ReadyQ[i].ProcessName) && !calc_response[i]) {
						if(cur_time>ReadyQ[i].getArrivalTime()) {
							ReadyQ[i].setResponseTime(cur_time - ReadyQ[i].getArrivalTime());
						}
						   calc_response[i] = true;
					}
				}
	
				if(temp.getLeaveOperation()>TimeSlice) {
					cur_time+=TimeSlice;
					temp.setLeaveOperation(temp.getLeaveOperation()-TimeSlice);
					for(int i=0; i<=cur_time; i++) {
						for(int j=0; j<n; j++) {
							if(ReadyQ[j].getArrivalTime()==i  && !isExist(ReadyQ[j].ProcessName) && ReadyQ[j].getLeaveOperation()>0 ) {
								queue.add(ReadyQ[j]);	
							}
						}
					}
					queue.removeFirst();
					System.out.println(temp.ProcessName);
					list.add(new ProcessDispatch(temp,TimeSlice));
				}
				else {
					System.out.println(temp.ProcessName);
					cur_time += temp.getLeaveOperation();
					temp.setWaitingTime(cur_time-temp.getOperationTime());
					for(int i=0; i<n; i++) {
						if(temp.ProcessName.equals(ReadyQ[i].ProcessName)) {
							ReadyQ[i] = temp;
						}
						for(int j=0; j<=cur_time; j++) {
						//	for(int j=0; j<n; j++) {
							if(ReadyQ[i].getArrivalTime()==j  && !isExist(ReadyQ[i].ProcessName) && ReadyQ[i].getLeaveOperation()>0) {
								queue.add(ReadyQ[i]);
							}
							
						}
					}
					
					
					queue.removeFirst();
				//	System.out.println(temp.ProcessName + ", "+ temp.getLeaveOperation());
					list.add(new ProcessDispatch(temp,temp.getLeaveOperation()));
					temp.setLeaveOperation(0);
					temp.setDispatch(true);
					Pcnt++;
					System.out.println(Pcnt);
				}
			}
			System.out.println("--");
		}
	//	System.out.println(queue.removeFirst().ProcessName);	
		for(int i=0; i<n; i++) {
			ReadyQ[i].setTurnaroundTime(ReadyQ[i].getOperationTime() + ReadyQ[i].getWaitingTime()-ReadyQ[i].getArrivalTime());
			ReadyQ[i].setWaitingTime(ReadyQ[i].getTurnaroundTime() - ReadyQ[i].getOperationTime());
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
		for(int i=0; i<queue.size(); i++) {
		//	System.out.println(name);
			if(queue.get(i).ProcessName.equals(name)) return true;
			
		}
		return false;
	}
	
}
