package objects3D;


import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Windmill {

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

    public Windmill() {

    }

    // Draw a Tent
    public void DrawWindmill(float delta, int swingInt, boolean Status, Texture text1) {
        float LimbRotation;
        float theta = (float) (delta * 2 * Math.PI);
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


        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glScalef(8, 8, 8);
            cylinder.drawCylinder(36,30,32);
        }
        GL11.glPopMatrix();
        GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
        GL11.glPushMatrix();{
            GL11.glRotatef(-90, 1, 0, 0);
            GL11.glScalef(8, 8, 8);
            GL11.glTranslatef(0,0,2);
            cylinder.drawCylinder(4,180,32);
            GL11.glColor4f(spot[0], spot[1], spot[2], 1f);
            GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(spot));
            GL11.glPushMatrix();{
                GL11.glTranslatef(-10,-10,180);
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glRotatef(-45, 1, 0, 0);
                GL11.glScalef(4, 4, 4);
                cylinder.drawCylinder(2f,8,32);
                GL11.glColor4f(spot[0], spot[1], spot[2], 0.5f);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(spot));
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0,0,7.5f);
//                    System.out.println(swingInt);
//                    if(!Status){
                        GL11.glRotatef(((swingInt)%360), 0, 0, 1);
//                    }
//                    GL11.glRotatef(LimbRotation*200, 0, 0, 1);
                    GL11.glScalef(4, 4, 4);
                    sphere.drawSphere(0.55f,20,32);
                    GL11.glColor4f(cyan[0], cyan[1], cyan[2], 0.8f);
                    GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(cyan));
                    GL11.glPushMatrix();{
                        GL11.glRotatef(60, 0, 0, 1);
                        GL11.glTranslatef(2f,0,0);
                        GL11.glScalef(2.4f, 0.15f, 0.3f);
                        cube.drawCube();
                    }
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();{
                        GL11.glRotatef(-60, 0, 0, 1);
                        GL11.glTranslatef(2f,0,0);
                        GL11.glScalef(2.4f, 0.15f, 0.3f);
                        cube.drawCube();
                    }
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();{
                        GL11.glRotatef(180, 0, 0, 1);
                        GL11.glTranslatef(2f,0,0);
                        GL11.glScalef(2.4f, 0.15f, 0.3f);
                        cube.drawCube();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        //shadow
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
        GL11.glPushMatrix();{
//            GL11.glRotatef(-90, 1, 0, 0);
            GL11.glTranslatef(0,-20,2);
            GL11.glScalef(8, 0, 8);

            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            cylinder.drawCylinder(4,180,32);
            GL11.glPushMatrix();{
                GL11.glTranslatef(10,10,180);
//                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glRotatef(-45, 0, 1, 0);
                GL11.glScalef(4, 4, 4);

                cylinder.drawCylinder(2f,8,32);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0,0,7.5f);
//                    if(!Status){
                        GL11.glRotatef(((delta*200)%360), 0, 0, 1);
//                    }

                    GL11.glScalef(4, 4, 4);
                    sphere.drawSphere(0.55f,20,32);
                    GL11.glPushMatrix();{
                        GL11.glRotatef(60, 0, 0, 1);
                        GL11.glTranslatef(2f,0,0);
                        GL11.glScalef(2.4f, 0.15f, 0.3f);
                        cube.drawCube();
                    }
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();{
                        GL11.glRotatef(-60, 0, 0, 1);
                        GL11.glTranslatef(2f,0,0);
                        GL11.glScalef(2.4f, 0.15f, 0.3f);
                        cube.drawCube();
                    }
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();{
                        GL11.glRotatef(180, 0, 0, 1);
                        GL11.glTranslatef(2f,0,0);
                        GL11.glScalef(2.4f, 0.15f, 0.3f);
                        cube.drawCube();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

}
