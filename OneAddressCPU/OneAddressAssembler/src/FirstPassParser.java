import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Charles Kann
 * purpose This class implements a first pass parser for a 1-Address assembly
 *         language.  In the first pass, the purpose is to build and return
 *         a symbol table, which is here defined as a HashMap.
 *         
 * Program History:
 *     7/6/2016    - CWK - Initial release
 */
public class FirstPassParser {

	public FirstPassParser() {
	}
	
	/**
	 * This function processes a file of assembly language instructions, and
	 * returns a symbol table with the Labels and their addresses defined.
	 * 
	 * @param br the file of assembly language instructions to be processed.
	 * @return the symbol table of labels and addresses
	 * @throws IOException exception if there is an error processing the file
	 * @throws AssemblerException parse error processing the file
	 */
	public static HashMap<String, Label> parseFile(BufferedReader br) 
			throws IOException, AssemblerException {
		HashMap<String, Label> st = new HashMap<String, Label>();
		boolean processingText = true;  // processing text of data segment.  
		                                // Default to a text segement.
		int currentTextAddress = 0;     // current address in processing
		                                // text segment
		int currentDataAddress = 0;     // current address in processing
		                                // data segment
		int lineNumber = 1;             // line number in file for error
		                                // messages
	   
		// Process each line in the file.
    	String s=br.readLine();
	    while (s != null) {
	    	
	    	// remove all extraneous blanks, and parse tokens
	    	String s1 = s.trim();
	    	String[] tokens = s1.split("\\s+");
	    	
	    	// Skip comments and blank lines
	    	if (tokens[0].equals("#"))
	    		; // Skip comment
	    	else if (tokens[0].equals(""))
	    		; //Skip blank line
	    	
	    	// Check if the program segment (.text or .data) is set.
	    	else if (tokens[0].equals(".text"))
	    		processingText = true;
	    		
	    	else if (tokens[0].equals(".data")) 
	    		processingText = false;
	    	
            // Process a label
	    	else if (tokens[0].equals(".label")){
		    	// If this is a label, .label must be the first token and the label 
		    	// name must be the second token.  There should be nothing after the label name  
		    	// on the line.  If valid, add the entry to the Symbol Table.
	    		if (tokens.length != 2)
	    			throw new AssemblerException("Syntax is '.label name;.  Nothing can follow name.  Line number: "+ lineNumber);

	    		// build label for symbol table
    			String labelName = tokens[1];
    			char memType;
    			int memAddress;
	    		if (processingText) {
	    			memType = 'c';
	    			memAddress = currentTextAddress;
	    		}
	    		else {
	    			memType = 'd';
	    			memAddress = currentDataAddress;
	    		}
	    		
	    		// Put the label into the symbol table.  If it is already in the symbol
	    		// table, this is a duplicate, so give an error.
	    		if (st.put(labelName, new Label(labelName, memAddress, memType)) != null) {
	    			throw new AssemblerException("Duplicate label name at: " + lineNumber);
	    		}

	    	}
	    	
	    	// Labels, blank lines, and comments do not take up program or data space, 
	    	// so they were not accounted for in the data or text space.  What is 
	    	// left to process is .number assembler directives in the data space,
	    	// and assembly instructions in the text space.  Both take up 1 word of
	    	// space, so increment the memory counters appropriately.
	    	else if (processingText == false) {  // Data segment
	    		// only a .number assembly directives allowed in data segment.  
	    		if (tokens[0].equals(".number")) // numbers take up 1 space
	    			currentDataAddress = currentDataAddress + 1;
	    		else
	    			throw new AssemblerException("Only .number directives allowed in .data segment: line number: " + lineNumber);
	    	}
	    		
	    	else if (processingText == true) {  // text segment
				currentTextAddress = currentTextAddress + 1;
			}
	    	
	    	else {
	    			throw new AssemblerException("Error in Assembler - Pass 1 : Line no:" + lineNumber);
    		}
	    	
            // Continue by processing the next line.
	    	lineNumber = lineNumber + 1;
	    	s=br.readLine();
	    }
	    return st;
	}

}
