
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment.
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment.
//

// Do not touch this class, I will be making a version of it for your 3rd Assignment
public class MainWindow {


	private boolean hit = false;

	private boolean icRight = true;

	int jump = 0;

	private static float timePassed;

	private boolean MouseOnepressed = true;
	public static Camera camera = new Camera();
	Player player =new Player();
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean Earth = false;

	private boolean isGod = false;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;

	private boolean Switch  = false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	Arcball MyArcball = new Arcball();

	float icapB = -2500;
	float icapL;
	float icapS;
	float deltaTmp = 0;
	float startTime;
	private int rotate = 0;
	private boolean HouseStatus = false;

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

	int move = 0;
	int direction = 0;

	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.


	int OrthoNumber = 3000; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
	// // do not change this for assignment 3 but you can change everything for your
	// project

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	private static final ArrayList<Vector4f> positions = new ArrayList<>();
	private static final ArrayList<Vector4f> rotations = new ArrayList<>();

	static int bullet_counter;

	public MainWindow() throws IOException {
	}


	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process

	public void start() throws IOException {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		gluPerspective((float) 60, Display.getWidth() / Display.getHeight(), 100f, 50000f);
		GL11.glMatrixMode(GL_MODELVIEW);
		GL11.glMatrixMode(GL_MODELVIEW);



//		camera.setCamera(new Vector4f(
//				0, 2800, 0, 0
//		));
//
//		positions.add(new Vector4f(0, 0, 0, 300));
//		rotations.add(new Vector4f(0, 0, 0, 300));
////
//		positions.add(new Vector4f(1000, 1000, -1000, 2000));
//		rotations.add(new Vector4f(60, 45, 60, 2000));
//
//		positions.add(new Vector4f(1000, 1300, -1000, 2000));// 4000
//		rotations.add(new Vector4f(120, 70, 60, 2000));
//
//		positions.add(new Vector4f(1000, 1700, -1000, 2000));
//		rotations.add(new Vector4f(120, 75, 120, 2000));
////
//		positions.add(new Vector4f(-1000, 2000, -1000, 2500));
//		rotations.add(new Vector4f(150, 35, 150, 2500));
//
////
//		positions.add(new Vector4f(3500, 3500, 7000, 2000));
//		rotations.add(new Vector4f(180, 35, 150, 2000));
//
//
//		positions.add(new Vector4f(3500, 3500, 7000, 2000));
//		rotations.add(new Vector4f(180, 35, 150, 2000));
//
//
//		positions.add(new Vector4f(3500, 3500, 7000, 2000));
//		rotations.add(new Vector4f(210, 35, 120, 2000));
//
//		positions.add(new Vector4f(4000, 2000, 4000, 2000));
//		rotations.add(new Vector4f(210, 35, 120, 2000));
//
//		positions.add(new Vector4f(3200, -1200, 3800, 2000));
//		rotations.add(new Vector4f(210, 50, 220, 2000));
//
//		positions.add(new Vector4f(3200, -1200, 3800, 1000));
//		rotations.add(new Vector4f(210, 50, 220, 1000));
//
//		positions.add(new Vector4f(3400, -700, 3800, 2000));
//		rotations.add(new Vector4f(210, 100, 160, 2000));
//		positions.add(new Vector4f(3400, -700, 3800, 1000));
//		rotations.add(new Vector4f(210, 100, 160, 1000));
/////
//		positions.add(new Vector4f(3800, -700, 4200, 2000));
//		rotations.add(new Vector4f(210, 70, 190, 2000));
//
//		positions.add(new Vector4f(3800, -700, 4200, 2000));//
//		rotations.add(new Vector4f(210, 70, 190, 2000));
//
//
//
//		positions.add(new Vector4f(-400, -1750, 3850, 2000));
//		rotations.add(new Vector4f(160, 20, 170, 2000));
//
////		positions.add(new Vector4f(-400, -1750, 3850, 2000));
////		rotations.add(new Vector4f(160, 20, 170, 2000));
//
//
//		Runnable cameraRunnable = new Runnable() {
//			@Override
//			public void run() {
//				Camera.position = new Vector4f(
//						1000, 2000, 1000, 0
//				);
//				Camera.rotation = new Vector3f(0, 0, 400);
//				for (int i = 0; i < positions.size(); i++) {
//					Vector4f pos = positions.get(i);
//					Vector4f rot = rotations.get(i);
//					Vector4f originalPos = new Vector4f(Camera.position.x, Camera.position.y, Camera.position.z, Camera.position.a);
//					Vector3f originalRot = new Vector3f(Camera.rotation.x, Camera.rotation.y, Camera.rotation.z);
//					Vector4f curPos = Camera.position;
//					Vector3f curRot = Camera.rotation;
//					float posDisX = pos.x - curPos.x;
//					float posDisY = pos.y - curPos.y;
//					float posDisZ = pos.z - curPos.z;
//					float rotDisX = rot.x - curRot.x;
//					float rotDisY = rot.y - curRot.y;
//					float rotDisZ = rot.z - curRot.z;
//					float posDisX_step = posDisX / pos.a;
//					float posDisY_step = posDisY / pos.a;
//					float posDisZ_step = posDisZ / pos.a;
//					float rotDisX_step = rotDisX / pos.a;
//					float rotDisY_step = rotDisY / pos.a;
//					float rotDisZ_step = rotDisZ / pos.a;
//					float count = 0f;
//					while (count < pos.a) {
//
//						curPos.x = (float) (originalPos.x + posDisX_step * count * Math.sin(count / pos.a * Math.PI / 2));
//						curPos.y = (float) (originalPos.y + posDisY_step * count * Math.sin(count / pos.a * Math.PI / 2));
//						curPos.z = (float) (originalPos.z + posDisZ_step * count * Math.sin(count / pos.a * Math.PI / 2));
//
//						curRot.x = (float) (originalRot.x + rotDisX_step * count * Math.sin(count / pos.a * Math.PI / 2));
//						curRot.y = (float) (originalRot.y + rotDisY_step * count * Math.sin(count / pos.a * Math.PI / 2));
//						curRot.z = (float) (originalRot.z + rotDisZ_step * count * Math.sin(count / pos.a * Math.PI / 2));
//						count += 1;
//						try {
//							Thread.sleep(1);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//				Camera.finishedLoading = true;
//			}
//		};
//
//		new Thread(cameraRunnable).start();



		while (!Display.isCloseRequested()) {

			glLoadIdentity();
			camera.updatePosition();
			player.updatePosition();


			camera.update_keyboard(getDelta());
			player.update_player(getDelta(),isGod);
			int delta = getDelta();
			update(delta);
//			player.checkInput(delta);


			camera.setCamera(new Vector4f(
					0, -2600, -2600 ,0
			));
			Camera.maxLookDown = -360;
			Camera.maxLookUp = 360;

//			camera.setCamera(new Vector4f(
//					0, -2300, -1000, 0
//			));
//			Camera.maxLookDown = -360;
//			Camera.maxLookUp = 360;

			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}


	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;

		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();

		boolean MouseButonPressed = Mouse.isButtonDown(0);

		if (MouseButonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			// System.out.println("Mouse drag mode");
			MyArcball.startBall(MouseX, MouseY, 1200, 800);
			dragMode = true;

		} else if (!MouseButonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		if (dragMode) {
			MyArcball.updateBall(MouseX, MouseY, 1200, 800);
		}

		if (WheelPostion > 0) {
			OrthoNumber += 10;

		}

		if (WheelPostion < 0) {
			OrthoNumber -= 10;
			if (OrthoNumber < 610) {
				OrthoNumber = 610;
			}

			// System.out.println("Orth nubmer = " + OrthoNumber);

		}

		/** rest key is R */
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			MyArcball.reset();

		/* bad animation can be turn on or off using A key) */

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			BadAnimation = !BadAnimation;
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			y += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			y -= 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			Earth = !Earth;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			isGod = !isGod;
		}





		if (waitForKeyrelease) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {

				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
					waitForKeyrelease = true;
				} else {
					waitForKeyrelease = false;

				}
			}
		}

		/** to check if key is released */
		if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
			waitForKeyrelease = true;
		} else {
			waitForKeyrelease = false;

		}


		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter

		LastMouseX = MouseX;
		LastMouseY = MouseY;}


	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}


	public void initGL() {

		glEnable(GL13.GL_MULTISAMPLE);
//        Display.setResizable(true);
		glClear(GL_COLOR_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();



//		changeOrth();

		GL11.glOrtho(0, 1600, 800, 0, 1, -1);

		GL11.glMatrixMode(GL_MODELVIEW);
//		MyArcball.startBall(0, 0, 1200, 800);


//		// the light from the sun and sky
		FloatBuffer lightPos41 = BufferUtils.createFloatBuffer(4);
		lightPos41.put(-400).put(2000).put(-800).put(0).flip();

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos41);
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(10000f).put(10000f).put(5000).put(0).flip();

		FloatBuffer lightPosition2 = BufferUtils.createFloatBuffer(4);
		lightPosition2.put(0f).put(10000f).put(0).put(0).flip();

		FloatBuffer lightPosition3 = BufferUtils.createFloatBuffer(4);
		lightPosition3.put(0).put(3000f).put(0).put(1).flip();

		FloatBuffer noAmbient = BufferUtils.createFloatBuffer(4);
		FloatBuffer diffuse =BufferUtils.createFloatBuffer(4);
		FloatBuffer specular =BufferUtils.createFloatBuffer(4);
		FloatBuffer direction = BufferUtils.createFloatBuffer(4);


		noAmbient.put(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
		noAmbient.rewind();
		diffuse.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
		diffuse.rewind();
		specular.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
		specular.rewind();
		direction.put(new float[]{0f, 0f, -1f, 0});
		direction.rewind();

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);

		GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_QUADRATIC_ATTENUATION, 0.5f);

		GL11.glEnable(GL11.GL_LIGHT0); // SUN_light


		// ambient
		GL11.glLight(GL11.GL_LIGHT1, GL_POSITION, lightPosition2);
		float[] ambient = {0.2f, 0.2f, 0.2f, 1.0f};
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
		GL11.glLight(GL11.GL_LIGHT1, GL_AMBIENT, Utils.ConvertForGL(ambient));
		GL11.glEnable(GL11.GL_LIGHT1);

		// diffuse
		GL11.glLight(GL11.GL_LIGHT2, GL_POSITION, lightPosition3);
		GL11.glLight(GL11.GL_LIGHT2, GL_DIFFUSE, diffuse);
		GL11.glLight(GL11.GL_LIGHT2, GL_SPECULAR, specular);
		GL11.glLight(GL11.GL_LIGHT2, GL_SPOT_DIRECTION, direction);
		GL11.glLightf(GL11.GL_LIGHT2, GL_SPOT_CUTOFF, 40);


		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
		// on
		GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);





		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
		glMatrixMode(GL_MODELVIEW);

		gluPerspective((float) 60, Display.getWidth() / Display.getHeight(), 100f, 50000f);

		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);
