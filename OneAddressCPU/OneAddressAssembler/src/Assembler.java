import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * @author Charles Kann
 * Date    July 5, 2016
 * Purpose This program implements a 2-pass assembler for the 1-address
 *         CPU in the 1-address monograph.
 *         
 *         Input - A file containing an assembly program.
 *         Output - Two files.  One (.mc) contains the text segment
 *                 machine code, and the second (.dat) contains the
 *                 values for the data segment.
 * Procedure
 * 			1 - Open the input file name, and call the FirstPassParser
 *              to build a symbol table for each of the label entries.
 *              The symbol table is implemented as a HashMap, with the
 *              key being the symbol, and the data be a Label, a data
 *              structure containing the symbol, program address of the 
 *              symbol, and memory segment the symbol should be in.
 *          2 - Using the symbol table, call the SecondPassParser to 
 *              create two files, the ".mc" (or machine code) file, which
 *              contains the machine code to include in the text segment,
 *              and the ".dat" (or data) file, which contains the data 
 *              segment for the program.  These can be copied to the Logisim
 *              simulator memory, and used to run the CPU.
 *          3 - If an error occurs, it is trapped in the program try block,
 *              and an error message and line number for the line that caused
 *              the error is printed.
 *          4 - If the program completes normally, the assemble method returns
 *              a true.
 *              
 * Program History:
 *     7/6/2016    - CWK - Initial release
 */

public class Assembler {

	/**
	 *  Constructor - Not really needed.
	 */
	public Assembler() {
		
	}

	public static void main(String[] args) {
		try {
		    assemble("TestAssembler.asm", "TestAssembler");
		}  catch (AssemblerException ae) {
			System.out.println(ae.getMessage());
		} catch (IOException ioe){
           ioe.printStackTrace();
		}
	}
	
	/**
	 * assemble - This method parses the input file to produce the .mc and .dat
	 *            files used by the CPU
	 *            
	 * @param inputFileName The name of the assembly file to assemble
	 * @param outputFileName The name of the .mc and .dat files.  This
	 *                       will often be the name of the input file, 
	 *                       but can be differnt.
	 * @throws AssemblerException An exception thrown with an error 
	 *                       encountered while parsing the assembly program.
	 * @throws IOException   A exception occurs while processing the file, e.g.
	 *                       file not found.
	 */
	public static void assemble(String inputFileName, String outputFileName) 
		throws AssemblerException, IOException {

	    // Open the file, and do a first pass to create the symbol table st.
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		HashMap<String, Label> st = FirstPassParser.parseFile(br);
		
		// reset the file to the start.  Call SecondPassParser with 
		// file and symbol table to build assembler output files.
		br = new BufferedReader(new FileReader(inputFileName));
		PrintStream mc = new PrintStream(outputFileName + ".mc");
		PrintStream dat = new PrintStream(outputFileName + ".dat");
		SecondPassParser.parseFile(st, br, mc, dat);
	}

}
