package com.wesleynp1.sillygame.salas;

import java.util.ArrayList;

import com.wesleynp1.sillygame.TecladoResposivo;
import com.wesleynp1.sillygame.objetos.ObjetoJogo;

import java.awt.event.KeyEvent;

public abstract class Sala{

    protected ArrayList<ObjetoJogo> objetosJogo = new ArrayList<ObjetoJogo>();

    public ArrayList<ObjetoJogo> getObjetosjogo(){
        return objetosJogo;
    }

    public void setObjetosJogo(ArrayList<ObjetoJogo> objetoJogos){
        this.objetosJogo = objetoJogos;
    }
    
    public void atualizaLogicaJogo(){
        for(ObjetoJogo objetoJogo : objetosJogo){
            objetoJogo.atualizarLogicaJogo();
        }
    }

    public ArrayList<ObjetoJogo> getObjetosJogo() {
        return objetosJogo;
    }

    
    public void botaoPressionado(KeyEvent e) {
        for(ObjetoJogo objetoJogo : objetosJogo){
            if(objetoJogo instanceof TecladoResposivo){
                ((TecladoResposivo)objetoJogo).teclaPressionada(e);
            }
        }
    }    
    
    public void botaoLiberado(KeyEvent e) {
        for(ObjetoJogo objetoJogo : objetosJogo){
            if(objetoJogo instanceof TecladoResposivo){
                ((TecladoResposivo)objetoJogo).teclaLiberada(e);
            }
        }
    }
}
