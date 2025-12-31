package com.wesleynp1.sillygame.objetos;

import java.awt.Graphics2D;

/**
 * Um objeto que pode ser pintado pela tela
 */
public interface ObjetoJogo{
        public void autoDesenhar(Graphics2D g2d);
        public void atualizarLogicaJogo();
    }