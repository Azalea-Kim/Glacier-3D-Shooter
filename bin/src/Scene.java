import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * @Author: WangYuyang
 * @Date: 2021/11/4-16:28
 * @Project: Assignment3
 * @Package: main
 * @Description:
 **/
public class Scene {

    private static final Random random = new Random();

    public static float bookRotate_Speed = 0.1f;
    private static Integer bullet_counter = 0;

    public Scene() throws IOException {
    }


//    private static ParticleEmitter rightParticleEmitter = new ParticleEmitterBuilder()
//            .setLocation(new Vector3f(2.5f,12900,0))
//            .setEnable3D(true)
//            .setInitialVelocity(new Vector3f(0, 0, 0))
//            .setGravity(new Vector3f(0, -0.4f, 0))
//            .setSpawningRate(20)
//            .setParticleLifeTime(300)
//            .createParticleEmitter();
//
//    private static ParticleEmitter leftParticleEmitter = new ParticleEmitterBuilder()
//            .setLocation(new Vector3f(-2.5f,12900,0))
//            .setEnable3D(true)
//            .setInitialVelocity(new Vector3f(0, 0, 0))
//            .setGravity(new Vector3f(0, -0.4f, 0))
//            .setSpawningRate(20)
//            .setParticleLifeTime(300)
//            .createParticleEmitter();
//
//    private static ParticleEmitter backParticleEmitter = new ParticleEmitterBuilder()
//            .setLocation(new Vector3f(0,12900,-2.5f))
//            .setEnable3D(true)
//            .setInitialVelocity(new Vector3f(0, 0, 0))
//            .setGravity(new Vector3f(0, -0.4f, 0))
//            .setSpawningRate(10)
//            .setParticleLifeTime(200)
//            .createParticleEmitter();

    public static void initScene(SceneManager sceneManager, HashMap textures) {

    }
    Texture t_down =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/down.png"));
    Texture t_front =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/front.png"));
    Texture t_right =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/right.png"));
    Texture t_left =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/left.png"));
    Texture t_back =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/back.png"));
    Texture t_up =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/up.png"));

    public static void initBackground(SceneManager backgroundManager, HashMap textures) throws IOException {
        Texture t_down =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/down.png"));
        Texture t_front =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/front.png"));
        Texture t_right =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/right.png"));
        Texture t_left =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/left.png"));
        Texture t_back =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/back.png"));
        Texture t_up =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/skybox_old/up.png"));

//        backgroundManager.addSceneObject(new Ground(
//                new Point4f(0, 0, 0, 0),
//                new Point4f(0, 0, 0, 0),
//                new Vector4f(5000f, 1f, 5000f, 0),
//                textures
//        ));
        backgroundManager.setSkybox(new Skybox(
                new Point4f(0, 0, 0, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(30000f, 30000f, 30000f, 0),t_down,t_front,t_right,t_left,t_back,t_up));


    }

    public static void drawBackground(SceneManager backgroundManager, Integer delta) {
        backgroundManager.drawSkyBox(delta);
//        backgroundManager.drawAll(new IDrawListener() {
//            @Override
//            public void beforeEachDraw(SceneObject object) {
//                GL11.glPushMatrix();
//            }
//
//            @Override
//            public void afterEachDraw(SceneObject object) {
//                frontParticleEmitter.draw();
//                if (Book.hasSomeJumpping)
//                    bookParticleEmitter.draw();
////                rightParticleEmitter.draw();
////                leftParticleEmitter.draw();
////                backParticleEmitter.draw();
//                GL11.glPopMatrix();
//            }
//        }, delta);
//
//        frontParticleEmitter.update();
//        if (Book.hasSomeJumpping)
//            bookParticleEmitter.update();
//
//        bookParticleEmitter.colorVec = new Vector3f(random.nextFloat() + 0.5f, random.nextFloat() + 0.5f, random.nextFloat() + 0.5f);
//        if (SuperJumpPaddle.height <= 10) {
//            SuperJumpPaddle.height += +1f;
//        } else {
//            SuperJumpPaddle.height = -10;
//        }
////        rightParticleEmitter.update();
////        leftParticleEmitter.update();
////        backParticleEmitter.update();
//    }
//
//    public static void drawScene(SceneManager sceneManager, Integer delta) {
//        if (Mouse.isButtonDown(0) & bullet_counter <= 0) {
//            bullet_counter = 30;
//            Bullet bullet = new Bullet(
//                    Scene.player.getOrigin(),
//                    Scene.player.getPosition(),
//                    new Vector4f(90, 90, 90, 0),
//                    Engine.getTextures()
//            );
//            bullet.setDirection(new Vector4f(
//                    (float) Math.sin(Math.toRadians(180 - Camera.rotation.y)),
//                    0,
//                    (float) Math.cos(Math.toRadians(180 - Camera.rotation.y)),
//                    0
//            ));
//            sceneManager.addSceneObject(bullet);
//        }
//        if (bullet_counter > 0) {
//            bullet_counter--;
//        }
//        sceneManager.drawAll(new IDrawListener() {
//            @Override
//            public void beforeEachDraw(SceneObject object) {
//                GL11.glPushMatrix();
//            }
//
//            @Override
//            public void afterEachDraw(SceneObject object) {
//                GL11.glPopMatrix();
//                if (object instanceof Bullet) {
//                    Bullet bullet = (Bullet) object;
//                    if (!Main.harmless)
//                        sceneManager.BulletHit(bullet);
//                    if (bullet.getDrawCount() >= 300) {
//                        sceneManager.remove(bullet);
//                    }
//                }
//            }
//        }, delta);
    }

}
