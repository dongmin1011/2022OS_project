import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SchedulingFrame extends JFrame{
	
	JPanel mainPanel = new JPanel();
	
	JLabel Subject = new JLabel();
	
	JScrollPane Ganttscroll;
	JPanel GanttPanel;// = new JPanel();
//	JPanel DrawGantt = new JPanel();
//	JPanel DrawNum = new JPanel();
//	JLabel [] GanttLabel;
//	JLabel [] GanttNum;
	
	JPanel ResultPanel = new JPanel();
	JPanel RunningPanel = new JPanel();
	JPanel AveragePanel = new JPanel();
	JLabel [] AverageResult = new JLabel[3];
	SchedulingFrame(){
		
		InitMainPanel();
		
		
		
		setLocation(500, 460);
		setSize(720, 520);
	
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void InitMainPanel() {
	
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(720, 520));
	//	mainPanel.setBackground(Color.white);
		add(mainPanel);
	//	System.out.println("1");
		
		Subject.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		mainPanel.add(Subject,"North");
		
		
	//	setGanttChart();	
	//	setResult();
	//	setRunning();
	//	setAverage();
	//	setGanttChart();	
	}
	public void setSubject(String str) {
		
		Subject.setText(str);
	}
	public void setGanttChart() {
		GanttPanel.setBackground(Color.white);
		Ganttscroll = new JScrollPane(GanttPanel);
		Ganttscroll.setPreferredSize(new Dimension(700, 80));
		
		
	//	GanttPanel.setLayout(new BoxLayout(GanttPanel, BoxLayout.Y_AXIS));
	//	mainPanel.add(GanttPanel);
	//	Ganttscroll.add(GanttPanel);
		
		mainPanel.add(Ganttscroll);
	}
	public void DrawGantt(ArrayList<ProcessDispatch> list) {
	/*	GanttLabel = new JLabel[n];
		GanttNum = new JLabel[n+1];
		
		EtchedBorder eborder=new EtchedBorder(EtchedBorder.RAISED);//평면에 끌로 판듯이 외곽선 효과를 내는 것이고 양각의 효과를 준다.
		 
		DrawGantt.setLayout(new BoxLayout(DrawGantt, BoxLayout.X_AXIS));
		DrawNum.setLayout(new BoxLayout(DrawNum, BoxLayout.X_AXIS));
		
		for(int i=0; i<n+1; i++) {
			if(i<n) {
				GanttLabel[i] = new JLabel("1");
			//	System.out.println("setlabel = "+i);
			//	GanttLabel[i].setPreferredSize(new);
			//	GanttLabel[i].setPreferredSize(new Dimension(30,30));
			//	GanttLabel[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				
			//	GanttLabel[i].setBounds(50, 50, 50, 50);
			//	GanttLabel[i].setBorder(eborder);
				DrawGantt.add(GanttLabel[i]);
				
				
			}
			GanttNum[i] = new JLabel();
			GanttNum[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		//	GanttNum[i].setPreferredSize(new Dimension(650/(n+2), 50));
			GanttNum[i].setHorizontalAlignment(JLabel.CENTER);
			GanttNum[i].setText(Integer.toString(i));
			DrawNum.add(GanttNum[i]);		
		}
	//	GanttPanel.setBackground(Color.black);
	//	DrawNum.setBackground(Color.black);
	//	DrawGantt.setBackground(Color.red);
	//	DrawGantt.setPreferredSize(new Dimension(650,30));
	//	DrawNum.setPreferredSize(new Dimension(700,30));
		GanttPanel.add(DrawGantt);
		GanttPanel.add(DrawNum);
	//	Ganttscroll.add(GanttPanel);
	//	Ganttscroll.setBackground(Color.black);
		*/
		
	//	GanttPanel  = new  DrawGantt(a);
	
		
		
	//	GanttPanel.setLayout(new BoxLayout(GanttPanel, BoxLayout.Y_AXIS));
	//	mainPanel.add(GanttPanel);
	//	Ganttscroll.add(GanttPanel);
		
	//	mainPanel.add(Ganttscroll);
		GanttPanel = new DrawGantt(list);
		GanttPanel.setBackground(Color.white);
		GanttPanel.setPreferredSize(new Dimension(3000,50));
		Ganttscroll = new JScrollPane(GanttPanel);
		Ganttscroll.setPreferredSize(new Dimension(700, 100));
		
		mainPanel.add(Ganttscroll);
	//	GanttPanel.add(GanttPanel);
	}
	public void setResult() {
		EtchedBorder eborder=new EtchedBorder(EtchedBorder.RAISED);
		ResultPanel.setBorder(eborder);
	//	ResultPanel.setPreferredSize(new Dimension(650, 250));
		ResultPanel.setBackground(Color.white);
		
		JScrollPane scroll = new JScrollPane(ResultPanel);
		scroll.setPreferredSize(new Dimension(450, 250));
		 
		mainPanel.add(scroll);
	}
	public void setRunning() {
		JPanel DisPatch = new JPanel();
	//	JTextField DispatchField = new JTextField();
		JScrollPane scroll = new JScrollPane(RunningPanel);
		scroll.setPreferredSize(new Dimension(200, 250));
		RunningPanel.setBackground(Color.white);
		RunningPanel.setLayout(new BoxLayout(RunningPanel, BoxLayout.Y_AXIS));
	//	DisPatch.setLayout(new BoxLayout(DisPatch, BoxLayout.Y_AXIS));
	//	DisPatch.setBackground(Color.black);
	//	RunningPanel.add(DispatchField);
	//	DispatchField.setPreferredSize(new Dimension(150, 250));
	//	JPanel SubjectPanel = new JPanel();
	//	JLabel Subject = new JLabel("알고리즘 진행상황");
	//	Subject.setHorizontalAlignment(JLabel.CENTER);
		
		
		TitledBorder tborder = new TitledBorder("알고리즘 진행상황");
		tborder.setTitleFont(new Font("맑은 고딕", Font.BOLD, 15));
		  tborder.setTitlePosition(TitledBorder.ABOVE_TOP);//지정한 위치에 타이틀을 나타내주는 보더...
		  tborder.setTitleJustification(TitledBorder.CENTER);//자리맞춤을 가운데로 지정...
		  RunningPanel.setBorder(tborder);
		
		
	//	Subject.setBackground(Color.white);
	//	Subject.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
	//	SubjectPanel.add(Subject);
	//	DisPatch.add(SubjectPanel);
		DisPatch.add(scroll);
		
		mainPanel.add(DisPatch);
	}
	public void PrintRunning(ArrayList<ProcessDispatch>list) {
	/*	ArrayList<JLabel> run = new ArrayList<>();
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++) {
			run.add(new JLabel());
			run.get(i).setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			run.get(i).setText(Integer.toString(i+1)+": ");
			if(list.get(i).getEndtime()<list.get(i).getArrivalTime())  
				run.get(i).setText(run.get(i).getText()+ list.get(i).getProcessName()+"프로세스 "+list.get(i).getArrivalTime()+"ms도착");
			else run.get(i).setText(run.get(i).getText()+ list.get(i).getProcessName()+"프로세스 "+list.get(i).getEndtime()+"아웃");
			RunningPanel.add(run.get(i));
		}*/
	}
	public void PrintResult(int n, Process[] process) {
		String [] temp = {"Process", "도착시간", "작업시간", "우선순위", "대기시간","응답시간", "반환시간"};
		JLabel [] Subject = new JLabel[7];
		JLabel [][] ProcessInfo = new JLabel[n][7]; 
		
		ResultPanel.setLayout(new GridLayout(n+1, 7));
		
		for(int i=0; i<7; i++) {
			Subject[i] = new JLabel(temp[i]);
			Subject[i].setHorizontalAlignment(JLabel.CENTER);
			Subject[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			ResultPanel.add(Subject[i]);
		}
		for(int i=0; i<n; i++) {			
			ProcessInfo[i][0] = new JLabel(process[i].ProcessName);
			ProcessInfo[i][1] = new JLabel(Integer.toString(process[i].ArrivalTime));
			ProcessInfo[i][2] = new JLabel(Integer.toString(process[i].OperationTime));
			ProcessInfo[i][3] = new JLabel(Integer.toString(process[i].Priority));
			ProcessInfo[i][4] = new JLabel(Integer.toString(process[i].WaitingTime));
			ProcessInfo[i][5] = new JLabel(Integer.toString(process[i].ResponseTime));
			ProcessInfo[i][6] = new JLabel(Integer.toString(process[i].TurnaroundTime));		
			for(int j=0; j<7; j++) {
				ProcessInfo[i][j].setHorizontalAlignment(JLabel.CENTER);
				ResultPanel.add(ProcessInfo[i][j]);
			}
		}
		
		
	}
	public void setAverage() {
		JLabel WaitingAverage = new JLabel("Average Waiting Time:");
		JLabel ResponseAverage = new JLabel("Average Response Time:");
		JLabel TurnAroundAverage = new JLabel("Average TurnAround Time:");
		Font font = new Font("맑은 고딕", Font.BOLD, 15);
		
		WaitingAverage.setFont(font);
		ResponseAverage.setFont(font);
		TurnAroundAverage.setFont(font);
		
		for(int i=0; i<3; i++) {
			AverageResult[i] = new JLabel("0");
			AverageResult[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		}
		
		AveragePanel.add(WaitingAverage);
		AveragePanel.add(AverageResult[0]);
		AveragePanel.add(ResponseAverage);
		AveragePanel.add(AverageResult[1]);
		AveragePanel.add(TurnAroundAverage);
		AveragePanel.add(AverageResult[2]);
		
		BevelBorder border=new BevelBorder(EtchedBorder.RAISED);
		AveragePanel.setBorder(border);
		AveragePanel.setBackground(Color.white);
		AveragePanel.setPreferredSize(new Dimension(650, 60));
		AveragePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		mainPanel.add(AveragePanel);
		
	}
	public void SetAverage(double Waiting, double Response, double TurnAround) {
	//	Double WaitingAVG = 
	//	String TrunAroungAVG = String.format("%.3f", TurnAround);
		AverageResult[0].setText(Double.toString(Math.round(Waiting*100)/100.0)); 
		AverageResult[1].setText(Double.toString(Math.round(Response*100)/100.0)); 
		AverageResult[2].setText(Double.toString(Math.round(TurnAround*100)/100.0)); 
	}
	
}
@SuppressWarnings("serial")
class DrawGantt extends JPanel{
	ArrayList<ProcessDispatch> process;
	 public int x=30, y=20, size = 10, width=30, height=40;
     public int start=0, RWID=0;
     DrawGantt(){}
	DrawGantt(ArrayList<ProcessDispatch> process){
		this.process = process;
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawString("0", 25, y+55);
		for(int i=0; i < process.size(); i++) {
		//	System.out.println("11");
			 if(i==0) start=x+0;
             else start = start+RWID;
             RWID=size*(process.get(i).getRuntime())+width;
         //    g.setFont(new Font("맑은 고딕", Font.BOLD, 30));
         //    System.out.println(process.get(i).getColor());
            Color c = process.get(i).getColor();
            g.setColor(c);
            if(c==null) g.setColor(Color.gray);
			g.fillRect(start, y, RWID, height);       
	       
	        
	        g.setColor(Color.black);
	        g.drawRect(start, y, RWID, height);
	        String name = process.get(i).getProcessName();
	        if(name.equals("none")) g.drawString(name, start+10, y+25);
	        else g.drawString(name, start+20, y+25);
	        g.drawString(Integer.toString(process.get(i).getEndtime()), start+RWID-5, y+55);
	        
	        
		}
		
          }
	//	System.out.println("Draw");
			
	
	
}
