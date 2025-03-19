package objects3D;


import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import static org.lwjgl.opengl.GL11.*;

public class TextCube1 {
    int repeat; // number of times to repeat texture
    public TextCube1(int repeat) {
        this.repeat = repeat;
    }

    // Implement using notes and looking at TexSphere
    public void drawTexCube() {
        float length = 1.0f;
        // the 8 vertexes of the cube
        Point4f[] points ={
                new Point4f(length,0,0,0), new Point4f(length,length,0,0),
                new Point4f(length,length,length,0), new Point4f(length,0,length,0),
                new Point4f(0,0,length,0), new Point4f(0,length,length,0),
                new Point4f(0,length,0,0), new Point4f(0,0,0,0)
        };

        // the 6 faces of the cube
        int[][] faces = {
                {0,3,2,1},{3,4,5,2},{4,7,6,5},{7,0,1,6},{1,2,5,6},{7,4,3,0}
        };
        glBegin(GL_QUADS);
        for (int[] face : faces) {
            Vector4f v1 = points[face[0]].MinusPoint(points[face[1]]);
            Vector4f v2 = points[face[1]].MinusPoint(points[face[2]]);
            Vector4f normal = v1.cross(v2).Normal();
            glNormal3f(normal.x, normal.y, normal.z); // normal to face

            // texture coordinates to coordinate of cube faces
            glTexCoord2f(0, 0);
            glVertex3f(points[face[1]].x, points[face[1]].y, points[face[1]].z);
            glTexCoord2f(0, repeat);
            glVertex3f(points[face[2]].x, points[face[2]].y, points[face[2]].z);
            glTexCoord2f(repeat, repeat);
            glVertex3f(points[face[3]].x, points[face[3]].y, points[face[3]].z);
            glTexCoord2f(repeat, 0);
            glVertex3f(points[face[0]].x, points[face[0]].y, points[face[0]].z);
        }
        glEnd();


    }

}

/*
 *
 *
 * }
 *
 */
