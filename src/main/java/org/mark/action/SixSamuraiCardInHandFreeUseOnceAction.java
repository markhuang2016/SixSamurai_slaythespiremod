package org.mark.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.CardTag;

import java.util.Iterator;

/**
 * @description: 手牌中的 六武众 卡牌下次使用不消耗能量
 * @author: huangzhiqiang
 * @create: 2022/01/07 16:13
 */
public class SixSamuraiCardInHandFreeUseOnceAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    static {
        uiStrings =
            CardCrawlGame.languagePack.getUIString(SixSamuraiCardInHandFreeUseOnceAction.class.getSimpleName());
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;

    public SixSamuraiCardInHandFreeUseOnceAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        this.addToBot(new SelectCardsInHandAction(this.amount, TEXT[0], x -> x.hasTag(CardTag.SixSamurai),
            cards -> {
                cards.forEach(card -> {
                        card.freeToPlayOnce = true;
                });
            }));
        this.isDone = true;
    }
}
