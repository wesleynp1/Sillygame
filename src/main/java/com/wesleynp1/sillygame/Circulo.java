package com.wesleynp1.sillygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Circulo implements ObjetoJogo, KeyListener {
    int x, y;
    int velocX, velocY;
    Color cor;

    Circulo(int x, int y, int velocX, int velocY, Color cor) {
        this.x = x;
        this.y = y;
        this.velocX = velocX;
        this.velocY = velocY;
        this.cor = cor;
    }

    @Override
    public void autoDesenhar(Graphics2D g2d) {
        g2d.setColor(this.cor);
        g2d.fillArc(x, y, 32, 32, 0, 360);
        g2d.setColor(Color.BLACK);
        g2d.drawArc(x, y, 32, 32, 0, 360);
    }

    @Override
    public void atualizarLogicaJogo() {
        this.x += this.velocX;
        this.y += this.velocY;

        decidirDirecao();
    }

    private void decidirDirecao() {
        if (x >= 1024 - 32 && velocX > 0) {
            velocX = (int) (velocX * (-1) * 0.8);
        } else if (x <= 0 && velocX < 0) {
            velocX = (int) (velocX * (-1) * 0.8);
        }

        if (y >= 768 - 32 && velocY > 0) {
            velocY = (int) (velocY * (-1) * 0.8);
        } else if (y <= 0 && velocY < 0) {
            velocY = (int) (velocY * (-1) * 0.8);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            this.velocX = (int) (Math.random() * 8)*(Math.random()>0.5 ? -1 : 1);
            this.velocY = (int) (Math.random() * 8)*(Math.random()>0.5 ? -1 : 1);
        }
    }
}