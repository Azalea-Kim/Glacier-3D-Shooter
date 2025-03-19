
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


	private boolean ShipStatus = false;

	private boolean icRight = true;


	private boolean MouseOnepressed = true;
	public static Camera camera = new Camera();
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean Earth = false;

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

	float deltaTmp = 0;
	private int swingInt = 0;
	private boolean Millstatus = false;

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

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
	private static final ArrayList<Vector4f> key_positions = new ArrayList<>();
	private static final ArrayList<Vector4f> key_rotations = new ArrayList<>();


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
		setOrtho(Camera.OrthoNumber);
		enterModelView();

//        String s;
//        s.equalsIgnoreCase()


		camera.setCamera(new Vector4f(
				0, 2800, 0, 0
		));
		Display.setTitle("CG Project 1 Loading....... Scene: Init");

		Display.setTitle("CG Project 1 Loading....... Scene: Ok.");
//		key_positions.add(new Vector4f(0, 5000, 0, 2000));
//		key_rotations.add(new Vector4f(0, 0, 0, 2000));
//
//		key_positions.add(new Vector4f(-1000, 2000, 0, 1000));
//		key_rotations.add(new Vector4f(-10, -90, 0, 1000));
//
//		key_positions.add(new Vector4f(1500, 2000, 0, 2000));
//		key_rotations.add(new Vector4f(-15, -90, 0, 2000));
//
//		key_positions.add(new Vector4f(0, 5000, 0, 1000));
//		key_rotations.add(new Vector4f(-20, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(0, 10000, -8000, 2000));
//		key_rotations.add(new Vector4f(0, -180, 0, 2000));
//
//		key_positions.add(new Vector4f(0, 5000, 8000, 1000));
//		key_rotations.add(new Vector4f(-20, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(0, 1000, 8000, 1000));
//		key_rotations.add(new Vector4f(20, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(0, 100, 5000, 2000));
//		key_rotations.add(new Vector4f(30, -180, 0, 2000));
//
//		key_positions.add(new Vector4f(-3000, 100, 5000, 1000));
//		key_rotations.add(new Vector4f(30, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(-3000, 1000, 3000, 2000));
//		key_rotations.add(new Vector4f(40, -180, 0, 2000));

		key_positions.add(new Vector4f(-3000, 100, 5000, 2000));
		key_rotations.add(new Vector4f(0, -180, 0, 2000));

//
//        key_positions.add(new Vector4f(0, 7000, 0, 2000));
//        key_rotations.add(new Vector4f(90, 0, 0, 2000));
//
//        key_positions.add(new Vector4f(0, 7000, 0, 2000));
//        key_rotations.add(new Vector4f(90, 180, 0, 2000));
//
//        key_positions.add(new Vector4f(0, 7000, 0, 1000));
//        key_rotations.add(new Vector4f(90, 180, 45, 1000));
//
//        key_positions.add(new Vector4f(0, 7000, 0, 1000));
//        key_rotations.add(new Vector4f(90, 180, -45, 1000));


