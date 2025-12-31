package com.wesleynp1.sillygame.salas;

import java.util.ArrayList;

import com.wesleynp1.sillygame.TecladoResposivo;
import com.wesleynp1.sillygame.objetos.ObjetoJogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Sala implements KeyListener{

    protected ArrayList<ObjetoJogo> objetosJogo = new ArrayList<ObjetoJogo>();
    public abstract void criaAndAddObjetosJogo();
    
    public void atualizaLogicaJogo(){
        for(ObjetoJogo objetoJogo : objetosJogo){
            objetoJogo.atualizarLogicaJogo();
        }
    }

    public ArrayList<ObjetoJogo> getObjetosJogo() {
        return objetosJogo;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(ObjetoJogo objetoJogo : objetosJogo){
            if(objetoJogo instanceof TecladoResposivo){
                ((TecladoResposivo)objetoJogo).teclaPressionada(e);
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        for(ObjetoJogo objetoJogo : objetosJogo){
            if(objetoJogo instanceof TecladoResposivo){
                ((TecladoResposivo)objetoJogo).teclaLiberada(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
