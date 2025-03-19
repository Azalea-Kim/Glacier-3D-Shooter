package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glTexCoord2f;

public class TexTetrahedron {
    public TexTetrahedron() {
    }

    public void DrawTetrahedron() {
        Point4f[] vertices = new Point4f[]{new Point4f(-1.0F, -1.0F, -1.0F, 0.0F), new Point4f(-1.0F, -1.0F, 1.0F, 0.0F), new Point4f(1.0F, -1.0F, -1.0F, 0.0F), new Point4f(1.0F, -1.0F, 1.0F, 0.0F), new Point4f(0.0F, (float)Math.sqrt(3.0), 0.0F, 0.0F)};
        int[][] faces = new int[][]{{0, 1, 3, 2}, {4, 3, 1}, {4, 3, 2}, {4, 2, 0}, {4, 0, 1}};
        GL11.glBegin(7);
        Vector4f v = vertices[faces[0][1]].MinusPoint(vertices[faces[0][0]]);
        Vector4f w = vertices[faces[0][3]].MinusPoint(vertices[faces[0][0]]);
        Vector4f normal = v.cross(w).Normal();
        GL11.glNormal3f(normal.x, normal.y, normal.z);
        GL11.glVertex3f(vertices[faces[0][0]].x, vertices[faces[0][0]].y, vertices[faces[0][0]].z);
        GL11.glVertex3f(vertices[faces[0][1]].x, vertices[faces[0][1]].y, vertices[faces[0][1]].z);
        GL11.glVertex3f(vertices[faces[0][2]].x, vertices[faces[0][2]].y, vertices[faces[0][2]].z);
        GL11.glVertex3f(vertices[faces[0][3]].x, vertices[faces[0][3]].y, vertices[faces[0][3]].z);
        GL11.glEnd();
        GL11.glBegin(4);

        for(int face = 1; face < 5; ++face) {
            v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            w = vertices[faces[face][2]].MinusPoint(vertices[faces[face][0]]);
            normal = v.cross(w).Normal();
            GL11.glNormal3f(normal.x, normal.y, normal.z);
            glTexCoord2f(0, 0);
            GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
            glTexCoord2f(1, 0);
            GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
            glTexCoord2f(1, 1);
            GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
        }

        GL11.glEnd();
    }
}
