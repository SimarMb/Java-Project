import java.util.ArrayList;


public class Graph {
										
	private String graphName;
	private ArrayList <Part> partsList;
	private ArrayList <Constraint> constsList ;
	private ArrayList <Part> partSequenceList;
	
	
	public Graph(String graphName){
		
	this.graphName= graphName;	
	this.partsList = new ArrayList<Part> ();
	this.constsList = new ArrayList<Constraint> ();
	
		
	}
	
	public void addPart(Part prt){
		
		this.partsList.add(prt);
		
	}
	
	public void addConstraints(Constraint constr){
		
		this.constsList.add(constr);
		
		
	}
	
    
    public Part findAPart(String partName)
    {
    	for(Part p : partsList)
    	{
			 if(partName.equalsIgnoreCase(p.getPartName()))
			 {
				return p ;
			 }
		 }
    	return null;
    }
    
    public Constraint findAConstraint(String conName)
    {  Constraint con1 = null;
    
    	for(Constraint con : constsList)
    	{
			 if(conName.equalsIgnoreCase(con.getConstraintName()))
			 {
				 con1= con;
			 }
		 }
        return con1;
    }
	
	public void generateAssemblySequence() {
		
	    this.partSequenceList = new ArrayList<Part>();
	    
	 //************************Finding First Part **************************************//
		int maxConst =0;
		int currentSize =0;
		Part firstPart=null;
		
	// Assigning Part with max no. of constraints as FIRST PART
		
		for(int i = 0; i < this.partsList.size(); i++)   
		{   
			currentSize =partsList.get(i).getMyConsts().size();
			
			if(currentSize>maxConst) 
			{ 
				maxConst = currentSize;
				firstPart= this.partsList.get(i);	
			}
		      
		}	
	
	// Assigning or replacing Part with Constraint "FIX"  as FIRST PART
		
		for(int i = 0; i < this.partsList.size(); i++)  
		{
           for(Constraint con:partsList.get(i).getMyConsts())
           {
        	 if (  con.getConstraintName().equalsIgnoreCase("Fix"))
        			 firstPart=this.partsList.get(i);
           }
		}
		
    // Adding first part in partSequenceList 		
		 this.partSequenceList.add(firstPart);	
		 
 //******************************Finding Second Part *************************************//

	     for (int i = 0; i < partsList.size(); i++) {
			
		
		      // Constraint con = findAConstraint("Touch");
		       Part LastPart=this.partSequenceList.get(this.partSequenceList.size()-1);
		       ArrayList<Part> Neighbours=findNeighbours(LastPart,"Touch");
				if (Neighbours.size()==1)
				{ 
					this.partSequenceList.add(Neighbours.get(0));
							
				}
				else if (Neighbours.size()>1)
				{
					for(Part p:Neighbours)
					{
						if (!p.getPartName().equalsIgnoreCase(LastPart.getPartName()))
								{
									this.partSequenceList.add(p);
								}
					}
				}
	     }	
	     System.out.println("Sequence is : ");
	     for (int i = 0; i < this.partSequenceList.size(); i++) 
	     {
			Part p = this.partSequenceList.get(i); 
	        System.out.println( "Part no. "+ i + " "+ p.getPartName());
	     }
	}
				
			
		
	
	
	
	

	public ArrayList<Part> findNeighbours(Part p, String st){
		 ArrayList<Part> Neighbours = new ArrayList<Part>();
		 
		for (Constraint con :p.getMyConsts())
		{   
			System.out.println("A"+con.getConstraintName()+"E");
			if ( con.getConstraintName().equalsIgnoreCase(st) )
			{
				Part p1= con.getPartnerPart(p);
				Neighbours.add(p1);
			}
					
		}
		return Neighbours;
	}
		
	public ArrayList<Part> findNeighbours(Part p, Constraint con){
		   ArrayList<Part> Neighbours= new ArrayList<Part>();
		   Part p1= con.getPartnerPart(p);
		   Neighbours.add(p1);
		
		   return Neighbours;
	}
		
		
		
		
		
		/*Part p1=null;
		for (Part prt : partsList) 
		{
	      for (Constraint con : prt.getMyConsts())
	      {
	    	  if (con.getFirstPart().equals(p))
	    	  {
	    		  p1=con.getFirstPart();
	    	  }
	    	  else if (con.getSecondPart().equals(p))
	    	  {
	    		  p1=con.getFirstPart();
	    	  }
	    	  
			
		  }
		}
		return p1;
	}
*/
	public ArrayList<Part> getPartsList() {
		return partsList;
	}

	public void setPartsList(ArrayList<Part> partsList) {
		this.partsList = partsList;
	}

	public ArrayList<Constraint> getConstsList() {
		return constsList;
	}

	public void setConstsList(ArrayList<Constraint> constsList) {
		this.constsList = constsList;
	}
	
	
}
