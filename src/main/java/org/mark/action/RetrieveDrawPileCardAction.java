package org.mark.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description: 从抽牌堆加入指定卡牌到手牌
 * @author: huangzhiqiang
 * @create: 2022/01/06 16:25
 */
public class RetrieveDrawPileCardAction extends AbstractGameAction {

    private AbstractPlayer p;
    private Predicate<AbstractCard> filter;

    public RetrieveDrawPileCardAction(AbstractPlayer player, AbstractCreature source, Predicate<AbstractCard> filter,
        int amount) {
        this.setValues(player, source, amount);
        this.p = player;
        this.filter = filter;
    }

    @Override
    public void update() {
        List<AbstractCard> targetCards = p.drawPile.group.stream().filter(filter).collect(Collectors.toList());
        if (targetCards.size() <= this.amount) {
            targetCards.forEach(x -> p.drawPile.moveToHand(x));
        } else {
            for (int i = 0; i < this.amount; i++) {
                p.drawPile.moveToHand(targetCards.get(i));
            }
        }
        this.isDone = true;
    }
}
