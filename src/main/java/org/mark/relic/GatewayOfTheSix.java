package org.mark.relic;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.SixSamuraiMod;
import org.mark.action.RetrieveDrawPileSixSamuraiCardAction;
import org.mark.enums.CardTag;

/**
 * @description: 六武之门
 * 效果：每次「六武众」怪兽召唤·特殊召唤给这张卡放置2个武士道指示物。
 * ②：可以把自己场上的武士道指示物的以下数量取除，那个效果发动。
 * ●2个：以场上1只「六武众」效果怪兽或者「紫炎」效果怪兽为对象才能发动。那只怪兽的攻击力直到回合结束时上升500。
 * ●4个：从自己的卡组·墓地选1只「六武众」怪兽加入手卡。
 * ●6个：以自己墓地1只「紫炎」效果怪兽为对象才能发动。那只怪兽特殊召唤。
 * @author: huangzhiqiang
 * @create: 2021/12/27 17:59
 */
public class GatewayOfTheSix extends AbstractRightClickRelic {

    public static final String ID = GatewayOfTheSix.class.getSimpleName();
    // TODO 图片
    public static final String IMG_PATH = SixSamuraiMod.ModId + "/img/relics/resize/GateWayofSix.png";
    private static final String OUTLINE_PATH = SixSamuraiMod.ModId + "/img/relics/outline/GateWayofSix.png";

    public GatewayOfTheSix() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RelicTier.STARTER,
            AbstractRelic.LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        // 使用六武众卡时获得2个指示物
        if (card.hasTag(CardTag.SixSamurai)) {
            this.counter += 2;
        }
    }

    @Override
    public void onRightClick() {
        if (this.counter >= 4) {
            this.counter -= 4;
            this.addToBot(new RetrieveDrawPileSixSamuraiCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public void onVictory() {
        //在胜利时触发
        this.counter = 0;
    }

}
