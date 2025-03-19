
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Camera {

    public static int maxLookUp = 30;
    public static int maxLookDown = -30;
    public static int OrthoNumber = 0; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
//    public static Vector3f rotation = new Vector3f(10, 180, 0);

    public static Vector3f rotation = new Vector3f(0, 180, -14);
    public static Vector4f position = new Vector4f(0, 0, 0, 0);


    private static final int mouseSpeed = 1;
    private static final FloatBuffer noAmbient = BufferUtils.createFloatBuffer(4);
    private static final FloatBuffer diffuse = BufferUtils.createFloatBuffer(4);
    private static final FloatBuffer spec = BufferUtils.createFloatBuffer(4);
    private static final FloatBuffer direction = BufferUtils.createFloatBuffer(4);


    public static Boolean finishedLoading = false;
    public static FloatBuffer lightPos;
    private static Vector4f initialPos = new Vector4f();



    public Camera() {

    }

    public void setCamera(Vector4f v) {
        initialPos = v;
        noAmbient.put(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        noAmbient.rewind();
        diffuse.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        diffuse.rewind();
        spec.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        spec.rewind();
        direction.put(new float[]{0f, 0f, -1f, 0});
        direction.rewind();
        lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(0).put(3000f).put(0).put(1).flip();
        GL11.glLight(GL11.GL_LIGHT7, GL_POSITION, lightPos);
        GL11.glLight(GL11.GL_LIGHT7, GL_DIFFUSE, diffuse);
        GL11.glLight(GL11.GL_LIGHT7, GL_SPECULAR, spec);
        GL11.glLight(GL11.GL_LIGHT7, GL_SPOT_DIRECTION, direction);
        GL11.glLightf(GL11.GL_LIGHT7, GL_SPOT_CUTOFF, 45);

    }




    public void updatePosition() {
        glTranslatef(initialPos.x, initialPos.y, initialPos.z);
        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);
        glRotatef(-rotation.z, 0, 0, 1);
        glTranslatef(position.x, -position.y, position.z);

    }

    public void update_mouse() {

        int WheelPosition = Mouse.getDWheel();
        int MouseX = Mouse.getX();
        int MouseY = Mouse.getY();
        int MouseDX = Mouse.getDX();
        int MouseDY = Mouse.getDY();
        boolean MouseButtonPressed = Mouse.isButtonDown(0);
        boolean isGrabbed = Mouse.isGrabbed();

        if (WheelPosition > 0) {
            OrthoNumber += 10;
        }
        if (WheelPosition < 0) {
            OrthoNumber -= 10;
        }

        while (Mouse.next()) {
            if (Mouse.isButtonDown(0)) {
                Mouse.setGrabbed(true);
            }
            if (Mouse.isButtonDown(1)) {
                Mouse.setGrabbed(false);
            }
        }

        if (Mouse.isGrabbed()) {
            float mouseDX = MouseDX * mouseSpeed * 0.16f;
            float mouseDY = MouseDY * mouseSpeed * 0.16f;
            if (rotation.y + mouseDX >= 360) {
                rotation.y = rotation.y + mouseDX - 360;
            } else if (rotation.y + mouseDX < 0) {
                rotation.y = 360 - rotation.y + mouseDX;
            } else {
                rotation.y += mouseDX;
            }
            if (rotation.x - mouseDY >= maxLookDown && rotation.x - mouseDY <= maxLookUp) {
                rotation.x += -mouseDY;
            } else if (rotation.x - mouseDY < maxLookDown) {
                rotation.x = maxLookDown;
            } else if (rotation.x - mouseDY > maxLookUp) {
                rotation.x = maxLookUp;
            }
        }



    }




    public void update_keyboard(float delta) {
        float speed = delta;

        System.out.println();
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)||Keyboard.isKeyDown(Keyboard.KEY_U)) {
            Vector4f movement = new Vector4f();
            movement = movement.PlusVector(new Vector4f(0, 0, speed, 0));
            float angle = rotation.y - 180;
            float new_x = (float) (movement.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (movement.length() * Math.cos(Math.toRadians(angle)));
            movement.x = new_x;
            movement.z = -new_z;
//            position = position.PlusVector(new Vector4f(0,10,10,0));
            position = position.PlusVector(movement);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)||Keyboard.isKeyDown(Keyboard.KEY_K)) {
            Vector4f movement = new Vector4f();
            movement = movement.PlusVector(new Vector4f(speed, 0, 0, 0));
            float angle = 180 - rotation.y + 90;
            float new_x = (float) (movement.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (movement.length() * Math.cos(Math.toRadians(angle)));
            movement.x = new_x;
            movement.z = new_z;
            position = position.PlusVector(movement);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)||Keyboard.isKeyDown(Keyboard.KEY_J)) {
            Vector4f movement = new Vector4f();
            movement = movement.PlusVector(new Vector4f(0, -0, -speed, 0));
            float angle = rotation.y - 180;
            float new_x = (float) (movement.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (movement.length() * Math.cos(Math.toRadians(angle)));
            movement.x = -new_x;
            movement.z = new_z;
            position = position.PlusVector(movement);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)||Keyboard.isKeyDown(Keyboard.KEY_H)) {
            Vector4f movement = new Vector4f();
            movement = movement.PlusVector(new Vector4f(-speed, 0, 0, 0));
            float angle = 180 - rotation.y - 90;
            float new_x = (float) (movement.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (movement.length() * Math.cos(Math.toRadians(angle)));
            movement.x = new_x;
            movement.z = new_z;
            position = position.PlusVector(movement);
        }


//        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
//            if (position.y < 0) {
//
//            } else {
//                position.y -= speed;
//            }
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
//            position.y += speed;
//        }




    }

    public static Vector4f getPosition() {
        return position;
    }
}
