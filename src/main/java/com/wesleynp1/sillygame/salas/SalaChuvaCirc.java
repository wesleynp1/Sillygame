package com.wesleynp1.sillygame.salas;

import com.wesleynp1.sillygame.SillyGame;
import com.wesleynp1.sillygame.objetos.Aviso;
import com.wesleynp1.sillygame.objetos.Circulo;

public class SalaChuvaCirc extends Sala{
    public SalaChuvaCirc(){
        criaAndAddObjetosJogo();
    }

    @Override
    public void criaAndAddObjetosJogo() {
        objetosJogo.add(new Aviso("PRESIONE ESPAÇO", SillyGame.WIDTH_TELA/2-124, SillyGame.HEIGHT_TELA/2));

        for(int i = 0; i < 900; i++){
            this.objetosJogo.add(new Circulo());
        }
    }
}
