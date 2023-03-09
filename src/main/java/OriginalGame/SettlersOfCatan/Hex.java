package Team7.SettlersOfCatan;

public class Hex {
	
	private int number;
	private String resource;
	protected Intersection[] intersections = new Intersection[6];
	protected boolean hasRobber;
	
	public Hex(int i) {
		hasRobber = false;
		number = i;
	}

	public int getNumber() {
		return number;
	}
	
	public void setResource(String r) {
		resource = r;
	}
	
	public String getResource() {
		return resource;
	}
	
	public void setIntersections(Intersection i1, Intersection i2, Intersection i3, Intersection i4,
			Intersection i5, Intersection i6) {
		intersections[0] = i1;
		intersections[1] = i2;
		intersections[2] = i3;
		intersections[3] = i4;
		intersections[4] = i5;
		intersections[5] = i6;
	}
	

}