//
//		 if(MouseOnepressed)
//		 {

		MyArcball.getMatrix(CurrentMatrix);
//		 }

		glLoadMatrix(CurrentMatrix);

	}






	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 *
	 */

	public void renderGL() throws IOException {
		updateFPS();

//		changeOrth();
		camera.update_mouse();
		camera.update_keyboard(getDelta());


//		checking

		Boolean ishit = false;

//		System.out.println(Player.getGlobalPos().x);
		if (player.checkHit()) {
			System.out.println("hit");
					ishit = true;
					player.hit(200,3000);
				}
		if (!ishit)
			player.update_player(getDelta(),isGod);


		timePassed = getTime() - StartTime;

//		GLU.gluLookAt(0, -200, 1500, x, 350, -800 + y, 0, 1, 0);
		GLU.gluLookAt(0, 0, y, x, 1000, -800 + y, 0, 1, 0);

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta);


		GL11.glPushMatrix();
		Skybox skybox = new Skybox(
				new Point4f(0, 0, 0, 0),
				new Point4f(0, 0.2F, 0, 0),
				new Vector4f(30000, 30000, 30000, 0),texture_down,texture_front,texture_right,texture_left,texture_back,texture_up);
		skybox.DrawSkyBox();

		GL11.glPopMatrix();

		/*
		 * This code draws a grid to help you view the human models movement You may
		 * change this code to move the grid around and change its starting angle as you
		 * please
		 */
		if (DRAWGRID) {
			glPushMatrix();
			Grid MyGrid = new Grid();
			glTranslatef(200, 400, 0);
			glScalef(200f, 200f, 200f);
			MyGrid.DrawGrid();
			glPopMatrix();
		}


