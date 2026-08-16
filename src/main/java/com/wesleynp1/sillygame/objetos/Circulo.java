package com.wesleynp1.sillygame.objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.wesleynp1.sillygame.SillyGame;
import com.wesleynp1.sillygame.interfaces.TecladoResposivo;
import com.wesleynp1.sillygame.interfaces.Colidivel;


/**
 * Descrição da Classe: circulo que saltita na tela, cor e velocidade aleatórias, perde velocidado ao tocar as bordas,
 * velocidade aumenta ao pressionar espaço
 */
public class Circulo extends ObjetoJogo implements TecladoResposivo, Colidivel{
    int velocX, velocY;
    Color cor;
    Color contorno;

    public Circulo() {
        super(
            (int)(Math.random()*(SillyGame.WIDTH_TELA-32)), 
            (int)(Math.random()*(SillyGame.HEIGHT_TELA-32)),
            Math.round((float) (Math.random()*9)),
            48,
            48
        );
        int maxVelocCirv = 10;

        Color[] cores = Circulo.cores();
        

        this.velocX = (int)((Math.random()+0.1)*maxVelocCirv)*(Math.random()>0.5 ? -1 : 1);
        this.velocY = (int)((Math.random()+0.1)*maxVelocCirv)*(Math.random()>0.5 ? -1 : 1);
        this.cor = cores[(int)(Math.random()*cores.length-1)];
        this.contorno = Color.BLACK;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
    
    public static Color[] cores(){
         Color[] cores ={
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

        return cores;
    }

    @Override
    public void autoDesenhar(Graphics2D g2d) {
        g2d.setColor(cor);
        g2d.fillArc(x, y, width, height, 0, 360);
        g2d.setColor(contorno);
        g2d.drawArc(x, y, width, height, 0, 360);
        g2d.setFont(new Font("Arial", Font.BOLD, (int)(this.width*0.75)));
        g2d.drawString(String.valueOf(z), x+(int)(this.height*0.25), y+(int)(this.height*0.75));
    }

    @Override
    public void atualizarLogicaJogo() {
        this.x += this.velocX;
        this.y += this.velocY;

        decidirDirecao();
    }

    private void decidirDirecao() {
        float proporcao = 1; 

        if (x >= SillyGame.WIDTH_TELA-width && velocX > 0) {
            velocX = (int) ((-1) * Math.abs(velocX) * proporcao);
        } else if (x <= 0 && velocX < 0) {
            velocX = (int) (Math.abs(velocX) * proporcao);
        }

        if (y >= SillyGame.HEIGHT_TELA-width && velocY > 0) {
            velocY = (int) ((-1) * Math.abs(velocX) * proporcao);
        } else if (y <= 0 && velocY < 0) {
            velocY = (int) (Math.abs(velocX) * proporcao);
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

    @Override
    public void aoColidir(Colidivel colidido) {
        ObjetoJogo objColidido = (ObjetoJogo) colidido;

        if(y < objColidido.getY()+objColidido.getHeight() && y+height > objColidido.getY()){
        velocX = velocX *(-1);
        }

        if(x < objColidido.getX()+objColidido.getWidth() && x+width > objColidido.getX()){
        velocY = velocY *(-1);
        }

        this.contorno = (contorno == Color.BLACK ? Color.WHITE : Color.BLACK);
        decidirDirecao();
    }
}