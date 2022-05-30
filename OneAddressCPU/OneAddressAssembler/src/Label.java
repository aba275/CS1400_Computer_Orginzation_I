
/**
 * @author Charles Kann
 *
 * This class represents a label in the symbol table.
 * 
 * Program History:
 *     7/6/2016    - CWK - Initial release
 */
public class Label {
	final String name;       // The name of the label
	final int address;       // The adddress of the label
	final Character segment; // memory the label is located in, 
	                         // t for text, d for data
	
	public Label(String name, int address, char segment){
		this.name = name;
		this.address = address;
		this.segment = segment;
	}
	
	public int getAddress() {
		return address;
	}
	
	public char getSegment() {
		return segment;
	}
	
	@Override
	public boolean equals(Object o) {
		Label l = (Label) o;
		return this.name.equals(l.name);
	}

	@Override
	public String toString() {
		return ("name: " + name + "; segment: " + segment + "; address: " + address);
	}

}

