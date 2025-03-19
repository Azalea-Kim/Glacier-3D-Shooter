
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Skybox {
    int face1 = glGenLists(1);
    int face2 = glGenLists(1);
    int face3 = glGenLists(1);
    int face4 = glGenLists(1);
    int face5 = glGenLists(1);
    int face6 = glGenLists(1);


    private Point4f origin = new Point4f();
    private Point4f position = new Point4f();
    private Vector4f scale = new Vector4f();
    private Vector4f rotation = new Vector4f();

    private Texture texture_down,texture_up,texture_right,texture_left,texture_back,texture_front;


    public Skybox(Point4f origin, Point4f position, Vector4f scale, Texture texture_down,  Texture texture_front,  Texture texture_right,  Texture texture_left,  Texture texture_back, Texture  texture_up) {
        this.origin = new Point4f(origin.x, origin.y, origin.z, 0);
        this.position = new Point4f(position.x, position.y, position.z, 0);
        this.scale = new Vector4f(scale.x, scale.y, scale.z, 0);
        this.texture_down = texture_down;
        this.texture_right= texture_right;
        this.texture_left = texture_left;
        this.texture_up= texture_up;
        this.texture_front = texture_front;
        this.texture_back = texture_back;
        glNewList(face1, GL11.GL_COMPILE);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glEnd();
        glEndList();

        glNewList(face2, GL11.GL_COMPILE);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(-0.5f, 0.5f, -0.5f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
        GL11.glEnd();
        glEndList();

        glNewList(face3, GL11.GL_COMPILE);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(-0.5f, 0.5f, 0.5f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(-0.5f, 0.5f, -0.5f);
        GL11.glEnd();
        glEndList();

        glNewList(face4, GL11.GL_COMPILE);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
        GL11.glEnd();
        glEndList();

        glNewList(face5, GL11.GL_COMPILE);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(-0.5f, 0.5f, 0.5f);
        GL11.glEnd();
        glEndList();

        glNewList(face6, GL11.GL_COMPILE);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(-0.5f, 0.5f, -0.5f);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(-0.5f, 0.5f, 0.5f);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
        GL11.glEnd();
        glEndList();
    }

    public void drawList() {

        GL11.glTexParameteri(
                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                GL11.GL_REPEAT);
        GL11.glTexParameteri(
                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                GL11.GL_REPEAT);
        Color.white.bind();
        //bind texture
        glDisable(GL_CULL_FACE);
        texture_down.bind();
        glCallList(face1);

        texture_front.bind();
        glCallList(face2);

        texture_right.bind();
        glCallList(face3);

        texture_left.bind();
        glCallList(face4);


        texture_back.bind();
        glCallList(face5);


        texture_up.bind();
        glCallList(face6);


        glEnable(GL_CULL_FACE);

    }

    public void DrawSkyBox(){


        GL11.glTranslatef(origin.x, origin.y, origin.z);
        GL11.glRotatef(rotation.a, rotation.x, rotation.y, rotation.z);
        GL11.glScalef(scale.x, scale.y, scale.z);
        GL11.glTranslatef(position.x, position.y, position.z);
        this.drawList();

    }


}
