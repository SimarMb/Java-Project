import java.util.*;


public class Constraint {
	private String ConstraintName;
	private  Part FirstPart ;
	private  Part SecondPart ;
	
	public  Constraint(String name,Part part1,Part part2){
	this.ConstraintName= name;
	
	this.FirstPart =  part1;
	this.SecondPart = part2;
			
	}

	public String getConstraintName() {
		return ConstraintName;
	}

	public void setConstraintName(String constraintName) {
		ConstraintName = constraintName;
	}
    
	public Part getPartnerPart(Part p)
	{ 
		
		if (p.getPartName().equals(this.FirstPart.getPartName()))
		{
			return SecondPart;
		}
		else if (p.getPartName().equals(this.SecondPart.getPartName()))
		{
			return FirstPart;
			
		}
		else return null;
		
	}
	
	
	public Part getFirstPart() {
		return FirstPart;
	}

	public void setFirstPart(Part firstPart) {
		FirstPart = firstPart;
	}

	public Part getSecondPart() {
		return SecondPart;
	}

	public void setSecondPart(Part secondPart) {
		SecondPart = secondPart;
	}

	
	@Override
	public String toString() {
		return "Constraint [ConstraintName=" + ConstraintName + ", FirstPart="
				+ FirstPart.getPartName() + ", SecondPart=" + SecondPart.getPartName() + "]";
	}

}
