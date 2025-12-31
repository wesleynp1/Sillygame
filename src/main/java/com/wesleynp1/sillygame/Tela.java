package com.wesleynp1.sillygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.wesleynp1.sillygame.objetos.ObjetoJogo;
import com.wesleynp1.sillygame.salas.Sala;

/*
*Descrição da Classe: a tela onde o jogo é pintado
*/
public class Tela extends JPanel{
        Sala sala;

        Tela(Sala sala,int width,int height){
            this.sala = sala;
            addKeyListener(sala);
            setPreferredSize(new Dimension(width,height));
            setBackground(Color.DARK_GRAY);
            setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
                if(!sala.equals(null)){
                    for(ObjetoJogo objetoJogo : sala.getObjetosJogo()){
                    objetoJogo.autoDesenhar(g2d);
                    
                }   
            }                                     
        }            
    }
