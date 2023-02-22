package cloneminizelda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.List;


public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGHT = 480;
    public static int SCALE = 3;
    public static Player player;
    public World world;
    public List<Inimigo> inimigos = new ArrayList<Inimigo>();

    public Game(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        new Spritesheet();
        player = new Player(120,80);
        world = new World();
        inimigos.add(new Inimigo(32,32));
    }

    public void tick(){
        player.tick();

        for(int i = 0; i < inimigos.size(); i++){
            inimigos.get(i).tick();
        }
    }

    public void render(){
        //cria o buff (imagem) que vemos
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        //usado para definir cor,tamanho,etc
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(0,165,30));
        g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);
        player.render(g);
        for(int i = 0; i < inimigos.size(); i++){
            inimigos.get(i).render(g);
        }
        world.render(g);
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame janela = new JFrame();

        janela.add(game);
        janela.setTitle("Mini Zelda");
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while(true){
            tick();
            render();
            try{
                Thread.sleep(1000/60); //rodar a 60fps
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_Z){
            player.shoot = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = false;
        }
    }
}