//
//		if (Mouse.isButtonDown(0) & bullet_counter <= 0) {
//			System.out.println("pressed button");
//			bullet_counter = 30;
//			Bullet bullet = new Bullet(Camera.getPosition(),
//					new Vector4f(90, 90, 90, 0),
//					texture_cube
//
//			);
//			bullet.setDirection(new Vector4f(
//					(float) Math.sin(Math.toRadians(180 - Camera.rotation.y)),
//					0,
//					(float) Math.cos(Math.toRadians(180 - Camera.rotation.y)),
//					0
//			));
//			GL11.glPushMatrix();
//			bullet.draw((int) delta);
//
//
//
//			GL11.glPopMatrix();
//		}
//
//
//
//		if (bullet_counter > 0) {
//			bullet_counter--;
//		}




		GL11.glPushMatrix();
		player.setPos(new Point4f(200,450,-500,0), 90);

		GL11.glTranslatef(0 , 200, 0 );
		glScalef(90f, 90f, 90f);


		player.DrawPlayer(delta);


		GL11.glPopMatrix();


		GL11.glPushMatrix();
		Bunny bunny = new Bunny(new Point4f(200,700,500,0));
		bunny.checkBulltHit();

//		GL11.glRotatef(theta,0,1,0);

//		glRotatef((float) ((-theta*180f)/Math.PI)+180, 0.0f, 0.5f, 0.0f);

		GL11.glTranslatef(200,700,500);
		glScalef(90, 90, 90);

//
//		player.DrawPlayer(delta);
		bunny.draw(getDelta(),getTimePassed());


		GL11.glPopMatrix();






		//soldiers
		glPushMatrix();
		Soldiers so = new Soldiers();
		glTranslatef(500, 400, 0);
		glScalef(90f, 90f, 90f);
		so.drawHumanTexture(delta,getTimePassed(),head, chest, pelvis, round);
//		so.drawHuman(delta,getTimePassed());
		glPopMatrix();

		glPushMatrix();
		Soldiers so1 = new Soldiers();
		glTranslatef(500, 400, 0);
		glScalef(90f, 90f, 90f);
		so.drawHumanTexture(delta,getTimePassed(),head, chest, pelvis, round);
