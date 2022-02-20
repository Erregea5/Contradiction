/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.contradiction;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sebastian
 */
public class Ending extends Thread{

        public void start() {
        JFrame jf=new JFrame("Contradiction");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        //jf.setLayout(new BoxLayout());
        jf.setSize(500, 500);
        
        endPanel p=new endPanel(); 
        Container cont=jf.getContentPane();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        cont.add(p);
    }

}

class endPanel extends JPanel{
    
    private int indexOfText=0;
    private String[] text={
             "                                                             You actually did that!"
            ,"                                                                     you murderer!!"
            ,"those villagers just wanted to get rid\n of the monster who's been terrorising them"
            ,"                                                                                ..."
            ,"                                There was a reason I didn't finish making this game"
            ,"                                     And it's not because I was too much of a noob!"
            ,"                The reality is that I couldn't bear to give power to such a monster"
            ,"                                                          But you finished his code"
            ,"                                       now the game is complete... but at what cost"
            ,"                                               I just hope you can forgive yourself"};
    
    public endPanel(){
        this.setBackground(Color.black);
        
        Font f= new Font(Font.SERIF,Font.ITALIC,35);
        Font f2= new Font(Font.SERIF,Font.ITALIC,15);
        JLabel jt= new JLabel(
             "                                                      wait...");
        jt.setForeground(Color.white);
        jt.setFont(f);
        
        JButton next=new JButton("next");
        next.setFont(f2);
        next.setLocation(200, 200);
        next.addActionListener((ActionEvent e)->{
            jt.repaint();
            if(indexOfText<text.length){
                jt.setText(text[indexOfText]);
                indexOfText++;
            }    
            else next.setVisible(false);
        });
        
        Dimension d= new Dimension(300,150);
        this.add(Box.createVerticalStrut(300));
        this.add(jt);
        this.add(Box.createRigidArea(d));
        this.add(next);
        this.add(Box.createRigidArea(d));
    }
    
}