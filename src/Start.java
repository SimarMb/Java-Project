
// this is main class 

public class Start {
	
	 public static void main (String arg[]){
		 
		 try {
			    Graph graph1 = new Graph("KolbenStange");
			    
			    ReadFile reader = new ReadFile(graph1);
			    
			    reader.setInputFile("F:/DIK_HIWI/cad/ubg_kolbenstange.xls");
			    reader.readCad();
			    
			    graph1.generateAssemblySequence();
			    
			    
		} catch (Exception e) {
	
			e.printStackTrace();
		}
			   
       }
}
