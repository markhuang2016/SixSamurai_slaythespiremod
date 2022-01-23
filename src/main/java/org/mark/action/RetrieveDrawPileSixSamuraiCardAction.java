package org.mark.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
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
 * @description: 从抽牌堆检索六武众卡牌到手牌
 * @author: huangzhiqiang
 * @create: 2022/01/05 16:28
 */
public class RetrieveDrawPileSixSamuraiCardAction extends AbstractGameAction {
    private AbstractPlayer p;

    public RetrieveDrawPileSixSamuraiCardAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(p, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        this.addToBot(new MoveCardsAction(p.hand, p.drawPile, x -> x.hasTag(CardTag.SixSamurai), this.amount));
        this.isDone = true;
    }
}
