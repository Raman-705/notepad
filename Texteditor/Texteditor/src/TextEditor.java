import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // declairing frame
    JFrame frame;
    //declairing menu
    JMenuBar menuBar;
    JMenu File,Edit;
    //creating file menu items
    JMenuItem newFile,openFile,saveFile;
    // creating edit menu itema
    JMenuItem cut,copy,paste,selectAll,close ;
    JTextArea textArea;
    TextEditor(){
     //intitializing a jframe
        frame=new JFrame();
        //intitializing a new menubar
        menuBar =new JMenuBar();
        //initializing textArea
        textArea=new JTextArea();
        File=new JMenu("file");
        Edit=new JMenu("edit");
        //intilialize menu items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");
          //add to actionlistner to filemenu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //Adding tem to filemenu
        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);
        //Initializing edit menu item
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //add to actionlistner to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding e item to edit menu
        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(selectAll);
        Edit.add(close);
        //addding menus to menubar
        menuBar.add(File);
        menuBar.add(Edit);
        // set menuBar to frame
        frame.setJMenuBar(menuBar);

        //set textarea to frame
        frame.add(textArea);

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add tect area to pannel
        panel.add(textArea,BorderLayout.CENTER);
        //cretae scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //adding scroll pane to pannel
        panel.add(scrollPane);
        //add pannel to frame
        frame.add(panel);
        //dimension of frame
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
     if(actionEvent.getSource()==cut){
         //perform cut operation
         textArea.cut();
     }
     if(actionEvent.getSource()==copy){
         //perform copy operation
         textArea.copy();
     }
     if(actionEvent.getSource()==paste){
         //perform paste operation
         textArea.paste();
     }
     if(actionEvent.getSource()==selectAll){
         //perform selectall operation
         textArea.selectAll();
     }
     if(actionEvent.getSource()==close){
         //perform close operation
         System.exit(0);
     }
     if(actionEvent.getSource()==openFile){
         //open a file chooser
         JFileChooser fileChooser=new  JFileChooser("C:");
         int chooseOption=fileChooser.showOpenDialog(null);
         //ifnwe clicked on ok option
         if(chooseOption==JFileChooser.APPROVE_OPTION){
             //getting selected file
             File file=fileChooser.getSelectedFile();
             //get path of selected file    s
             String filePath=file.getPath();
             try {
                 FileReader fileReader=new FileReader(filePath);
                 BufferedReader bufferedReader=new BufferedReader(fileReader);
                 String intermediate ="",output="";
                 //read contnent of file
                 while((intermediate=bufferedReader.readLine()) !=null)
                 {
                     output+=intermediate+"\n";
                 }
                 textArea.setText(output);
             }
             catch(FileNotFoundException fileNotFoundException){
                 fileNotFoundException.printStackTrace();
             }
             catch (IOException fileNotFoundException){
                 fileNotFoundException.printStackTrace();
             }


         }
     }

       if(actionEvent.getSource()==saveFile){
           //intitalize file picker
           JFileChooser fileChooser=new JFileChooser("C:");
           //get chose option from file chooser
           int chooseOption =fileChooser.showSaveDialog(null);
           //check we  clicked on sAVE BUTTON
           if(chooseOption==JFileChooser.APPROVE_OPTION){
               //create a new file
               File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
               try{
               FileWriter fileWriter=new FileWriter(file);
               //intiliaze buffer
                   BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                   //write content of text area to file
                   textArea.write(bufferedWriter);
                   bufferedWriter.close();
               }
               catch (IOException ioException){
                   ioException.printStackTrace();

               }
           }
       }
       if(actionEvent.getSource()==newFile){
           TextEditor textEditor=new TextEditor();
       }
    }
    public static void main(String[] args) {
     TextEditor texteditor=new TextEditor();
    }
}