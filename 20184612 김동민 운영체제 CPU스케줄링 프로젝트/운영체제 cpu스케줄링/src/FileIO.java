import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileIO {
	private File file;
	private BufferedWriter bw;
	private BufferedReader br;
	
	FileIO(){
		
	}
	public void TableFileWrite(ArrayList<String> process) {
		try {
			file = new File("Process.txt");
			bw = new BufferedWriter(new FileWriter(file, false));
			if(file.isFile() && file.canWrite()) {
				for(int i=0; i<process.size(); i++) {
					
					bw.write(process.get(i));
					
					bw.newLine();
				}
				bw.flush();
				bw.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Object[][] TableFileRead() {				
		try {
			Object[][] processAry;
			ArrayList<String> list = new ArrayList<>();
			String line = null;
	//		String []ary = new String[4];
			
			
			
			
			file = new File("Process.txt");	
			br = new BufferedReader(new FileReader(file));
			
			
			while((line=br.readLine())!=null) {
				list.add(line);
			}
			processAry = new Object[list.size()][5];
			
			
			
			for(int i=0; i<list.size();i++) {
				String s = list.get(i);					//현재 리스트를 s에 저장한다.
				StringTokenizer st = new StringTokenizer(s," ");
				
				
			//	System.out.println(s);
				int k=0;
				while(st.hasMoreElements()) {
					processAry[i][k++] = st.nextToken();
				//	System.out.println(k);
				}
				
			//	processAry[i][k] = Boolean.FALSE;
			}
			
			
			br.close();
			
			return processAry;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	public void TimesliceFileWrite(String timeslice) {
		try {
			file = new File("Timeslice.txt");
			bw = new BufferedWriter(new FileWriter(file, false));
			if(file.isFile() && file.canWrite()) {
				
				bw.write(timeslice);
				
				bw.flush();
				bw.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String TimesliceFileRead() {
		try {
			String line = null;
			file = new File("Timeslice.txt");	
			br = new BufferedReader(new FileReader(file));
			
			line=br.readLine(); 
			
			line = line.replace("ms", "");
			return line;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
