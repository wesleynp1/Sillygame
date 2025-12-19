package com.wesleynp1.sillygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/*
*Descrição da Classe: a tela onde o jogo é pintado
*/
public class Tela extends JPanel{
        ArrayList<ObjetoJogo> objetosJogo;

        Tela(ArrayList<ObjetoJogo> objetosJogo,int width,int height){
            this.objetosJogo = objetosJogo;
            setPreferredSize(new Dimension(width,height));
            setBackground(Color.DARK_GRAY);
            setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for(ObjetoJogo objetoJogo : this.objetosJogo){
                objetoJogo.autoDesenhar(g2d);
            }                            
        }            
    }
