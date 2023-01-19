import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_Interface extends JFrame{
	JPanel mainPanel = new JPanel();
	JPanel SubPanel = new JPanel();
	
	JPanel ButtonPanel = new JPanel();
	
	JButton SchedulingBtn[] = new JButton[7];
	JButton changeBtn = new JButton();
	
//	SchedulingFrame Scheduling;
	Process_Interface PI;
	GUI_Interface(){
		setTitle("CPU �����ٸ� in JAVA");
		SetMainPanel();
		SetSubPanel();
		
		
		Btn_Click();
		
		add(mainPanel);
		
		setLocation(300, 100);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(560, 360);
		setResizable(false);
		setVisible(true);
	}
	public void SetMainPanel() {
		JLabel Subject = new JLabel();
		JLabel name = new JLabel("20184612 �赿��");
		JPanel SubjectPanel = new JPanel();
		SubjectPanel.setLayout(new GridLayout(2,1));
		Subject.setText("CPU�����ٸ� �˰���");
		Subject.setFont(new Font("���� ���", Font.BOLD, 25));
		
		name.setFont(new Font("���� ���", Font.PLAIN, 20));
		name.setHorizontalAlignment(JLabel.CENTER);
	//	SubjectPanel.setPreferredSize(new Dimension(560, 50));
		SubjectPanel.add(Subject);
		SubjectPanel.add(name);
		mainPanel.add(SubjectPanel, "North");
		
	}
	public void SetSubPanel() {
		SubPanel.setBackground(Color.black);
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		SetScheduling();
		ChangeBtn();
		SubPanel.add(ButtonPanel);
		mainPanel.add(SubPanel);
	}
	public void SetScheduling() {
		
		JPanel SchedulingPanel = new JPanel();
		
		SchedulingPanel.setLayout(new GridLayout(4,2, 30, 15));
		String str[] = {"FCFS", "SJF", "���� Priority", "���� Priority", "Round-Robin", "SRT", "HRN"};
		for(int i=0; i<7; i++) {
			SchedulingBtn[i]= new JButton(str[i]+" �����ٸ�");
			SchedulingBtn[i].setPreferredSize(new Dimension(170, 40));
			SchedulingPanel.add(SchedulingBtn[i]);
		}
	//	SchedulingPanel.setBackground(Color.white);
	//	ButtonPanel.setBackground(Color.white);
		ButtonPanel.add(SchedulingPanel);	
	}
	public void ChangeBtn() {
		changeBtn.setText("<html>���μ��� ��<br>�����ð�<br>�۾��ð�<br>�켱����<br>Ÿ�ӽ����̽�</html>");
		changeBtn.setPreferredSize(new Dimension(120, 150));
		ButtonPanel.add(changeBtn);
	}
	public void Btn_Click() {
		changeBtn.addActionListener(e->OpenProcessInterface());
		
		SchedulingBtn[0].addActionListener(e->new FCFSScheduling());
		SchedulingBtn[1].addActionListener(e->new SJFScheduling());
		SchedulingBtn[2].addActionListener(e->new NonPriorityScheduling());
		SchedulingBtn[3].addActionListener(e->new PriorityScheduling());
		SchedulingBtn[4].addActionListener(e->new RoundRobinScheduling());
		SchedulingBtn[5].addActionListener(e->new SRTScheduling());
		SchedulingBtn[6].addActionListener(e->new HRNScheduling());

	}
	public void OpenProcessInterface() {
		if(PI==null)PI = new Process_Interface();
		else {
			
			PI.setVisible(true);
		}
	}
}
