package com.wesleynp1.sillygame.salas;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.wesleynp1.sillygame.SillyGame;
import com.wesleynp1.sillygame.objetos.Aviso;
import com.wesleynp1.sillygame.objetos.Circulo;
import com.wesleynp1.sillygame.objetos.ObjetoJogo;

public class SalaChuvaCirc extends Sala{
    private int nDeCirc;
    
    public SalaChuvaCirc(int nDeCirc){
        this.nDeCirc = nDeCirc;

        ArrayList<ObjetoJogo> listaObjetosJogo = new ArrayList<ObjetoJogo>();
        

        listaObjetosJogo.add(new Aviso("PRESIONE ESPAÇO", SillyGame.WIDTH_TELA/2-124, SillyGame.HEIGHT_TELA/2));

        for(int i = 0; i < this.nDeCirc; i++){
            listaObjetosJogo.add(new Circulo());
        }

        this.setObjetosJogo(listaObjetosJogo);
    }

    @Override
    public void atualizaLogicaJogo() {
        super.atualizaLogicaJogo();

    }

    @Override
    protected void RespostaTeclaLiberada(KeyEvent e) {
        if (e.getKeyCode() ==KeyEvent.VK_A){
            adicionarObjeto(new Circulo());
        }
    }
}
