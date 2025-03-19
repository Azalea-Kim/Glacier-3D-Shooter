package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Wafer {
    public Wafer() {
    }

    public void DrawWafer() {
        GL11.glBegin(4);

        for(float i = 0.0F; i <= 32.0F; ++i) {
            float angle = (float)(6.283185307179586 * (double)i / 32.0);
            float nextAngle = (float)(6.283185307179586 * (double)(i + 1.0F) / 32.0);
            float x1 = (float)Math.cos((double)angle);
            float y1 = (float)Math.sin((double)angle);
            float x2 = (float)Math.cos((double)nextAngle);
            float y2 = (float)Math.sin((double)nextAngle);
            Vector4f v1 = (new Point4f(x1, y1, 0.0F, 0.0F)).MinusPoint(new Point4f(0.0F, 0.0F, 0.0F, 0.0F));
            Vector4f w1 = (new Point4f(x2, y2, 0.0F, 0.0F)).MinusPoint(new Point4f(0.0F, 0.0F, 0.0F, 0.0F));
            Vector4f n = v1.cross(w1).Normal();
            GL11.glNormal3f(n.x, n.y, n.z);
            GL11.glVertex3f(x1, y1, 0.0F);
            GL11.glNormal3f(n.x, n.y, n.z);
            GL11.glVertex3f(x2, y2, 0.0F);
            GL11.glNormal3f(n.x, n.y, n.z);
            GL11.glVertex3f(0.0F, 0.0F, 0.0F);
        }

        GL11.glEnd();
    }
}
