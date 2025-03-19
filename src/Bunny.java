import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import objects3D.bunnyObj;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Bunny {

    public static ArrayList<Bunny> bunnies = new ArrayList<>();

    private long timePassed;

    private boolean color;

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 0.8f, 1.0f, 0.9f, 1.0f };

    static float grey[] = { 0.3f, 0.3f, 0.3f, 0.8f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 0.7f, 0.3f, 0.2f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    // secondary colours
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.45f, 0.32f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };



    private Vector4f direction = new Vector4f();
    private Integer drawCount = 0;
    private Point4f origin = new Point4f();
    private Point4f bunnyPos = new Point4f();

    private Vector4f rotation = new Vector4f();

    private Texture texture;
    private boolean isHit;
    public static int colorInt;



    private Point4f bunnyPosI = new Point4f();


    public Bunny(Point4f a) throws IOException {
            this.bunnyPos =a;

    }



    public void draw(int delta,long tp) {

        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation;
            LimbRotation = (float) Math.cos(10*theta) * 45;
//        drawCount += 1;
//        move(new Vector4f(5,0,0f,0));
        glPushMatrix();

        Color.white.bind();



        if(colorInt == 0){
        glColor3f(blue[0], blue[1], blue[2]);
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));


        }
        else if(colorInt ==1){
            glColor3f(green[0], green[1], green[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
        }

        else if(colorInt ==2){
            glColor3f(magenta[0], magenta[1], magenta[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(magenta));
        }
        else if(colorInt ==3){
            glColor3f(yellow[0], yellow[1], yellow[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
        }
        else if(colorInt ==4){
            glColor3f(pink[0], pink[1], pink[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
        }
        else if(colorInt ==5){
            glColor3f(cyan[0], cyan[1], cyan[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(cyan));
        }



//            System.out.println(position.x+" "+position.y+" "+position.z);
        GL11.glTranslatef(bunnyPos.x/90, bunnyPos.y/90, bunnyPos.z/90);
        // rotate the left shoulder front and back
        glCallList(bunnyObj.getBunnyDisplayList());

        GL11.glPopMatrix();



    }
//
//    -494,-489 z
//    197 207 x
//
//    450 442 y



    public boolean checkBulltHit(){
        boolean yes = false;

        for(Bullet b:Player.bullets){
            float x1 = getGlobalPos().x-5;
            float x2 = getGlobalPos().x+5;
            float y1 = getGlobalPos().y-260-4;
            float y2 = getGlobalPos().y-260+4;
            float z1 = (float) (getGlobalPos().z-1000-2.5);
            float z2 = (float) (getGlobalPos().z-1000+2.5);
//
//            bullet: 200.0 450.0 291.0
//            bunny: 200.0 450.0 1000.0

//            System.out.println("bunny: "+getGlobalPos().x+" "+y1+" "+z1);
//            System.out.println("bullet: "+b.getGlobalPos().x+" "+b.getGlobalPos().y+" "+b.getGlobalPos().z);

//            G: 200.0 450.0 -500.0
//            bunny: 200.0 700.0 500.0
//            bullet: 200.0 450.0 -400.0



//            203.0 439.0 -490.0




            yes = b.getGlobalPos().x>x1&&b.getGlobalPos().x<x2 & b.getGlobalPos().y>y1&& b.getGlobalPos().y<y2&b.getGlobalPos().z>z1;
            if(yes){
                System.out.println("hit bunny");
                colorInt = random();
                Player.removeBullets.add(b);


            }

        }
        return yes;

    }

    private int random(){
        java.util.Random random= new  java.util.Random();
        int  result=random.nextInt( 6 );

        return result;
    }

    private Point4f getGlobalPos(){
//
//        Point4f global = new Point4f(bunnyPosI.x+bunnyPos.x, bunnyPosI.y-bunnyPos.y, bunnyPosI.z+bunnyPos.z,0);
//        return global;
        return bunnyPos;
    }


}
