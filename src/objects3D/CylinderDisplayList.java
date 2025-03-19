package objects3D;


import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class CylinderDisplayList {
    int displayListHandler = glGenLists(1);

    public CylinderDisplayList(float radius, float height, int nSegments) {
        glNewList(displayListHandler, GL_COMPILE);
        GL11.glBegin(GL11.GL_TRIANGLES);

        for (float i = 0.0f; i < nSegments; i += 1.0) {
            //angle on left
            float angle = (float) (Math.PI * i * 2.0 / nSegments);
            //angle on right
            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

            //angle on left
            float x1 = (float) Math.sin(angle) * radius;
            float y1 = (float) Math.cos(angle) * radius;

            //angle on right
            float x2 = (float) Math.sin(nextAngle) * radius;
            float y2 = (float) Math.cos(nextAngle) * radius;

            //upper triangle
            glTexCoord2f(0, 0);
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, 0);

            glTexCoord2f(1, 0);
            GL11.glNormal3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, height);

            glTexCoord2f(1, 1);
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, height);

            //lower triangle
            glTexCoord2f(0, 0);
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, 0);
            glTexCoord2f(1, 0);
            GL11.glNormal3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, 0.0f);
            glTexCoord2f(1, 1);
            GL11.glNormal3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, height);

            //top & bottom
            Vector4f normalTop = (new Vector4f(x1, y1, 0, 0)).cross(new Vector4f(x2 - x1, y2 - y1, 0, 0));
            Vector4f normalBottom = (new Vector4f(x1, y1, 0, 0)).cross(new Vector4f(x2 - x1, y2 - y1, 0, 0));

            //the top normal vector
            GL11.glNormal3f(-normalTop.x, -normalTop.y, -normalTop.z);
            glTexCoord2f(0, 0);
            GL11.glVertex3f(x1, y1, height);
            glTexCoord2f(1, 0);
            GL11.glVertex3f(x2, y2, height);
            glTexCoord2f(1, 1);
            GL11.glVertex3f(0, 0, height);

            //the bottom normal vector
            GL11.glNormal3f(normalBottom.x, normalBottom.y, normalBottom.z);
            glTexCoord2f(0, 0);
            GL11.glVertex3f(x1, y1, 0);
            glTexCoord2f(1, 0);
            GL11.glVertex3f(x2, y2, 0);
            glTexCoord2f(1, 1);
            GL11.glVertex3f(0, 0, 0);


        }

        GL11.glEnd();
        glEndList();
    }

    public void DrawCylinder() {
        glCallList(displayListHandler);
    }
}
