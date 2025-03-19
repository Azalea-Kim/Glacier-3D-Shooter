package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class House {

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


    private static boolean open = true;
    private static float flag1;

    public House() {

    }

    // Draw a Tent
    public void DrawHouse(float delta, int swingInt,Texture rock, Texture cube_texture, Texture flag,Texture ice, Texture space) {
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
        Tetrahedron tetrahedron = new Tetrahedron();
        Texture texture=null;
        TexTetrahedron texTetrahedron = new TexTetrahedron();

//
//
//        GL11.glColor4f(green[0], green[1],green[2], 0.7F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(green));
//        GL11.glPushMatrix();
//        GL11.glScalef(1F, 0.7F, 1F);
//        tetrahedron.DrawTetrahedron();
//

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        rock.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glPushMatrix();
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glScalef(8, 8, 8);
            cylinder.drawTCylinder(150,30,6,rock);



        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        cube_texture.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 0F, -80F);
        GL11.glScalef(60, 60, 60);
        texcube.DrawTexCube(cube_texture);



        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        cube_texture.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        glRotatef(270,4,0,0);
        GL11.glTranslatef(0F, 1F, 0F);
        GL11.glScalef(1F, 0.8F, 1F);
        texTetrahedron.DrawTetrahedron();



        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        cube_texture.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 0.5F, 0.8F);
        GL11.glScalef(0.05F, 1.5F, 0.05F);
        texcube.DrawTexCube(cube_texture);


        GL11.glPopMatrix();

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        flag.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glRotatef(90,1,0,0);
        if(delta<1){

            GL11.glTranslatef(0F, 1.85F, -0.3F-delta);

        }
        else{
            GL11.glTranslatef(0F, 1.85F, -1.3F);

        }

        GL11.glScalef(0.05F, 1F, 0.7F);
        texcube.DrawTexCube(flag);
        GL11.glPopMatrix();


        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        cube_texture.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 0.5F, -0.8F);
        GL11.glScalef(0.05F, 1F, 0.05F);
        texcube.DrawTexCube(cube_texture);



        GL11.glPopMatrix();

        GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        cube_texture.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        GL11.glPushMatrix();
//            GL11.glRotatef(-90, 1, 0, 0);
            GL11.glScalef(0.1F, 0.1F, 0.1F);
            GL11.glTranslatef(0,14.5F,-8.5F);
            cylinder.drawTCylinder(1,8,100,cube_texture);


                GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                cube_texture.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

                GL11.glPushMatrix();
        //            GL11.glRotatef(-90, 1, 0, 0);
                GL11.glScalef(1F, 1F, 1F);
//                GL11.glTranslatef(0,14.5F,-15.5F);
                texsphere.DrawTexSphere(1,30,32,cube_texture);

                GL11.glPopMatrix();


                GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                cube_texture.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

                GL11.glPushMatrix();
                //            GL11.glRotatef(-90, 1, 0, 0);
                GL11.glScalef(1F, 1F, 1F);

                    GL11.glRotatef(-((swingInt)%360), 0, 0, 1);
                        GL11.glTranslatef(0,0F,-8F);
                texsphere.DrawTexSphere(1,30,32,cube_texture);





                // zhuan
                GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                rock.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glClearColor(0.0f, 0.0f, 0.0f, 0.0f);


                GL11.glPushMatrix();{
                            GL11.glRotatef(60, 0, 0, 1);
                            GL11.glRotatef(50,1,0,0);
                            GL11.glTranslatef(4f,0,0);
                            GL11.glScalef(4.8f, 0.3f, 0.6f);
                            texcube.DrawTexCube(rock);
                        }
                        GL11.glPopMatrix();

                GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                rock.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glClearColor(0.0f, 0.0f, 0.0f, 0.0f);


                GL11.glPushMatrix();{
                    GL11.glRotatef(-60, 0, 0, 1);
            GL11.glRotatef(50,1,0,0);
                    GL11.glTranslatef(4f,0,0);
                    GL11.glScalef(4.8f, 0.3f, 0.6f);
                    texcube.DrawTexCube(rock);
                }
                GL11.glPopMatrix();

                GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                rock.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glClearColor(0.0f, 0.0f, 0.0f, 0.0f);


                GL11.glPushMatrix();{
                    GL11.glRotatef(180, 0, 0, 1);
                    GL11.glRotatef(50,1,0,0);
                    GL11.glTranslatef(4f,0,0);
                    GL11.glScalef(4.8f, 0.3f, 0.6f);
                    texcube.DrawTexCube(rock);
                }
                GL11.glPopMatrix();







                GL11.glPopMatrix();


            GL11.glPopMatrix();


        GL11.glPopMatrix();






        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        rock.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glPushMatrix();
//        GL11.glRotatef(90, 1, 0, 0);
        GL11.glScalef(0.1F, 0.1F, 0.1F);
        glTranslatef(-10,-5,10);


            if(90.0f - limb*90.0f<175 && open){
                GL11.glRotatef((90.0f - limb*90.0f) , 0, 0, 1);

            }else{
                open = false;
                GL11.glRotatef(175,0,0,1);
            }

        cylinder.drawTCylinder(0.5F,10,66,rock);


                GL11.glColor4f(white[0], white[1], white[2], 1f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                ice.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                GL11.glPushMatrix();
        //        GL11.glRotatef(90, 1, 0, 0);
                GL11.glScalef(0.3F, 2.5F, 5F);
                glTranslatef(-1,1F,-1);
                texcube.DrawTexCube(ice);

                glPopMatrix();




        glPopMatrix();



        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        rock.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glPushMatrix();
//        GL11.glRotatef(90, 1, 0, 0);
        GL11.glScalef(0.1F, 0.1F, 0.1F);
        glTranslatef(-10,5,10);

        if(90.0f - limb*90.0f>-175 && open){
            GL11.glRotatef(-(90.0f - limb*90.0f) , 0, 0, 1);

        }else{
            open = false;
            GL11.glRotatef(-175,0,0,1);
        }

        cylinder.drawTCylinder(0.5F,10,66,rock);

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        ice.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glPushMatrix();
        //        GL11.glRotatef(90, 1, 0, 0);
        GL11.glScalef(0.3F, 2.5F, 5F);
        glTranslatef(-1,-1F,-1);
        texcube.DrawTexCube(ice);

        glPopMatrix();


        glPopMatrix();



        GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        space.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glPushMatrix();
        glTranslatef(-1F,-0,0.5F);
//        GL11.glRotatef(90, 1, 0, 0);
        GL11.glScalef(0.1F, 0.4F, 0.5F);
//        cylinder.drawTCylinder(150,30,6,space);

        texcube.DrawTexCube(space);
//        cube.drawCube();
        glPopMatrix();


        GL11.glPopMatrix();





        GL11.glPopMatrix();




}

    public static void setFlag1(float flag1) {
        House.flag1 = flag1;
    }
}
