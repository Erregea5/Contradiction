/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contradiction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Math;

public class CodeIt extends Thread{
    @Override
    public void start(){
        JFrame jf=new JFrame("CodeIt");
        //jf.setAlwaysOnTop(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setSize(500, 500);
        
        CodePanel cp=new CodePanel(); 
        Container cont=jf.getContentPane();
        cp.setLayout(new BoxLayout(cp,BoxLayout.Y_AXIS));
        cont.add(cp);
    }
}

class CodePanel extends JPanel {
    
    private String helpText="""
                            Well Well, look who's the one who needs help now
                            There's only four functions you can code:
                            walk, jump, wait, and attack
                            after each function you must put a space, a number between -10 and 10, and another space
                            the walk function makes you go left and right
                            the jump function makes you go up and down
                            the wait funtion makes you wait
                            the attack function shoots lasers depending on the number given
                            1 is up, 2 is left, 3 is right, 4 is down, 5 is death
                            you cannot exit the borders
                            now try to finish making my game
                            goodluck
                                """;
    public ArrayList commands;
    
    public CodePanel(){
        
        commands=new ArrayList<String>();
        
        this.setBackground(Color.black);
        Font font= new Font(Font.SERIF,Font.ITALIC,30);
        Font font2= new Font(Font.SERIF,Font.ITALIC,20);
        JLabel jl=new JLabel("                           Code here                               and don't be afraid to call for help");
        jl.setForeground(Color.white);
        jl.setFont(font);
        
        JTextArea jtf=new JTextArea();
        jtf.setFont(font2);
        
        JLabel notReady=new JLabel("notReady");
        notReady.setForeground(Color.white);
        notReady.setFont(font2);
        notReady.setVisible(false);
        
        JButton jb=new JButton("Enter");
        jb.addActionListener((ActionEvent a)->{
            String[] s= jtf.getText().toLowerCase().trim().split("\n");
            String fs="";
            for(String sf:s){
                fs+=sf;
            }
            String[] f=fs.split(" ");
            try{
            boolean check=true;
            for(int i=0;i<f.length-1;i++){
                String all="-1-2-3-4-5-6-7-8-90123456789helpwalkjumpattackwait1005025\n";
                String nums="-1-2-3-4-5-6-7-8-901234567891005025";
                String in=f[i];
                System.out.println(in);
                if(!all.contains(in)){
                    check=false;
                }
                String next=i<f.length-1?f[i+1]:"qwaszx";
                if(in.equals("help")){
                    jtf.setText(helpText);
                    break;
                }
                
                if((!next.equals("qwaszx"))&&nums.contains(next)){
                    int num=Integer.parseInt(next)%10;
                    if(in.equals("walk"))walk(num);
                    if(in.equals("jump"))jump(num);
                    if(in.equals("attack"))attack(num);
                    if(in.equals("wait"))waits(Integer.parseInt(next));
                    i++;
                }
                
            }
            notReady.setVisible(!check);
            if(!notReady.isVisible()){
                Game game=new Game(commands);
                game.start();
            }
            }
            catch(Exception e){
                System.err.println(e);
            }
        });
        this.add(jl);
        this.add(Box.createHorizontalStrut(100));
        this.add(jtf);
        
        this.add(notReady);
        this.add(jb);
        
    }
    public void walk(int in){
        for(int i=0;i<Math.abs(in);i++){
            commands.add(in>0?"right":"left");
        }
    }
    public void jump(int in){
        for(int i=0;i<Math.abs(in);i++){
            commands.add(in>0?"up":"down");
        }
    }
    public void attack(int in){
        commands.add(in==1?"one":in==2?"two":in==3?"three":in==4?"four":"zero");
    }
    public void waits(int in){
        for(int i=0;i<Math.abs(in);i++){
            commands.add("zero");
        }
    }
}