//		so.drawHuman(delta,getTimePassed());
		glPopMatrix();

		glPushMatrix();
		Soldiers so2 = new Soldiers();
		glTranslatef(650, 400, 150);
		glScalef(90f, 90f, 90f);
		so2.drawHumanTexture(delta,getTimePassed(),head, chest, pelvis, round);
//		so.drawHuman(delta,getTimePassed());

		glPopMatrix();
		glPushMatrix();
		Soldiers so22 = new Soldiers();
		glTranslatef(500, 400, 150);
		glScalef(90f, 90f, 90f);
		so22.drawHumanTexture(delta,getTimePassed(),head, chest, pelvis, round);
//		so.drawHuman(delta,getTimePassed());
		glPopMatrix();
//
		glPushMatrix();
		Soldiers so3 = new Soldiers();
		glTranslatef(650, 400, 0);
		glScalef(90f, 90f, 90f);
		so3.drawHumanTexture(delta,getTimePassed(),head, chest, pelvis, round);
//		so.drawHuman(delta,getTimePassed());
		glPopMatrix();

		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();

		TexSphere texSphere144 = new TexSphere();
//		glTranslatef(-2100, 280, 5800);

		long TimeP = (long) (timePassed/10000f);
//		glTranslatef(600,280,-100);
		if ((int) (TimeP % 4) == 0) {
			glTranslatef((float) (600+(delta-Math.floor(delta/4)*4)*1000),280,-100);


		}
		if ((int) (TimeP % 4) == 1) {
			glTranslatef(1600, (float) 280, (float) (-100-(delta-Math.floor(delta/4)*4)*1000+1000));

		}
		if ((int) (TimeP % 4) == 2) {
			glTranslatef((float) (1600-(delta-Math.floor(delta/4)*4)*1000+2000),280,-1100);

		}
		if ((int) (TimeP % 4) == 3) {
			glTranslatef((float) 600, (float) 280, (float) (-1100+(delta-Math.floor(delta/4)*4)*1000-3000));


		}





		GL11.glScalef(14, 0, 14);
		texSphere144.DrawTexSphere(12,32,32,shadow);
		GL11.glPopMatrix();




		// create human draw human

		glPushMatrix();
		Human MyHuman = new Human();
		glRotatef(180,0,1,0);
		glTranslatef(110,2420,2300);
		glScalef(90f, 90f, 90f);

//		if (!BadAnimation) {
//			// insert your animation code to correct the postion for the human rotating
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//
//		} else {
//
//			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//		}

//		MyHuman.drawHuman(delta, !BadAnimation); // give a delta for the Human object ot be animated
//
		MyHuman.drawHumanTexture(delta, true, texture_face, texture_chest, sss,sss,sss);

		glPopMatrix();




//		// mud
//		glPushMatrix();
//		TexCube MyGlobe1 = new TexCube();
//		glTranslatef(2200, 300, 2200);
//		glScalef(2200, 70f, 2200);
//
//
//		GL11.glTexParameteri(3553, 10240, 9728);
//
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//
//		Color.white.bind();
//		b.bind();
//		glEnable(GL_TEXTURE_2D);
//		MyGlobe1.DrawTexCube1(b);
//
//		glPopMatrix();


//
		//zhuanpan
		GL11.glColor4f(white[0], white[1], white[2], 1f);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		CylinderDisplayList cylinderDisplayList111 = new CylinderDisplayList(150,30,6);
		Color.white.bind();
		rock.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glScalef(6, 6, 6);
		GL11.glTranslatef(0,400,-500);
		GL11.glRotatef(90,1,0,0);

		cylinderDisplayList111.DrawCylinder();

		glPopMatrix();


		GL11.glColor4f(white[0], white[1], white[2], 1f);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Cylinder d = new Cylinder();
		Color.white.bind();
		rock.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glScalef(6, 6, 6);
		GL11.glTranslatef(0,370,-500);
		GL11.glRotatef(90,1,0,0);

		d.drawTCylinder(150,30,6,rock);

		glPopMatrix();


		GL11.glColor4f(white[0], white[1], white[2], 1f);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		CylinderDisplayList cylinderDisplayList113 = new CylinderDisplayList(150,30,6);
		Color.white.bind();
		ishadow.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glScalef(6, 6, 6);
		GL11.glTranslatef(200,60,-800);
		GL11.glRotatef(90,1,0,0);

		cylinderDisplayList113.DrawCylinder();

		glPopMatrix();



		// ice cap light big

		CylinderDisplayList ice2 = new CylinderDisplayList(12,2,20);

		glPushMatrix();
		Cylinder cc = new Cylinder();
		if(delta<4){
			glTranslatef(-2500+delta*1000, 400, 6000);
		}
		else if(delta>4&&delta<7){
			glTranslatef(1500-(delta-4)*150, 400, 6000-(delta-4)*1000);
		}else if(delta>7 && delta<9){
			glTranslatef((float) (1050-(delta-7)*1575), 400F, (float) (3000+(delta-7)*150));

		}else if(delta>9&&delta<11){
			glTranslatef((float) (-2100-(delta-9)*200), 400F, (float) (3300+(delta-9)*1350));
		}else{
			glTranslatef(-2500,400,6000);
		}


		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 90, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		ice2.DrawCylinder();
		glPopMatrix();


		glPushMatrix();
