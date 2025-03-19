package objects3D;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import java.io.IOException;

public class TexCube {

	public TexCube() {

	}

	// Implement using notes and looking at TexSphere
	public void DrawTexCube(Texture myTexture) {

		// get each points on the cube

		Point4f vertices[] = {new Point4f(-1.0f, -1.0f, -1.0f, 0.0f), new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(-1.0f, 1.0f, -1.0f, 0.0f), new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, -1.0f, -1.0f, 0.0f), new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, -1.0f, 0.0f), new Point4f(1.0f, 1.0f, 1.0f, 0.0f)};

		// represent each faces with the points
		int faces[][] = {{0, 4, 5, 1}, {0, 2, 6, 4}, {0, 1, 3, 2}, {4, 6, 7, 5}, {1, 5, 7, 3},
				{2, 3, 7, 6}};

		glBegin(GL_QUADS);

		for (int face = 0; face < 6; face++) { // per face

			// get the normal vector of the current face
			Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
			Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
			Vector4f normal = v.cross(w).Normal();

			// set the normal vector of the current face and draw a square
			glNormal3f(normal.x, normal.y, normal.z);


			// give surface parameters for each vertex
			glTexCoord2f(0, 0);
			glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
			glTexCoord2f(1, 0);
			glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
			glTexCoord2f(1, 1);
			glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
			glTexCoord2f(0, 1);
			glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
		} // per face

		glEnd();

	}
	public void DrawTexCube1(Texture myTexture) {
		Point4f[] vertices = new Point4f[]{new Point4f(-1.0F, -1.0F, -1.0F, 0.0F), new Point4f(-1.0F, -1.0F, 1.0F, 0.0F), new Point4f(-1.0F, 1.0F, -1.0F, 0.0F), new Point4f(-1.0F, 1.0F, 1.0F, 0.0F), new Point4f(1.0F, -1.0F, -1.0F, 0.0F), new Point4f(1.0F, -1.0F, 1.0F, 0.0F), new Point4f(1.0F, 1.0F, -1.0F, 0.0F), new Point4f(1.0F, 1.0F, 1.0F, 0.0F)};
		int[][] faces = new int[][]{{0, 4, 5, 1}, {0, 2, 6, 4}, {0, 1, 3, 2}, {4, 6, 7, 5}, {1, 5, 7, 3}, {2, 3, 7, 6}};
		GL11.glBegin(7);

		for(int face = 0; face < 6; ++face) {
			Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
			Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
			Vector4f normal = v.cross(w).Normal();
			GL11.glNormal3f(normal.x, normal.y, normal.z);
			GL11.glTexCoord2f(0.0F, 0.0F);
			GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
			GL11.glTexCoord2f(0.0F, 1.0F);
			GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
			GL11.glTexCoord2f(1.0F, 1.0F);
			GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
			GL11.glTexCoord2f(1.0F, 0.0F);
			GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
		}

		GL11.glEnd();
	}





}



/*
 * 
 * 
 * }
 * 
 */
