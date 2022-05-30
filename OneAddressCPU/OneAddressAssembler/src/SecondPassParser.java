import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * @author Charles Kann
 * purpose This class implements a second pass parser for a 1-Address assembly
 *         language.  In the second pass, the purpose is to build and write
 *         to an output file.
 *         
 * Program History:
 *     7/6/2016    - CWK - Initial release
 */
public class SecondPassParser {

	public SecondPassParser() {
	}
	
	/**
	 * This function translates the input file of assembly code into
	 * machine and data memory that can be used with the 1-address CPU.
	 * 
	 * @param st symbol table to use to find addresses of labels
	 * @param br input assembly language file
	 * @param mc output machine code file
	 * @param dat output data file
	 * @throws IOException error in processing file
	 * @throws AssemblerException parsing error in the assembly code
	 */
	public static void parseFile(HashMap<String, Label> st,BufferedReader br, 
			PrintStream mc, PrintStream dat) 
			throws IOException, AssemblerException {
		try {
			boolean processingText = true;  // Default to a text segement.
			int currentTextAddress = 0;
			int currentDataAddress = 0;
			int lineNumber = 1;
			
			// Write first line to both files
			mc.println("v2.0 raw");
			dat.println("v2.0 raw");
	
		    
	    	String s=br.readLine();
		    while (s != null) {
		    	String s1 = s.trim();
		    	String[] tokens = s1.split("\\s+");
		    	
		    	// Check if the program segment (.text or .data) is set.
		    	if (tokens[0].equals("#"))
		    		; // Skip comment
		    	else if (tokens[0].equals(""))
		    		; //Skip blank line
		    	else if (tokens[0].equals(".label"))
		    		; // Skip label statements 
		    	else if (tokens[0].equals(".text"))
		    		processingText = true;
		    		
		    	else if (tokens[0].equals(".data")) 
		    		processingText = false;
		    	
		    	else if (processingText == false) {
		    		if (tokens[0].equals(".number")) {
		    			String writeString = InstructionParser.intStringToHexString(tokens[1], lineNumber);
	    				dat.println(writeString);
		    			currentDataAddress = currentDataAddress + 1;
		    		}
		    		else
		    			throw new AssemblerException("Only .number directives allowed in .data segment: line number: " + lineNumber);
		    	}
		    		
		    	else if (processingText == true) {
		    		InstructionParser.Parser p = InstructionParser.getParser(tokens[0]);
		    		if (p == null)
		    			throw new AssemblerException("Operator " + tokens[0] + " not found - Line Number: " + lineNumber);
		    		String input;
		    		if (tokens.length == 1)
		    			input = "";
	    			else
	    				input = tokens[1];
		    		mc.println(p.parse(input, st, lineNumber));
					currentTextAddress = currentTextAddress + 1;
				}
		    	
		    	else {
		    			throw new AssemblerException("Error in Assembler - Pass 1 : Line no:" + lineNumber);
	    		}
	
	            // Continue by processing the next line.
		    	lineNumber = lineNumber + 1;
		    	s=br.readLine();
	
		    }
		} finally {
    		mc.close();
	    	dat.close();
		}
	}
	

}
