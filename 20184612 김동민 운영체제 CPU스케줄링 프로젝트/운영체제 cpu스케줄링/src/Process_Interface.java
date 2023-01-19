import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class Process_Interface extends JFrame{
	ArrayList<Process> process = new ArrayList<>();
	Object[][] processAry;
	FileIO file = new FileIO();
	
	JPanel ProcessPanel = new JPanel();
	JPanel InfoPanel = new JPanel();
	JPanel InputPanel = new JPanel();
	JPanel TextPanel = new JPanel();
	JLabel TextLabel = new JLabel();
	JPanel BtnPanel = new JPanel();
	
	JTable InputTable;

	
	JComboBox<String> timesliceComboBox;
	String timeSlice;
	JButton processbtn[] = new JButton[3];
	
	Process_Interface(){
		setTitle("프로세스 수정");
		
		
		ProcessPanel();
		
		
		setLocation(860, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(560, 360);
		setResizable(false);
		setVisible(true);
	}
	public void ProcessPanel() {
	//	ProcessPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		ProcessSubject();
		
		InfoPanel();
		
		
		
		
		
		add(ProcessPanel);
	}

	public void ProcessSubject() {
	//	String str[] = {"프로세스","도착시간","작업시간","우선순위","타임슬라이스"}; 
		JPanel SubjectPanel = new JPanel();
		JLabel Subject = new JLabel();
	//	JLabel Subject[] = new JLabel[5];
	//	SubjectPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));
	//	SubjectPanel.setPreferredSize(new Dimension(400,60));
	/*	for(int i=0; i<5; i++) {
			Subject[i] = new JLabel(str[i]);
			Subject[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			SubjectPanel.add(Subject[i]);
		}*/
		Subject.setText("프로세스 수정");
		Subject.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		SubjectPanel.add(Subject);
		
		ProcessPanel.add(SubjectPanel);
		
	}
	public void InfoPanel() {
		InfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		ArrayList<Integer> rowArray = new ArrayList<>();
		
		InputPanel.setLayout(new BoxLayout(InputPanel, BoxLayout.Y_AXIS));
		SetInputTable(rowArray);
		
		SetTextPanel();
		
		JPanel Input = new JPanel();
		Input.add(InputPanel);
		Input.setBackground(Color.black);
		InfoPanel.add(Input);
		
		Btn_Func();
		BtnClick(rowArray);
		ProcessPanel.add(InfoPanel);
	}
	public void SetTextPanel() {
		InputPanel.add(TextPanel);
		TextPanel.setPreferredSize(new Dimension(350, 50));
		TextPanel.add(TextLabel);
		TextLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	
		
		TextLabel.setText("<html><body style='text-align:center;'>추가, 삭제, 타임슬라이스 설정시 <br>확인버튼을 \"꼭\"눌러주세요!</html>");
		
		TextPanel.setBackground(Color.white);
	}
	public void SetInputTable(ArrayList<Integer> rowArray) {
		
		
		JCheckBox checkbox = new JCheckBox();
		
		final Object str[] = {"프로세스","도착시간","작업시간","우선순위","삭제"};
		processAry = file.TableFileRead();
		for(int i=0; i<processAry.length; i++) processAry[i][4] = Boolean.FALSE;
		
		
		DefaultTableModel tableModel =new DefaultTableModel(processAry,str) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return column != 0;
			   }
			};
		
		InputTable = new JTable(tableModel);
		
		ProcessUpdate();
		
	//	TableCellRenderer render = InputTable.getCellRenderer(0, 0);
	//	render.setBackground(Color.black);
	//	render.setForeground(Color.black);
		
	//	DefaultTableCellRenderer renderer = new MyDefaultTableCellRenderer();
	//	InputTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	//	InputTable.getColumn("삭제").setCellRenderer(renderer);
		
		InputTable.getColumn("삭제").setCellRenderer(dcr);	
		InputTable.getColumn("삭제").setCellEditor(new DefaultCellEditor(checkbox));
		InputTable.getColumn("삭제").setPreferredWidth(20);
		checkbox.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane InputScroll = new JScrollPane(InputTable);
		
		
		checkbox.addActionListener(e->CheckBox(rowArray));
			
		
		InputPanel.add(InputScroll);	
		InputScroll.setPreferredSize(new Dimension(400, 200));
		
		TableCenter();				//표 가운데 정렬
		
		InputTable.getTableHeader().setReorderingAllowed(false);	//표 수정 불가
	//	InputPanel.setBackground(Color.black);						//패널 색 지정
	//	InputPanel.setPreferredSize(new Dimension(400, 250));
	//	InfoPanel.add(InputPanel);
	}
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
	{ 
		public Component getTableCellRendererComponent  // 셀렌더러
		 (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
		//	System.out.println(row);
			JCheckBox box= new JCheckBox();
			box.setSelected(((Boolean)value).booleanValue());   
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}
	};
	public void CheckBox(ArrayList<Integer> rowArray) {
		
		if (!rowArray.contains(InputTable.getSelectedRow())){
			
			rowArray.add(InputTable.getSelectedRow());
			
		}
		else {
			rowArray.remove(rowArray.indexOf(InputTable.getSelectedRow()));
		}

	}
	public void Btn_Func() {
		String str[] = {"프로세스 추가", "프로세스 제거", "확인"};
		
		BtnPanel.setLayout(new GridLayout(4,1, 20, 10));
		
		
		SetTimeSlice();
	//	BtnPanel.setPreferredSize(new Dimension(150, 50));
		for(int i=0; i<3; i++) {
			processbtn[i] = new JButton(str[i]);
			processbtn[i].setPreferredSize(new Dimension(120, 30));
			BtnPanel.add(processbtn[i]);
		}
		
		InfoPanel.add(BtnPanel);
	}
	public void SetTimeSlice() {
		JPanel slicePanel = new JPanel();
		JLabel sliceSubject  = new JLabel("타임슬라이스");
		String tmp[] = new String[20];
		for(int i=1; i<=20; i++) {	tmp[i-1] = Integer.toString(i)+"ms";	}
		timesliceComboBox = new JComboBox<String>(tmp);
		timeSlice = file.TimesliceFileRead();//
		int n = Integer.parseInt(timeSlice.substring(0, 1));
		
		timesliceComboBox.setSelectedIndex(n-1);
		
		sliceSubject.setHorizontalAlignment(JLabel.CENTER);
		sliceSubject.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		slicePanel.setLayout(new GridLayout(2,1));
		slicePanel.add(sliceSubject);
		slicePanel.add(timesliceComboBox);
		
		BtnPanel.add(slicePanel);
	}
	public void BtnClick(ArrayList<Integer> rowArray) {
		processbtn[0].addActionListener(e->AddTable());
		processbtn[1].addActionListener(e->DeleteTable(rowArray));
		processbtn[2].addActionListener(e->CheckTable());
		
		timesliceComboBox.addActionListener(e->TimesliceCheck());	
	}
	public void TimesliceCheck() {
		String Item = timesliceComboBox.getSelectedItem().toString();
		timeSlice = Item;
		TextLabel.setText("타임슬라이스: "+timeSlice);
	}
	public void AddTable() {
		
		
		 DefaultTableModel model = (DefaultTableModel) InputTable.getModel();
		 model.addRow(new Object[]{"p"+(InputTable.getRowCount()+1),"","","", false});
	
		 TextLabel.setText("<html><body style='text-align:center;'>도착시간:음수불가 / 작업시간 우선순위: 0이하 불가<br>잘못 입력시 행이 삭제됩니다.</html>");
	//	 InputTable.;
		 
	}
	public void DeleteTable(ArrayList<Integer> rowArray) {
		 DefaultTableModel model = (DefaultTableModel) InputTable.getModel();
		 
		 Collections.sort(rowArray, Collections.reverseOrder() );
			
	//	 System.out.println(rowArray.size()+","+InputTable.getRowCount());
		 if(rowArray.size()==InputTable.getRowCount()) {
			 TextLabel.setText("프로세스 전체를 삭제할 수 없습니다!");
			 return;
		 }
		 
		 if(rowArray.size()<=0) {
			 TextLabel.setText("<html><body style='text-align:center;'>삭제할 행을 체크하고 삭제버튼을 누르세요!</html>");
			 return;
		 }
		 else {
			 TextLabel.setText("");
			 String temp = "";
			 for(int i=rowArray.size()-1; i>=0; i--) {
				 temp += Integer.toString(rowArray.get(i)+1)+((i!=0)?",":"행");
			 }
			 TextLabel.setText("<html><body style='text-align:center;'>"+ temp+"<br>"+ rowArray.size()+"개의 행 삭제 완료! 확인버튼을 눌러 저장</html>");
			 for(int i=0; i<rowArray.size(); i++) {		 
				 model.removeRow(rowArray.get(i));	 
			 }
			rowArray.clear();
		 }
		 ProcessUpdate();
	}
	public void CheckTable() {
	//	String [] array = new String[InputTable.getRowCount()];
		ArrayList <String>ary = new ArrayList<>();
		DefaultTableModel model = (DefaultTableModel) InputTable.getModel();
		String text ="";
		 TextLabel.setText("");
		try {
			for(int i=0; i<InputTable.getRowCount(); i++) {
				String temp = "";
				String str = "";
				boolean flag = true;
				for(int j=0; j<4; j++) {
					 temp = (String) InputTable.getValueAt(i, j);
					 if(temp.equals(null) || temp.isBlank()|| (j>=1 && Integer.parseInt(temp)<0) || ((j==2 || j==3)&& Integer.parseInt(temp)==0)) {
						 text = "잘못된 행은 삭제됩니다!";
						 model.removeRow(i);	
						 i--;
						 flag = false;
						 break;
					 }
					 else str += temp+" ";
					 
				}
				if(flag) ary.add(str);
			}
			if(InputTable.getRowCount()==0) {
				//DefaultTableModel model = (DefaultTableModel) InputTable.getModel();
				String temp = "";
				String str="";
				 model.addRow(new Object[]{"p"+(InputTable.getRowCount()+1),"0","1","1", false});
				
				 for(int j=0; j<4; j++) {
					 temp = (String) InputTable.getValueAt(0, j);
					 str += temp+" ";
				 }
				 text = "행이 하나 남았기 때문에 초기화됩니다.";
				 ary.add(str);
				
			}
			TextLabel.setText("<html><body style='text-align:center;'>"+((!text.equals(""))? text+"<br>": "") +"프로세스 정보가 저장되었습니다.</html>");
			ProcessUpdate();
			if(ary.size()!=0)file.TableFileWrite(ary);
			file.TimesliceFileWrite(timeSlice);
			
			
		}
		catch(NumberFormatException e) {}
		
	//	System.out.println(file.TimesliceFileRead());
		
	}
	public void ProcessUpdate() {
		 for(int i=0; i<InputTable.getRowCount(); i++) {		 
			 InputTable.setValueAt("p"+(i+1), i, 0);		//프로세스 업데이트
			 
	 }
	}
	public void TableCenter() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = InputTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount()-1; i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}
	
}

