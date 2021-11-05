package com.wonkmy.core;

import com.wonkmy.coreLogic.ILogic;
import com.wonkmy.coreLogic.RenderManager;
import com.wonkmy.coreLogic.WindowManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class NewGame implements ILogic {

    private int direction=0;
    private float colour=0.0f;

    private final RenderManager render;
    private final WindowManager window;

    public NewGame() {
        render = new RenderManager();
        window = Launcher.getWindow();
    }

    @Override
    public void init() throws Exception {
        render.init();
    }

    @Override
    public void input() {
        if(window.isKeyPressed(GLFW.GLFW_KEY_UP)){
            direction=1;
        }
        else if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN)){
            direction=-1;
        }
        else
            direction=0;
    }

    @Override
    public void update() {
        colour+=direction*0.01f;
        if(colour>1)
            colour=1.0f;
        else if(colour<=0)
            colour=0.0f;
    }

    @Override
    public void render() {
        if(window.isResize()){
            GL11.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setResize(true);
        }

        window.setClearColour(colour,colour,colour,0.0f);
        render.clear();
    }

    @Override
    public void cleanup() {
        window.cleanup();
    }
}
