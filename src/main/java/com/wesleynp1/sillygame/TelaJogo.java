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
public class TelaJogo extends JPanel{
        private Sala sala;

        public Sala getSala(){
            return sala;
        }

        public void setSala(Sala sala){
            this.sala = sala;            
        }

        TelaJogo(Sala sala, int width, int height){
            this.sala = sala;
            setPreferredSize(new Dimension(width,height));
            setBackground(Color.DARK_GRAY);
            setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            sala.getObjetosJogo().sort((obj0, obj1) -> (Integer.compare(obj0.getZ(), obj1.getZ()))*-1);

            for(ObjetoJogo objetoJogo : sala.getObjetosJogo()){
                objetoJogo.autoDesenhar(g2d);
            }                                                 
        }            
    }
