package org.mark.relic;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.SixSamuraiMod;
import org.mark.card.optional.GatewayOfTheSixOne;
import org.mark.card.optional.GatewayOfTheSixThree;
import org.mark.card.optional.GatewayOfTheSixTwo;
import org.mark.enums.CardTag;

import java.util.ArrayList;

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
public class GatewayOfTheSix extends CustomRelic implements ClickableRelic {

    public static final String ID = GatewayOfTheSix.class.getSimpleName();
    // TODO 图片
    public static final String IMG_PATH = SixSamuraiMod.ModId + "/img/relics/dest/GatewayOfTheSix.png";
    private static final String OUTLINE_PATH = SixSamuraiMod.ModId + "/img/relics/outline/背景.png";

    public GatewayOfTheSix() {
        super(ID, ImageMaster.loadImage(IMG_PATH),
            ImageMaster.loadImage(OUTLINE_PATH),
            RelicTier.STARTER,
            AbstractRelic.LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        // 使用 六武众 卡时获得2个武士道指示物，右键触发移除 2、4、6个指示物时，可以选择使用 六武之门-壹式、六武之门-贰式、六武之门-叁式
        if (card.hasTag(CardTag.SixSamurai)) {
            this.counter += 2;
        }
    }

    @Override
    public void onRightClick() {
        if (this.counter < 2) {
            return;
        }
        InputHelper.moveCursorToNeutralPosition();
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new GatewayOfTheSixOne());
        if (this.counter >= 4) {
            choices.add(new GatewayOfTheSixTwo());
        }
        if (this.counter >= 6) {
            choices.add(new GatewayOfTheSixThree());
        }
        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void onVictory() {
        //在胜利时触发
        this.counter = 0;
    }

}
