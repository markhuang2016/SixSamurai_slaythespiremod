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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.CardTag;

import java.util.Iterator;

/**
 * @description: 消耗弃牌堆 六武众 的卡牌
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:18
 */
public class ExhaustDiscardPileSixSamuraiCardAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    static {
        uiStrings =
            CardCrawlGame.languagePack.getUIString(ExhaustDiscardPileSixSamuraiCardAction.class.getSimpleName());
        TEXT = uiStrings.TEXT;
    }

    private static final Logger log =
        LogManager.getLogger(ExhaustDiscardPileSixSamuraiCardAction.class.getSimpleName());

    private AbstractPlayer p;

    public ExhaustDiscardPileSixSamuraiCardAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        log.info("开始从弃牌堆选择牌进行消耗");

        CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.p.discardPile.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai))
            .forEach(cardGroup::addToBottom);

        if (this.duration == Settings.ACTION_DUR_MED) {
            log.info("持续时间为满");
            if (cardGroup.isEmpty()) {
                this.isDone = false;
                return;
            }

            if (cardGroup.size() <= this.amount) {
                cardGroup.group.forEach(x -> this.p.discardPile.moveToExhaustPile(x));
            }

            if (cardGroup.size() > this.amount) {
                log.info("即将打开选择面板");
                AbstractDungeon.gridSelectScreen.open(cardGroup, this.amount, TEXT[0], false, false, false, false);
                this.tickDuration();
                log.info("返回，duration:{}", this.duration);
                return;
            }
        }

        log.info("持续时间不满，duration:{}", this.duration);
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            log.info("有卡被选中，数量:{}", AbstractDungeon.gridSelectScreen.selectedCards.size());
            Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

            while (var3.hasNext()) {
                AbstractCard c = (AbstractCard) var3.next();
                cardGroup.removeCard(c);
                this.p.discardPile.moveToExhaustPile(c);
                this.p.discardPile.refreshHandLayout();
                this.p.hand.applyPowers();

            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.discardPile.refreshHandLayout();
        }

        this.tickDuration();
        log.info("结束从弃牌堆选择牌进行消耗");

    }
}
