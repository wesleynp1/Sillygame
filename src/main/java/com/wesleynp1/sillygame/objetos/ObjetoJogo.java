package com.wesleynp1.sillygame.objetos;

import java.awt.Graphics2D;

/**
 * Um objeto que pode ser pintado pela tela
 */
public abstract class ObjetoJogo{
        protected int x, y, z, width, height;
        public abstract void autoDesenhar(Graphics2D g2d);
        public abstract void atualizarLogicaJogo();

        ObjetoJogo(int x , int y, int z, int width, int height){
            this.x = x;
            this.y = y;
            this.z = z;
            this.width = width;
            this.height = height;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getZ() { return z; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }

        public void setX(int x){ this.x = x; }
        public void setY(int y){ this.y = y; }
        public void setZ(int z){ this.z = z; }
    }