import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import objects3D.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;


import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import static org.lwjgl.opengl.GL11.*;

public class Player {

    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.45f, 0.32f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    private Integer bullet_counter = 0;
    private int scale;
    private boolean b = false;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> removeBullets = new ArrayList<>();

    public static  int x =0;
    public static  int y=0;
    private float jump_height = 0f;

    public static Vector4f playerPos = new Vector4f(0, 0, 0, 0);

    public static Vector4f playerScale = new Vector4f(0, 0, 0, 0);

    static float white[] = { 0.8f, 1.0f, 0.9f, 1.0f };
    public static Point4f playerPosI = new Point4f();
    private boolean isJumping = false;
    private boolean isHit = false;




    public void setPos(Point4f v, int s) {
        playerPosI = v;
        scale = s;

    }


    public Player() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void updatePosition() {
        glTranslatef(playerPosI.x, playerPosI.y, playerPosI.z);
        glTranslatef(playerPos.x, -playerPos.y, playerPos.z);
        bullet_counter--;

            remove();


    }


    // Draw a Tent
    public void DrawPlayer(float delta) {
        float LimbRotation;
        float theta = (float) (delta * 2 * Math.PI);
        float flag1 = 0;
        float limb;

        if (true) {
            LimbRotation = (float) (Math.cos(delta * 0.4 * Math.PI));
            limb = (float) (Math.cos(theta));
        } else {
            LimbRotation = 0;
            limb = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Cube cube = new Cube();




        GL11.glColor4f(cyan [0], cyan[1], cyan[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(cyan));
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
//

//        mud.bind();
//        glEnable(GL_TEXTURE_2D);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        GL11.glPushMatrix();
        GL11.glTranslatef(playerPos.x, playerPos.y, playerPos.z);

        GL11.glScalef(1, 1, 1);
        cube.drawCube();

        glPushMatrix();
//        glRotatef(270,1,0,0);
        glTranslatef(0,3,0);
        GL11.glScalef(4, 4, 4);
        glCallList(gunObj.getGunDisplayList());


        for(Bullet b : bullets){
            glPushMatrix();
            glTranslatef(0,0.1f,0);
            b.draw();

            glPopMatrix();

        }

        glPopMatrix();

        GL11.glPopMatrix();

//        texcube.DrawTexCube(mud);





    }
    public void update_player(float delta, boolean isGod) throws IOException {


        float speed = delta;
        if (Keyboard.isKeyDown(Keyboard.KEY_U)) {

            playerPos = playerPos.PlusVector(new Vector4f(0,0,0.5f,0));

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_K)) {

            playerPos = playerPos.PlusVector(new Vector4f(-0.5f,0,0,0));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_J)) {

            playerPos = playerPos.PlusVector(new Vector4f(0, -0, -0.5f, 0));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_H)) {

            playerPos = playerPos.PlusVector(new Vector4f(0.5f,0,0,0));
        }
        if(isGod){
            if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {

                playerPos = playerPos.PlusVector(new Vector4f(0, 0.5f, 0f, 0));
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_I)) {

                playerPos = playerPos.PlusVector(new Vector4f(0,-0.5f,0,0));
            }
//
//            if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
//                this.hit(200,3000);
//            }

        }else{
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            this.jump(150);
        }


        }


        if (Mouse.isButtonDown(1)&&bullet_counter<0) {
            this.shoot();

            bullet_counter = 5;



        }


        if (Keyboard.isKeyDown(Keyboard.KEY_0)) {
           playerPos = new Vector4f(0,0,0,0);
        }


    }

    public void shoot() throws IOException, ConcurrentModificationException  {

        System.out.println(getPlayerPos().x+" "+getPlayerPos().y+" "+getPlayerPos().z);
        System.out.println("G: "+getGlobalPos().x+" "+getGlobalPos().y+" "+getGlobalPos().z);
            Bullet bullet = new Bullet(
                    getPlayerPos()

            );

            bullets.add(bullet);
//            GL11.glPushMatrix();
//
//        System.out.println("draw");
//
//            bullet.draw();
////            bullet.setDirection(new Vector4f(
////                    (float) Math.sin(Math.toRadians(180 - Camera.rotation.y)),
////                    0,
////                    (float) Math.cos(Math.toRadians(180 - Camera.rotation.y)),
////                    0
////            ));
//            GL11.glPopMatrix();
        for(Bullet b : bullets){
        if(b.getDrawCount()>=200){
//            System.out.println("added");
            removeBullets.add(b);
        }}
    }

    private void remove(){

        for(Bullet b : removeBullets){
            bullets.remove(b);


        }

    }

    public boolean checkHit(){

       return getGlobalPos().x>196&&getGlobalPos().x<201&getGlobalPos().y>423&& getGlobalPos().y<428&getGlobalPos().z>-527&&getGlobalPos().z<-522;



    }

    public void hit(int speed, int speedz) {
        float g = 9.8f;

        // v= 130
        if (!isHit) {
            isHit = true;

            long start_time = System.currentTimeMillis();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    double h = 0;
                    long t = 0;
                    double dz = 0;
                    double z = playerPos.z;
                    double y = playerPos.y;
                    while (h >= 0) {
                        long start = System.currentTimeMillis();
                        long current_time = System.currentTimeMillis();
                        t = (current_time - start_time) / 10;
                        h = (speed * t - 0.5 * g * t * t) / 9000;
                        dz = (speedz*t) / 9000;


                        jump_height = (float) h;
//                        if(t<speed/g*2){
//                            Camera.position.y = jump_height * 400;
                            playerPos.y = (float) (y+jump_height*90+10/90);//extra translate in main
                            playerPos.z = (float) (z + dz);
//                        System.out.println("dz:"+playerPos.z);
//                        System.out.println("h"+playerPos.y);

                        long end = System.currentTimeMillis();
                        while (end - start < 100) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            end = System.currentTimeMillis();
                        }
                    }
                    jump_height = 0;
                    isHit = false;

                }
            });
            thread.start();
        }

    }






    public void jump(int speed) {
        float g = 9.8f;

        // v= 130
        if (!isJumping) {
            isJumping = true;

            long start_time = System.currentTimeMillis();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    double h = 0;
                    long t = 0;
                    while (h >= 0) {
                        long start = System.currentTimeMillis();
                        long current_time = System.currentTimeMillis();
                        t = (current_time - start_time) / 10;
                        h = (speed * t - 0.5 * g * t * t) / 9000;
//
//                        System.out.println("h:"+h);
                        jump_height = (float) h;
                        if(t<speed/g*2+10){
                        Camera.position.y = jump_height * 400;
                        playerPos.y = jump_height*90+10/90;}//extra translate in main

//                        long end = System.currentTimeMillis();
//                        while (end - start < 16) {
//                            try {
//                                Thread.sleep(1);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            end = System.currentTimeMillis();
//                        }
                    }
                    jump_height = 0;
                    isJumping = false;

                }
            });
            thread.start();
        }

    }

    public static Vector4f getPlayerPos() {
        return playerPos;
    }
    //    public void checkInput(float delta){
//        if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
//            x -= 100*delta;
//        }
//
//        else if (Keyboard.isKeyDown(Keyboard.KEY_K) ) {
//            x += 100 * delta;
//
//            System.out.println("K");
////			if(DancingStatus == 2)
////				x += 0.15f * delta;
//        } else if (Keyboard.isKeyDown(Keyboard.KEY_U) ) {
//            y += 100 * delta;
//
//            System.out.println("U");
////			if(DancingStatus == 2)
////				y += 0.15f * delta;
//        } else if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
//            y -= 100 * delta;
//
////			if(DancingStatus == 2)
////				y -= 0.15f * delta;
//        } else {
//
//        }
//    }


//    }

    public static Point4f getPlayerPosI(){
        return playerPosI;
    }

    public static Point4f getGlobalPos(){

        Point4f global = new Point4f(playerPosI.x+playerPos.x, playerPosI.y-playerPos.y, playerPosI.z+playerPos.z,0);
        return global;
    }




}
