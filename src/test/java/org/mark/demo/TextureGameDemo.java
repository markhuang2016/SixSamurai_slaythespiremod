package org.mark.demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * @description:
 * @author: huangzhiqiang
 * @create: 2022/01/19 17:46
 */
public class TextureGameDemo implements ApplicationListener {
    private OrthographicCamera camera = new OrthographicCamera();
    public static FitViewport viewport;
    private SpriteBatch sb;
    private Texture background;
    private Texture texture;
    private Texture textureOutline;
    private Texture slay;
    private Texture slayOutline;

    @Override
    public void create() {
        viewport = new FitViewport(512.0F, 256.0F, this.camera);
        viewport.apply();
        this.sb = new SpriteBatch();
        this.background = new Texture("slay/whiteScreen.png");
        this.texture = new Texture("SixSamurai/img/relics/dest/GatewayOfTheSix.png");
        this.textureOutline = new Texture("SixSamurai/img/relics/outline/背景.png");
        this.slay = new Texture("slay/abacus.png");
        this.slayOutline = new Texture("slay/abacus 2.png");
    }

    public void render() {
        this.sb.begin();
//        this.sb.setColor(Color.RED);
        this.sb.draw(this.background, 0.0F, 0.0F, 256+128.0F, 256.0F);
        this.sb.draw(this.texture, 0.0F, 0.0F, 128.0F, 128.0F);
        this.sb.draw(this.textureOutline, 128.0F, 0.0F, 128.0F, 128.0F);
        this.sb.draw(this.slay, 0.0F, 128.0F, 128.0F, 128.0F);
        this.sb.draw(this.slayOutline, 128.0F, 128.0F, 128.0F, 128.0F);

        // 组合
        this.sb.draw(this.textureOutline, 256.0F, 0.0F);
        this.sb.draw(this.texture, 256.0F, 0.0F);

        this.sb.draw(this.slayOutline,256,128);
        this.sb.draw(this.slay,256,128);

        this.sb.draw(this.textureOutline, 256.0F+128.0F, 0.0F);
        this.sb.draw(this.texture, 256.0F+128.0F, 0.0F);

        this.sb.draw(this.slayOutline,256.0F+128.0F,128);
        this.sb.draw(this.slay,256.0F+128.0F,128);

        this.sb.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        this.camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
