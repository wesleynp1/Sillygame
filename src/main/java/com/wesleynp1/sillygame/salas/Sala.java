package com.wesleynp1.sillygame.salas;

import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.wesleynp1.sillygame.TecladoResposivo;
import com.wesleynp1.sillygame.objetos.ObjetoJogo;


public abstract class Sala{

    protected List<ObjetoJogo> objetosJogo;    

    protected abstract List<ObjetoJogo> criaListaObjetosJogo();

    public List<ObjetoJogo> getObjetosJogo() {
        return objetosJogo;
    }

    public void setObjetosJogo(List<ObjetoJogo> objetosJogo) {
        this.objetosJogo = objetosJogo;
        ordernarObjetos();
    }

    public void adicionarObjeto(ObjetoJogo obj){
        this.objetosJogo.add(obj);
        ordernarObjetos();        
    }

    public void removerObjeto(ObjetoJogo obj){
        this.objetosJogo.remove(obj);
        ordernarObjetos();
    }

    private void ordernarObjetos(){
        objetosJogo.sort((obj0, obj1) -> Integer.compare(obj0.getZ(), obj1.getZ()));
    }
    
    public void atualizaLogicaJogo(){
        for(ObjetoJogo objetoJogo : objetosJogo){
            objetoJogo.atualizarLogicaJogo();
        }
    }

    public void autoDesenhar(Graphics2D g2d){
        for(ObjetoJogo objetoJogo : objetosJogo){
            objetoJogo.autoDesenhar(g2d);
        }
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
