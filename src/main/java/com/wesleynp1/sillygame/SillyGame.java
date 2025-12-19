package com.wesleynp1.sillygame;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;

public class SillyGame implements Runnable{

    public static final int WIDTH_TELA = 1024;
    public static final int HEIGHT_TELA = 768;

    private Tela telaJogo;
    boolean sairJogo = false;
    ArrayList<ObjetoJogo> objetosJogo = new ArrayList<ObjetoJogo>();
    int milisecondsPerCiclo = 1000/60;//60 FPS
    
    public static void main(String[] args) {
        new SillyGame();
    }
    
    SillyGame(){
        iniciaInterfaceGrafica();
        criaAndAddObjetosJogo();
        iniciaLoopPrincipalDoJogo();
    }

    private void iniciaInterfaceGrafica(){
        JFrame JanelaDoJogo = new JFrame("Silly Game - by Wesley Natan Pereira"); 
        telaJogo = new Tela(this.objetosJogo, WIDTH_TELA, HEIGHT_TELA);

        JanelaDoJogo.add(this.telaJogo);
        JanelaDoJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JanelaDoJogo.pack();
        JanelaDoJogo.setVisible(true);
        JanelaDoJogo.setResizable(false);
        JanelaDoJogo.setLocationRelativeTo(null);
    }

    private void criaAndAddObjetosJogo(){
        objetosJogo.add(new Aviso("PRESIONE ESPAÇO", WIDTH_TELA/2-124, HEIGHT_TELA/2));

        for(int i = 0; i < 800; i++){
            Circulo novoCirculo = new Circulo();
            this.telaJogo.addKeyListener(novoCirculo);
            this.objetosJogo.add(novoCirculo);
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
                SwingUtilities.invokeAndWait(() -> this.telaJogo.repaint());
                long tempoRestanteAteProximoCiclo = milisecondsPerCiclo - (inicio - System.currentTimeMillis());
                if(tempoRestanteAteProximoCiclo>0){
                    Thread.sleep(tempoRestanteAteProximoCiclo);
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
}
