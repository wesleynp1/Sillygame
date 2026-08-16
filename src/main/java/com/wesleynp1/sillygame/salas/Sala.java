package com.wesleynp1.sillygame.salas;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.wesleynp1.sillygame.interfaces.TecladoResposivo;
import com.wesleynp1.sillygame.interfaces.Colidivel;

import com.wesleynp1.sillygame.objetos.ObjetoJogo;


public abstract class Sala {

    private List<ObjetoJogo> objetosParaAdicionar;
    private List<ObjetoJogo> objetosParaRemover;
    
    protected List<ObjetoJogo> objetosJogo;
    protected List<Colidivel> objetosColidiveis;

    Sala(){
        objetosJogo = new ArrayList<ObjetoJogo>();
        objetosColidiveis = new ArrayList<Colidivel>();
        objetosParaAdicionar = new ArrayList<ObjetoJogo>();
        objetosParaRemover = new ArrayList<ObjetoJogo>();        
    }

    public List<ObjetoJogo> getObjetosJogo() {
        return objetosJogo;
    }
    
    public void setObjetosJogo(List<ObjetoJogo> objetosJogo) {
        this.objetosJogo = objetosJogo;
        aoAlterarObjetosJogo();        
    }

    public void adicionarObjeto(ObjetoJogo obj){
        this.objetosParaAdicionar.add(obj);
    }

    public void removerObjeto(ObjetoJogo obj){
        this.objetosParaRemover.add(obj);
        aoAlterarObjetosJogo();
    }

    private void aoAlterarObjetosJogo(){
        ordernarObjetos();
        atualizaListaDeColidiveis();
    }

    private void atualizaListaDeColidiveis(){
        objetosColidiveis = new ArrayList<Colidivel>();

        for(ObjetoJogo objetoJogo : objetosJogo){
            if(objetoJogo instanceof Colidivel){
                this.objetosColidiveis.add((Colidivel)objetoJogo);
            }
        }
    }

    private void ordernarObjetos(){
        objetosJogo.sort((obj0, obj1) -> Integer.compare(obj0.getZ(), obj1.getZ()));
    }
    
    public void atualizaLogicaJogo(){
        for(ObjetoJogo objetoParaRemover : objetosParaRemover){
            objetosJogo.remove(objetoParaRemover);
            objetosParaRemover = new ArrayList<ObjetoJogo>();
            aoAlterarObjetosJogo();
        }

        for(ObjetoJogo objetoParaAdicionar : objetosParaAdicionar){
            objetosJogo.add(objetoParaAdicionar);
            objetosParaAdicionar = new ArrayList<ObjetoJogo>();
            aoAlterarObjetosJogo();
        }

        for(ObjetoJogo objetoJogo : objetosJogo){
            objetoJogo.atualizarLogicaJogo();
            this.notificaObjetosColisao();
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
            this.RespostaTeclaPressionada(e);
        }
    }    
    
    public void botaoLiberado(KeyEvent e) {
        for(ObjetoJogo objetoJogo : objetosJogo){
            if(objetoJogo instanceof TecladoResposivo){
                ((TecladoResposivo)objetoJogo).teclaLiberada(e);
            }            
        }

        this.RespostaTeclaLiberada(e);
    }

    private void notificaObjetosColisao(){
        
        for(int i=0; i<this.objetosColidiveis.size(); i++){
            for(int j=i+1; j<this.objetosColidiveis.size(); j++){
                if(houveColisao(objetosColidiveis.get(i), objetosColidiveis.get(j))){
                    objetosColidiveis.get(i).aoColidir(objetosColidiveis.get(j));
                    objetosColidiveis.get(j).aoColidir(objetosColidiveis.get(i));
                }
            }
        }
        
    }

    private boolean houveColisao(Colidivel Col1, Colidivel Col2){

        ObjetoJogo obj1 = (ObjetoJogo) Col1;
        ObjetoJogo obj2 = (ObjetoJogo) Col2;

        if (
            obj1.getX() + obj1.getWidth()  < obj2.getX() || 
            obj1.getX() + obj1.getWidth()  < obj2.getX() ||
            obj1.getY() + obj1.getHeight() < obj2.getY() || 
            obj1.getY() + obj1.getHeight() < obj2.getY()
        ){
            return false;
        }

        int catetoA = Math.abs( (obj1.getX()+(obj1.getWidth() /2)) - (obj2.getX()+(obj2.getWidth() /2)) );
        int catetoB = Math.abs( (obj1.getY()+(obj1.getHeight()/2)) - (obj2.getY()+(obj2.getHeight()/2)) );

        int distancia = (int)Math.sqrt((catetoA*catetoA) + (catetoB*catetoB));

        int altura = obj1.getHeight();
        
        return distancia < altura;
    }


    /**
     * Sobreescreva este método para adicionar resposta de teclado à sala
     * 
     * @param e
     */
    protected void RespostaTeclaPressionada(KeyEvent e){
        
    }

    /**
     * Sobreescreva este método para adicionar resposta de teclado à sala
     * 
     * @param e
     */
    protected void RespostaTeclaLiberada(KeyEvent e){
        
    }
}
