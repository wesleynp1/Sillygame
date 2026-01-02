package com.wesleynp1.sillygame.objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.wesleynp1.sillygame.SillyGame;
import com.wesleynp1.sillygame.TecladoResposivo;

/**
 * Descrição da Classe: circulo sem física que saltita na tela, cor e velocidade aleatórias, perde velocidado ao tocar as bordas,
 * velocidade alterada ao pressionar espaço
 */
public class Circulo extends ObjetoJogo implements TecladoResposivo{
    int velocX, velocY;
    Color cor;

    public Circulo() {
        super(
            (int)(Math.random()*(SillyGame.WIDTH_TELA-32)), 
            (int)(Math.random()*(SillyGame.HEIGHT_TELA-32)),
            Math.round((float) (Math.random()*9))
        );
        int maxVelocCirv = 16;

        Color[] cores = {
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.YELLOW,
            Color.RED,
            Color.BLUE,
            Color.LIGHT_GRAY,
            Color.WHITE,
            Color.DARK_GRAY,
            new Color(55,10,10),            
            new Color(0,0,100)
        };

        this.velocX = (int)((Math.random()+0.1)*maxVelocCirv)*(Math.random()>0.5 ? -1 : 1);
        this.velocY = (int)((Math.random()+0.1)*maxVelocCirv)*(Math.random()>0.5 ? -1 : 1);
        this.cor = cores[(int)(Math.random()*cores.length-1)];
    }

    

    @Override
    public void autoDesenhar(Graphics2D g2d) {
        g2d.setColor(this.cor);
        g2d.fillArc(x, y, 32, 32, 0, 360);
        g2d.setColor(Color.BLACK);
        g2d.drawArc(x, y, 32, 32, 0, 360);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString(String.valueOf(z), x+8, y+24);
    }

    @Override
    public void atualizarLogicaJogo() {
        this.x += this.velocX;
        this.y += this.velocY;

        decidirDirecao();
    }

    private void decidirDirecao() {
        if (x >= SillyGame.WIDTH_TELA-32 && velocX > 0) {
            velocX = (int) (velocX * (-1) * 0.8);
        } else if (x <= 0 && velocX < 0) {
            velocX = (int) (velocX * (-1) * 0.8);
        }

        if (y >= SillyGame.HEIGHT_TELA-32 && velocY > 0) {
            velocY = (int) (velocY * (-1) * 0.8);
        } else if (y <= 0 && velocY < 0) {
            velocY = (int) (velocY * (-1) * 0.8);
        }
    }

    @Override
    public void teclaLiberada(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            this.velocX = (int) ((Math.random()+0.1) * 8)*(Math.random()>0.5 ? -1 : 1);
            this.velocY = (int) ((Math.random()+0.1) * 8)*(Math.random()>0.5 ? -1 : 1);
        }
    }

    @Override
    public void teclaPressionada(KeyEvent e) {}
}