import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //frame for Text Editor
    JFrame frame;

    //holds all the item(drop down options) in it like file,edit,close
    JMenuBar menuBar;

    //Menus...
    JMenu file , edit ;

    //Menu Items for file menu
    JMenuItem newFile , openFile , saveFile;

    //menu items for edit menu
    JMenuItem cut , copy , paste , selectAll , close;

    //
    JTextArea textArea;


    //constructor
    TextEditor(){
        //Initialize frame
        frame = new JFrame();

        //Initialize Text Area
        textArea = new JTextArea();

        //Initialize MenuBar
        menuBar = new JMenuBar();

        //Initialize File menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize menu items for file menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //Add Action Listner to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize menu items for edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close Window");

        //Add Action Listner to edit menu item
        cut.addActionListener(this); //this is used -> specify to use the object of the textEditor class
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);




        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Add menuBar to frame  ,function to set menubar
        frame.setJMenuBar(menuBar);

        //
        frame.add(textArea);

        //for dimention of the jrame
        frame.setBounds(100,100,1200,500);

        //Make frame unhidden
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //if source is cut
        if(actionEvent.getSource()==cut){ //.getSource -> telling us where the action event is performed
            //Perform action according to cut event...
            textArea.cut(); //pre defined method in JTextArea .cut
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==newFile){
            //Create a new editor
            TextEditor newWindow = new TextEditor(); // create a new texteditor with same properties
        }
        if(actionEvent.getSource()==openFile){
            //Open new file
            //Initialize file chooser
            JFileChooser fileChooser = new JFileChooser("G:"); // create a file chooser with JfileChooser class
            //Fet choose option from file chooser
            int chooseOption = fileChooser.showOpenDialog(null); //
            // if choose option is equal to approve
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Get selected file
                File file = fileChooser.getSelectedFile();
                //Get selected file path
                String filePath = file.getPath();

                try{
                    //create Buffered reader..
                    BufferedReader bf = new BufferedReader(new FileReader(filePath));
                    //create string to store content from file
                    String intermidiate = "" ,output = ""; // intermidiate = line  , output = all the content
                    //Read content line by Line
                    while((intermidiate = bf.readLine())!=null){
                        output+=intermidiate+"\n";
                    }
                    //set output to text Area
                    textArea.setText(output);
                }
                catch (Exception e){
                    e.printStackTrace(); // this method print the exception
                }
            }

        }
        //If source is save
        if(actionEvent.getSource()==saveFile){
            //save file
            //Create a file chooser..
            JFileChooser fileChooser = new JFileChooser("G:");
            // Get choosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if choosen option is Approve
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Create a file object with selected path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // create Buffered writer to write content to file
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}