//		glTranslatef(-2500, 230, 6000);

		if(delta<4){
			glTranslatef(-2500+delta*1000, 230, 6000);
		}
		else if(delta>4&&delta<7){
			glTranslatef(1500-(delta-4)*150, 230, 6000-(delta-4)*1000);
		}else if(delta>7 && delta<9){
			glTranslatef((float) (1050-(delta-7)*1575), 230, (float) (3000+(delta-7)*150));

		}else if(delta>9&&delta<11){
			glTranslatef((float) (-2100-(delta-9)*200), 230, (float) (3300+(delta-9)*1350));
		}else{
			glTranslatef(-2500,230,6000);
		}




		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 90, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		cc.drawTCylinder(12,2,20,il);
		glPopMatrix();


		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		Sphere sphere = new Sphere();
		TexSphere texSphere = new TexSphere();
//		glTranslatef(-2100, 280, 5800);

		if(delta<4){
			glTranslatef(-2100+delta*1000, 280, 5800);
		}
		else if(delta>4&&delta<7){
			glTranslatef(1900-(delta-4)*150, 280, 5800-(delta-4)*1000);
		}
		else if(delta>7 && delta<9){
			glTranslatef((float) (1450-(delta-7)*1575), 280, (float) (2800+(delta-7)*150));

		}
		else if(delta>9&&delta<11){
			glTranslatef((float) (-1700-(delta-9)*200), 280, (float) (3100+(delta-9)*1350));
		}

		else{
			glTranslatef(-2100,280,5800);
		}
		GL11.glScalef(80, 0, 80);
		texSphere.DrawTexSphere(12,32,32,shadow);
		GL11.glPopMatrix();



		// ice cap light small
		CylinderDisplayList ice3 = new CylinderDisplayList(6,2,20);
		glPushMatrix();
		Cylinder cc1 = new Cylinder();
//		glTranslatef(-1200, 400, 3000);
		if(delta<4){
			glTranslatef(-1200-delta*1000, 400, 3000-delta*200);

		}
		else if(delta>4&&delta<8){
			glTranslatef((float) (-5200+(delta-4)*50), 400F, (float) (2200+(delta-4)*1000));

		}

		else if(delta>8 && delta<10){
			glTranslatef((float) (-5000+(delta-8)*1500), 400F, (float) (6200-(delta-8)*150));


		}else if(delta >10 && delta<14){
			glTranslatef(-2000-(delta-10)*200,400F,5900-(delta-10)*725);
		}else{
			glTranslatef(-2800, 400, 3000);
		}


		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 90, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		ice3.DrawCylinder();
		glPopMatrix();


		glPushMatrix();
//		glTranslatef(-1200, 230, 3000);
		if(delta<4){
			glTranslatef(-1200-delta*1000, 230, 3000-delta*200);

		}
		else if(delta>4&&delta<8){
			glTranslatef((float) (-5200+(delta-4)*50), 230, (float) (2200+(delta-4)*1000));

		}

		else if(delta>8 && delta<10){
			glTranslatef((float) (-5000+(delta-8)*1500), 230, (float) (6200-(delta-8)*150));


		}else if(delta >10 && delta<14){
			glTranslatef(-2000-(delta-10)*200,230,5900-(delta-10)*725);
		}else{
			glTranslatef(-2800, 230, 3000);
		}
		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 90, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		cc1.drawTCylinder(6,2,20,il);
		glPopMatrix();

		// shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexSphere texSphere1 = new TexSphere();
//		glTranslatef(-900, 280, 2900);
		if(delta<4){
			glTranslatef(-900-delta*1000, 280, 2900-delta*200);

		}
		else if(delta>4&&delta<8){
			glTranslatef((float) (-4900+(delta-4)*50), 280, (float) (2100+(delta-4)*1000));

		}

		else if(delta>8 && delta<10){
			glTranslatef((float) (-4700+(delta-8)*1500), 280, (float) (6100-(delta-8)*150));


		}else if(delta >10 && delta<14){
			glTranslatef(-1700-(delta-10)*200,280,5800-(delta-10)*725);
		}else{
			glTranslatef(-2500, 280, 2900);
		}

		GL11.glScalef(80, 0, 80);
		texSphere1.DrawTexSphere(6,2,20,shadow);
		GL11.glPopMatrix();


		// ice cap dark
		CylinderDisplayList ice4 = new CylinderDisplayList(15,2,20);
		glPushMatrix();
		Cylinder c = new Cylinder();
