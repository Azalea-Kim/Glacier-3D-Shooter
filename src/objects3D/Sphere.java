package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import static java.lang.Math.*;
import static java.lang.Math.sin;
import static org.lwjgl.opengl.GL11.*;

public class Sphere {

	public Sphere() {

	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at
	// lecture 7 , 7b and 8
	// 7b should be your primary source, we will cover more about circles in later
	// lectures to understand why the code works
	public void drawSphere(float radius, float nSlices, float nSegments) {
// transfer the meaning of segments and slices to variations that could be applied to actual degree calculation.
		float inctheta = (float) ((2.0f*PI)/nSlices); // variation in longitude
		float incphi = (float) (PI/nSegments); // variation in latitude

		// draw quads
		GL11.glBegin(GL11.GL_QUADS);
		for(float theta = (float) -PI; theta<PI; theta+=inctheta) // loop through longitude
		{
			for(float phi = (float) -(PI/2.0f); phi<(PI/2.0f); phi+=incphi) // loop through latitude on each longitude
			{
				// get four points of each surface segment(quad)

				//point 1
				float x = (float) (radius*cos(phi)*cos(theta));
				float y = (float) (radius*cos(phi)*sin(theta));
				float z = (float) (radius*sin(phi));

				//point 2 (one incphi latitude above and same longitude with point 1)
				float x1 = (float) (radius*cos(phi +incphi)*cos(theta));
				float y1 = (float) (radius*cos(phi +incphi)*sin(theta));
				float z1 = (float) (radius*sin(phi+incphi));

				//point 3 (same latitude with point 1 and one inctheta next to point 1)
				float x2 = (float) (radius*cos(phi)*cos(theta+inctheta));
				float y2 = (float) (radius*cos(phi)*sin(theta+inctheta));
				float z2 = (float) (radius*sin(phi));


				//point 4 (one incphi latitude above and one inctheta next to point 1)
				float x3 = (float) (radius*cos(phi +incphi)*cos(theta+inctheta));
				float y3 = (float) (radius*cos(phi +incphi)*sin(theta+inctheta));
				float z3 = (float) (radius*sin(phi+incphi));


				// get the points and normal vectors for each points
				Point4f p1 = new Point4f(x, y, z, 0.0f);
				Vector4f n1 = new Vector4f(x, y, z, 0.0f);

				Point4f p3 = new Point4f(x2, y2, z2, 0.0f);
				Vector4f n3 = new Vector4f(x2, y2, z2, 0.0f);

				Point4f p2 = new Point4f(x1, y1, z1, 0.0f);
				Vector4f n2 = new Vector4f(x1, y1, z1, 0.0f);


				Point4f p4 = new Point4f(x3, y3, z3, 0.0f);
				Vector4f n4 = new Vector4f(x3, y3, z3, 0.0f);


				// provide normal vector for each vertex to make the surface smoother

				// draw the quad in an anti-clockwise manner, which is 1 3 4 2.

				GL11.glNormal3f(n1.x, n1.y, n1.z);
				GL11.glVertex3f(p1.x, p1.y, p1.z);

				GL11.glNormal3f(n3.x, n3.y, n3.z);
				GL11.glVertex3f(p3.x, p3.y, p3.z);


				GL11.glNormal3f(n4.x, n4.y, n4.z);
				GL11.glVertex3f(p4.x, p4.y, p4.z);

				GL11.glNormal3f(n2.x, n2.y, n2.z);
				GL11.glVertex3f(p2.x, p2.y, p2.z);;




			}
		}

		GL11.glEnd();



	}
}
