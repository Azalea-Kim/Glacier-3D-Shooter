//package objects3D;
//
//import org.lwjgl.BufferUtils;
//import org.lwjgl.LWJGLException;
//import org.lwjgl.Sys;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.util.glu.GLU;
//import org.newdawn.slick.Color;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;
//
//import java.io.IOException;
//import java.nio.FloatBuffer;
//
//
//glTexParameter
//
//public class hi {
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//import GraphicsObjects.Arcball;
//import GraphicsObjects.Utils;
//import java.io.IOException;
//import java.nio.FloatBuffer;
//import objects3D.Bird;
//import objects3D.Brand;
//import objects3D.Flower;
//import objects3D.Human;
//import objects3D.HumanShadow;
//import objects3D.Light;
//import objects3D.Obstacle;
//import objects3D.Ship;
//import objects3D.Sphere;
//import objects3D.Swing;
//import objects3D.Tent;
//import objects3D.Tetrahedron;
//import objects3D.TexCube;
//import objects3D.Windmill;
//import org.lwjgl.BufferUtils;
//import org.lwjgl.LWJGLException;
//import org.lwjgl.Sys;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.util.glu.GLU;
//import org.newdawn.slick.Color;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;
//
//    public class MainWindow {
//        private boolean MouseOnepressed = true;
//        private boolean dragMode = false;
//        private boolean BadAnimation = true;
//        private boolean Earth = false;
//        private boolean SwingStatus = false;
//        private boolean TentStatus = false;
//        private boolean ShipStatus = false;
//        private boolean BrandStatus = false;
//        private boolean ObstacleStatus = false;
//        private boolean MillStatus = false;
//        private int DancingStatus = 0;
//        private int DancingStatusTmp = 0;
//        private int CountFlag = 0;
//        private boolean bufferFlag = true;
//        private float birdStart = 2000.0F;
//        private boolean birdFlag = true;
//        private float birdTmp = 0.0F;
//        private int windInfluenceTime = 0;
//        private int windInfluenceCount = 0;
//        private boolean lemonDown = false;
//        private boolean SwingTmpFlag = false;
//        private boolean birdChange = false;
//        private int lemonDownFinished = 0;
//        private int swingInt = 0;
//        float x = 0.0F;
//        float y = 0.0F;
//        float tmpX = 0.0F;
//        float tmpY = 0.0F;
//        float rotation = 0.0F;
//        long lastFrame;
//        int fps;
//        long lastFPS;
//        boolean flag = false;
//        boolean flagObstacle = false;
//        long myDelta = 0L;
//        float Alpha = 0.0F;
//        long StartTime;
//        Arcball MyArcball = new Arcball();
//        boolean DRAWGRID = false;
//        boolean waitForKeyrelease = true;
//        int LastMouseX = -1;
//        int LastMouseY = -1;
//        boolean watching = false;
//        float deltaTmp = 0.0F;
//        float deltaShipTmp = 0.0F;
//        float pullX = 0.0F;
//        float pullY = 0.0F;
//        int move = 0;
//        int direction = 0;
//        int tmpDirection = 0;
//        int jump = 0;
//        int limit = 0;
//        int OrthoNumber = 3200;
//        static float[] black = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
//        static float[] white = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
//        static float[] grey = new float[]{0.5F, 0.5F, 0.5F, 1.0F};
//        static float[] spot = new float[]{0.1F, 0.1F, 0.1F, 0.5F};
//        static float[] red = new float[]{1.0F, 0.0F, 0.0F, 1.0F};
//        static float[] green = new float[]{0.0F, 1.0F, 0.0F, 1.0F};
//        static float[] blue = new float[]{0.0F, 0.0F, 1.0F, 1.0F};
//        static float[] yellow = new float[]{1.0F, 1.0F, 0.0F, 1.0F};
//        static float[] magenta = new float[]{1.0F, 0.0F, 1.0F, 1.0F};
//        static float[] cyan = new float[]{0.0F, 1.0F, 1.0F, 1.0F};
//        static float[] orange = new float[]{1.0F, 0.5F, 0.0F, 1.0F, 1.0F};
//        static float[] brown = new float[]{0.5F, 0.25F, 0.0F, 1.0F, 1.0F};
//        static float[] dkgreen = new float[]{0.0F, 0.5F, 0.0F, 1.0F, 1.0F};
//        static float[] pink = new float[]{1.0F, 0.6F, 0.6F, 1.0F, 1.0F};
//        Texture texture;
//        Texture texture2;
//        Texture texture3;
//        Texture texture4;
//        Texture texture5;
//        Texture texture6;
//        Texture texture7;
//        Texture texture8;
//        Texture texture9;
//        Texture texture10;
//        Texture texture11;
//        Texture texture12;
//        Texture texture13;
//        Texture texture14;
//        Texture[] gifTexture = new Texture[11];
//
//        public MainWindow() {
//        }
//
//        public void start() {
//            this.StartTime = this.getTime();
//
//            try {
//                Display.setDisplayMode(new DisplayMode(1200, 800));
//                Display.create();
//            } catch (LWJGLException var2) {
//                var2.printStackTrace();
//                System.exit(0);
//            }
//
//            this.initGL();
//            this.getDelta();
//            this.lastFPS = this.getTime();
//
//            while(!Display.isCloseRequested()) {
//                int delta = this.getDelta();
//                this.update(delta);
//                this.renderGL();
//                Display.update();
//                Display.sync(120);
//            }
//
//            Display.destroy();
//        }
//
//        public void update(int delta) {
//            int MouseX = Mouse.getX();
//            int MouseY = Mouse.getY();
//            int WheelPostion = Mouse.getDWheel();
//            boolean MouseButonPressed = Mouse.isButtonDown(0);
//            if (MouseButonPressed && !this.MouseOnepressed) {
//                this.MouseOnepressed = true;
//                this.MyArcball.startBall(MouseX, MouseY, 1200, 800);
//                this.dragMode = true;
//            } else if (!MouseButonPressed) {
//                this.MouseOnepressed = false;
//                this.dragMode = false;
//            }
//
//            if (WheelPostion > 0) {
//                this.OrthoNumber += 10;
//            }
//
//            if (WheelPostion < 0) {
//                this.OrthoNumber -= 10;
//                if (this.OrthoNumber < 610) {
//                    this.OrthoNumber = 610;
//                }
//            }
//
//            if (Keyboard.isKeyDown(30) && !this.SwingStatus && !this.TentStatus && !this.watching) {
//                this.x -= 0.35F * (float)delta;
//                this.direction = 0;
//                this.move = 1;
//                if (this.DancingStatus == 2) {
//                    this.x -= 0.15F * (float)delta;
//                }
//            } else if (Keyboard.isKeyDown(32) && !this.SwingStatus && !this.TentStatus && !this.watching) {
//                this.x += 0.35F * (float)delta;
//                this.direction = 1;
//                this.move = 2;
//                if (this.DancingStatus == 2) {
//                    this.x += 0.15F * (float)delta;
//                }
//            } else if (Keyboard.isKeyDown(17) && !this.SwingStatus && !this.TentStatus && !this.watching) {
//                this.y += 0.35F * (float)delta;
//                this.direction = 2;
//                this.move = 3;
//                if (this.DancingStatus == 2) {
//                    this.y += 0.15F * (float)delta;
//                }
//            } else if (Keyboard.isKeyDown(31) && !this.SwingStatus && !this.TentStatus && !this.watching) {
//                this.y -= 0.35F * (float)delta;
//                this.direction = 3;
//                this.move = 4;
//                if (this.DancingStatus == 2) {
//                    this.y -= 0.15F * (float)delta;
//                }
//            } else {
//                this.move = 0;
//            }
//
//            Thread thread;
//            Thread thread2;
//            if (Keyboard.isKeyDown(2) && this.x <= -50.0F && this.x >= -400.0F && this.y <= 50.0F && this.y >= -150.0F) {
//                this.SwingStatus = true;
//                this.SwingTmpFlag = true;
//                thread = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(5000L);
//                        } catch (InterruptedException var5) {
//                            var5.printStackTrace();
//                        } finally {
//                            MainWindow.this.SwingStatus = false;
//                        }
//
//                    }
//                };
//                thread.start();
//                thread2 = new Thread() {
//                    public void run() {
//                        boolean var6 = false;
//
//                        class NamelessClass_1 extends Thread {
//                            NamelessClass_1() {
//                            }
//
//                            public void run() {
//                                try {
//                                    sleep(800L);
//                                } catch (InterruptedException var5) {
//                                    var5.printStackTrace();
//                                } finally {
//                                    MainWindow.this.lemonDownFinished = 2;
//                                }
//
//                            }
//                        }
//
//                        NamelessClass_1 thread3;
//                        label46: {
//                            try {
//                                var6 = true;
//                                sleep(1500L);
//                                var6 = false;
//                                break label46;
//                            } catch (InterruptedException var7) {
//                                var7.printStackTrace();
//                                var6 = false;
//                            } finally {
//                                if (var6) {
//                                    MainWindow.this.lemonDownFinished = 1;
//                                    NamelessClass_1 var3 = new NamelessClass_1();
//                                    var3.start();
//                                }
//                            }
//
//                            MainWindow.this.lemonDownFinished = 1;
//                            thread3 = new NamelessClass_1();
//                            thread3.start();
//                            return;
//                        }
//
//                        MainWindow.this.lemonDownFinished = 1;
//                        thread3 = new NamelessClass_1();
//                        thread3.start();
//                    }
//                };
//                if (!this.lemonDown) {
//                    thread2.start();
//                }
//
//                this.lemonDown = true;
//            }
//
//            if (Keyboard.isKeyDown(3) && this.x >= 1130.0F && this.x <= 1870.0F && this.y >= 420.0F && this.y <= 520.0F && this.CountFlag == 0) {
//                this.ShipStatus = true;
//                this.ObstacleStatus = true;
//                this.watching = true;
//                this.CountFlag = 1;
//                thread = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(4000L);
//                        } catch (InterruptedException var5) {
//                            var5.printStackTrace();
//                        } finally {
//                            MainWindow.this.watching = false;
//                            MainWindow.this.CountFlag = 3;
//                            MainWindow.this.flagObstacle = false;
//                        }
//
//                    }
//                };
//                thread.start();
//                thread2 = new Thread() {
//                    public void run() {
//                        boolean var6 = false;
//
//                        class NamelessClass_1 extends Thread {
//                            NamelessClass_1() {
//                            }
//
//                            public void run() {
//                                try {
//                                    sleep(4000L);
//                                } catch (InterruptedException var5) {
//                                    var5.printStackTrace();
//                                } finally {
//                                    MainWindow.this.CountFlag = 4;
//                                }
//
//                            }
//                        }
//
//                        NamelessClass_1 thread3;
//                        label46: {
//                            try {
//                                var6 = true;
//                                sleep(22000L);
//                                var6 = false;
//                                break label46;
//                            } catch (InterruptedException var7) {
//                                var7.printStackTrace();
//                                var6 = false;
//                            } finally {
//                                if (var6) {
//                                    MainWindow.this.ObstacleStatus = false;
//                                    MainWindow.this.CountFlag = 2;
//                                    NamelessClass_1 var3 = new NamelessClass_1();
//                                    var3.start();
//                                }
//                            }
//
//                            MainWindow.this.ObstacleStatus = false;
//                            MainWindow.this.CountFlag = 2;
//                            thread3 = new NamelessClass_1();
//                            thread3.start();
//                            return;
//                        }
//
//                        MainWindow.this.ObstacleStatus = false;
//                        MainWindow.this.CountFlag = 2;
//                        thread3 = new NamelessClass_1();
//                        thread3.start();
//                    }
//                };
//                thread2.start();
//            }
//
//            if (Keyboard.isKeyDown(4) && this.x >= -985.0F && this.x <= -615.0F && this.y >= -700.0F && this.y <= -510.0F && this.direction == 2) {
//                if (!this.BrandStatus) {
//                    this.DancingStatusTmp = this.DancingStatus;
//                }
//
//                this.BrandStatus = true;
//                this.DancingStatus = 1;
//                thread = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(10000L);
//                        } catch (InterruptedException var5) {
//                            var5.printStackTrace();
//                        } finally {
//                            MainWindow.this.BrandStatus = false;
//                        }
//
//                    }
//                };
//                thread2 = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(4000L);
//                        } catch (InterruptedException var5) {
//                            var5.printStackTrace();
//                        } finally {
//                            MainWindow.this.DancingStatus = MainWindow.this.DancingStatusTmp;
//                            MainWindow.this.DancingStatusTmp = 0;
//                        }
//
//                    }
//                };
//                thread.start();
//                thread2.start();
//            }
//
//            if (Mouse.isButtonDown(0) && this.bufferFlag) {
//                if (this.DancingStatus == 0) {
//                    this.DancingStatus = 2;
//                } else if (this.DancingStatus == 2) {
//                    this.DancingStatus = 0;
//                }
//
//                this.bufferFlag = false;
//                thread = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(1000L);
//                        } catch (InterruptedException var5) {
//                            var5.printStackTrace();
//                        } finally {
//                            MainWindow.this.bufferFlag = true;
//                        }
//
//                    }
//                };
//                thread.start();
//            }
//
//            if (Keyboard.isKeyDown(5) && this.x <= 2400.0F && this.x >= 1600.0F && this.y >= -1000.0F && this.y <= -725.0F) {
//                this.TentStatus = true;
//                thread = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(5000L);
//                        } catch (InterruptedException var5) {
//                            var5.printStackTrace();
//                        } finally {
//                            MainWindow.this.TentStatus = false;
//                            MainWindow.this.flag = false;
//                        }
//
//                    }
//                };
//                thread.start();
//            }
//
//            if (Keyboard.isKeyDown(57) && !this.SwingStatus && !this.TentStatus && !this.watching) {
//                this.jump = (int)((float)this.jump + 0.5F * (float)delta);
//                if (this.jump > 80) {
//                    this.limit = 1;
//                    this.jump = 80;
//                }
//
//                if (this.limit == 1) {
//                    this.jump = (int)((float)this.jump - 1.0F * (float)delta);
//                }
//
//                if (this.jump <= 0) {
//                    this.jump = 0;
//                    this.limit = 0;
//                }
//            }
//
//            if (!Keyboard.isKeyDown(57)) {
//                this.jump = (int)((float)this.jump - 0.5F * (float)delta);
//                if (this.jump <= 0) {
//                    this.jump = 0;
//                    this.limit = 0;
//                }
//            }
//
//            if (this.waitForKeyrelease && Keyboard.isKeyDown(34)) {
//                this.DRAWGRID = !this.DRAWGRID;
//                Keyboard.next();
//                if (Keyboard.isKeyDown(34)) {
//                    this.waitForKeyrelease = true;
//                } else {
//                    this.waitForKeyrelease = false;
//                }
//            }
//
//            if (!Keyboard.isKeyDown(34)) {
//                this.waitForKeyrelease = true;
//            } else {
//                this.waitForKeyrelease = false;
//            }
//
//            if (this.x < -2000.0F) {
//                this.x = -2000.0F;
//            }
//
//            if (this.x > 2400.0F) {
//                this.x = 2400.0F;
//            }
//
//            if (this.y < -1540.0F) {
//                this.y = -1540.0F;
//            }
//
//            if (this.y > 520.0F) {
//                this.y = 520.0F;
//            }
//
//            if (this.x < -715.0F && this.x > -915.0F && this.y < -390.0F && this.y > -510.0F) {
//                if (this.direction == 0) {
//                    this.x = -715.0F;
//                } else if (this.direction == 1) {
//                    this.x = -915.0F;
//                } else if (this.direction == 2) {
//                    this.y = -510.0F;
//                } else if (this.direction == 3) {
//                    this.y = -390.0F;
//                }
//            }
//
//            if (this.x < 50.0F && this.x > -480.0F && this.y < 150.0F && this.y > 0.0F) {
//                if (this.direction == 0) {
//                    this.x = 50.0F;
//                } else if (this.direction == 1) {
//                    this.x = -480.0F;
//                } else if (this.direction == 2) {
//                    this.y = 0.0F;
//                } else if (this.direction == 3) {
//                    this.y = 150.0F;
//                }
//            }
//
//            if (this.x < -65.0F && this.x > -345.0F && this.y < 240.0F && this.y > 100.0F) {
//                if (this.direction == 0) {
//                    this.x = -65.0F;
//                } else if (this.direction == 1) {
//                    this.x = -345.0F;
//                } else if (this.direction == 2) {
//                    this.y = 100.0F;
//                } else if (this.direction == 3) {
//                    this.y = 240.0F;
//                }
//            }
//
//            if (this.x < 2450.0F && this.x > 1450.0F && this.y < -290.0F && this.y > -730.0F) {
//                if (this.direction == 0) {
//                    this.x = 2400.0F;
//                } else if (this.direction == 1) {
//                    this.x = 1450.0F;
//                } else if (this.direction == 2) {
//                    this.y = -730.0F;
//                } else if (this.direction == 3) {
//                    this.y = -290.0F;
//                }
//            }
//
//            if (this.x < 1100.0F && this.x > 900.0F && this.y < 520.0F && this.y > 440.0F) {
//                if (this.direction == 0) {
//                    this.x = 1100.0F;
//                } else if (this.direction == 1) {
//                    this.x = 900.0F;
//                } else if (this.direction == 2) {
//                    this.y = 440.0F;
//                } else if (this.direction == 3) {
//                    this.y = 520.0F;
//                }
//            }
//
//            if (this.x < 1785.0F && this.x > 1585.0F && this.y < 520.0F && this.y > 440.0F) {
//                if (this.direction == 0) {
//                    this.x = 1785.0F;
//                } else if (this.direction == 1) {
//                    this.x = 1585.0F;
//                } else if (this.direction == 2) {
//                    this.y = 440.0F;
//                } else if (this.direction == 3) {
//                    this.y = 520.0F;
//                }
//            }
//
//            if (this.x < 65.0F && this.x > -285.0F && this.y > 430.0F) {
//                if (this.direction == 0) {
//                    this.x = 65.0F;
//                } else if (this.direction == 1) {
//                    this.x = -285.0F;
//                } else if (this.direction == 2) {
//                    this.y = 430.0F;
//                }
//            }
//
//            if (this.x < -1900.0F && this.x >= -2000.0F && this.y <= 520.0F && this.y > 390.0F) {
//                if (this.direction == 0) {
//                    this.x = -1900.0F;
//                } else if (this.direction == 1) {
//                    this.x = -2000.0F;
//                } else if (this.direction == 2) {
//                    this.y = 390.0F;
//                } else if (this.direction == 3) {
//                    this.y = 520.0F;
//                }
//            }
//
//            this.updateFPS();
//            this.LastMouseX = MouseX;
//            this.LastMouseY = MouseY;
//        }
//
//        public int getDelta() {
//            long time = this.getTime();
//            int delta = (int)(time - this.lastFrame);
//            this.lastFrame = time;
//            return delta;
//        }
//
//        public long getTime() {
//            return Sys.getTime() * 1000L / Sys.getTimerResolution();
//        }
//
//        public void updateFPS() {
//            if (this.getTime() - this.lastFPS > 1000L) {
//                Display.setTitle("FPS: " + this.fps);
//                this.fps = 0;
//                this.lastFPS += 1000L;
//            }
//
//            ++this.fps;
//        }
//
//        public void initGL() {
//            GL11.glMatrixMode(5889);
//            GL11.glLoadIdentity();
//            this.changeOrth();
//            this.MyArcball.startBall(0, 0, 1200, 800);
//            GL11.glMatrixMode(5888);
//            FloatBuffer lightPos1 = BufferUtils.createFloatBuffer(4);
//            lightPos1.put(1870.0F).put(600.0F).put(975.0F).put(1.0F).flip();
//            FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
//            lightPos2.put(1170.0F).put(600.0F).put(975.0F).put(1.0F).flip();
//            FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
//            lightPos4.put(-400.0F).put(2000.0F).put(-800.0F).put(0.0F).flip();
//            FloatBuffer lightPos6 = BufferUtils.createFloatBuffer(4);
//            lightPos6.put(-750.0F).put(2000.0F).put(-1300.0F).put(1.0F).flip();
//            FloatBuffer lightPos7 = BufferUtils.createFloatBuffer(4);
//            lightPos7.put(-550.0F).put(2100.0F).put(550.0F).put(1.0F).flip();
//            FloatBuffer lightPos8 = BufferUtils.createFloatBuffer(4);
//            lightPos8.put(-100.0F).put(2100.0F).put(100.0F).put(1.0F).flip();
//            FloatBuffer lightPos9 = BufferUtils.createFloatBuffer(4);
//            lightPos9.put(-900.0F).put(2100.0F).put(-100.0F).put(1.0F).flip();
//            GL11.glLight(16384, 4611, lightPos1);
//            GL11.glEnable(16384);
//            GL11.glLight(16384, 4609, Utils.ConvertForGL(pink));
//            GL11.glLight(16385, 4611, lightPos2);
//            GL11.glEnable(16385);
//            GL11.glLight(16385, 4609, Utils.ConvertForGL(pink));
//            GL11.glLight(16387, 4611, lightPos4);
//            GL11.glEnable(16387);
//            GL11.glLight(16387, 4609, Utils.ConvertForGL(grey));
//            GL11.glLight(16388, 4611, lightPos6);
//            GL11.glEnable(16388);
//            GL11.glLight(16388, 4609, Utils.ConvertForGL(white));
//            GL11.glLight(16389, 4611, lightPos7);
//            GL11.glEnable(16389);
//            GL11.glLight(16389, 4609, Utils.ConvertForGL(white));
//            GL11.glLight(16390, 4611, lightPos8);
//            GL11.glEnable(16390);
//            GL11.glLight(16390, 4609, Utils.ConvertForGL(white));
//            GL11.glLight(16391, 4611, lightPos9);
//            GL11.glEnable(16391);
//            GL11.glLight(16391, 4609, Utils.ConvertForGL(white));
//            GL11.glEnable(2896);
//            GL11.glEnable(2929);
//            GL11.glEnable(2977);
//            GL11.glEnable(2903);
//            GL11.glEnable(3042);
//            GL11.glBlendFunc(770, 771);
//
//            try {
//                this.init();
//            } catch (IOException var9) {
//                var9.printStackTrace();
//            }
//
//        }
//
//        public void changeOrth() {
//            GL11.glMatrixMode(5889);
//            GL11.glLoadIdentity();
//            GL11.glOrtho((double)(1200 - this.OrthoNumber), (double)this.OrthoNumber, (double)(800.0F - (float)this.OrthoNumber * 0.66F), (double)((float)this.OrthoNumber * 0.66F), 100000.0, -100000.0);
//            GL11.glMatrixMode(5888);
//            FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
//            GL11.glGetFloat(2982, CurrentMatrix);
//            this.MyArcball.getMatrix(CurrentMatrix);
//            GL11.glLoadMatrix(CurrentMatrix);
//        }
//
//        public void renderGL() {
//            this.changeOrth();
//            GLU.gluLookAt(0.0F, -200.0F, 1500.0F, this.x, 350.0F, -800.0F + this.y, 0.0F, 1.0F, 0.0F);
//            GL11.glClear(16640);
//            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
//            GL11.glColor3f(0.5F, 0.5F, 1.0F);
//            this.myDelta = this.getTime() - this.StartTime;
//            float delta = (float)this.myDelta / 10000.0F;
//            float theta = (float)((double)(delta * 2.0F) * Math.PI);
//            float thetaDeg = delta * 360.0F;
//            float posn_x = (float)Math.cos((double)theta);
//            float posn_y = (float)Math.sin((double)theta);
//            GL11.glPushMatrix();
//            Human MyHuman = new Human();
//            if (!this.SwingStatus && !this.watching && !this.TentStatus) {
//                GL11.glTranslatef(200.0F + this.x, (float)(200 + this.jump), -500.0F + 3.0F * this.y);
//                GL11.glScalef(90.0F, 90.0F, 90.0F);
//                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//            } else if (this.SwingStatus) {
//                GL11.glTranslatef(0.0F, 220.0F, -400.0F);
//                GL11.glScalef(90.0F, 90.0F, 90.0F);
//                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//                this.direction = 3;
//                float thetaTmp = (float)((double)(delta * 10.0F) * Math.PI);
//                float LimbRotation = (float)Math.cos((double)thetaTmp) * 45.0F;
//                GL11.glRotatef(-LimbRotation / 2.0F, 1.0F, 0.0F, 0.0F);
//                GL11.glTranslatef(0.0F, 0.0F, LimbRotation / 11.0F);
//            } else if (this.ShipStatus) {
//                GL11.glTranslatef(200.0F + this.x, (float)(200 + this.jump), -500.0F + 3.0F * this.y);
//                GL11.glScalef(90.0F, 90.0F, 90.0F);
//                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//                this.direction = 2;
//            } else if (this.TentStatus) {
//                GL11.glTranslatef(200.0F + this.x, (float)(200 + this.jump), -500.0F + 3.0F * this.y);
//                GL11.glScalef(90.0F, 90.0F, 90.0F);
//                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//                this.direction = 2;
//            }
//
//            if (this.direction == 0) {
//                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            if (this.direction == 1) {
//                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            if (this.direction == 2) {
//                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            if (this.direction == 3) {
//                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            Boolean moveStatus = true;
//            if (this.move == 0) {
//                moveStatus = false;
//            }
//
//            MyHuman.DrawHuman(delta * 10.0F, moveStatus, this.SwingStatus, this.watching, this.DancingStatus, this.texture, this.texture5);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            HumanShadow shadow = new HumanShadow();
//            if (!this.SwingStatus && !this.watching && !this.TentStatus) {
//                GL11.glTranslatef(200.0F + this.x, 10.0F, -700.0F + 3.0F * this.y - (float)this.jump);
//                GL11.glScalef(90.0F, 5.0F, 90.0F);
//                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//            } else if (this.SwingStatus) {
//                GL11.glTranslatef(0.0F, 10.0F, -850.0F);
//                GL11.glScalef(90.0F, 5.0F, 90.0F);
//                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//                float thetaTmp = (float)((double)(delta * 10.0F) * Math.PI);
//                float LimbRotation = (float)Math.cos((double)thetaTmp) * 45.0F;
//                GL11.glRotatef(LimbRotation / 2.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glTranslatef(-LimbRotation / 14.0F, 0.0F, 0.0F);
//            } else if (this.ShipStatus && this.watching) {
//                GL11.glTranslatef(200.0F + this.x, 10.0F, -700.0F + 3.0F * this.y - (float)this.jump);
//                GL11.glScalef(90.0F, 5.0F, 90.0F);
//                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//                this.direction = 2;
//            } else if (this.TentStatus) {
//                GL11.glTranslatef(200.0F + this.x, 10.0F, -700.0F + 3.0F * this.y - (float)this.jump);
//                GL11.glScalef(90.0F, 5.0F, 90.0F);
//                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//                this.direction = 2;
//            }
//
//            if (this.direction == 0) {
//                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            if (this.direction == 1) {
//                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            if (this.direction == 2) {
//                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            if (this.direction == 3) {
//                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//            }
//
//            GL11.glColor3f(black[0], black[1], black[2]);
//            shadow.DrawHuman(delta * 10.0F, moveStatus, this.SwingStatus, this.watching, this.DancingStatus);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Ship ship = new Ship();
//            if (!this.ShipStatus) {
//                this.deltaShipTmp = delta;
//            }
//
//            GL11.glTranslatef(1450.0F - (delta - this.deltaShipTmp) * 1000.0F, 0.0F, 2000.0F);
//            GL11.glScalef(200.0F, 200.0F, 200.0F);
//            ship.DrawShip(delta * 5.0F, true, this.texture9);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Brand brand = new Brand();
//            GL11.glTranslatef(-600.0F, 400.0F, -1800.0F);
//            GL11.glScalef(100.0F, 100.0F, 100.0F);
//            brand.DrawBrand(delta * 225.0F, this.BrandStatus, this.gifTexture, this.texture9, this.texture10);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Tent tent = new Tent();
//            GL11.glTranslatef(2200.0F, 500.0F, -2000.0F);
//            GL11.glScalef(200.0F, 200.0F, 200.0F);
//            if (!this.flag && this.TentStatus) {
//                this.deltaTmp = delta;
//                this.flag = true;
//            }
//
//            tent.DrawTent((delta - this.deltaTmp) * 2.0F, this.TentStatus, this.texture9);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Swing swing = new Swing();
//            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
//            GL11.glScalef(100.0F, 80.0F, 100.0F);
//            if (this.SwingStatus && this.SwingTmpFlag) {
//                this.deltaTmp = delta;
//                this.SwingTmpFlag = false;
//            }
//
//            swing.DrawSwing(delta * 5.0F, (delta - this.deltaTmp) * 5.0F, this.SwingStatus, this.lemonDown, this.lemonDownFinished, this.texture6, this.texture7, this.texture8, this.texture14);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Obstacle obstacle = new Obstacle();
//            GL11.glTranslatef(100.0F, 30.0F, 1000.0F);
//            GL11.glScalef(1.0F, 1.0F, 1.0F);
//            if (!this.flagObstacle && (this.CountFlag == 1 || this.CountFlag == 2)) {
//                this.deltaTmp = delta;
//                this.flagObstacle = true;
//            }
//
//            obstacle.DrawObstacle((delta - this.deltaTmp) * 5.92F, this.ObstacleStatus, this.CountFlag, this.texture11);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            TexCube road = new TexCube();
//            GL11.glTranslatef(600.0F, -20.0F, -6600.0F);
//            GL11.glScalef(7200.0F, 10.0F, 8000.0F);
//            GL11.glTexParameteri(3553, 10242, 10496);
//            Color.white.bind();
//            this.texture3.bind();
//            GL11.glEnable(3553);
//            GL11.glTexParameteri(3553, 10240, 9728);
//            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
//            road.DrawTexCube();
//            GL11.glDisable(3553);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            road = new TexCube();
//            GL11.glTranslatef(600.0F, 2000.0F, 2700.0F);
//            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
//            GL11.glScalef(10.0F, 2000.0F, 7200.0F);
//            GL11.glTexParameteri(3553, 10242, 10496);
//            Color.white.bind();
//            this.texture2.bind();
//            GL11.glEnable(3553);
//            GL11.glTexParameteri(3553, 10240, 9728);
//            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
//            road.DrawTexCube();
//            GL11.glDisable(3553);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Windmill windmill = new Windmill();
//            GL11.glTranslatef(-1800.0F, 30.0F, 800.0F);
//            GL11.glScalef(1.0F, 1.0F, 1.0F);
//            if (this.ShipStatus && !this.MillStatus) {
//                this.deltaTmp = delta;
//                this.MillStatus = true;
//                this.swingInt = 0;
//            }
//
//            windmill.DrawWindmill(delta - this.deltaTmp, this.swingInt++, !this.ShipStatus, this.texture);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            road = new TexCube();
//            GL11.glTranslatef(600.0F, -20.0F, 1800.0F);
//            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//            GL11.glScalef(1000.0F, 10.0F, 7000.0F);
//            GL11.glTexParameteri(3553, 10242, 10496);
//            Color.white.bind();
//            this.texture4.bind();
//            GL11.glEnable(3553);
//            GL11.glTexParameteri(3553, 10240, 9728);
//            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
//            road.DrawTexCube();
//            GL11.glDisable(3553);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            GL11.glColor4f(orange[0], orange[1], orange[2], 1.0F);
//            GL11.glMaterial(1028, 5634, Utils.ConvertForGL(yellow));
//            Sphere sphere = new Sphere();
//            new Tetrahedron();
//            GL11.glTranslatef(-600.0F, 1800.0F, 1800.0F);
//            GL11.glScalef(1.0F, 1.0F, 1.0F);
//            sphere.DrawSphere(100.0F, 32.0F, 32.0F);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            Light light = new Light();
//            GL11.glTranslatef(1870.0F, 600.0F, 975.0F);
//            GL11.glScalef(1.0F, 1.0F, 1.0F);
//            light.DrawLight(delta, this.watching);
//            GL11.glPopMatrix();
//            GL11.glPushMatrix();
//            light = new Light();
//            GL11.glTranslatef(1170.0F, 600.0F, 975.0F);
//            GL11.glScalef(1.0F, 1.0F, 1.0F);
//            light.DrawLight(delta, this.watching);
//            GL11.glPopMatrix();
//
//            for(int i = 0; i < 5; ++i) {
//                GL11.glPushMatrix();
//                Flower flower = new Flower();
//                GL11.glTranslatef((float)(-1500 - i * 100), 0.0F, (float)(-1000 - i * 100));
//                GL11.glScalef(1.0F, 1.0F, 1.0F);
//                flower.DrawFlower(delta, this.ShipStatus);
//                GL11.glPopMatrix();
//            }
//
//            GL11.glPushMatrix();
//            Bird bird = new Bird();
//            if (this.birdFlag) {
//                if (!this.birdChange) {
//                    GL11.glTranslatef(this.birdStart - (delta - this.birdTmp) * 3000.0F, 1800.0F, -1000.0F);
//                } else {
//                    GL11.glTranslatef(this.birdStart - (delta - this.birdTmp) * 3000.0F + (float)(this.windInfluenceCount / 150), 1800.0F, -1000.0F);
//                    this.windInfluenceCount += this.windInfluenceTime * 2;
//                    ++this.windInfluenceTime;
//                }
//            } else if (!this.birdChange) {
//                GL11.glTranslatef(this.birdStart + (delta - this.birdTmp) * 3000.0F, 1800.0F, -1000.0F);
//            } else {
//                GL11.glTranslatef(this.birdStart + (delta - this.birdTmp) * 4500.0F - (float)(this.windInfluenceCount / 200), 1800.0F, -1000.0F);
//                this.windInfluenceCount += this.windInfluenceTime * 2;
//                ++this.windInfluenceTime;
//            }
//
//            if (this.birdChange && this.ShipStatus) {
//                if (this.birdStart - (delta - this.birdTmp) * 3000.0F + (float)(this.windInfluenceCount / 150) <= -2500.0F && this.birdFlag) {
//                    this.birdTmp = delta;
//                    this.birdFlag = false;
//                    this.birdStart = -2500.0F;
//                    this.windInfluenceTime = 0;
//                    this.windInfluenceCount = 0;
//                } else if (this.birdStart + (delta - this.birdTmp) * 4500.0F - (float)(this.windInfluenceCount / 200) >= 2500.0F && !this.birdFlag) {
//                    this.birdTmp = delta;
//                    this.birdFlag = true;
//                    this.birdStart = 2500.0F;
//                    this.windInfluenceTime = 0;
//                    this.windInfluenceCount = 0;
//                }
//            } else if (this.birdStart - (delta - this.birdTmp) * 3000.0F <= -2500.0F && this.birdFlag) {
//                this.birdTmp = delta;
//                this.birdFlag = false;
//                if (this.ShipStatus) {
//                    this.birdChange = true;
//                }
//
//                this.birdStart = -2500.0F;
//                this.windInfluenceTime = 0;
//                this.windInfluenceCount = 0;
//            } else if (this.birdStart + (delta - this.birdTmp) * 3000.0F >= 2500.0F && !this.birdFlag) {
//                this.birdTmp = delta;
//                this.birdFlag = true;
//                if (this.ShipStatus) {
//                    this.birdChange = true;
//                }
//
//                this.birdStart = 2500.0F;
//                this.windInfluenceTime = 0;
//                this.windInfluenceCount = 0;
//            }
//
//            GL11.glScalef(1.0F, 1.0F, 1.0F);
//            bird.DrawBird(delta, this.birdFlag, this.texture12, this.texture13);
//            GL11.glPopMatrix();
//        }
//
//        public static void main(String[] argv) {
//            MainWindow hello = new MainWindow();
//            hello.start();
//        }
//
//        public void init() throws IOException {
//            this.texture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/aliens.jpeg"));
//            this.texture2 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/2021.jpg"));
//            this.texture3 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/grass.jpeg"));
//            this.texture4 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/water.jpeg"));
//            this.texture5 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/aliens2.jpeg"));
//            this.texture6 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/trunk.jpeg"));
//            this.texture7 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/Tree.jpeg"));
//            this.texture8 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/wooden.jpeg"));
//            this.texture9 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/Ship.jpeg"));
//            this.texture10 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/load_dog.jpg"));
//            this.texture11 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/texture_wooden.jpeg"));
//            this.texture12 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/feather.jpeg"));
//            this.texture13 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/feather2.jpeg"));
//            this.texture14 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/lemon.jpeg"));
//
//            for(int i = 0; i < 11; ++i) {
//                this.gifTexture[i] = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/Dog" + (i + 1) + ".jpg"));
//            }
//
//            System.out.println("Texture loaded okay ");
//        }
//    }
//}
