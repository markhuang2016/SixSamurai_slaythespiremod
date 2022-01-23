package org.mark.relic;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.mark.SixSamuraiMod;
import org.mark.action.RemoveTemporaryRelicAction;
import org.mark.power.RemoveTemporaryRelicPower;

import java.util.UUID;

/**
 * @description: 临时遗物，战斗中获取，战斗后移除
 * @author: huangzhiqiang
 * @create: 2022/01/18 10:31
 */
public abstract class TemporaryRelic extends CustomRelic implements ClickableRelic {

    public AbstractCard card;

    public TemporaryRelic(String id, Texture texture, Texture outlineTexture, RelicTier tier, LandingSound sfx) {
        super(id, texture, outlineTexture, tier, sfx);
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
            new RemoveTemporaryRelicPower(this),
            1));
    }

    public TemporaryRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
            new RemoveTemporaryRelicPower(this),
            1));
    }

    public void remove() {
        this.addToTop(new RemoveTemporaryRelicAction(this));
        AbstractPower power = AbstractDungeon.player.getPower(RemoveTemporaryRelicPower.POWER_ID);
        if (power != null) {
            ((RemoveTemporaryRelicPower) power).remove(this);
        }
    }

    @Override
    public void onRightClick() {
        this.remove();
    }
}
