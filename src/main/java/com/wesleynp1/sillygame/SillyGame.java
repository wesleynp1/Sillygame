package com.wesleynp1.sillygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SillyGame implements Runnable{

    Tela tela = new Tela();
    boolean sairJogo = false;
    ObjetoJogo[] objetosJogo;
    int milisecondsPerFps = 1000/60;//60 FPS
    
    public static void main(String[] args) {
        new SillyGame();
    }
    
    SillyGame(){
        iniciaInterfaceGrafica();
        criaObjetosJogo(); 
        iniciaLoopPrincipalDoJogo();   
    }

    private void iniciaInterfaceGrafica(){
        JFrame Janela = new JFrame("Silly Game - by Wesley Natan Pereira");       

        Janela.add(this.tela);
        Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Janela.pack();
        Janela.setVisible(true);
        Janela.setResizable(false);
        Janela.setLocationRelativeTo(null);
    }

    private void criaObjetosJogo(){
        int NdeCirculos = 500;
        int maxVelocCirv = 8;
        Color[] cores = {
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.YELLOW,
            Color.RED,
            Color.BLUE,
            Color.LIGHT_GRAY,
            Color.WHITE,
            new Color(55,10,10),
            new Color(0,0,0),
            new Color(0,0,100)
        };

        this.objetosJogo = new ObjetoJogo[NdeCirculos];

        for(int i = 0; i < NdeCirculos; i++){

            this.objetosJogo[i] = new Circulo(
                (int)(Math.random()*(this.tela.getWidth()-32)),
                (int)(Math.random()*(this.tela.getWidth()-32)),
                (int)(Math.random()*maxVelocCirv)*(Math.random()>0.5 ? -1 : 1),
                (int)(Math.random()*maxVelocCirv)*(Math.random()>0.5 ? -1 : 1),
                cores[(int)(Math.random()*cores.length-1)]);

                tela.addKeyListener((Circulo)this.objetosJogo[i]);
        }
    }

    private void iniciaLoopPrincipalDoJogo(){
        Thread loopPrincipal = new Thread(this, "LoopPrincipal");
        loopPrincipal.setPriority(Thread.MAX_PRIORITY);
        loopPrincipal.start();
    }

    @Override
    public void run() {
        
        while(!sairJogo){   
            Long inicio = System.currentTimeMillis();
            atualizaLogicaJogo();       

            try {                                
                SwingUtilities.invokeAndWait(() -> tela.repaint());
                if(milisecondsPerFps > (inicio-System.currentTimeMillis())){
                    Thread.sleep( milisecondsPerFps - (inicio - System.currentTimeMillis()) );                    
                }
            } catch (InterruptedException | InvocationTargetException e){
                e.printStackTrace();
            }            
        }
    }

    private void atualizaLogicaJogo() {
        for(ObjetoJogo objetoJogo : objetosJogo){
            objetoJogo.atualizarLogicaJogo();
        }
    }

    private class Tela extends JPanel{        

        Tela(){
            setPreferredSize(new Dimension(1024,768));
            setBackground(Color.DARK_GRAY);
            setFocusable(true);                
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString("PRESIONE ESPAÇO", this.getWidth()/2-124, this.getHeight()/2);

            for(ObjetoJogo objetoJogo : objetosJogo){
                objetoJogo.autoDesenhar(g2d);
            }                            
        }            
    }

}
