package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Tree {
    static float[] black = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
    static float[] white = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
    static float[] grey = new float[]{0.5F, 0.5F, 0.5F, 1.0F};
    static float[] spot = new float[]{0.1F, 0.1F, 0.1F, 0.5F};
    static float[] red = new float[]{0.7F, 0.3F, 0.2F, 1.0F};
    static float[] green = new float[]{0.0F, 1.0F, 0.0F, 1.0F};
    static float[] blue = new float[]{0.0F, 0.0F, 1.0F, 1.0F};
    static float[] yellow = new float[]{1.0F, 1.0F, 0.0F, 1.0F};
    static float[] magenta = new float[]{1.0F, 0.0F, 1.0F, 1.0F};
    static float[] cyan = new float[]{0.0F, 1.0F, 1.0F, 1.0F};
    static float[] orange = new float[]{1.0F, 0.5F, 0.0F, 1.0F, 1.0F};
    static float[] brown = new float[]{0.45F, 0.32F, 0.0F, 1.0F, 1.0F};
    static float[] dkgreen = new float[]{0.0F, 0.5F, 0.0F, 1.0F, 1.0F};
    static float[] pink = new float[]{1.0F, 0.6F, 0.6F, 1.0F, 1.0F};

    public Tree() {
    }

    public void DrawTent(float delta, boolean Status, Texture text1) {
        float theta = (float)((double)(delta * 2.0F) * Math.PI);
        float LimbRotation;
        float limb;
        if (Status) {
            LimbRotation = (float)Math.cos((double)delta * 0.4 * Math.PI);
            limb = (float)Math.cos((double)theta);
        } else {
            LimbRotation = 0.0F;
            limb = 0.0F;
        }

        Cylinder cylinder = new Cylinder();
        Cube cube = new Cube();
        Tetrahedron tetrahedron = new Tetrahedron();


        GL11.glColor4f(green[0], green[1],green[2], 0.7F);
        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(green));
        GL11.glPushMatrix();
        GL11.glScalef(1F, 0.7F, 1F);
        tetrahedron.DrawTetrahedron();

        TexCube texCube = new TexCube();
        Color.white.bind();
        text1.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, -10F, 0F);
//        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(3F, 1F, 3F);
        texCube.DrawTexCube(text1);
        GL11.glPopMatrix();
        Color.white.bind();

        GL11.glColor4f(green[0], green[1],green[2], 0.85F);
        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(green));
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, -2.2F, 0F);
//        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(1.4F, 1.1F, 1.4F);
        tetrahedron.DrawTetrahedron();


        GL11.glColor4f(green[0], green[1],green[2], 1.0F);
        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(green));
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, -2F, 0F);
//        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(1.4F, 1.1F, 1.4F);
        tetrahedron.DrawTetrahedron();


        GL11.glColor4f(brown[0], brown[1], brown[2], 1.0F);
        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(brown));
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 0F, 0F);
//        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.2F, 0.2F, 0.05F);
        cylinder.drawCylinder(1.3F, 100.0F, 32);






        GL11.glPopMatrix();

        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);


//
//        GL11.glColor4f(brown[0], brown[1], brown[2], 1.0F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(brown));
//        GL11.glPushMatrix();
//                GL11.glTranslatef(0F, -10F, 0F);
////        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
////        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glScalef(3F, 1F, 3F);
//
//        cube.drawCube();
////
//        GL11.glPopMatrix();
        GL11.glPopMatrix();




//        GL11.glColor4f(red[0], red[1], red[2], 0.7F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(red));
//        GL11.glPushMatrix();
//        GL11.glTranslatef(0.4F, 0.3F, -0.77F);
//        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glScalef(0.2F, 0.2F, 0.05F);
//        cylinder.drawCylinder(0.2F, 26.0F, 32);
//
//
//        GL11.glColor4f(white[0], white[1], white[2], 0.5F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(white));
//        GL11.glPushMatrix();
//        GL11.glTranslatef(-1.0F, 0.0F, 12.9F);
//        GL11.glScalef(1.0F, 0.2F, 13.0F);
//        cube.drawCube();
//
//        GL11.glPopMatrix();
//        GL11.glPopMatrix();
//
//
//        GL11.glColor4f(red[0], red[1], red[2], 0.7F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(red));
//        GL11.glPushMatrix();
//        GL11.glTranslatef(-0.4F, 0.3F, -0.77F);
//        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glScalef(0.2F, 0.2F, 0.05F);
//        cylinder.drawCylinder(0.2F, 26.0F, 32);
//
//
//        GL11.glColor4f(white[0], white[1], white[2], 0.5F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(white));
//        GL11.glPushMatrix();
//        GL11.glTranslatef(1.0F, 0.0F, 12.9F);
//        GL11.glScalef(1.0F, 0.2F, 13.0F);
//        cube.drawCube();
//
//        GL11.glPopMatrix();
//
//        GL11.glPopMatrix();
//
//        GL11.glPopMatrix();
//
//        GL11.glColor4f(black[0], black[1], black[2], 1.0F);
//        GL11.glMaterial(1028, 5634, Utils.ConvertForGL(black));
//        GL11.glPushMatrix();
//        GL11.glTranslatef(0.0F, -2.5F, -5.0F);
//        GL11.glScalef(2.5F, 0.0F, 2.5F);
//        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//        tetrahedron.DrawTetrahedron();
//        GL11.glPopMatrix();
    }
}
