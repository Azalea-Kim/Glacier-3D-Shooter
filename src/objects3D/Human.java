package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class Human {

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

	public Human() {

	}


	// Implement using notes in Animation lecture
	public void drawHuman(float delta, boolean GoodAnimation) throws IOException {
		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;
		if (GoodAnimation) {
			LimbRotation = (float) Math.cos(10*theta) * 45;
		} else {
			LimbRotation = 0;
		}

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

	public void drawHumanTexture(float delta, boolean GoodAnimation, Texture texture_head, Texture texture_chest, Texture texture_pelvis, Texture spheret, Texture cylindert) throws IOException {
		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;
		if (GoodAnimation) {
			LimbRotation = (float) Math.cos(10*theta) * 45;
		} else {
			LimbRotation = 0;
		}

		Sphere sphere = new Sphere();
		TexSphere texSphere = new TexSphere();
		Cylinder cylinder = new Cylinder();

		glPushMatrix();

		{
			glTranslatef(0.0f, 0.5f, 0.0f);

			// slightly rotate the human as a whole as the human walks, making the direction of human's velocity vertical to radius of the circle path

//			glRotatef((float) ((-theta*180f)/Math.PI)+180, 0.0f, 0.5f, 0.0f);

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
					Color.white.bind();
					cylinder.drawCylinder(0.15f, 0.7f, 32);

//					cylinder.drawTCylinder(0.15f, 0.7f, 32,cylindert);

//					DisplayListCylinder ice4 = new DisplayListCylinder(0.15f, 0.7f, 32);
//					glTranslatef(0.0f, 0.0f, 0.0f);
//					glColor3f(1, 1, 1);
//					GL11.glTexParameteri(3553, 10240, 9728);
//					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//					Color.white.bind();
//					cylindert.bind();
//					glEnable(GL_TEXTURE_2D);
//					ice4.DrawCylinder();


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

						glRotatef(90, 1.0f, 0.0f, 1f);
						glRotatef(+LimbRotation/4, 0f, 0.0f, 1.0f);

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
							Color.white.bind();
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
							glRotatef(270, 0f, 0f, 1f);
							glRotatef(90,1,0,0);
							glRotatef(+LimbRotation/4, 0f, 0.0f, 1.0f);


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
//					glRotatef(-LimbRotation/2,-1.0f, 0.0f, 0.0f);
					glRotatef(-90,-1.0f, 0.0f, 0.0f);
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
//					glRotatef(LimbRotation/2,-1.0f, 0.0f, 0.0f);
					glRotatef(-90,-1.0f, 0.0f, 0.0f);

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
