package org.mark.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.mark.enums.CardTag;

import java.util.Iterator;

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
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            this.p.exhaustPile.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai))
                .forEach(cardGroup::addToBottom);

            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (cardGroup.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (cardGroup.size() <= this.amount) {
                    cardGroup.group.forEach(x -> this.p.exhaustPile.moveToHand(x));
                }

                if (cardGroup.size() > this.amount) {
                    AbstractDungeon.gridSelectScreen.open(cardGroup, this.amount, TEXT[0], false, false, true, false);
                    this.tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (var3.hasNext()) {
                    AbstractCard c = (AbstractCard) var3.next();
                    this.p.exhaustPile.moveToHand(c);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
