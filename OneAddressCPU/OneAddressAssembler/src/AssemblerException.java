
/**
 * @author Charles Kann
 * purpose to create an exception specific to the Assembler 
 * program, where assembler errors can be maintained.
 * 
 * Program History:
 *     7/6/2016    - CWK - Initial release
 *
 */
public class AssemblerException extends Exception {

	private static final long serialVersionUID = 1L;

	public AssemblerException() {
		// TODO Auto-generated constructor stub
	}

	public AssemblerException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AssemblerException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AssemblerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public AssemblerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
