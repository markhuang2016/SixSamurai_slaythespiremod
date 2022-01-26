package org.mark.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.mark.enums.CardTag;

/**
 * @description: 从已 消耗牌 中选择 六武众 卡加入手牌
 * @author: huangzhiqiang
 * @create: 2022/01/06 15:18
 */
public class RetrieveExhaustPileSixSamuraiCardAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RetrieveDrawPileSixSamuraiCardAction.class.getSimpleName());
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;

    public RetrieveExhaustPileSixSamuraiCardAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues((AbstractCreature) null, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        // TODO 参考ExhumeAction 优化
        this.addToBot(new MoveCardsAction(p.hand, p.exhaustPile, x -> x.hasTag(CardTag.SixSamurai), this.amount,
            cards -> cards.forEach(x -> x.unfadeOut())));
        this.isDone = true;
    }
}