//
		if(delta<1.5){
			glTranslatef(2800-delta*1000, 400, 4000-delta*700);

		}
		else if(delta>1.5&&delta<3){
			glTranslatef((float) (1300-(delta-1.5)*2000), 400F, (float) ((float) (2950)-(delta-1.5)*300));

		}
		else if(delta>3 && delta<4.5){
			glTranslatef((float) (-1700)-(delta-3)*300, 400F, (float) (2500+(delta-3)*2000));
		}

		else if(delta>4.5&&delta<8){
			glTranslatef((float) (-2150+(delta-4.5)*1000), 400F, (float) (5500+(delta-4.5)*100));

		}

		else if(delta>8&&delta<13){
			glTranslatef((float) (1350+(delta-8)*290), 400F, (float) (5850-(delta-8)*370));
		}

		else{
			glTranslatef(2800, 400, 4000);
		}

		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 90, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		ice4.DrawCylinder();
		glPopMatrix();

		glPushMatrix();
//		glTranslatef(2800, 230, 4000);
		if(delta<1.5){
			glTranslatef(2800-delta*1000, 230, 4000-delta*700);
		}
		else if(delta>1.5&&delta<3){
			glTranslatef((float) (1300-(delta-1.5)*2000), 230, (float) ((float) (2950)-(delta-1.5)*300));

		}
		else if(delta>3 && delta<4.5){
			glTranslatef((float) (-1700)-(delta-3)*300, 230, (float) (2500+(delta-3)*2000));
		}

		else if(delta>4.5&&delta<8){
			glTranslatef((float) (-2150+(delta-4.5)*1000), 230, (float) (5500+(delta-4.5)*100));
		}

		else if(delta>8&&delta<13){
			glTranslatef((float) (1350+(delta-8)*290), 230, (float) (5850-(delta-8)*370));
		}

		else{
			glTranslatef(2800, 230, 4000);
		}
		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 90f, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		c.drawTCylinder(15,2,20,texture_skybox);
		ice4.DrawCylinder();
		glPopMatrix();
		// shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexSphere texSphere11 = new TexSphere();
