package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Soldiers {

	public static int newAngle = 0;
	public static int angle = 0;
	private float timePassed;
	private long TimeP;

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] ={ 1.0f, 0.0f, 0.0f, 1.0f }; // neck
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };  // changed to red

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] =  { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f }; //change to grey
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	public Soldiers() {

	}

	// Implement using notes in Animation lecture
	public void drawHuman(float delta, long tp) throws IOException {

		if (Math.abs(angle - newAngle) > 200) {
			angle = newAngle;
		}

		if (angle > newAngle) {
			angle -= 1f;
		} else if (angle < newAngle) {
			angle += 1f;
		}



		timePassed  = tp;
		TimeP = (long) (timePassed/10000f);





		if ((int) (TimeP % 4) == 0) {

			newAngle = 90;

			glTranslatef((float) ((delta-Math.floor(delta/4)*4)*10),0,0);

		}
		if ((int) (TimeP % 4) == 1) {

			newAngle = 90 + 90;
			glTranslatef(10F, (float) 0, (float) (-(delta-Math.floor(delta/4)*4)*10+10));

		}
		if ((int) (TimeP % 4) == 2) {
			newAngle = 90 + 90 + 90;

			glTranslatef((float) (30-(delta-Math.floor(delta/4)*4)*10),0,-10);
//			System.out.println((delta-Math.floor(delta/40)*40)*10);
		}
		if ((int) (TimeP % 4) == 3) {

			newAngle = 90 + 90 + 90 + 90;
			glTranslatef((float) 0, (float) 0, (float) ((delta-Math.floor(delta/4)*4)*10-40));
//			System.out.println((delta-Math.floor(delta/40)*40)*10);



		}

		GL11.glRotatef(angle + 180, 0, 1, 0);



		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;
		LimbRotation = (float) Math.cos(10*theta) * 45;

		Sphere sphere = new Sphere();
		TexSphere texSphere = new TexSphere();
		Cylinder cylinder = new Cylinder();

		glPushMatrix();

		{
			glTranslatef(0.0f, 0.5f, 0.0f);

			// slightly rotate the human as a whole as the human walks, making the direction of human's velocity vertical to radius of the circle path
//			glRotatef((float) ((-theta*180f)/Math.PI)+180, 0.0f, 0.5f, 0.0f);
			sphere.drawSphere(0.5f, 32, 32);

			// chest
			glColor3f(green[0], green[1], green[2]);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			glPushMatrix();
			{
				glTranslatef(0.0f, 0.5f, 0.0f);

				// make the chest rotate with the same direction as the arm that is at the front
				glRotatef(LimbRotation/2, 0.0f, 0.5f, 0.0f);
				sphere.drawSphere(0.5f, 32, 32);

				// neck
				glColor3f(grey[0], grey[1], grey[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
				glPushMatrix();
				{
					glTranslatef(0.0f, 0.0f, 0.0f);
					glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
					cylinder.drawCylinder(0.15f, 0.7f, 32);

					// head
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 1.0f);
						sphere.drawSphere(0.5f, 32, 32);
						glPopMatrix();
					}
					glPopMatrix();

					// left shoulder
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(0.5f, 0.4f, 0.0f);
						// rotate the left shoulder front and back
						glRotatef(-LimbRotation/4, 1.0f, 0.0f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);


						// left arm
						glColor3f(grey[0], grey[1], grey[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							// rotate the left upper arm front and back while walking
							glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
							// glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// left elbow
							glColor3f(red[0], red[1], red[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);
								sphere.drawSphere(0.2f, 32, 32);

								// left forearm
								glColor3f(grey[0], grey[1], grey[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);

									// rotate the left forearm within the moving plane of the upper arm but in opposite direction to make the movement more natural
									glRotatef(-(LimbRotation/2)+90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(red[0], red[1], red[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// to chest

					// right shoulder
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(-0.5f, 0.4f, 0.0f);
						// rotate the right shoulder front and back
						glRotatef(-LimbRotation/4, 1.0f, 0.0f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);
						// right arm
						glColor3f(grey[0], grey[1], grey[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							// rotate the right upper arm front and back while walking
							glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
							// glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// right elbow
							glColor3f(red[0], red[1], red[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);

								sphere.drawSphere(0.2f, 32, 32);

								// right forearm
								glColor3f(grey[0], grey[1], grey[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);

									// rotate the right forearm within the moving plane of the upper arm but in opposite direction to make the movement more natural
									glRotatef((LimbRotation/2)+90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);


									// right hand
									glColor3f(red[0], red[1], red[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);

									}
									glPopMatrix();
								}glPopMatrix();
							}glPopMatrix();
						}glPopMatrix();
					}
					glPopMatrix();
					// chest

				}
				glPopMatrix();

				// pelvis

				// left hip
				glColor3f(red[0], red[1], red[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
				glPushMatrix();
				{
					glTranslatef(-0.5f, -0.2f, 0.0f);

					// rotate the left hip
					glRotatef(-LimbRotation/2,-1.0f, 0.0f, 0.0f);
					sphere.drawSphere(0.25f, 32, 32);

					// left high leg
					glColor3f(grey[0], grey[1], grey[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						// rotate the left high leg with the same direction as the left hip
						glRotatef((LimbRotation / 4) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// left knee
						glColor3f(red[0], red[1], red[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							sphere.drawSphere(0.25f, 32, 32);

							// left low leg
							glColor3f(grey[0], grey[1], grey[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);

								// rotate the left low leg with the same direction as the left high leg but slower
								// make the leg horizontal as a whole when it reaches the highest point
								glRotatef(-20.0f+(LimbRotation*20/45),1.0f,0.0f,0.0f);
								// glRotatef(0.0f,0.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// left foot
								glColor3f(red[0], red[1], red[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.drawSphere(0.3f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

				// pelvis

				// right hip
				glColor3f(red[0], red[1], red[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
				glPushMatrix();
				{
					glTranslatef(0.5f, -0.2f, 0.0f);

					// rotate the right hip
					glRotatef(LimbRotation/2,-1.0f, 0.0f, 0.0f);
					sphere.drawSphere(0.25f, 32, 32);

					// right high leg
					glColor3f(grey[0], grey[1], grey[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);

						// rotate the right high leg with the same direction as the right hip
						glRotatef((-LimbRotation / 4) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// right knee
						glColor3f(red[0], red[1], red[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							sphere.drawSphere(0.25f, 32, 32);

							// right low leg
							glColor3f(grey[0], grey[1], grey[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);

								// rotate the right low leg with the same direction as the right high leg but slower
								// make the leg horizontal as a whole when it reaches the highest point
								glRotatef(-20-(LimbRotation*20/45),1.0f,0.0f,0.0f);
//								 glRotatef(45.0f-(LimbRotation/2),0.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// right foot
								glColor3f(red[0], red[1], red[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.drawSphere(0.3f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

			}
			glPopMatrix();

		}

	}

	public void drawHumanTexture(float delta, float tp, Texture texture_head, Texture texture_chest, Texture texture_pelvis, Texture spheret) throws IOException {
		if (Math.abs(angle - newAngle) > 200) {
			angle = newAngle;
		}

		if (angle > newAngle) {
			angle -= 1f;
		} else if (angle < newAngle) {
			angle += 1f;
		}


		timePassed  = tp;
		TimeP = (long) (timePassed/10000f);
		if ((int) (TimeP % 4) == 0) {

			newAngle = 90;
			glTranslatef((float) ((delta-Math.floor(delta/4)*4)*10),0,0);

		}
		if ((int) (TimeP % 4) == 1) {

			newAngle = 90 + 90;
			glTranslatef(10F, (float) 0, (float) (-(delta-Math.floor(delta/4)*4)*10+10));
		}
		if ((int) (TimeP % 4) == 2) {
			newAngle = 90 + 90 + 90;

			glTranslatef((float) (30-(delta-Math.floor(delta/4)*4)*10),0,-10);
		}
		if ((int) (TimeP % 4) == 3) {

			newAngle = 90 + 90 + 90 + 90;
			glTranslatef((float) 0, (float) 0, (float) ((delta-Math.floor(delta/4)*4)*10-40));


		}


		GL11.glRotatef(angle + 180, 0, 1, 0);



		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;
		LimbRotation = (float) Math.cos(10*theta) * 45;

		TexSphere texSphere = new TexSphere();
		Cylinder cylinder = new Cylinder();

		glPushMatrix();

		{
			glTranslatef(0.0f, 0.5f, 0.0f);

			// slightly rotate the human as a whole as the human walks, making the direction of human's velocity vertical to radius of the circle path


			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();

			// bind texture
			texture_pelvis.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			texSphere.DrawTexSphere(0.5f, 32, 32,texture_pelvis);

			// chest
			glColor3f(green[0], green[1], green[2]);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			glPushMatrix();
			{
				glTranslatef(0.0f, 0.5f, 0.0f);

				// make the chest rotate with the same direction as the arm that is at the front
				glRotatef(LimbRotation/2, 0.0f, 0.5f, 0.0f);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
				Color.white.bind();
				texture_chest.bind();
				glEnable(GL_TEXTURE_2D);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
				texSphere.DrawTexSphere(0.5f, 32, 32,texture_chest);

				// neck
				glColor3f(grey[0], grey[1], grey[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
				glPushMatrix();
				{
					glTranslatef(0.0f, 0.0f, 0.0f);
					glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);

					cylinder.drawCylinder(0.15f, 0.7f, 32);

					// head
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 1.0f);
						glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
						Color.white.bind();
						texture_head.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
						texSphere.DrawTexSphere(0.5f, 32, 32,texture_head);
						glPopMatrix();

					}
					glPopMatrix();

					// left shoulder
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(0.5f, 0.4f, 0.0f);

						// rotate the left shoulder front and back
						glRotatef(+LimbRotation/4, 1.0f, 0.0f, 0.0f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
						Color.white.bind();
						spheret.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
						texSphere.DrawTexSphere(0.25f, 32, 32,spheret);
//						sphere.drawSphere(0.25f, 32, 32);


						// left arm
						glColor3f(grey[0], grey[1], grey[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);


							// rotate the left upper arm front and back while walking
							glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// left elbow
							glColor3f(red[0], red[1], red[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
								Color.white.bind();
								spheret.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
								texSphere.DrawTexSphere(0.2f, 32, 32,spheret);
//								sphere.drawSphere(0.2f, 32, 32);

								// left forearm
								glColor3f(grey[0], grey[1], grey[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);


									// rotate the left forearm within the moving plane of the upper arm but in opposite direction to make the movement more natural
									glRotatef(-(LimbRotation/2)+90.0f, 1.0f, 0.0f, 0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(red[0], red[1], red[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
										Color.white.bind();
										spheret.bind();
										glEnable(GL_TEXTURE_2D);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
										texSphere.DrawTexSphere(0.2f, 32, 32,spheret);
//										sphere.drawSphere(0.2f, 32, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// to chest

					// right shoulder
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(-0.5f, 0.4f, 0.0f);
						// rotate the right shoulder front and back
						glRotatef(-LimbRotation/4, 1.0f, 0.0f, 0.0f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
						Color.white.bind();
						spheret.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
						texSphere.DrawTexSphere(0.25f, 32, 32,spheret);
//						sphere.drawSphere(0.25f, 32, 32);
						// right arm
						glColor3f(grey[0], grey[1], grey[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);


							// rotate the right upper arm front and back while walking
							glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
							// glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);
							// right elbow
							glColor3f(red[0], red[1], red[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);

								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
								Color.white.bind();
								spheret.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
								texSphere.DrawTexSphere(0.2f, 32, 32,spheret);

//								sphere.drawSphere(0.2f, 32, 32);

								// right forearm
								glColor3f(grey[0], grey[1], grey[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);


									// rotate the right forearm within the moving plane of the upper arm but in opposite direction to make the movement more natural
									glRotatef((LimbRotation/2)+90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);


									// right hand
									glColor3f(red[0], red[1], red[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
										Color.white.bind();
										spheret.bind();
										glEnable(GL_TEXTURE_2D);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
										texSphere.DrawTexSphere(0.2f, 32, 32,spheret);
//										sphere.drawSphere(0.2f, 32, 32);
									}
									glPopMatrix();
								}glPopMatrix();
							}glPopMatrix();
						}glPopMatrix();
					}
					glPopMatrix();
					// chest

				}
				glPopMatrix();

				// pelvis

				// left hip
				glColor3f(red[0], red[1], red[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
				glPushMatrix();
				{
					glTranslatef(-0.5f, -0.2f, 0.0f);
					// rotate the left hip
					glRotatef(-LimbRotation/2,-1.0f, 0.0f, 0.0f);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
					Color.white.bind();
					spheret.bind();
					glEnable(GL_TEXTURE_2D);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
					texSphere.DrawTexSphere(0.25f, 32, 32,spheret);
//					sphere.drawSphere(0.25f, 32, 32);

					// left high leg
					glColor3f(grey[0], grey[1], grey[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

						// rotate the left high leg with the same direction as the left hip
						glRotatef((LimbRotation / 4) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// left knee
						glColor3f(red[0], red[1], red[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
							Color.white.bind();
							spheret.bind();
							glEnable(GL_TEXTURE_2D);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
							texSphere.DrawTexSphere(0.25f, 32, 32,spheret);
//							sphere.drawSphere(0.25f, 32, 32);

							// left low leg
							glColor3f(grey[0], grey[1], grey[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);

								// rotate the left low leg with the same direction as the left high leg but slower
								// make the leg horizontal as a whole when it reaches the highest point
								glRotatef(-20.0f+(LimbRotation*20/45),1.0f,0.0f,0.0f);
								// glRotatef(0.0f,0.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// left foot
								glColor3f(red[0], red[1], red[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
									Color.white.bind();
									spheret.bind();
									glEnable(GL_TEXTURE_2D);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
									texSphere.DrawTexSphere(0.3f, 32, 32,spheret);
//									sphere.drawSphere(0.3f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

				// pelvis

				// right hip
				glColor3f(red[0], red[1], red[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
				glPushMatrix();
				{
					glTranslatef(0.5f, -0.2f, 0.0f);

					// rotate the right hip
					glRotatef(LimbRotation/2,-1.0f, 0.0f, 0.0f);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
					Color.white.bind();
					spheret.bind();
					glEnable(GL_TEXTURE_2D);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
					texSphere.DrawTexSphere(0.25f, 32, 32,spheret);
//					sphere.drawSphere(0.25f, 32, 32);

					// right high leg
					glColor3f(grey[0], grey[1], grey[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

						// rotate the right high leg with the same direction as the right hip
						glRotatef((-LimbRotation / 4) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// right knee
						glColor3f(red[0], red[1], red[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
							Color.white.bind();
							spheret.bind();
							glEnable(GL_TEXTURE_2D);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
							texSphere.DrawTexSphere(0.25f, 32, 32,spheret);
//							sphere.drawSphere(0.25f, 32, 32);
							// right low leg
							glColor3f(grey[0], grey[1], grey[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								// rotate the right low leg with the same direction as the right high leg but slower
								// make the leg horizontal as a whole when it reaches the highest point
								glRotatef(-20-(LimbRotation*20/45),1.0f,0.0f,0.0f);
//								 glRotatef(45.0f-(LimbRotation/2),0.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// right foot
								glColor3f(red[0], red[1], red[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
									Color.white.bind();
									spheret.bind();
									glEnable(GL_TEXTURE_2D);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
									texSphere.DrawTexSphere(0.3f, 32, 32,spheret);
//									sphere.drawSphere(0.3f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();



			}
			glPopMatrix();

		}

	}

}

/*
 *
 *
 * }
 *
 */
