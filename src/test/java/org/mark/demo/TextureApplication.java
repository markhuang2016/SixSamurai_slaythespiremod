package org.mark.demo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.core.TestGame;
import com.megacrit.cardcrawl.desktop.DesktopLauncher;
import com.megacrit.cardcrawl.desktop.TestLauncher;

import java.io.IOException;

/**
 * @description:
 * @author: huangzhiqiang
 * @create: 2022/01/19 16:14
 */
public class TextureApplication {

    public static void main(String[] args) throws IOException {
//        TestLauncher.main(args);
//        DesktopLauncher.main(args);
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.resizable = false;
        config.title = "512*256 Fullscreen Test";
        config.width = 512;
        config.height = 256;
        config.fullscreen = false;
        config.foregroundFPS = 60;
        config.backgroundFPS = 24;
        new LwjglApplication(new TextureGameDemo(), config);
    }

}
