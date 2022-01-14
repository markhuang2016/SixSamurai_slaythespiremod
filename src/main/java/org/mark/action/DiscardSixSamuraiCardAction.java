package org.mark.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.mark.enums.CardTag;

import java.util.List;

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
        this.setValues((AbstractCreature) null, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        this.addToBot(new SelectCardsInHandAction(this.amount, TEXT[0], x -> x.hasTag(CardTag.SixSamurai),
            x -> this.discardCards(this.p.hand, x)));
        this.isDone = true;
//        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
//            this.isDone = true;
//        } else {
//            CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//            this.p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).forEach(cardGroup::addToBottom);
//
//            if (this.duration == Settings.ACTION_DUR_FASTER) {
//                if (cardGroup.isEmpty()) {
//                    this.isDone = true;
//                    return;
//                }
//
//                if (cardGroup.size() <= this.amount) {
//                    cardGroup.group.forEach(x -> this.discardCard(p.hand, x));
//                    this.isDone = true;
//                    return;
//                }
//
//                if (cardGroup.size() > this.amount) {
//                    AbstractDungeon.gridSelectScreen.open(cardGroup, this.amount, TEXT[0], false, false, false, false);
//                    this.tickDuration();
//                    return;
//                }
//            }
//
//            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
//                Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
//
//                while (var3.hasNext()) {
//                    AbstractCard c = (AbstractCard) var3.next();
//                    cardGroup.removeCard(c);
//                    discardCard(p.hand, c);
//                }
//
//                AbstractDungeon.gridSelectScreen.selectedCards.clear();
//                AbstractDungeon.player.hand.refreshHandLayout();
//            }
//
//            this.tickDuration();
//        }
    }

    private void discardCards(CardGroup cardGroup, List<AbstractCard> cards) {
        cards.forEach(card->{
            cardGroup.moveToDiscardPile(card);
            card.triggerOnManualDiscard();
            GameActionManager.incrementDiscard(false);
        });
        cards.clear();
    }

    public void discardCard(CardGroup cardGroup, AbstractCard card) {
        cardGroup.moveToDiscardPile(card);
        card.triggerOnManualDiscard();
        GameActionManager.incrementDiscard(false);
    }
}
