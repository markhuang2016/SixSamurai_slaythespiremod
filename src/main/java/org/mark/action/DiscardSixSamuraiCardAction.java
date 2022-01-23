package org.mark.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.CardTag;

/**
 * @description: 从手牌丢弃 六武众 卡牌到弃牌堆
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:41
 */
public class DiscardSixSamuraiCardAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(DiscardSixSamuraiCardAction.class.getSimpleName());
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;

    public DiscardSixSamuraiCardAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(p, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        this.addToBot(new SelectCardsInHandAction(this.amount, TEXT[0], x -> x.hasTag(CardTag.SixSamurai),
            cards -> {
                cards.forEach(card -> {
                    this.addToBot(new DiscardSpecificCardAction(card));
                });
            }));
        this.isDone = true;
    }
}