//		key_positions.add(new Vector4f(0, 500, -1000, 4000));
//		key_rotations.add(new Vector4f(20, 0, 0, 4000));
//
//		key_positions.add(new Vector4f(0, 0, 0, 5000));
//		key_rotations.add(new Vector4f(10, 180, 0, 5000));
//
//        key_positions.add(new Vector4f(0, 10000, 0, 3000));
//        key_rotations.add(new Vector4f(90, 180, 0, 3000));
//
//        key_positions.add(new Vector4f(0, 0, 0, 3000));
//        key_rotations.add(new Vector4f(10, 180, 0, 3000));

		Runnable cameraRunnable = new Runnable() {
			@Override
			public void run() {
				Camera.position = new Vector4f(
						0, 12000, 0, 0
				);
				Camera.rotation = new Vector3f(10, 0, 0);
				for (int i = 0; i < key_positions.size(); i++) {
					Vector4f key_position = key_positions.get(i);
					Vector4f key_rotation = key_rotations.get(i);
					Vector4f origin_position = new Vector4f(Camera.position.x, Camera.position.y, Camera.position.z, Camera.position.a);
					Vector3f origin_rotation = new Vector3f(Camera.rotation.x, Camera.rotation.y, Camera.rotation.z);
					Vector4f Current_position = Camera.position;
					Vector3f Current_rotation = Camera.rotation;
//                    System.out.println("Current_position: " + Current_position);
//                    System.out.println("Current_rotation: " + Current_rotation);
//                    System.out.println("key_position: " + key_position);
//                    System.out.println("key_rotation: " + key_rotation);
					float position_x_distance = key_position.x - Current_position.x;
					float position_y_distance = key_position.y - Current_position.y;
					float position_z_distance = key_position.z - Current_position.z;
					float rotation_x_distance = key_rotation.x - Current_rotation.x;
					float rotation_y_distance = key_rotation.y - Current_rotation.y;
					float rotation_z_distance = key_rotation.z - Current_rotation.z;
					float position_x_distance_step = position_x_distance / key_position.a;
					float position_y_distance_step = position_y_distance / key_position.a;
					float position_z_distance_step = position_z_distance / key_position.a;
					float rotation_x_distance_step = rotation_x_distance / key_position.a;
					float rotation_y_distance_step = rotation_y_distance / key_position.a;
					float rotation_z_distance_step = rotation_z_distance / key_position.a;
//                    System.out.println("position_x_distance_step: " + position_x_distance_step);
//                    System.out.println("position_y_distance_step: " + position_y_distance_step);
//                    System.out.println("position_z_distance_step: " + position_z_distance_step);
//                    System.out.println("rotation_x_distance_step: " + rotation_x_distance_step);
//                    System.out.println("rotation_y_distance_step: " + rotation_y_distance_step);
//                    System.out.println("rotation_z_distance_step: " + rotation_z_distance_step);
					float count = 0f;
					while (count < key_position.a) {

						Current_position.x = (float) (origin_position.x + position_x_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
						Current_position.y = (float) (origin_position.y + position_y_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
						Current_position.z = (float) (origin_position.z + position_z_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));

						Current_rotation.x = (float) (origin_rotation.x + rotation_x_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
						Current_rotation.y = (float) (origin_rotation.y + rotation_y_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
						Current_rotation.z = (float) (origin_rotation.z + rotation_z_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
//                        Current_rotation.x = (float) (rotation_x_distance_step * count * Math.sin(count/key_position.a * Math.PI/2));
//                        Current_rotation.y = (float) (rotation_y_distance_step * count * Math.sin(count/key_position.a * Math.PI/2));
//                        Current_rotation.z = (float) (rotation_z_distance_step * count * Math.sin(count/key_position.a * Math.PI/2));
						count += 1;
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
//                    System.out.println(Current_position);
//                    System.out.println(Current_rotation);
					Current_position = key_position;
					Current_rotation = new Vector3f(key_rotation.x, key_rotation.y, key_rotation.z);
//                    System.out.println(Current_position);
//                    System.out.println(Current_rotation);
//                    System.out.println("-------------------------------------------------");
				}
				Camera.loading_finished = true;
			}
		};        new Thread(cameraRunnable).start();



		while (!Display.isCloseRequested()) {

			glLoadIdentity();
			camera.updatePosition();
			int delta = getDelta();
			update(delta);


			camera.setCamera(new Vector4f(
					0, -2300, -600, 0
			));
			Camera.maxLookDown = -360;
			Camera.maxLookUp = 360;

			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	private void setOrtho(int orthoNumber) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		//Placing 0,0 at the center of the screen
//        GL11.glOrtho(1200 / 2 - OrthoNumber, OrthoNumber / 2, (800 / 2 - (OrthoNumber * 0.66f)), (OrthoNumber / 2 * 0.66f), 100000, -100000);
//        GL11.glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
		gluPerspective((float) 60, Display.getWidth() / Display.getHeight(), 100f, 50000f);
//            GL11.glOrtho(-600 - OrthoNumber, 600 + OrthoNumber, -100 - OrthoNumber * 0.66, 700 + OrthoNumber * 0.66, 100000, -100000);
		enterModelView();

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
		LastMouseY = MouseY;
	}

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
	public void enterModelView() {
		GL11.glMatrixMode(GL_MODELVIEW);
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



		//road light
		FloatBuffer lightPos1 = BufferUtils.createFloatBuffer(4);
		lightPos1.put(1870).put(600).put(975).put(1).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(1170).put(600).put(975).put(1).flip();

		// the light Pos from sky
		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(-400).put(2000).put(-800).put(0).flip();

		// the light Pos from sun
		FloatBuffer lightPos6 = BufferUtils.createFloatBuffer(4);
		lightPos6.put(-750).put(2000).put(-1300).put(1f).flip();
		FloatBuffer lightPos7 = BufferUtils.createFloatBuffer(4);
		lightPos7.put(-550).put(2100).put(550).put(1f).flip();
		FloatBuffer lightPos8 = BufferUtils.createFloatBuffer(4);
		lightPos8.put(-100).put(2100).put(100).put(1f).flip();
		FloatBuffer lightPos9 = BufferUtils.createFloatBuffer(4);
		lightPos9.put(-900).put(2100).put(-100).put(1f).flip();

//
		glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos1);
		glEnable(GL11.GL_LIGHT0);
		glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));

		glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos2);
		glEnable(GL11.GL_LIGHT1); // switch light #0 on
		glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
////
//        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3);
//        GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
//        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
////

		// Grey direction lights mimic the grey glow of the sky
		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4);
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));
		GL11.glLight(GL11.GL_LIGHT4, GL11.GL_POSITION, lightPos6);
		// Multiple directional lights were used to simulate the brightness of the sun
		GL11.glEnable(GL11.GL_LIGHT4);
		GL11.glLight(GL11.GL_LIGHT4, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
//		GL11.glLight(GL11.GL_LIGHT5, GL11.GL_POSITION, lightPos7);
//		GL11.glEnable(GL11.GL_LIGHT5);
//		GL11.glLight(GL11.GL_LIGHT5, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
//		GL11.glLight(GL11.GL_LIGHT6, GL11.GL_POSITION, lightPos8);
//		GL11.glEnable(GL11.GL_LIGHT6);
//		GL11.glLight(GL11.GL_LIGHT6, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
//		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_POSITION, lightPos9);
//		GL11.glEnable(GL11.GL_LIGHT7);
//		GL11.glLight(GL11.GL_LIGHT7, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));


		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
		// on
		GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
//		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();
//
//
//		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
//		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();
//
//		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
//		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();
//
//		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
//		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();
//
//
//		FloatBuffer lightPos5 = BufferUtils.createFloatBuffer(4);
//		lightPos4.put(1000).put(1000).put(1000).put(0).flip();
//
//
//
//
//		glLight(GL_LIGHT0, GL_POSITION, lightPos); // specify the
//													// position
//													// of the
//													// light
////		 glEnable(GL_LIGHT0); // switch light #0 on // I've setup specific materials
//		// so in real light it will look abit strange
//
//		glLight(GL_LIGHT1, GL_POSITION, lightPos); // specify the
//													// position
//													// of the
//													// light
////		glEnable(GL_LIGHT1); // switch light #0 on
//		glLight(GL_LIGHT1, GL_DIFFUSE, ConvertForGL(grey));
//
//		glLight(GL_LIGHT2, GL_POSITION, lightPos3); // specify
//													// the
//													// position
//													// of the
//													// light
////		glEnable(GL_LIGHT2); // switch light #0 on
//		glLight(GL_LIGHT2, GL_DIFFUSE, ConvertForGL(grey));
//
//
//
//		glLight(GL_LIGHT3, GL_POSITION, lightPos4); // specify
//													// the
//													// position
//													// of the
//													// light
//
//		glEnable(GL_LIGHT3); // switch light #0 on
//		glLight(GL_LIGHT3, GL_DIFFUSE, ConvertForGL(white));
//
//		glEnable(GL_LIGHTING); // switch lighting on
//		glEnable(GL_DEPTH_TEST); // make sure depth buffer is switched
//									// on
//		glEnable(GL_NORMALIZE); // normalize normal vectors for safety
//		glEnable(GL_COLOR_MATERIAL);
//
//		glEnable(GL_BLEND);
//		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

//		key_positions.add(new Vector4f(0, 5000, 0, 2000));
//		key_rotations.add(new Vector4f(0, 0, 0, 2000));
//
//		key_positions.add(new Vector4f(-1000, 2000, 0, 1000));
//		key_rotations.add(new Vector4f(-10, -90, 0, 1000));
//
//		key_positions.add(new Vector4f(1500, 2000, 0, 2000));
//		key_rotations.add(new Vector4f(-15, -90, 0, 2000));
//
//		key_positions.add(new Vector4f(0, 5000, 0, 1000));
//		key_rotations.add(new Vector4f(-20, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(0, 10000, -8000, 2000));
//		key_rotations.add(new Vector4f(0, -180, 0, 2000));
//
//		key_positions.add(new Vector4f(0, 5000, 8000, 1000));
//		key_rotations.add(new Vector4f(-20, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(0, 1000, 8000, 1000));
//		key_rotations.add(new Vector4f(20, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(0, 100, 5000, 2000));
//		key_rotations.add(new Vector4f(30, -180, 0, 2000));
//
//		key_positions.add(new Vector4f(-3000, 100, 5000, 1000));
//		key_rotations.add(new Vector4f(30, -180, 0, 1000));
//
//		key_positions.add(new Vector4f(-3000, 1000, 3000, 2000));
//		key_rotations.add(new Vector4f(40, -180, 0, 2000));
//
//		key_positions.add(new Vector4f(-3000, 100, 5000, 2000));
//		key_rotations.add(new Vector4f(0, -180, 0, 2000));
//
//
//		key_positions.add(new Vector4f(0, 500, -1000, 4000));
//		key_rotations.add(new Vector4f(20, 0, 0, 4000));
//
//		key_positions.add(new Vector4f(0, 0, 0, 5000));
//		key_rotations.add(new Vector4f(10, 180, 0, 5000));
//
//		Runnable cameraRunnable = new Runnable() {
//			@Override
//			public void run() {
//				Camera.position = new Vector4f(
//						0, 12000, 0, 0
//				);
//				Camera.rotation = new Vector3f(10, 0, 0);
//				for (int i = 0; i < key_positions.size(); i++) {
//					Vector4f key_position = key_positions.get(i);
//					Vector4f key_rotation = key_rotations.get(i);
//					Vector4f origin_position = new Vector4f(Camera.position.x, Camera.position.y, Camera.position.z, Camera.position.a);
//					Vector3f origin_rotation = new Vector3f(Camera.rotation.x, Camera.rotation.y, Camera.rotation.z);
//					Vector4f Current_position = Camera.position;
//					Vector3f Current_rotation = Camera.rotation;
////                    System.out.println("Current_position: " + Current_position);
////                    System.out.println("Current_rotation: " + Current_rotation);
////                    System.out.println("key_position: " + key_position);
////                    System.out.println("key_rotation: " + key_rotation);
//					float position_x_distance = key_position.x - Current_position.x;
//					float position_y_distance = key_position.y - Current_position.y;
//					float position_z_distance = key_position.z - Current_position.z;
//					float rotation_x_distance = key_rotation.x - Current_rotation.x;
//					float rotation_y_distance = key_rotation.y - Current_rotation.y;
//					float rotation_z_distance = key_rotation.z - Current_rotation.z;
//					float position_x_distance_step = position_x_distance / key_position.a;
//					float position_y_distance_step = position_y_distance / key_position.a;
//					float position_z_distance_step = position_z_distance / key_position.a;
//					float rotation_x_distance_step = rotation_x_distance / key_position.a;
//					float rotation_y_distance_step = rotation_y_distance / key_position.a;
//					float rotation_z_distance_step = rotation_z_distance / key_position.a;
////                    System.out.println("position_x_distance_step: " + position_x_distance_step);
////                    System.out.println("position_y_distance_step: " + position_y_distance_step);
////                    System.out.println("position_z_distance_step: " + position_z_distance_step);
////                    System.out.println("rotation_x_distance_step: " + rotation_x_distance_step);
////                    System.out.println("rotation_y_distance_step: " + rotation_y_distance_step);
////                    System.out.println("rotation_z_distance_step: " + rotation_z_distance_step);
//					float count = 0f;
//					while (count < key_position.a) {
//
//						Current_position.x = (float) (origin_position.x + position_x_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
//						Current_position.y = (float) (origin_position.y + position_y_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
//						Current_position.z = (float) (origin_position.z + position_z_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
//
//						Current_rotation.x = (float) (origin_rotation.x + rotation_x_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
//						Current_rotation.y = (float) (origin_rotation.y + rotation_y_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
//						Current_rotation.z = (float) (origin_rotation.z + rotation_z_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
////                        Current_rotation.x = (float) (rotation_x_distance_step * count * Math.sin(count/key_position.a * Math.PI/2));
////                        Current_rotation.y = (float) (rotation_y_distance_step * count * Math.sin(count/key_position.a * Math.PI/2));
////                        Current_rotation.z = (float) (rotation_z_distance_step * count * Math.sin(count/key_position.a * Math.PI/2));
//						count += 1;
//						try {
//							Thread.sleep(1);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}//                    System.out.println(Current_position);
////                    System.out.println(Current_rotation);
//					Current_position = key_position;
//					Current_rotation = new Vector3f(key_rotation.x, key_rotation.y, key_rotation.z);
////                    System.out.println(Current_position);
////                    System.out.println(Current_rotation);
////                    System.out.println("-------------------------------------------------");
//				}
//				Camera.loading_finished = true;
//			}
//		};
//
//
//		new Thread(cameraRunnable).start();



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
		camera.update(getDelta());

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
				new Point4f(0, 0, 0, 0),
				new Vector4f(30000, 30000, 30000, 0),texture_down,texture_front,texture_right,texture_left,texture_back,texture_up);
		skybox.Draw();

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



		// create human draw human

		glPushMatrix();
		Human MyHuman = new Human();
		glTranslatef(300, 400, 0);
		glScalef(90f, 90f, 90f);

		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);

		} else {

			// bad animation version
			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MyHuman.drawHuman(delta, !BadAnimation); // give a delta for the Human object ot be animated
//
		MyHuman.drawHumanTexture(delta, !BadAnimation, texture_face, texture_chest, texture_pelvis);

		glPopMatrix();




		//Tree
		GL11.glPushMatrix();
		{
			Tree tree = new Tree();
			GL11.glTranslatef(1500, 800, 200);
			GL11.glScalef(50, 50, 50);
//			if (!flag && TentStatus) {
//				deltaTmp = delta;
//				flag = true;
//			}
			tree.DrawTent(delta, false, grass);
			GL11.glPopMatrix();
		}



		// cube earth
		glPushMatrix();
		TexCube cube = new TexCube();
		glTranslatef(300, 600, 300);
		glScalef(50f, 50f, 50f);

		// make it rotate with the human
		glTranslatef(posn_x * 12.0f, 0.0f, posn_y * 12.0f);



		// making the direction of the earth cube's velocity vertical to radius of the circle path
		glRotatef((float) ((-theta*180f)/Math.PI)+180, 0.0f, 0.5f, 0.0f);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		Color.white.bind();
		texture_cube.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		cube.DrawTexCube1(texture_cube);
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
		// grass
		glPushMatrix();
		TexCube MyGlobe = new TexCube();
		glTranslatef(-2300, 350, -600);
		glScalef(1200, 80, 1200);


		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		Color.white.bind();
		rock.bind();
		glEnable(GL_TEXTURE_2D);
		MyGlobe.DrawTexCube1(rock);

		glPopMatrix();


		//mud
		glPushMatrix();
		Cylinder cy = new Cylinder();
		glTranslatef(-2300, 350, -600);
		glRotatef(90,1,0,0);
		glScalef(100, 100, 100);


		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		Color.white.bind();
		mud.bind();
		glEnable(GL_TEXTURE_2D);
		cy.drawTCylinder(9F,1.5F,6,mud);

		glPopMatrix();





		// ice cap
		glPushMatrix();
		Cylinder c1c = new Cylinder();
		glTranslatef(-5000, 230, 7200);

//		if(-2500<(delta*300) && delta*300<8000){
//			glTranslatef(delta*300, 0.0f, 0);}
//		else{
//			glTranslatef(-delta*300, 0.0f, 0);}


		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(50f, 20f, 50f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		c1c.drawTCylinder(12,2,20,il);
		glPopMatrix();



		// ice cap
		glPushMatrix();
		Cylinder cc = new Cylinder();
		glTranslatef(-2500, 230, 6000);


//		if(-900<(delta*300) && delta*300<900 &&icRight ){
//		glTranslatef(delta*300, 0.0f, 0);
//		icRight = true;
//
//		}
//		else{
//			icRight = false;
//			glTranslatef(-delta*300, 0.0f, 0);}


		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 50f, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		cc.drawTCylinder(12,2,20,il);
		glPopMatrix();

		// ice cap
		glPushMatrix();
		Cylinder cc1 = new Cylinder();
		glTranslatef(-1200, 230, 3000);
		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 50f, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		Color.white.bind();
		il.bind();
		glEnable(GL_TEXTURE_2D);
		cc1.drawTCylinder(6,2,20,il);
		glPopMatrix();


		// ice cap
		glPushMatrix();
		Cylinder c = new Cylinder();
		glTranslatef(2800, 220, 4000);
		GL11.glRotatef(90, 1f, 0f, 0f);
		glScalef(90f, 50f, 90f);
		glColor3f(1, 1, 1);
		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
		c.drawTCylinder(15,2,20,texture_skybox);
		glPopMatrix();



		// qiu
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




		// qiu
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

		// qiu
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

		// qiu
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


		// zhuizi
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

//
//		GL11.glTexParameteri(3553, 10242, 10496);

		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
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

//
//		GL11.glTexParameteri(3553, 10242, 10496);

		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		Color.white.bind();
		texture_skybox.bind();
		glEnable(GL_TEXTURE_2D);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		oceanC.DrawTexCube1(texture_skybox);

		glPopMatrix();


		// Ocean
		glPushMatrix();
		TexCube oceanCC = new TexCube();
		glTranslatef(7500-3000, 250, 7700-3000);
		glScalef(3500f, 20f, 3500f);

//
//		GL11.glTexParameteri(3553, 10242, 10496);

		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		Color.white.bind();
		liquid.bind();
		glEnable(GL_TEXTURE_2D);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		oceanCC.DrawTexCube1(liquid);

		glPopMatrix();

		// Ocean
		glPushMatrix();
		TexCube oceanCCC = new TexCube();
		glTranslatef(500-3000, 250, 7700-3000);
		glScalef(3500f, 20f, 3500f);

//
//		GL11.glTexParameteri(3553, 10242, 10496);

		GL11.glTexParameteri(3553, 10240, 9728);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		Color.white.bind();
		liquid.bind();
		glEnable(GL_TEXTURE_2D);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		oceanCCC.DrawTexCube1(liquid);

		glPopMatrix();

//		// Wall2 left
//		glPushMatrix();
//		TexCube Wall = new TexCube();
//		glTranslatef(0, -2000, 40000);
//		glScalef(3000f, 3000f, 20f);
//		glRotatef(90,1,0,0);
//
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
//
//		Color.white.bind();
//		t.bind();
//		glEnable(GL_TEXTURE_2D);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//		Wall.DrawTexCube(t);
//		glPopMatrix();




		// sphere moon
		glPushMatrix();
		TexSphere My = new TexSphere();

		glTranslatef(600, 1000, 100);
		glScalef(8f, 8f, 8f);

		// make it rotate with the human
		glTranslatef(posn_x * 55.0f, 0.0f, posn_y * 55.0f);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Color.white.bind();
		texture_sphere.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		My.DrawTexSphere(8f, 100, 100, texture_sphere);
		glPopMatrix();
		/*
		 * This code puts the earth code in which is larger than the human so it appears
		 * to change the scene
		 */
		if (Earth) {
			// Globe in the centre of the scene
			glPushMatrix();
			TexSphere Earth = new TexSphere();
			// TexCube MyGlobe = new TexCube();
			glTranslatef(500, 500, 500);
			glScalef(140f, 140f, 140f);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

			Color.white.bind();
			texture.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

			Earth.DrawTexSphere(8f, 100, 100, texture);
			// MyGlobe.DrawTexCube();
			glPopMatrix();
		}



		//windmill
		glPushMatrix();
		Windmill w = new Windmill();
		House h = new House();
		glTranslatef(3000,300,1000);
		glScalef(1,1,1);
		glColor3f(1,1,1);
		if(!Millstatus){
			deltaTmp = delta;
			Millstatus = true;
			swingInt = 0;}

//		w.DrawWindmill(delta-deltaTmp,swingInt++);\
		h.DrawHouse(delta-deltaTmp,swingInt++,ii,ih,t,il,space);
		glDisable(GL_TEXTURE_2D);
		glPopMatrix();






//		// bggg
//		glPushMatrix();
//		TexCube MyGlobeE1 = new TexCube();
////		glTranslatef(500,450,700);
//
//		glTranslatef(500,450,-30000);
////		glTranslatef(500, 1250, 700+8000);
//		glScalef(4000f, 4000, 20f);
//		glRotatef(180,1,0,0);
//
//
//		GL11.glTexParameteri(3553, 10240, 9728);
//
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//
//		Color.white.bind();
//		space.bind();
//		glEnable(GL_TEXTURE_2D);
//		MyGlobeE1.DrawTexCube1(space);
//
//		glPopMatrix();
//
//			//60000  //30000
//		// bggg
//		glPushMatrix();
//		TexCube MyGlobeE111 = new TexCube();
////		glTranslatef(500,450,700);
//
//		glTranslatef(500,450,30000);
////		glTranslatef(500, 1250, 700+8000);
//		glScalef(4000f, 4000, 20f);
//		glRotatef(180,1,0,0);
//
//
//		GL11.glTexParameteri(3553, 10240, 9728);
//
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//
//		Color.white.bind();
//		space.bind();
//		glEnable(GL_TEXTURE_2D);
//		MyGlobeE111.DrawTexCube1(space);
//
//		glPopMatrix();









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

	Texture liquid;

	Texture a,b,textc,t,rock,mud,bg,il,ii,cccc,ih,grass;
	Texture texture_down,texture_up,texture_right,texture_left,texture_back,texture_front,space;

	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {

		// load textures

		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_13.png"));
		texture_face = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_3.png"));
		texture_chest = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_6.png"));
		texture_pelvis = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_8.png"));
		texture_cube = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		texture_sphere = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_17.png"));
		texture_skybox = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/ice.jpg"));
		ocean = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/ocean.jpg"));

		liquid = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/liquid.jpg"));

		a = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_2.png"));

		b = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_8.png"));
		textc = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_16.png"));
		t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_18.png"));

//		rock = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/rock.jpg"));
		rock = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_29.png"));
		bg = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/background.jpg"));

		il = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/iceLight.jpg"));

		ii = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/icee.jpg"));

		cccc =  TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/iice.jpg"));
		texture_down =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/bottom.png"));
		texture_front=  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/front.png"));
		 texture_right =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/right.png"));
		texture_left =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/left.png"));
		texture_back =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/back.png"));
		texture_up =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/top.png"));

		ih =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_28.png"));
		space =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/space.png"));


		mud = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_15.png"));
		grass = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/img_2.png"));



		System.out.println("Texture loaded okay ");
	}
}
