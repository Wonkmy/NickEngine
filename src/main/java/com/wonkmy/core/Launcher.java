package com.wonkmy.core;

import com.wonkmy.coreLogic.EngineManager;
import com.wonkmy.coreLogic.WindowManager;
public class Launcher {

    private static WindowManager window;
    private static NewGame myFirstGame;

    public static void main(String[] args) {
        window=new WindowManager("NickGameEngine",1280,720,false);
        myFirstGame=new NewGame();
        EngineManager engine=new EngineManager();
        try {
            engine.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static WindowManager getWindow() {
        return window;
    }

    public static NewGame getMyFirstGame() {
        return myFirstGame;
    }
}
