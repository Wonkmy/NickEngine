package com.wonkmy.coreLogic;

import com.wonkmy.core.Launcher;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class EngineManager {
    public static  final long NANOSECOND=1000000000L;
    public static final float FRAMERATE=1000;

    private static int fps;
    private static float frametime=1.0f/FRAMERATE;

    private boolean isRunning;

    private WindowManager window;
    private GLFWErrorCallback errorCallback;
    private ILogic gameIlogic;

    private void init() throws Exception{
        GLFW.glfwSetErrorCallback(errorCallback=GLFWErrorCallback.createPrint(System.err));
        window= Launcher.getWindow();
        gameIlogic=Launcher.getMyFirstGame();
        window.init();
        gameIlogic.init();
    }
    
    public void start() throws Exception{
        init();
        if(isRunning)return;
        run();
    }

    private void run() {
        this.isRunning=true;
        int frames=0;
        long frameCounter=0;
        long lastTime=System.nanoTime();
        double unprocessedTime=0;

        while (isRunning){
            boolean render=false;
            long startTime=System.nanoTime();
            long passedTime=startTime-lastTime;
            lastTime=startTime;

            unprocessedTime+=passedTime/(double)NANOSECOND;
            frameCounter+=passedTime;

            input();

            while (unprocessedTime>frametime){
                render=true;
                unprocessedTime-=frametime;

                if(window.windowShouldClose())
                    stop();

                if(frameCounter>=NANOSECOND){
                    setFps(frames);
                    window.setTitle("NickGameEngine FPS: "+getFps());
                    frames=0;
                    frameCounter=0;
                }
            }

            if(render){
                update();
                render();
                frames++;
            }
        }
        cleanup();
    }

    private void stop() {
        if(!isRunning)
            return;
        isRunning=false;
    }

    private void input() {
        gameIlogic.input();
    }

    private void render() {
        gameIlogic.render();
        window.update();
    }

    private void update() {
        gameIlogic.update();
    }

    private void cleanup() {
        window.cleanup();
        gameIlogic.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }
}
