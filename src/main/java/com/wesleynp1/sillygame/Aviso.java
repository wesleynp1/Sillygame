package com.wesleynp1.sillygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Aviso implements ObjetoJogo{
    private String texto;
    private int x,y;

    Aviso(String texto,int x, int y){
        this.texto = texto;
        this.x = x;
        this.y = y;
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