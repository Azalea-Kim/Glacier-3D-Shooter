import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

import objects3D.DisplayListTexSphere;
import objects3D.Sphere;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;



import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;


public class Bullet{

    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.45f, 0.32f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    Texture sun;
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] ={ 1.0f, 0.0f, 0.0f, 1.0f }; // neck
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };  // changed to red

    DisplayListTexSphere sphere = new DisplayListTexSphere(1f, 16, 16,sun);
    private Vector4f direction = new Vector4f();
    private Integer drawCount = 0;
    private Point4f origin = new Point4f();
    private Vector4f position = new Vector4f();
    private Vector4f scale = new Vector4f();
    private Vector4f rotation = new Vector4f();

    private Texture texture;
    private boolean ishit;



    public Bullet(Vector4f position) throws IOException {

        this.position = position;



    }



    public void draw() {
        drawCount += 1;
        move(new Vector4f(0,0,100f,0));
        Sphere sphere = new Sphere();
        GL11.glColor4f(dkgreen [0], dkgreen[1], dkgreen[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(dkgreen));
        glPushMatrix();


//            System.out.println(getGlobalPos().x+" "+getGlobalPos().y+" "+getGlobalPos().z);
            GL11.glTranslatef(position.x/90,position.y/90-0.1f,position.z/90);
            // rotate the left shoulder front and back
            sphere.drawSphere(0.04f, 32, 32);
//        position = position.PlusVector(new Vector4f(0,0,0.5f,0));


        GL11.glPopMatrix();



        //bind texture
//        getTextures().get("debug").bind();
//        texture.bind();
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
////        oval.DrawOval();
//        sphere.DrawTexSphere();
//
//        Color.white.bind();


    }

    public Point4f getGlobalPos(){

        Point4f global = new Point4f(Player.playerPosI.x+position.x, Player.playerPosI.y-position.y, Player.playerPosI.z+position.z,0);
        return global;
    }

    public DisplayListTexSphere getSphere() {
        return sphere;
    }

    public void setSphere(DisplayListTexSphere sphere) {
        this.sphere = sphere;
    }

    public Vector4f getDirection() {
        return direction;
    }

    public void setDirection(Vector4f direction) {
        this.direction = direction;
    }

    public Integer getDrawCount() {
        return drawCount;
    }

    public void move(Vector4f vector) {
        this.position = position.PlusVector(vector);

//        GL11.glTranslatef(getOrigin().x, getOrigin().y, getOrigin().z);
//        GL11.glScalef(90f, 90f, 90f);
//        GL11.glTranslatef(getPosition().x , getPosition().y, getPosition().z);
    }
}
