package com.wesleynp1.sillygame.salas;

import java.util.ArrayList;
import java.util.List;
import com.wesleynp1.sillygame.SillyGame;
import com.wesleynp1.sillygame.objetos.Aviso;
import com.wesleynp1.sillygame.objetos.Circulo;
import com.wesleynp1.sillygame.objetos.ObjetoJogo;

public class SalaChuvaCirc extends Sala{
    private int nDeCirc;

    public SalaChuvaCirc(int d){
        this.nDeCirc = d;
        this.objetosJogo = criaListaObjetosJogo();
    }

    @Override
    protected List<ObjetoJogo> criaListaObjetosJogo(){
        ArrayList<ObjetoJogo> listaObjetosJogo = new ArrayList<ObjetoJogo>();

        listaObjetosJogo.add(new Aviso("PRESIONE ESPAÇO", SillyGame.WIDTH_TELA/2-124, SillyGame.HEIGHT_TELA/2));

        for(int i = 0; i < this.nDeCirc; i++){
            listaObjetosJogo.add(new Circulo());
        }

        return listaObjetosJogo;
    }
}
