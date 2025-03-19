package objects3D;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class TexCylinder {
	
	public TexCylinder() {
		
	}

	public void DrawCylinder(float radius, float height, int nSegments, Texture texture ) 
	{
		 
		//nSegments = 5000;
		float x1, y1, z1, x2, y2, z2, s, t;
		float unit = (float) ((2.0f * Math.PI) / nSegments);
		float distance = radius / nSegments;
		
		/**
		 * 	Drawing Principle:
		 * 			divide the surface into pieces of triangles.
		 * 			Then draw each triangle follow the increasing of angle that each small triangle occupies.
		 * 			Finally, the cylinder is created successfully.
		 */
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0; i < (2 * Math.PI); i += unit) {
			
			x1 = (float) (Math.cos(i) * radius);
			y1 = (float) (Math.sin(i) * radius);
			z1 = height / 2;
			s = (float) (i / (Math.PI*2));
			x2 = (float) (Math.cos(i + unit) * radius);
			y2 = (float) (Math.sin(i + unit) * radius);
			z2 = - (height / 2);
			t = (float) ((i + unit) / (Math.PI*2));
			
			//Draw one triangle
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, z1);
			GL11.glTexCoord2f(s,0);
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, z2);
			GL11.glTexCoord2f(t, 1);
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, z2);
			GL11.glTexCoord2f(s,1);
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, z2);
			GL11.glTexCoord2f(t,1);
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, z1);
			GL11.glTexCoord2f(s,0);
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, z1);
			GL11.glTexCoord2f(t,0);
			
		}
		GL11.glEnd();
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float phi = 0; phi <= (2 * Math.PI); phi += unit) {
			
			x1 = (float) (Math.cos(phi) * radius);
			y1 = (float) (Math.sin(phi) * radius);
			z1 = height / 2;
			x2 = (float) (Math.cos(phi + unit) * radius);
			y2 = (float) (Math.sin(phi + unit) * radius);
			z2 = - (height / 2);
			
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
	
}
