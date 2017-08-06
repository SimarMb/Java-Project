// this class reads the cad export file .This class uses jxl.jar 


import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadFile {

    private String inputFile;
    
    private Graph grph;

    public ReadFile(Graph grph){
    	
    	this.grph = grph;
    	
    }
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    public void readCad() throws IOException  {

      File inputWorkbook = new File(inputFile);
        Workbook w;

        try {
        	w = Workbook.getWorkbook(inputWorkbook);
            
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            
            //**************** Find the body parts********************************************//
           
           
            for (int j = 0; j < sheet.getColumns(); j++) 
            {  
            	Cell cell = sheet.getCell(j,0);
            	
            	if (cell.getContents().equalsIgnoreCase("Component Name"))
            	{  
            		 for (int i = 1; i < sheet.getRows(); i++)
            		 {
                     	Cell cell2 = sheet.getCell(j,i);
                     	
                     	if (!cell2.getContents().equalsIgnoreCase(""))
                     	{
                     		Part part =new Part(cell2.getContents());
                     		grph.addPart(part);
                     	} 
            		 }
            	}
            }
            
            //************** find the Starting and end row for constraints*******************//
            int startAfterThis = 0;
    		int endBeforeThis=0;

            for (int i = 0; i < sheet.getRows(); i++)
            {
            	Cell cell = sheet.getCell(0,i);
            	String  cellContent =cell.getContents().trim();
            	if (cellContent.equalsIgnoreCase("Constraints"))
            	{
            	      startAfterThis =i;
            		
            	}
            	
            	if (cellContent.equalsIgnoreCase(grph.getPartsList().get(0).getPartName()))
            	{
            		endBeforeThis =i;
            		
            	}
            	
            }
           System.out.println("start = "+ startAfterThis);
           System.out.println("end = "+ endBeforeThis);
           
           // ***************************Add the Constraints************************//
               Part part1Typ =null;
               Part part2Typ =null;

               
            for (int i = startAfterThis+1; i < endBeforeThis; i++) 
            {
            	Cell cell= sheet.getCell(0, i);
            	String str = cell.getContents().trim();
            	String[] strArr =str.split("[\\(\\)\\,]");
            	
            	String con= strArr[0].trim();
            	System.out.println(con);
            	String part1= strArr[1].trim();
            	String part2 = "";
            	if (strArr.length>2)
            	{
            	 part2 = strArr[2].trim();
            	}
            	else
            	{
                	 part2= strArr[1].trim();
            	}
            	//****************************Assigning the Part-name to part type***************************************************//
            	for (Part prt:this.grph.getPartsList())
            	{
            		if (prt.getPartName().equalsIgnoreCase(part1))
            		{
            			 part1Typ= prt;
            		}
            		 if (prt.getPartName().equalsIgnoreCase(part2))
            		 {
            			 part2Typ =prt;
            		 }	 
            		
            	}
            	//************************+++++++++++++++Adding Constraint++++++++++++++++********************************//
            	
            	Constraint constr= new Constraint(con, part1Typ, part2Typ);
            	grph.addConstraints(constr);
            	
            	Part p =null;
            	if (this.grph.findAPart(part1)!=null)
            	{
            		 p=this.grph.findAPart(part1);
            		 p.addConstraint(constr);
            	}
            	            	
            	if(!part2.equals(part1))
            	{
            		if(this.grph.findAPart(part2)!=null)
                	{    
                		 p=this.grph.findAPart(part2);
                		 p.addConstraint(constr);

                	}
            	}
            }
				
			
            //******************++++++++++++++++++++++Printing parts++++++++++++++++++*******************************//
          for (int i = 0; i <grph.getPartsList().size(); i++) 
          {
			           System.out.println( "Parts : "+grph.getPartsList().get(i)+ " no. constraints = "+grph.getPartsList().get(i).getMyConsts().size());
          } 
          
          
          //******************+++++++++++++++++++++++Printing Constraints+++++++++++++++++++************************//

       for (int i = 0; i <grph.getConstsList().size(); i++) 
       {  
    	   Constraint con =grph.getConstsList().get(i);
    	   
		  System.out.println("Contraint no."+ i +" = " 
				  +con.getConstraintName()+" Part1 : "+con.getFirstPart().getPartName()+" Part2 :"+ con.getSecondPart().getPartName());	         
       }
		
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }


   /* public void read(int column, int row) throws IOException  {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            
            // Loop over first 10 column and lines
            System.out.println("number of Columns "+sheet.getColumns() );
            System.out.println("number of Rows "+sheet.getRows() );

            
            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number "
                                + cell.getContents());
                    }

                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }*/



}