//		glTranslatef(3600, 280, 3800);
		if(delta<1.5){
			glTranslatef(3600-delta*1000, 280, 3800-delta*700);

		}
		else if(delta>1.5&&delta<3){
			glTranslatef((float) (2100-(delta-1.5)*2000), 280, (float) ((float) (2750)-(delta-1.5)*300));

		}
		else if(delta>3 && delta<4.5){
			glTranslatef((float) (-900)-(delta-3)*300, 280, (float) (2300+(delta-3)*2000));
		}

		else if(delta>4.5&&delta<8){
			glTranslatef((float) (-1350+(delta-4.5)*1000), 280, (float) (5300+(delta-4.5)*100));

		}

		else if(delta>8&&delta<13){
			glTranslatef((float) (2150+(delta-8)*290), 280, (float) (5650-(delta-8)*370));
		}

		else{
			glTranslatef(3400, 280, 3800);
		}

		GL11.glScalef(80, 0, 80);
		texSphere11.DrawTexSphere(15,2,20,shadow);
		GL11.glPopMatrix();



		// right ice ball
		glPushMatrix();
		TexSphere st = new TexSphere();
		glTranslatef(-4000, 330, 5000);
		glScalef(300f, 130f, 300f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		st.DrawTexSphere(2,5,5,il);
		glPopMatrix();

		// shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexSphere texSphere111 = new TexSphere();
		glTranslatef(-3800, 280, 4900);
		GL11.glScalef(300, 0, 300);
		texSphere111.DrawTexSphere(2,5,5,shadow);
		GL11.glPopMatrix();




		// ice ball  left
		glPushMatrix();
		TexSphere s = new TexSphere();
		glTranslatef(4700, 330, 7000);
		glScalef(500f, 300f, 500f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		s.DrawTexSphere(2,5,5,il);
		glPopMatrix();


		// shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexSphere texSphere1111 = new TexSphere();
		glTranslatef(5000, 280, 6900);
		GL11.glScalef(500, 0, 500);
		texSphere1111.DrawTexSphere(2,5,5,shadow);
		GL11.glPopMatrix();



		// white rock big
		glPushMatrix();
		TexSphere s22 = new TexSphere();
		glTranslatef(-700, 420, 7800);
		glScalef(400f, 800f, 500f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glRotatef(90,0,1,0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		ii.bind();
		glEnable(GL_TEXTURE_2D);
		s22.DrawTexSphere(2,5,7,ii);
		glPopMatrix();

		//shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexSphere texSphere11111 = new TexSphere();
		glTranslatef(-200, 280, 7700);
		GL11.glScalef(400, 0, 400);
		texSphere11111.DrawTexSphere(2,5,7,shadow);
		GL11.glPopMatrix();



		// white rock small
		glPushMatrix();
		TexSphere s221 = new TexSphere();
		glTranslatef(-3000, 420, 7400);
		glScalef(200f, 280f, 200f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glRotatef(90,0,1,0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		ii.bind();
		glEnable(GL_TEXTURE_2D);
		s221.DrawTexSphere(2,5,7,ii);
		glPopMatrix();

		// shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexSphere texSphere2 = new TexSphere();
		glTranslatef(-2800, 280, 7400);
		GL11.glScalef(200, 0, 200);
		texSphere2.DrawTexSphere(2,5,7,shadow);
		GL11.glPopMatrix();




		// zhuizi
		glPushMatrix();
		TexTetrahedron tt = new TexTetrahedron();
		glTranslatef(2000, 500, 8000);
		glScalef(500f, 500f, 500f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		tt.DrawTetrahedron();
		glPopMatrix();

		// shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexTetrahedron shadoww = new TexTetrahedron();
		glRotatef(270,0,0,1);
		glTranslatef(-275, 2200, 7800);
		GL11.glScalef(0, 400, 500);
		shadoww.DrawTetrahedron();
		GL11.glPopMatrix();



		// zhuizi small
		glPushMatrix();
		TexTetrahedron tt1 = new TexTetrahedron();
		glTranslatef(-200, 500, 4400);
		glScalef(300f, 200f, 300f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		tt1.DrawTetrahedron();
		glPopMatrix();

		//shadow
		GL11.glColor4f(black[0], black[1], black[2], 1f);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		shadow.bind();
		glEnable(GL_TEXTURE_2D);
		GL11.glPushMatrix();
		TexTetrahedron shadoww1 = new TexTetrahedron();
		glRotatef(270,0,0,1);
		glTranslatef(-275, -150, 4120);
		GL11.glScalef(0, 300, 400);
		shadoww1.DrawTetrahedron();
		GL11.glPopMatrix();








		// Ground2
		glPushMatrix();
		TexCube MyGlobeE11 = new TexCube();
		glTranslatef(500-3000, 250, 700-3000-3000-4000);
		glScalef(3500f, 20f, 3500f);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		MyGlobeE11.DrawTexCube1(texture_skybox);
		glPopMatrix();


		// Ocean2
		glPushMatrix();
		TexCube oceanC1 = new TexCube();
		glTranslatef(7500-3000, 250, 700-3000-3000-4000);
		glScalef(3500f, 20f, 3500f);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		oceanC1.DrawTexCube1(texture_skybox);
		glPopMatrix();


		// Ground
		glPushMatrix();
		TexCube MyGlobeE = new TexCube();
		glTranslatef(500-3000, 250, 700-3000);
		glScalef(3500f, 20f, 3500f);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		MyGlobeE.DrawTexCube1(texture_skybox);
		glPopMatrix();


		// Ocean
		glPushMatrix();
		TexCube oceanC = new TexCube();
		glTranslatef(7500-3000, 250, 700-3000);
		glScalef(3500f, 20f, 3500f);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		oceanC.DrawTexCube1(texture_skybox);
		glPopMatrix();


		// Ocean
		glPushMatrix();
		TexCube oceanCC = new TexCube();
		glTranslatef(7500-3000, 250, 7700-3000);
		glScalef(3500f, 20f, 3500f);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		liquid.bind();
		glEnable(GL_TEXTURE_2D);
		oceanCC.DrawTexCube1(liquid);
		glPopMatrix();

		// Ocean
		glPushMatrix();
		TexCube oceanCCC = new TexCube();
		glTranslatef(500-3000, 250, 7700-3000);
		glScalef(3500f, 20f, 3500f);
		GL11.glTexParameteri(3553, 10240, 9728);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		Color.white.bind();
		liquid.bind();
		glEnable(GL_TEXTURE_2D);
		oceanCCC.DrawTexCube1(liquid);
		glPopMatrix();



		// garden
		glPushMatrix();
		Garden g = new Garden();
		glTranslatef(-2300, 800, -600);
		glScalef(100, 100, 100);
		glRotatef(theta*5,0,1,0);
		g.DrawGarden(delta,rock,grass,leaf);
		glPopMatrix();


		//garden shadow
		glPushMatrix();
		GardenShadow gs = new GardenShadow();
		glTranslatef(-2100, 320, -1100);
		glRotatef(theta*5,0,1,0);
		glScalef(100, 1, 100);
		gs.DrawGardenShadow(delta,ishadow);
		glPopMatrix();

		// sphere moon
		glPushMatrix();
		TexSphere My = new TexSphere();
		glTranslatef(3000,2500,1000);
		glScalef(8f, 8f, 8f);
		glTranslatef(posn_x * 55.0f, 0.0f, posn_y * 55.0f);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		Color.white.bind();
		texture_sphere.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		My.DrawTexSphere(8f, 100, 100, texture_sphere);
		glPopMatrix();


		// sphere moon shadow
		glPushMatrix();
		TexSphere My6 = new TexSphere();
		glTranslatef(3900,290,0);
		glScalef(9f, 1f, 9f);
		glTranslatef(posn_x * 55.0f, 0.0f, posn_y * 55.0f);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		Color.white.bind();
		ishadow.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		My6.DrawTexSphere(8f, 100, 100, ishadow);
		glPopMatrix();




		// cube earth
		glPushMatrix();
		TexCube cube = new TexCube();
		glTranslatef(3000,1000,1000);
		glScalef(300, 300, 300);
		glTranslatef(posn_x * 8, 0, posn_y * 8);
		// making the direction of the earth cube's velocity vertical to radius of the circle path
		glRotatef((float) ((-theta*180f)/Math.PI)+180, 0.0f, 0.5f, 0.0f);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		Color.white.bind();
		texture_cube.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		cube.DrawTexCube1(texture_cube);
		glPopMatrix();


		// cube earth shadow
		glPushMatrix();
		TexCube cubes = new TexCube();
		glTranslatef(3600,-20,100);
		glScalef(300, 300, 300);
		glTranslatef(posn_x * 8, 0, posn_y * 8);
		// making the direction of the earth cube's velocity vertical to radius of the circle path
		glRotatef((float) ((-theta*180f)/Math.PI)+180, 0.0f, 0.5f, 0.0f);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		Color.white.bind();
		ishadow.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		cubes.DrawTexCube1(ishadow);
		glPopMatrix();






		//House
		glPushMatrix();
		House h = new House();
		glTranslatef(3000,280,1000);
		glScalef(1,1,1);
		glColor3f(1,1,1);
		if(!HouseStatus){
			deltaTmp = delta;
			HouseStatus = true;
			rotate = 0;}
		h.DrawHouse(delta-deltaTmp, rotate++,ii,ih,t,il,space,true);
		glDisable(GL_TEXTURE_2D);




		GL11.glColor4f(white[0], white[1], white[2], 1f);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		CylinderDisplayList cylinderDisplayList11 = new CylinderDisplayList(150,30,6);
		Color.white.bind();
		ii.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glScalef(8, 8, 8);
		GL11.glTranslatef(375,65,125);
		GL11.glRotatef(90,1,0,0);
		cylinderDisplayList11.DrawCylinder();
		glPopMatrix();




		// House shadow
		GL11.glColor4f(white[0], white[1], white[2], 1f);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		CylinderDisplayList cylinderDisplayList112 = new CylinderDisplayList(150,30,6);
		Color.white.bind();
		ishadow.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glScalef(8, 8, 8);
		GL11.glTranslatef(420,35,25);
		GL11.glRotatef(90,1,0,0);
		cylinderDisplayList112.DrawCylinder();
		glPopMatrix();


		// Sun
		GL11.glColor4f(white[0], white[1], white[2], 1f);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		TexSphere sunn = new TexSphere();
		Color.white.bind();
		sun.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glScalef(9, 9, 9);
		GL11.glTranslatef(-1600,1100,1750);
		sunn.DrawTexSphere(100,100,100,sun);
		glPopMatrix();



		glPushMatrix();
		glRotatef(270,1,0,0);
		GL11.glTranslatef(420,2800,2400);
		GL11.glScalef(40, 40, 40);
		glCallList(CatObj.getCatDisplayList(timePassed));
		glPopMatrix();





	}


	public static long getTimePassed() {
		return (long) timePassed;
	}


	public static void main(String[] argv) throws IOException {
		MainWindow hello = new MainWindow();
		hello.start();
	}

	Texture texture;
	Texture texture_face;
	Texture texture_chest;

	Texture texture_pelvis;

	Texture texture_cube;

	Texture texture_sphere;
	Texture texture_skybox;

	Texture ocean;

	Texture liquid,leaf,sun,head, pelvis,chest,round;

	Texture a,b,textc,t,rock,mud,bg,il,ii,cccc,ih,grass,shadow, ishadow,sss;
	Texture texture_down,texture_up,texture_right,texture_left,texture_back,texture_front,space;

	Texture gun;

	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {

		// load textures

		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_13.png"));
		texture_face = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/face.png"));
		texture_chest = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cloth.png"));
		texture_pelvis = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_30.png"));
		texture_cube = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		texture_sphere = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_17.png"));
		texture_skybox = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/ice.jpg"));
		ocean = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/ocean.jpg"));

		liquid = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/liquid.jpg"));

		a = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_2.png"));

		b = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_8.png"));
		textc = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_16.png"));
		t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2022.png"));

//		rock = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/rock.jpg"));
		rock = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_29.png"));
		bg = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/background.jpg"));

		il = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/iceLight.jpg"));

		ii = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/icee.jpg"));

		cccc =  TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/iice.jpg"));
		texture_down =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/space.png"));
		texture_front=  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox/backk.png"));
		texture_right =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox/rightt.png"));
		texture_left =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox/lefttt.png"));
		texture_back =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox/frontt.png"));
		texture_up =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox/uppp.png"));

		ih =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_28.png"));
		space =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/space.png"));


		mud = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_15.png"));
		grass = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_2.png"));
		leaf = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/leaf.png"));

		shadow = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/shadow.png"));

		ishadow = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/ishadow.png"));
		sun = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/sunnn.png"));

		sss = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_6.png"));

		gun = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_8.png"));

		head = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/CA1.png"));
		pelvis = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_30.png"));
		chest = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/caa.png"));
		round = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cachest.png"));



		System.out.println("Texture loaded okay ");
	}


}
