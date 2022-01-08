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
 * @description: 从抽牌堆检索六武众卡牌到手牌
 * @author: huangzhiqiang
 * @create: 2022/01/05 16:28
 */
public class RetrieveDrawPileSixSamuraiCardAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    private static Logger log = LogManager.getLogger(RetrieveDrawPileSixSamuraiCardAction.class.getSimpleName());

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RetrieveDrawPileSixSamuraiCardAction.class.getSimpleName());
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;

    public RetrieveDrawPileSixSamuraiCardAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues((AbstractCreature) null, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        log.info("开始选择六武众卡牌到手牌，amount:{},duration:{}", this.amount, this.duration);
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            this.p.drawPile.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).forEach(cardGroup::addToBottom);
            log.info("可选卡牌数:{}", cardGroup.size());
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                log.info("进入初始化:{}", duration);
                if (cardGroup.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (cardGroup.size() <= this.amount) {
                    log.info("自动移卡");
                    cardGroup.group.forEach(x -> this.p.drawPile.moveToHand(x));
                    this.isDone = true;
                    return;
                }

                if (cardGroup.size() > this.amount) {
                    log.info("即将打开选卡面板");
                    AbstractDungeon.gridSelectScreen.open(cardGroup, this.amount, TEXT[0], false, false, true, false);
                    log.info("已打开选卡面板");
                    this.tickDuration();
                    log.info("即将返回1:{}", duration);
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                log.info("已选卡数：{},duration:{}", AbstractDungeon.gridSelectScreen.selectedCards.size(), duration);
                Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var3.hasNext()) {
                    AbstractCard c = (AbstractCard)var3.next();
                    cardGroup.removeCard(c);
                    this.p.drawPile.moveToHand(c);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            log.info("即将返回2:{}", duration);
            this.tickDuration();
        }
    }
}
