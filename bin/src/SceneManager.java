

import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * @Author: WangYuyang
 * @Date: 2021/11/2-15:39
 * @Project: Assignment3
 * @Package: Scene
 * @Description:
 **/
public class SceneManager {
    private ArrayList<SceneObject> sceneObjects = new ArrayList<>();
    private Skybox skybox;

    public void drawAll(IDrawListener listener, Integer delta) {
        for (int i = 0; i < sceneObjects.size(); i++) {
            sceneObjects.get(i).draw(listener, delta);
        }
    }

    public void remove(SceneObject sceneObject) {
        sceneObjects.remove(sceneObject);
    }

//    public void BulletHit(Bullet bullet) {
//        for (int i = 0; i < sceneObjects.size(); i++) {
////            System.out.println(sceneObjects.get(i).getWorldPosition().MinusPoint(new Point4f(bullet.getWorldPosition().x, bullet.getWorldPosition().y, bullet.getWorldPosition().z, 1)).length());
//            if (sceneObjects.get(i).getWorldPosition().MinusPoint(new Point4f(bullet.getWorldPosition().x, bullet.getWorldPosition().y, bullet.getWorldPosition().z, 1)).length() < 100) {
//                if (!(sceneObjects.get(i) instanceof Bullet) && !(sceneObjects.get(i) instanceof Player)) {
//                    sceneObjects.remove(i);
//                }
//
//            }
//        }
//    }

    public void drawSkyBox(Integer delta) {
        GL11.glPushMatrix();
        skybox.draw();
        GL11.glPopMatrix();

    }
    public void setSkybox(Skybox skybox){
        this.skybox = skybox;
    }

    public void addSceneObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    public ArrayList<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public void setSceneObjects(ArrayList<SceneObject> sceneObjects) {
        this.sceneObjects = sceneObjects;
    }
}
