package org.mark.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.mark.enums.CardTag;

/**
 * @description: 消耗弃牌堆 六武众 的卡牌
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:18
 */
public class ExhaustDiscardPileSixSamuraiCardAction extends AbstractGameAction {

    private AbstractPlayer p;

    public ExhaustDiscardPileSixSamuraiCardAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        this.addToBot(new MoveCardsAction(p.exhaustPile, p.discardPile, x -> x.hasTag(CardTag.SixSamurai), this.amount, null));
        this.isDone = true;
    }
}
