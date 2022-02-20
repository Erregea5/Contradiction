/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.contradiction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.time.*;

public class TitlePage {
    
    public static void main(String[] args) {
        JFrame jf=new JFrame("Contradiction");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        //jf.setLayout(new BoxLayout());
        jf.setSize(500, 500);
        
        panel p=new panel(); 
        Container cont=jf.getContentPane();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        cont.add(p);
    }

}

class panel extends JPanel{
    
    private int indexOfText=0;
    private String[] text={
             "You really thought I was going to finish making my game!"
            ,"                        But! My noobness is unbeatable!!"
            ,"                                                HAHAHAHA"
            ,"                                                     ..."
            ,"              I just thought of something really funny.."
            ,"                      what if I have YOU finish my game!"
            ,"                                It would serve you right"
            ,"                                               afterall,"
            ,"  you're the reason I had to do this in the first place!"
            ,"                               Think of this as revenge."
            ,"        btw... its really hard thats why I could't do it"
            ,"                         everything moves like lightning"
            ,"                                   so make sure to think"};
    
    public panel(){
        this.setBackground(Color.black);
        
        Font f= new Font(Font.SERIF,Font.ITALIC,35);
        Font f2= new Font(Font.SERIF,Font.ITALIC,15);
        JLabel jt= new JLabel(
             "                                                    HAHA");
        jt.setForeground(Color.white);
        jt.setFont(f);
        
        JLabel dir=new JLabel("go to next window");
        dir.setFont(f2);
        dir.setVisible(false);
        dir.setForeground(Color.white);
        
        JButton next=new JButton("next");
        next.setFont(f2);
        next.setLocation(200, 200);
        next.addActionListener((ActionEvent e)->{
            jt.repaint();
            if(indexOfText<text.length){
                jt.setText(text[indexOfText]);
                indexOfText++;
                }    
            else{
                CodeIt c=new CodeIt();
                c.start();
                dir.setVisible(true);
            }
        });
        
        Dimension d= new Dimension(300,150);
        this.add(Box.createVerticalStrut(300));
        this.add(jt);
        this.add(Box.createRigidArea(d));
        this.add(next);
        this.add(Box.createRigidArea(d));
        this.add(dir);
    }
    
}
/*
cheat sheet:
 attack 1 attack 4 walk 9 walk 9 walk 9 jump -9 jump -3 attack 2 jump -5 attack 2 attack 2 attack 2 walk 7 jump -4 attack 2 attack 2 attack 2 attack 2 
jump 3 Attack 2 jump 3 attack 2 jump 3 attack 2 jump 3 attack 2 jump 3 attack 2 jump 3 attack 2 jump 3 attack 2 jump 6 jump 6 attack 2 attack 2 attack 2 
attack 2 attack 2 attack 2 attack 2 attack 2 attack 2 jump -4 walk 5 attack 2 attack 2 attack 2 jump -3 walk -4 attack 1 walk 9 wait 100 attack 1 walk -3 
attack 4 walk -3 attack 4 wait 100 wait 100 jump -6 walk -9 attack 4 walk -9 attack 4 walk -9 attack 4 wait 25 walk 6 attack 4 walk 3 attack 4 wait 25 walk -3 
attack 4 walk -3 attack 4 walk -3 attack 4 walk -6 attack 4 walk -6 attack 4 walk -6 attack 4 walk -6 attack 4 walk -6 attack 4 walk -9 wait 100 

*/