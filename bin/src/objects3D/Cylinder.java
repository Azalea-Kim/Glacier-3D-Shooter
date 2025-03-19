package objects3D;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawNCylinder(float radius, float height, int nSegments )
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0.0f; i < nSegments; i += 1.0)
		{ /* a loop around circumference of a tube */
			float angle = (float) (PI * i * 2.0 / nSegments);
			float nextAngle = (float) (PI * (i + 1.0) * 2.0 / nSegments);
			/* compute sin & cosine */
			float x1 = (float) sin(angle)*radius, y1 = (float) cos(angle)*radius;
			float x2 = (float) sin(nextAngle)*radius, y2 = (float) cos(nextAngle)*radius;

			// get the points and the vectors of each point
			// p1 and p4 are shared by both the top and the bottom triangles.
			Point4f p1 = new Point4f(x1, y1, 0.0f, 0.0f);
			Vector4f n1 = new Vector4f(x1, y1, 0.0f, 0.0f);
			Point4f p2 = new Point4f(x2, y2, 0.0f, 0.0f);
			Vector4f n2 = new Vector4f(x2, y2, 0.0f, 0.0f);
			Point4f p3 = new Point4f(x1, y1, -height, 0.0f);
			Vector4f n3 = new Vector4f(x1, y1,- height, 0.0f);
			Point4f p4 = new Point4f(x2, y2, -height, 0.0f);
			Vector4f n4 = new Vector4f(x2, y2,- height, 0.0f);

			// Providing normals for each vertex make the cylinder smoother

			/* draw top (green) triangle */
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			GL11.glVertex3f(p1.x, p1.y, p1.z);

			GL11.glNormal3f(n4.x, n4.y, n4.z);
			GL11.glVertex3f(p4.x, p4.y, p4.z);

			GL11.glNormal3f(n3.x, n3.y, n3.z);
			GL11.glVertex3f(p3.x, p3.y, p3.z);


			/* draw bottom(red) triangle */
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			GL11.glVertex3f(p1.x, p1.y, p1.z);

			GL11.glNormal3f(n2.x, n2.y, n2.z);
			GL11.glVertex3f(p2.x, p2.y, p2.z);

			GL11.glNormal3f(n4.x, n4.y, n4.z);
			GL11.glVertex3f(p4.x, p4.y, p4.z);

		} // a loop around circumference of a tube

		GL11.glEnd();

		float x1, y1, z1, x2, y2, z2, s, t;

		float unit = (float) ((2.0f * Math.PI) / nSegments);
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float phi = 0; phi <= (2 * Math.PI); phi += unit) {

			x1 = (float) (Math.cos(phi) * radius);
			y1 = (float) (Math.sin(phi) * radius);
			z1 = -height;
			x2 = (float) (Math.cos(phi + unit) * radius);
			y2 = (float) (Math.sin(phi + unit) * radius);
			z2 = 0;

			GL11.glNormal3f(0, 0, z1);
			GL11.glVertex3f(x1, y1, z1);


			GL11.glNormal3f(0, 0, z1);
			GL11.glVertex3f(x2, y2, z1);

			GL11.glNormal3f(0, 0, z1);
			GL11.glVertex3f(0, 0, z1);

			GL11.glNormal3f(0, 0, z2);
			GL11.glVertex3f(x1, y1, z2);

			GL11.glNormal3f(0, 0, z2);
			GL11.glVertex3f(x2, y2, z2);

			GL11.glNormal3f(0, 0, z2);
			GL11.glVertex3f(0, 0, z2);
		}
		GL11.glEnd();

	}

	public void drawCylinder(float radius, float height, int nSegments )
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0.0f; i < nSegments; i += 1.0)
		{ /* a loop around circumference of a tube */
			float angle = (float) (PI * i * 2.0 / nSegments);
			float nextAngle = (float) (PI * (i + 1.0) * 2.0 / nSegments);
			/* compute sin & cosine */
			float x1 = (float) sin(angle)*radius, y1 = (float) cos(angle)*radius;
			float x2 = (float) sin(nextAngle)*radius, y2 = (float) cos(nextAngle)*radius;

			// get the points and the vectors of each point
			// p1 and p4 are shared by both the top and the bottom triangles.
			Point4f p1 = new Point4f(x1, y1, 0.0f, 0.0f);
			Vector4f n1 = new Vector4f(x1, y1, 0.0f, 0.0f);
			Point4f p2 = new Point4f(x2, y2, 0.0f, 0.0f);
			Vector4f n2 = new Vector4f(x2, y2, 0.0f, 0.0f);
			Point4f p3 = new Point4f(x1, y1, height, 0.0f);
			Vector4f n3 = new Vector4f(x1, y1, height, 0.0f);
			Point4f p4 = new Point4f(x2, y2, height, 0.0f);
			Vector4f n4 = new Vector4f(x2, y2,height, 0.0f);

			// Providing normals for each vertex make the cylinder smoother

			/* draw top (green) triangle */
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			GL11.glVertex3f(p1.x, p1.y, p1.z);

			GL11.glNormal3f(n4.x, n4.y, n4.z);
			GL11.glVertex3f(p4.x, p4.y, p4.z);

			GL11.glNormal3f(n3.x, n3.y, n3.z);
			GL11.glVertex3f(p3.x, p3.y, p3.z);


			/* draw bottom(red) triangle */
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			GL11.glVertex3f(p1.x, p1.y, p1.z);

			GL11.glNormal3f(n2.x, n2.y, n2.z);
			GL11.glVertex3f(p2.x, p2.y, p2.z);

			GL11.glNormal3f(n4.x, n4.y, n4.z);
			GL11.glVertex3f(p4.x, p4.y, p4.z);

		} // a loop around circumference of a tube

		GL11.glEnd();

	}





	public void drawTCylinder(float radius, float height, int nSegments , Texture texture)
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0.0f; i < nSegments; i += 1.0)
		{ /* a loop around circumference of a tube */
			float angle = (float) (PI * i * 2.0 / nSegments);
			float nextAngle = (float) (PI * (i + 1.0) * 2.0 / nSegments);
			/* compute sin & cosine */
			float x1 = (float) sin(angle)*radius, y1 = (float) cos(angle)*radius;
			float x2 = (float) sin(nextAngle)*radius, y2 = (float) cos(nextAngle)*radius;

			// get the points and the vectors of each point
			// p1 and p4 are shared by both the top and the bottom triangles.
			Point4f p1 = new Point4f(x1, y1, 0.0f, 0.0f);
			Vector4f n1 = new Vector4f(x1, y1, 0.0f, 0.0f);
			Point4f p2 = new Point4f(x2, y2, 0.0f, 0.0f);
			Vector4f n2 = new Vector4f(x2, y2, 0.0f, 0.0f);
			Point4f p3 = new Point4f(x1, y1, -height, 0.0f);
			Vector4f n3 = new Vector4f(x1, y1,- height, 0.0f);
			Point4f p4 = new Point4f(x2, y2, -height, 0.0f);
			Vector4f n4 = new Vector4f(x2, y2, -height, 0.0f);

			// Providing normals for each vertex make the cylinder smoother

			/* draw top (green) triangle */
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			GL11.glVertex3f(p1.x, p1.y, p1.z);
			GL11.glTexCoord2f(angle,0);

			GL11.glNormal3f(n4.x, n4.y, n4.z);
			GL11.glVertex3f(p4.x, p4.y, p4.z);

			GL11.glTexCoord2f(nextAngle,1);

			GL11.glNormal3f(n3.x, n3.y, n3.z);
			GL11.glVertex3f(p3.x, p3.y, p3.z);

			GL11.glTexCoord2f(angle,1);
			/* draw bottom(red) triangle */
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			GL11.glVertex3f(p1.x, p1.y, p1.z);
			GL11.glTexCoord2f(angle,0);


			GL11.glNormal3f(n2.x, n2.y, n2.z);
			GL11.glVertex3f(p2.x, p2.y, p2.z);
			GL11.glTexCoord2f(nextAngle,1);

			GL11.glNormal3f(n4.x, n4.y, n4.z);
			GL11.glVertex3f(p4.x, p4.y, p4.z);
			GL11.glTexCoord2f(nextAngle,0);

		} // a loop around circumference of a tube

		GL11.glEnd();

		float x1, y1, z1, x2, y2, z2, s, t;

		float unit = (float) ((2.0f * Math.PI) / nSegments);

		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float phi = unit/2; phi <= (2 * Math.PI)+unit/2; phi += unit) {

			x1 = (float) (Math.cos(phi) * radius);
			y1 = (float) (Math.sin(phi) * radius);
			z1 =-height;
			x2 = (float) (Math.cos(phi + unit) * radius);
			y2 = (float) (Math.sin(phi + unit) * radius);
			z2 = 0;



			glTexCoord2f(0, 0);
			GL11.glNormal3f(0, 0, z1);
			GL11.glVertex3f(x1, y1, z1);
			glTexCoord2f(1, 0);
			GL11.glNormal3f(0, 0, z1);
			GL11.glVertex3f(x2, y2, z1);
			glTexCoord2f(1, 1);
			GL11.glNormal3f(0, 0, z1);
			GL11.glVertex3f(0, 0, z1);

			glTexCoord2f(0, 0);
			GL11.glNormal3f(0, 0, z2);
			GL11.glVertex3f(x1, y1, z2);
			glTexCoord2f(1, 0);
			GL11.glNormal3f(0, 0, z2);
			GL11.glVertex3f(x2, y2, z2);
			glTexCoord2f(1, 1);
			GL11.glNormal3f(0, 0, z2);
			GL11.glVertex3f(0, 0, z2);
		}
		GL11.glEnd();



	}

	public void DrawCylinder(float radius, float height, int nSegments) {
		for (float i = 0; i < nSegments; i += 1) {
			// A loop around the circumference of the tube
			float angle = (float) (Math.PI * i * 2 / nSegments);
			float nextAngle = (float) (Math.PI * (i + 1) * 2 / nSegments);
			// Compute x1 and x2
			float x1 = (float) (radius * Math.sin(angle)), y1 = (float) (radius * Math.cos(angle));
			float x2 = (float) (radius * Math.sin(nextAngle)), y2 = (float) (radius * Math.cos(nextAngle));
			// To draw bottom part
			GL11.glBegin(GL11.GL_TRIANGLES);
			Vector4f v = new Vector4f(x1 - x2, y1 - y2, 0, 0);
			Vector4f w = new Vector4f(x1, y1, 0, 0);
			glNormalByVW(v,w);
			// Set normal for lights
			GL11.glVertex3f(x1, y1, 0);
			GL11.glVertex3f(x2, y2, 0);
			GL11.glVertex3f(0, 0, 0);
			GL11.glEnd();
			// To draw top part
			GL11.glBegin(GL11.GL_TRIANGLES);
			v = new Vector4f(x1 - x2, y1 - y2, 0, 0);
			w = new Vector4f(x1, y1, 0, 0);
			glNormalByVW(v,w);
			// Set normal for light.
			GL11.glVertex3f(x1, y1, height);
			GL11.glVertex3f(x2, y2, height);
			GL11.glVertex3f(0, 0, height);
			GL11.glEnd();
			//To draw top triangle part
			GL11.glBegin(GL11.GL_TRIANGLES);
			v = new Vector4f(x1 - x2, y1 - y2, 0 - height, 0);
			w = new Vector4f(0, 0, 0 - height, 0);
			glNormalByVW(v,w);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glVertex3f(x2, y2, height);
			GL11.glVertex3f(x1, y1, height);
			GL11.glEnd();
			// To draw bottom triangle part
			GL11.glBegin(GL11.GL_TRIANGLES);
			v = new Vector4f(x1 - x2, y1 - y2, 0, 0);
			w = new Vector4f(x1 - x2, y1 - y2, 0 - height, 0);
			glNormalByVW(v,w);
			// Set normal for light.
			GL11.glVertex3f(x1, y1, 0);
			GL11.glVertex3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);
			GL11.glEnd();
		}
	}
	// Encapsulation, convenient method reuse
	private void glNormalByVW(Vector4f v, Vector4f w){
		Vector4f normal = v.cross(w).Normal();
		GL11.glNormal3f(normal.x, normal.y, normal.z);
	}
}
