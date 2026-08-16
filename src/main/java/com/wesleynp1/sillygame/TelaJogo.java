package com.wesleynp1.sillygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import com.wesleynp1.sillygame.salas.Sala;

/**
* A tela onde o jogo é pintado. 
* Forneça uma Sala com os elementos a serem rederizados.
* Utilize o metodo repaint para atualizar a tela.
* @author Wesley Natan Pereira
*/
public class TelaJogo extends JPanel{
        private Sala sala;

        TelaJogo(Sala sala, int width, int height){
            this.sala = sala;
            setPreferredSize(new Dimension(width,height));
            setBackground(Color.DARK_GRAY);
            setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            sala.autoDesenhar((Graphics2D) g); 
        }            

        public Sala getSala(){
            return sala;
        }

        public void setSala(Sala sala){
            this.sala = sala;
        }
    }
