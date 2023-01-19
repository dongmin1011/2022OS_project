import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public abstract class SchedulingAlgorithm {
//	protected Process [] process;
	protected FileIO file = new FileIO();
	
	static SchedulingFrame []frame= new SchedulingFrame[7];
	
	protected Process []ReadyQ;
//	protected ArrayList<Process>ProcessPID = new ArrayList<>();
	int frameNum = -1;
	
	int TimeSlice;
	
	int operationSum = 0;
	int AvgWaiting = 0;
	int AvgResponse = 0;
	int AvgTurnAround = 0;
	
	ArrayList<ProcessDispatch> list = new ArrayList<>();
	SchedulingAlgorithm(){
		SetProcess();
		
		
	}
	
	public void SetProcess() {
		Object [][] temp;
		
		
		temp = file.TableFileRead();
		TimeSlice = Integer.parseInt(file.TimesliceFileRead());
	//	process = new Process[temp.length];
		ReadyQ = new Process[temp.length];
		
		for(int i=0; i<temp.length; i++) {
			ReadyQ[i] = new Process(temp[i][0], (temp[i][1]),  (temp[i][2]), (temp[i][3]));
			operationSum+=Integer.parseInt((String)temp[i][2]);
			ReadyQ[i].setLeaveOperation(ReadyQ[i].getOperationTime());
		}
		RandomColor();
		System.out.println(operationSum);
		
		
	}	
	public void ShowFrame() {
		for(int i=0; i<7; i++) {
			if(frame[i]!=null && frame[i].isVisible()) {
				 frame[i].setVisible(false);
			}
		}
	}
	public void SetGantt(String title) {
	//	System.out.println(operationSum);
		frame[frameNum] = new SchedulingFrame();

		frame[frameNum].setTitle(title+"스케줄링");
		frame[frameNum].setSubject(title+"스케줄링 알고리즘");
		frame[frameNum].DrawGantt(list);
		frame[frameNum].setResult();
		
	//	frame[frameNum].setRunning();
		frame[frameNum].setAverage();
		
		
	}
	public void PrintFrame() {
	//	isNoGantt();
		int n = ReadyQ.length;
	//	frame[frameNum].PrintRunning(list);
		frame[frameNum].PrintResult(ReadyQ.length, ReadyQ);
		frame[frameNum].SetAverage((double)AvgWaiting/n,(double)AvgResponse/n, (double)AvgTurnAround/n);
	}
	
	abstract public void RunAlgorithm();
	
	public void RandomColor() {
		Random rand = new Random();
		
		for(int i=0; i<ReadyQ.length; i++) {
			float r = (float) (rand.nextFloat() / 2f + 0.5);
		
			float g = (float) (rand.nextFloat() / 2f + 0.5);
			float b = (float) (rand.nextFloat() / 2f + 0.5);
			
			Color randomColor = new Color(r, g, b);
			
			ReadyQ[i].setColor(randomColor);
			for(int j=0; j<i; j++) {
				if(ReadyQ[j].getColor().getRGB()== randomColor.getRGB()) {
					i--;
				}
			}
		//	System.out.println(ReadyQ[i].getColor());
		}
	}
	public void GanttColor() {
		for(int i=0; i<list.size(); i++) {
			if(i==0)list.get(i).setEndtime(list.get(i).getRuntime());
			if(i!=0)list.get(i).setEndtime(list.get(i-1).getEndtime()+list.get(i).getRuntime());
			for(int j=0; j<ReadyQ.length; j++) {
				if(list.get(i).getProcessName().equals(ReadyQ[j].ProcessName)) {
					list.get(i).setColor(ReadyQ[j].getColor());
					break;
				}
				
			}
		//	System.out.println(list.get(i).getProcessName() + ","+list.get(i).getRuntime());
		}
	}
	public void isNoGantt() {
		if(list.get(0).getArrivalTime()!=0) {
			System.out.println(list.get(0).getArrivalTime());
			Process temp = new Process();
			temp.ProcessName = "None";
			temp.setColor(Color.white);
			list.add(0, new ProcessDispatch(temp,3));
		//	System.out.println(temp.getColor());
			for(int i=0; i<list.size(); i++) {
				list.get(i).setEndtime(list.get(i).endtime+list.get(1).getArrivalTime());
			}
		}
	}
	
	
}
