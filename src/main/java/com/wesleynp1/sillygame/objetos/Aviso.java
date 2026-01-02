package com.wesleynp1.sillygame.objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Aviso extends ObjetoJogo{
    private String texto;

    public Aviso(String texto,int x, int y){
        super(x, y,0);
        this.texto = texto;
    }

    @Override
    public void autoDesenhar(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString(texto, x, y);
    }

    @Override
    public void atualizarLogicaJogo() {}
}