package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Garden {

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


    public Garden() {

    }

    // Draw a Tent
    public void DrawGarden(float delta, Texture mud, Texture grass, Texture leaf) {
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
        Tree tree = new Tree();



        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

        Color.white.bind();
        mud.bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glPushMatrix();
        GL11.glRotatef(90, 1, 0, 0);
        GL11.glScalef(1, 1, 1);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
//        cylinder.drawTCylinder(9F,1.5F,6,mud);

        texcube.DrawTexCube(mud);

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);


        GL11.glPushMatrix();
        GL11.glRotatef(270, 1, 0, 0);
        glRotatef(45,0,1,0);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        glTranslatef(8,10,5.5F);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
        tree.DrawTent(delta,true,grass,leaf);

        glPopMatrix();


        GL11.glPushMatrix();
        GL11.glRotatef(270, 1, 0, 0);
        glRotatef(45,0,1,0);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        glTranslatef(0.5F,10,11F);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
        tree.DrawTent(delta,true,grass,leaf);

        glPopMatrix();


        GL11.glPushMatrix();
        GL11.glRotatef(270, 1, 0, 0);
        glRotatef(45,0,1,0);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        glTranslatef(-7.5F,10,5F);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
        tree.DrawTent(delta,true,grass,leaf);

        glPopMatrix();


//
        GL11.glPushMatrix();
        GL11.glRotatef(270, 1, 0, 0);
        glRotatef(45,0,1,0);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        glTranslatef(-6.5F,10,-6F);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
        tree.DrawTent(delta,true,grass,leaf);

        glPopMatrix();



        GL11.glPushMatrix();
        GL11.glRotatef(270, 1, 0, 0);
        glRotatef(45,0,1,0);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        glTranslatef(0.5F,10,-12F);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
        tree.DrawTent(delta,true,grass,leaf);

        glPopMatrix();

//
//
        GL11.glPushMatrix();
        GL11.glRotatef(270, 1, 0, 0);
        glRotatef(45,0,1,0);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        glTranslatef(12F,10,-3F);
//        cy.drawTCylinder(9F,1.5F,6,mud);
//        glRotatef(90,1,0,0);
        tree.DrawTent(delta,true,grass,leaf);

        glPopMatrix();


        glPopMatrix();

    }




}
