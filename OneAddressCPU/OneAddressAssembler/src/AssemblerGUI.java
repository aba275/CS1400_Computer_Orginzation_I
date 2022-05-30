import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;


/**
 * @Author: Amrit Dhakal
 */
public class AssemblerGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public AssemblerGUI() {
        setupGUI();
    }

    private void setupGUI() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
    	
        this.setTitle("One-Address Assembler");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(20, 20, 0, 20);
        JLabel inputLabel = new JLabel("Input Assembler File");
        mainPanel.add(inputLabel, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 20, 10, 0);
        JTextField inputField = new JTextField();
        inputField.setEditable(false);
        constraints.fill = GridBagConstraints.VERTICAL;
        inputField.setPreferredSize(new Dimension(400, inputField.getPreferredSize().height));
        mainPanel.add(inputField, constraints);

        constraints.insets = new Insets(0, 0, 10, 20);
        JButton inputButton = new JButton("Browse");

        mainPanel.add(inputButton, constraints);

        constraints.gridy++;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 20, 0, 20);
        JLabel outputLabel = new JLabel("Output Assembler Files");
        mainPanel.add(outputLabel, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 20, 10, 0);
        JTextField outputField1 = new JTextField();
        outputField1.setEditable(false);
        constraints.fill = GridBagConstraints.VERTICAL;
        outputField1.setPreferredSize(new Dimension(400, outputField1.getPreferredSize().height));
        mainPanel.add(outputField1, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 20, 10, 0);
        JTextField outputField2 = new JTextField();
        outputField2.setEditable(false);
        constraints.fill = GridBagConstraints.VERTICAL;
        outputField2.setPreferredSize(new Dimension(400, outputField2.getPreferredSize().height));
        mainPanel.add(outputField2, constraints);



        inputButton.addActionListener(e -> {
        	try {
        		textArea.setText("");
	            JFileChooser chooser = new JFileChooser(System.getProperty("user.dir")){
	            	@Override
	            	public void approveSelection(){
	            		if(!getSelectedFile().exists()){
	            			JOptionPane.showMessageDialog(this,  "File " + getSelectedFile().getName() + " does not exist.", "File does not exist", JOptionPane.ERROR_MESSAGE);
	            		}
	            		else if (!getSelectedFile().getName().endsWith(".asm")){
	            			JOptionPane.showMessageDialog(this,  "Input file must be a .asm file", "File extension no supported", JOptionPane.ERROR_MESSAGE);
	            		}
	            		else {
	            			super.approveSelection();
	            		}
	            	}
	            };
	            chooser.setDialogType(JFileChooser.FILES_ONLY);
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("Assembly", "asm");
	            chooser.addChoosableFileFilter(filter);
	            chooser.setAcceptAllFileFilterUsed(false);
	            chooser.setFileFilter(filter);
	            int response = chooser.showOpenDialog(AssemblerGUI.this);
	            if (response == JFileChooser.APPROVE_OPTION) {
	                String path = chooser.getSelectedFile().getAbsolutePath();
	                inputField.setText(path);
	                File f = new File(path);
	                if(!f.exists() || f.isDirectory()) { 
	                    throw new Exception("Input file must exist");
	                }
	                
	                int dotIndex = path.lastIndexOf(".");
	                String outputPath = path.substring(0, dotIndex);
	
	                outputField1.setText(outputPath + ".dat");
	                outputField2.setText(outputPath + ".mc");
	                
	                f = new File(outputPath + ".dat");
	                File f1 = new File(outputPath + ".mc");
	                if (f.exists() || f1.exists()) {
	                	throw new Exception ("Assemble output files exist\nAssembling will replace these files");
	                }

	            }
        	} catch (Exception ex) {
        		textArea.setText(ex.getMessage());
        	}
        });


        constraints.gridy++;


        JPanel buttonsPanel = new JPanel();
        JButton assembleButton = new JButton("Assemble");
        assembleButton.addActionListener(e -> {
        	
    		textArea.setText("");
        	String inputFileName = inputField.getText();
        	int dotIndex = inputFileName.lastIndexOf(".");
        	String outputFileName = inputFileName.substring(0, dotIndex); 
        	try {
                File f = new File(inputFileName);
                if(!f.exists() || f.isDirectory()) { 
                    throw new Exception("You must have a valid file to assemble");
                }
	            Assembler.assemble(inputFileName, outputFileName);
	            textArea.setText("Program assembled correctly");
        	} catch (Exception ex) {
        		textArea.setText(ex.getMessage());
        		if (new File(outputFileName + ".mc").exists())
            		(new File(outputFileName + ".mc")).delete();
        		if (new File(outputFileName + ".dat").exists())
        		    (new File(outputFileName + ".dat")).delete();
        	}
        });
        buttonsPanel.add(assembleButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        buttonsPanel.add(exitButton);

        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonsPanel, constraints);


        // Text area declared earlier as it is needed in the button listener.
        constraints.gridy++;
        constraints.insets = new Insets(0, 20, 10, 20);
        textArea.setPreferredSize(new Dimension(400, 100));
        mainPanel.add(textArea, constraints);


        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {
        new AssemblerGUI().setVisible(true);
    }
}
