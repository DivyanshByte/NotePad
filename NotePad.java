/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author byte
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class NotePad {
    JFrame f;
    JPanel main;
    JTextArea t,output;
    JMenuBar mb;
    JMenu file,run;
    JMenuItem newf, open, save, saveas, close, print, cr, out,c;
    String code, filename = "untitlted";
    boolean cod = false,s = false;
    JComboBox box;
    public static void main(String args[]){
        new NotePad().buildGui();
    }
    public void buildGui(){
        f = new JFrame(filename + " - Byte Notepad");
        main = new JPanel(new BorderLayout());
        t = new JTextArea();
        JScrollPane sp = new JScrollPane(t);
        main.add(BorderLayout.CENTER, sp);
        t.setFont(new Font("Apple Garmond Regular", Font.BOLD, 15));
        t.setBackground(Color.BLUE);
        t.setForeground(Color.RED);
        
        output = new JTextArea();
        output.setEditable(false);
        mb = new JMenuBar();
        mb.setBackground(Color.MAGENTA);
        file = new JMenu("File");
        run = new JMenu("Run");
        mb.add(file);
        mb.add(run);
        newf = new JMenuItem("New");
        newf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newf.addActionListener(new New());
        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        open.addActionListener(new Open());
        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        save.addActionListener(new Save());
        saveas = new JMenuItem("Save As");
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        saveas.addActionListener(new SaveAs());
        print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
        print.addActionListener(new Print());
        close = new JMenuItem("Close");
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
        close.addActionListener(new Close());
        c = new JMenuItem("Set File Type");
        c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
        c.addActionListener(new Code());
        cr = new JMenuItem("Complile and Run");
        cr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.ALT_MASK));
        cr.addActionListener(new CR());
        out = new JMenuItem("Show Output");
        out.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
        out.addActionListener(new Out());
        file.add(newf);
        file.add(open);
        file.add(new JSeparator());
        file.add(save);
        file.add(saveas);
        file.add(new JSeparator());
        file.add(print);
        file.add(close);
        run.add(c);
        run.add(new JSeparator());
        run.add(cr);
        run.add(new JSeparator());
        run.add(out);
        mb.add(file);
        mb.add(run);
        box = new JComboBox();
        box.addItem("Java");
        box.addItem("Html");
        box.addItem("Python");
        f.setJMenuBar(mb);
        f.getContentPane().add(main);
        f.setBounds(50,50,600,600);
        f.setVisible(true);
        
    }
    public class New implements ActionListener{
        public void actionPerformed(ActionEvent e){
            t.setText("");
            cod = false;
            s = false;
        }
    }
    public class Open implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            open();
        }

      
    }
    public void open(){
        JFileChooser fc= new JFileChooser("Open");
        int r = fc.showOpenDialog(f);
        if(r == JFileChooser.APPROVE_OPTION){
            String filename = fc.getSelectedFile().getAbsolutePath();
            f.setTitle(filename + " - Byte Notepad");
            File fi = new File(filename);
            try{
               FileReader fr = new FileReader(fi);
               BufferedReader br = new BufferedReader(fr);
               String line = null;
               t.setText("");
               while((line = br.readLine()) != null){
                   t.append(line  + "\n");
               }
               br.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(f, e.getMessage());
            }
         s = true;
         cod = false;
        }
        else{
            JOptionPane.showMessageDialog(f, "You Cancelled the Operation");
        }
    }
    public class Save implements ActionListener{
        public void actionPerformed(ActionEvent e){
            save();
        }
    }
    public void save(){
        if(!s){
            JFileChooser fc = new JFileChooser("Save");
            int r = fc.showSaveDialog(f);
           if(r == JFileChooser.APPROVE_OPTION){
               filename = fc.getSelectedFile().getAbsolutePath();
               f.setTitle(filename + " - Byte Notepad");
               File fi = new File(filename);
               try{
                   fi.createNewFile();
                   FileWriter fw = new FileWriter(fi, false);
                   BufferedWriter bw = new BufferedWriter(fw);
                   bw.write(t.getText());
                   bw.flush();
                   bw.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(f, e.getMessage());
               }
               s = true;
               cod = false;
           }
           else{
               JOptionPane.showMessageDialog(f, "You Cancelled the Operation");
           }
           
           
        }
        else if(s){
                       

              
               File fi = new File(filename);
               try{
                   fi.delete();
                   FileWriter fw = new FileWriter(fi, false);
                   BufferedWriter bw = new BufferedWriter(fw);
                   bw.write(t.getText());
                   bw.flush();
                   bw.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(f, e.getMessage());
               }
               s = true;
               cod = false;
           

           
        }
    }
    public class SaveAs implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFileChooser fc = new JFileChooser("Save As");
            int r = fc.showSaveDialog(f);
            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(fc.getSelectedFile().getAbsolutePath());
                try{
                    FileWriter fw = new FileWriter(fi,false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(t.getText());
                    bw.flush();
                    bw.close();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(f, ex.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f, "You Cancelled the Operation");
            }
        }
    }
    public class Print implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                t.print();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(f, ex.getMessage());
            }
        }
    }
    public class Close implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    public class Code implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setUpCode();
        }
    }
    public void setUpCode(){
        JDialog d = new JDialog();
                d.add(box);
        box.addItemListener(new Box());
        d.setSize(100,50);
        d.setVisible(true);
    }
    public class Box implements ItemListener{

        
        public void itemStateChanged(ItemEvent ie) {
          //To change body of generated methods, choose Tools | Templates.
          int i = box.getSelectedIndex();
          switch(i){
              case 0:
                  code = "java";
                  cod = true;
                  break;
              case 1:
                  code = "google-chrome";
                  cod = true;
                  break;
              case 2:
                  code = "python";
                  cod = true;
                  break;
          }
        }
        
    }
    public class CR implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(s){
            if(cod){
                run();
            }
            else{
                setUpCode();
                run();
            }
            }
            else{
                save();
            }
        }
    }
    public void run(){
        try{
            Process p = Runtime.getRuntime().exec(code + " " + filename);
            
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = null;
            output.setText("");
            while((line = stdInput.readLine()) != null){
                output.append(line + "\n");
            }
            while((line = stdError.readLine()) != null){
                output.append(line + "\n");
            }
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(f, e.getMessage());
        }
    }
    public class Out implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JDialog d = new JDialog(f);
            JScrollPane ap = new JScrollPane(output);
            d.add(ap);
            d.setSize(500,200);
            d.setVisible(true);
        }
    }
}
