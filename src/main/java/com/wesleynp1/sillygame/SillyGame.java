package com.wesleynp1.sillygame;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.wesleynp1.sillygame.salas.Sala;
import com.wesleynp1.sillygame.salas.SalaChuvaCirc;

import java.lang.reflect.InvocationTargetException;

public class SillyGame implements Runnable{

    public static final int WIDTH_TELA = 1024;
    public static final int HEIGHT_TELA = 768;

    private Tela telaJogo;
    boolean sairJogo = false;
    ArrayList<Sala> salas = new ArrayList<Sala>();
    int NdaSalaAtual = 0;
    int milisecondsPerCiclo = 1000/60;//60 FPS
    
    public static void main(String[] args) {
        new SillyGame();
    }
    
    SillyGame(){
        criaSalas();
        iniciaInterfaceGrafica();        
        iniciaLoopPrincipalDoJogo();
    }

    private void iniciaInterfaceGrafica(){
        JFrame JanelaDoJogo = new JFrame("Silly Game - by Wesley Natan Pereira");
        telaJogo = new Tela(this.salas.get(NdaSalaAtual), WIDTH_TELA, HEIGHT_TELA);

        JanelaDoJogo.add(this.telaJogo);
        JanelaDoJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JanelaDoJogo.pack();
        JanelaDoJogo.setVisible(true);
        JanelaDoJogo.setResizable(false);
        JanelaDoJogo.setLocationRelativeTo(null);
    }

    private void criaSalas(){
        salas.add(new SalaChuvaCirc());
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
        for(Sala sala : salas){
            sala.atualizaLogicaJogo();
        }
    }
}
