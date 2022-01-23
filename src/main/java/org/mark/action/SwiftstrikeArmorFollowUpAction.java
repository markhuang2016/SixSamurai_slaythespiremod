package org.mark.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import org.mark.SixSamuraiMod;
import org.mark.enums.CardTag;

/**
 * @description: 神速之具足抽卡跟随, 若抽到的卡为 六武众 卡，降费一次
 * @author: huangzhiqiang
 * @create: 2022/01/19 11:14
 */
public class SwiftstrikeArmorFollowUpAction extends AbstractGameAction {

    @Override
    public void update() {
        for (AbstractCard card : DrawCardAction.drawnCards) {
            SixSamuraiMod.logger.info("神速之具足抽卡：{}", card.cardID);
            if (card.hasTag(CardTag.SixSamurai)) {
                SixSamuraiMod.logger.info("神速之具足抽卡降费");
                card.freeToPlayOnce = true;
            }
        }
        this.isDone = true;
    }
}
