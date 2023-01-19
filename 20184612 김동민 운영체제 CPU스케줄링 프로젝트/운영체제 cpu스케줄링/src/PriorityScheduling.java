import java.util.Arrays;
import java.util.Comparator;

public class PriorityScheduling extends SchedulingAlgorithm{

	PriorityScheduling(){
		frameNum = 3;
		ShowFrame();
		
		System.out.println("변동 우선순위 ");
		
		SetGantt("변동 우선순위 ");
		RunAlgorithm();

	}
	@Override
	public void RunAlgorithm() {
		// TODO Auto-generated method stub
		int smallest, end=0, min;
	    int time=0, Pcnt=0;
	    min=9999;
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
	    
		for(time = 0; Pcnt!=n;) {
		
			 smallest=n-1; min=9999;
	         for(int i=0;i<n;i++) {
	            //System.out.println("min "+min);
	            if( ReadyQ[i].getArrivalTime() <= time &&	ReadyQ[i].getLeaveOperation()>0&& ReadyQ[i].getPriority() < min ) {
	               smallest=i;
	            //   System.out.println(smallest);
	               min= (int)ReadyQ[smallest].getPriority();
	            }
	            
	         }
	         if( min==9999) {
	        	 System.out.println(1234);
	        	 list.add(new ProcessDispatch(new Process("none"),1));
	        	 time++;
	        	 continue;
	         }
	         else {
	    //     if(st==0) st=ReadyQ[smallest].getArrivalTime();
		         if(!ReadyQ[smallest].getDispatch()) {
		        	 ReadyQ[smallest].setResponseTime(time-ReadyQ[smallest].getArrivalTime());
		        //	 System.out.println(ReadyQ[smallest].ProcessName);
		        	 ReadyQ[smallest].setDispatch(true);
		         }
		         
		         ReadyQ[smallest].setLeaveOperation(ReadyQ[smallest].getLeaveOperation()-1);
		     //    list.add(new ProcessDispatch(1, ReadyQ[smallest]));
		     //    	System.out.println(ReadyQ[smallest].ProcessName);
		         if(ReadyQ[smallest].getLeaveOperation()<=0) {
		        	 Pcnt++;
		        	 end = time+1;
		        	
		        //	 list.add(new ProcessDispatch(1, ReadyQ[smallest]));
		        	 System.out.println(1+ReadyQ[smallest].ProcessName +","+   ReadyQ[smallest].getLeaveOperation());
		        	 ReadyQ[smallest].setWaitingTime(end-ReadyQ[smallest].getArrivalTime()-ReadyQ[smallest].getOperationTime());
		        	 ReadyQ[smallest].setTurnaroundTime(end - ReadyQ[smallest].getArrivalTime());
		        //	 ReadyQ[smallest].setLeaveOperation(ReadyQ[smallest].getLeaveOperation()-ReadyQ[smallest].getOperationTime());
		        	 list.add(new ProcessDispatch(ReadyQ[smallest],1));
		         }
		         else {
		        	 System.out.println(ReadyQ[smallest].ProcessName +","+  ReadyQ[smallest].getLeaveOperation());
		        	 list.add(new ProcessDispatch(ReadyQ[smallest],1 ));
		         }
		         time++;
	         }
		}
		for(int i=1; i<list.size();) {
			if(list.get(i).getProcessName().equals(list.get(i-1).getProcessName())) {
				list.get(i-1).setRuntime(list.get(i-1).getRuntime()+1);
				list.remove(i);
			}
			else i++;
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
