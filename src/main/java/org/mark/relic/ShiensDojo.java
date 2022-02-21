package org.mark.relic;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.SixSamuraiMod;
import org.mark.card.MonsterCard;
import org.mark.enums.CardTag;

/**
 * @description: 紫炎的道场
 * @author: huangzhiqiang
 * @create: 2022/01/17 18:03
 */
public class ShiensDojo extends TemporaryRelic {

    public static final String ID = ShiensDojo.class.getSimpleName();

    public static final String IMG_PATH = SixSamuraiMod.ModId + "/img/relics/dest/ShiensDojo.png";
    private static final String OUTLINE_PATH = SixSamuraiMod.ModId + "/img/relics/outline/背景.png";

    public ShiensDojo(AbstractCard card) {
        super(ID, ImageMaster.loadImage(IMG_PATH),
            ImageMaster.loadImage(OUTLINE_PATH),
            RelicTier.SPECIAL,
            AbstractRelic.LandingSound.CLINK);
        this.card = card;
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.hasTag(CardTag.SixSamurai)) {
            this.counter++;
        }
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        this.addToBot(new MoveCardsAction(AbstractDungeon.player.hand, AbstractDungeon.player.drawPile,
            x -> (x.hasTag(CardTag.SixSamurai) || x.hasTag(CardTag.Shien)) && ((MonsterCard) x).star <= this.counter,
            1,
            cards -> cards.forEach(x -> x.freeToPlayOnce = true)));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
