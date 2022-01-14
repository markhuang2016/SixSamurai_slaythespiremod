package org.mark.relic;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

/**
 * @description: 支持右键触发遗物
 * @author: huangzhiqiang
 * @create: 2022/01/10 15:05
 */
public abstract class AbstractRightClickRelic extends CustomRelic {
    private boolean RclickStart = false;
    private boolean Rclick = false;

    public AbstractRightClickRelic(String setId, Texture img1, RelicTier tier, LandingSound sfx) {
        super(setId, img1, tier, sfx);
    }

    public void onRightClick() {

    };

    public void update() {
        super.update();
        if (this.RclickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Rclick = true;
            }

            this.RclickStart = false;
        }

        if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
            this.RclickStart = true;
        }

        if (this.Rclick) {
            this.Rclick = false;
            this.onRightClick();
        }

    }
}
