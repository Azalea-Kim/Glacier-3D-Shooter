package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

public class Light {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
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

    public Light() {

    }


    // to draw a brand. a dog running gif create by multiple pictures
    public void DrawLight(float delta, boolean Status) {
        float LimbRotation;
        int lightFlag = (int) (delta * 50);

        if (Status) {
            LimbRotation = (float) (Math.cos(delta * 0.2 * Math.PI));
        }else {
            LimbRotation =0;
        }

        Sphere sphere= new Sphere();
        Cylinder cylinder= new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Cube cube = new Cube();
        TexCylinder texCylinder = new TexCylinder();

        // rod
        GL11.glPushMatrix();
        {
            Cylinder light2 = new Cylinder();
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glTranslatef(0, 0, 0);
            GL11.glRotatef(90.0f, 1.0f, 0, 0);
            GL11.glScalef(0.5f, 0.5f, 6.0f);
            light2.drawCylinder(30.0f, 100.0f, 32);
            GL11.glPopMatrix();
        }

        // bulb
        GL11.glPushMatrix();
        {
            Sphere light3 = new Sphere();

            if(!Status){
                GL11.glColor4f(pink[0], pink[1], pink[2], (float) 0.8);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
            }
            else{
                if(lightFlag % 7 == 0){
                    GL11.glColor4f(red[0], red[1], red[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                }
                else if(lightFlag % 7 == 1){
                    GL11.glColor4f(orange[0], orange[1], orange[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                }
                else if(lightFlag % 7 == 2){
                    GL11.glColor4f(yellow[0], yellow[1], yellow[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
                }
                else if(lightFlag % 7 == 3){
                    GL11.glColor4f(green[0], green[1], green[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
                }
                else if(lightFlag % 7 == 4){
                    GL11.glColor4f(cyan[0], cyan[1], cyan[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(cyan));
                }
                else if(lightFlag % 7 == 5){
                    GL11.glColor4f(blue[0], blue[1], blue[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                }
                else {
                    GL11.glColor4f(pink[0], pink[1], pink[2], (float) 0.6);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                }
            }
            GL11.glTranslatef(0, 10, 0);
            GL11.glRotatef(90.0f, 1.0f, 0, 0);
            light3.drawSphere(50, 32, 32);
            GL11.glPopMatrix();
        }

        // bottom
        GL11.glPushMatrix();
        {
            Cylinder bottom = new Cylinder();
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glTranslatef(0, -600, 0);
            GL11.glRotatef(90.0f, 1.0f, 0, 0);
            GL11.glScalef(3f, 3f, 0.3f);
            bottom.drawCylinder(30.0f, 100.0f, 32);
            GL11.glPopMatrix();
        }

        // shadow rod
        GL11.glPushMatrix();
        {
            Cylinder light2 = new Cylinder();
            GL11.glColor3f(black[0], black[1], black[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            GL11.glTranslatef(0, -600, 0);
            GL11.glRotatef(90.0f, 1.0f, 0, 0);
            GL11.glRotatef(90.0f, 1f, 0, 0);
            GL11.glScalef(0.5f, 0, 6.0f);
            light2.drawCylinder(30.0f, 100.0f, 32);
            GL11.glPopMatrix();
        }

        // shadow bulb
        GL11.glPushMatrix();
        {
            Wafer light3 = new Wafer();
            GL11.glColor4f(black[0], black[1], black[2], (float) 1);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            GL11.glTranslatef(0, -590, -600);
            GL11.glRotatef(90.0f, 1.0f, 0, 0);
            GL11.glScalef(50,50,50);
            light3.DrawWafer();
            GL11.glPopMatrix();
        }

        // shadow bottom
        GL11.glPushMatrix();
        {
            Cylinder bottom = new Cylinder();
            GL11.glColor3f(black[0], black[1], black[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            GL11.glTranslatef(0, -600, 0);
            GL11.glRotatef(90.0f, 1.0f, 0, 0);
            GL11.glScalef(3f, 3f, 0.3f);
            bottom.drawCylinder(30.0f, 100.0f, 32);
            GL11.glPopMatrix();
        }
    }
}
