package com.wesleynp1.sillygame.objetos;

import java.awt.Graphics2D;

/**
 * Um objeto que pode ser pintado pela tela
 */
public abstract class ObjetoJogo{
        protected int x, y, z;
        public abstract void autoDesenhar(Graphics2D g2d);
        public abstract void atualizarLogicaJogo();

        ObjetoJogo(int x , int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getZ() { return z; }

        public void setX(int x){ this.x = x; }
        public void setY(int y){ this.y = y; }
        public void setZ(int z){ this.z = z; }
    }