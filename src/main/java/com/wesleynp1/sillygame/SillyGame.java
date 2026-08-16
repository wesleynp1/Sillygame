package com.wesleynp1.sillygame;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.wesleynp1.sillygame.salas.Sala;
import com.wesleynp1.sillygame.salas.SalaChuvaCirc;

public class SillyGame implements Runnable,KeyListener {

    public static final int WIDTH_TELA = 1024;
    public static final int HEIGHT_TELA = 768;

    private TelaJogo telaJogo;
    boolean sairJogo = false;
    ArrayList<Sala> salas = new ArrayList<Sala>();
    Sala salaAtual;
    int MILISECONDS_PER_CICLE = 1000/60;//60 FPS
    
    public static void main(String[] args) {
        new SillyGame();
    }
    
    SillyGame(){
        criaSalas();
        iniciaInterfaceGrafica();
        iniciaLoopPrincipalDoJogo();
    }

    private void criaSalas(){
        salas.add(new SalaChuvaCirc(10));
        salas.add(new SalaChuvaCirc(25));
        salas.add(new SalaChuvaCirc(50));
        salaAtual = salas.get(0);
    }

    private void iniciaInterfaceGrafica(){
        JFrame JanelaDoJogo = new JFrame("Silly Game - by Wesley Natan Pereira");
        telaJogo = new TelaJogo(salaAtual, WIDTH_TELA, HEIGHT_TELA);
        telaJogo.addKeyListener(this);

        JanelaDoJogo.add(this.telaJogo);
        JanelaDoJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JanelaDoJogo.pack();
        JanelaDoJogo.setVisible(true);
        JanelaDoJogo.setResizable(false);
        JanelaDoJogo.setLocationRelativeTo(null);
    }

    private void iniciaLoopPrincipalDoJogo(){
        Thread loopPrincipal = new Thread(this, "LoopPrincipal");
        loopPrincipal.start();
    }

    @Override
    public void run() {
        while(!sairJogo){   
            long inicio = System.currentTimeMillis();

            System.out.println("LOGICA INICIO: " + inicio);
            salaAtual.atualizaLogicaJogo();
            System.out.println("LOGICA DURAÇÃO: "+(System.currentTimeMillis() - inicio));

            try {
                SwingUtilities.invokeAndWait(() -> {
                    this.telaJogo.paintImmediately(0,0,WIDTH_TELA,HEIGHT_TELA);
                    this.telaJogo.revalidate();
                });
            } catch (InvocationTargetException | InterruptedException e) {                
                e.printStackTrace();
            }

            try {    
                long duração = (System.currentTimeMillis() - inicio);

                if(duração < MILISECONDS_PER_CICLE){
                    Thread.sleep(MILISECONDS_PER_CICLE - duração);
                }
                
            } catch (InterruptedException | IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        salaAtual.botaoPressionado(e);
        trocaTela(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        salaAtual.botaoLiberado(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void trocaTela(KeyEvent e) {
        int[] teclasTrocaTela = {
            KeyEvent.VK_NUMPAD1,
            KeyEvent.VK_NUMPAD2,
            KeyEvent.VK_NUMPAD3,
            KeyEvent.VK_NUMPAD4,
        };

        for(int i=0;i<teclasTrocaTela.length;i++){
            if(e.getKeyCode() == teclasTrocaTela[i] && i<salas.size()){
                salaAtual = salas.get(i);
                telaJogo.setSala(salaAtual);
            }
        }
    }

}

