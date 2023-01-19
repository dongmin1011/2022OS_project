
public class NonPriorityScheduling extends SchedulingAlgorithm{
	
	NonPriorityScheduling(){
		frameNum = 2;
		ShowFrame();
		
		System.out.println("고정 우선순위 ");
		SetGantt("고정 우선순위 ");
		RunAlgorithm();

	}

	@Override
	public void RunAlgorithm() {
		// TODO Auto-generated method stub
		int n = ReadyQ.length;
		int Pcnt = 0, time = 0;
	//	ReadyQ = process;
		
		while(true) {
			int c = n, min = 999;
			if(Pcnt == ReadyQ.length)
				break;
			for(int i=0; i<ReadyQ.length; i++) {
				if((ReadyQ[i].getArrivalTime()<=time)&& !ReadyQ[i].getDispatch()&&ReadyQ[i].getPriority()<min	) {
					min = ReadyQ[i].getPriority();
					
					c = i;
				}
			}
		//	if(tot==0) st = ReadyQ[c].getArrivalTime();
			 if (c==n) {
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
	            else
	            {
	            ReadyQ[c].setResponseTime(time+ReadyQ[c].getOperationTime());
			//	ReadyQ[c].setWaitingTime(st+ReadyQ[c].getOperationTime());
	            time+=ReadyQ[c].getOperationTime();
			//	ReadyQ[tot].
				ReadyQ[c].setTurnaroundTime(ReadyQ[c].getResponseTime()-ReadyQ[c].getArrivalTime());
				ReadyQ[c].setResponseTime(ReadyQ[c].getTurnaroundTime()-ReadyQ[c].getOperationTime());
				ReadyQ[c].setWaitingTime(ReadyQ[c].getTurnaroundTime()-ReadyQ[c].getOperationTime());
				ReadyQ[c].setDispatch(true);
				System.out.println(ReadyQ[c].ProcessName);
				Pcnt++;
				list.add(new ProcessDispatch(ReadyQ[c], ReadyQ[c].getLeaveOperation()));
			
	           }			
		}
		GanttColor();
		for(int i = 0; i<n; i++) {
			AvgWaiting += ReadyQ[i].getWaitingTime();
			AvgResponse += ReadyQ[i].getResponseTime();
			AvgTurnAround+=ReadyQ[i].getTurnaroundTime();
		}
		PrintFrame();
	}

	

}
