import java.util.ArrayList;


public class Part{
	

	private String PartName;
	private ArrayList <Constraint> myConsts ;
	
	public Part(String name){
		
		this.PartName=  name;
		this.myConsts=new ArrayList <Constraint>();
	}
    
	public void addConstraint (Constraint cons){
		
		this.myConsts.add(cons);
	}

	public ArrayList<Constraint> getMyConsts() {
		return myConsts;
	}


	public void setMyConsts(ArrayList<Constraint> myConsts) {
		this.myConsts = myConsts;
	}


	public String getPartName() {
		return PartName;
	}

	public void setPartName(String partName) {
		PartName = partName;
	}

	
	int i=0;
	public String toString() {
		StringBuilder b = new StringBuilder();
		  b.append("Part [PartName=" + PartName + "], myConsts="  + "  ");
		for (Constraint con : myConsts) {
			i++;
			b.append(i+". "+con.getConstraintName());
			
			
		}
		return b.toString() ;
		
	}
	
	

}
