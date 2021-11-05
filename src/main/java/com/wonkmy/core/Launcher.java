package com.wonkmy.core;

import com.wonkmy.coreLogic.WindowManager;
import org.lwjgl.Version;

public class Launcher {
    public static void main(String[] args) {
//        System.out.println(Version.getVersion());
        WindowManager window=new WindowManager("NickGameEngine",1280,720,false);
        window.init();

        while (!window.windowShouldClose()){
            window.update();
        }

        window.cleanup();
    }
}
