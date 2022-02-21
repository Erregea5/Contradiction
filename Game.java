/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.contradiction;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.Math;
import java.util.*;
import java.time.*;


/**
 *
 * @author Sebastian
 */
public class Game extends Thread{
    
    ArrayList<String> commands;
    public Game(ArrayList<String> in){
        commands=in;
    }
    
    public void start(){
        JFrame jf=new JFrame("Game");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setSize(1400, 750);
        
        paint p=new paint(commands); 
        Container cont=jf.getContentPane();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        cont.add(p);
    }
    
}

class paint extends JPanel{
    
    public int[] xvils={0, (74+145)/2, 216, 287, (-74-145)/2, -216, -287};
    public Rectangle[] rect;
    public Rectangle bound;
    public int posx;
    public int posy;
    final int step=15;
    int laserSpeed=2;
    ArrayList<String> commands;
    boolean isAlive;
    boolean thereAreVillagers;
    ArrayList<int[]> lasers;
    int[] turns;
    int dead=0;
    Rectangle[] deadrect;
    int laserIndex=0;
    
    
    public paint(ArrayList<String> in){
        lasers=new ArrayList<int[]>();
        isAlive=true;
        thereAreVillagers=true;
        System.out.println(in);
        commands=in;
        this.setBackground(Color.white);
        turns=new int[14];
        for(int i=0;i<turns.length;i++){
            turns[i]=i;
        }
        rect=new Rectangle[14];
        for(int iter=0;iter<xvils.length;iter++){    
            int vx=xvils[iter];
            int vy=(int)Math.sqrt(Math.abs((350*350)-(vx*vx)));
            rect[iter]=new Rectangle(vx+650,vy+350,70,70);
            rect[iter+7]=new Rectangle(-vx+650,-vy+350,70,70);
        }
        
        deadrect=new Rectangle[14];
        posx=650;
        posy=350;
        bound=new Rectangle(posx,posy,75,75);
        
        JButton jb= new JButton("start");
        jb.addActionListener((ActionEvent a)->{
            this.mechanics();
            jb.setVisible(false);
        });
        add(jb);
        
        
    }
    
    
    public void mechanics(){
        
        //System.out.println(commands.size());
        
        int limit=0;
        while(isAlive&&thereAreVillagers&&(commands.size()>limit||400>limit)){
            //System.out.println(commands.get(limit));
            String action=commands.size()>limit?commands.get(limit):"zero";
            if(action.equals("up"))posy-=step;
            if(action.equals("down"))posy+=step;
            if(action.equals("right"))posx+=step;
            if(action.equals("left"))posx-=step;
            if(action.equals("one"))lasers(posx+(75/2),posy+(75/2),0,1,1);
            if(action.equals("two"))lasers(posx+(75/2),posy+(75/2),-1,0,1);
            if(action.equals("three"))lasers(posx+(75/2),posy+(75/2),1,0,1);
            if(action.equals("four"))lasers(posx+(75/2),posy+(75/2),0,-1,1);
            if(action.equals("five"))isAlive=false;
            if(action.equals("zero"));
            bound=new Rectangle(posx,posy,75,75);
            for(int uj=0;uj<lasers.size();uj++){
                int[] l=lasers.get(uj);
                if(l[4]==1)laserSpeed=6;else laserSpeed=2;
                int[] next={l[0]+(l[2]*laserSpeed),l[1]+(l[3]*laserSpeed),l[2],l[3],l[4]};
                
                if((next[4]==2&&bound.contains(next[0],next[1]))){
                    isAlive=false;
                }
                if(next[4]==1)
                for(int o=0;o<rect.length;o++){
                    if(rect[o]==null)continue;
                    double q=rect[o].getMaxX();
                    double w=rect[o].getMaxY();
                    double e=rect[o].getMinX();
                    double r=rect[o].getMinY();
                    if(bound.contains(q,w)||bound.contains(q,r)||bound.contains(e,w)||bound.contains(e,r)){
                        isAlive=false;
                    }
                    if(rect[o].getMaxX()>=next[0]&&rect[o].getMinX()<=next[0]){
                        if(rect[o].contains(next[0],next[1])){
                            turns[laserIndex]=0;
                            deadrect[o]=rect[o];
                            dead++;
                            rect[o]=null;
                        }
                    }
                }
                lasers.set(uj, next);
            }
            
            if(turns[laserIndex]!=0){
                int dx=0;
                int dy=0;
                dx=(laserIndex<7&&laserIndex>3)||(laserIndex>9)?1:-1;
                dy=laserIndex<7?-1:1;
                if(laserIndex==1||laserIndex==7)dx=0;
                if(laserIndex%3==0)dy=0;
                if(rect[laserIndex]!=null)
                lasers((int)rect[laserIndex].getCenterX(),(int)rect[laserIndex].getCenterY(),dx,dy, 2);
            }
            System.out.print(limit);
            laserIndex++;
            if(laserIndex==14)laserIndex=0;
            if(dead==14)thereAreVillagers=false;
            try{Thread.sleep(50);}
            catch(Exception e){
                System.err.println(e);
            }
            limit++;
            this.update(this.getGraphics());
        }
        if(!thereAreVillagers){
            Ending end = new Ending();
            end.start();
        }
        else{
            JLabel jl=new JLabel("TRY AGAIN");
            Font f=new Font(Font.SERIF,Font.BOLD,50);
            jl.setFont(f);
            jl.setForeground(Color.black);
            add(jl);
        }
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        BufferedImage enemy=null;
        BufferedImage villagers=null;
        try{
            enemy=ImageIO.read(new File("C:\\Users\\Sebastian\\OneDrive\\Pictures\\enemy.png"));
            villagers=ImageIO.read(new File("C:\\Users\\Sebastian\\OneDrive\\Pictures\\villagers.png"));
        }
        catch(Exception io){
            System.err.print(io);
        }
        
        g.drawImage(enemy, posx, posy, posx+75, posy+75, 57, 1, 125, 76, null);
        
        for(int i=0;i<rect.length;i++){
            if(rect[i]!=null)
            g.drawImage(villagers, (int)rect[i].getX(), (int)rect[i].getY(), (int)rect[i].getX()+70, (int)rect[i].getY()+70, 70, 1, 140, 72, null);
        }
        
        for(int u=0;u<lasers.size();u++){
                int[] l=lasers.get(u);
                
                g.setColor(l[4]==1?Color.red:Color.green);
                g.fillRect(l[0], l[1], 20, 20);
                //int[] next={l[0]+(l[2]*laserSpeed),l[1]+(l[3]*laserSpeed),l[2],l[3],l[4]};
        }
        
        for(int o=0;o<deadrect.length;o++){
            if(deadrect[o]==null)continue;
            g.fillRect((int)deadrect[o].getCenterX()-15, (int)deadrect[o].getMaxY(), 35, 85);
            g.fillRect((int)deadrect[o].getMinX(), (int)deadrect[o].getMaxY()-10, 70, 30);
        }
        
        
        
    }
    public void lasers(int x,int y,int dx,int dy, int color){
        lasers.add(new int[]{x,y,dx,dy,color});
    }
}
