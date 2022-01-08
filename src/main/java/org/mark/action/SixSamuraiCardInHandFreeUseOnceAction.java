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

    private static final Logger log =
        LogManager.getLogger(SixSamuraiCardInHandFreeUseOnceAction.class.getSimpleName());

    private AbstractPlayer p;

    public SixSamuraiCardInHandFreeUseOnceAction(AbstractCreature source, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        log.debug("开始从手牌选择牌进行临时降费");

        CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai))
            .forEach(cardGroup::addToBottom);

        if (this.duration == Settings.ACTION_DUR_MED) {
            log.debug("持续时间为满");
            if (cardGroup.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (cardGroup.size() <= this.amount) {
                cardGroup.group.forEach(x -> x.freeToPlayOnce = true);
            }

            if (cardGroup.size() > this.amount) {
                log.debug("即将打开选择面板");
                AbstractDungeon.gridSelectScreen.open(cardGroup, this.amount, TEXT[0], false, false, true, false);
                this.tickDuration();
                log.debug("返回，duration:{}", this.duration);
                return;
            }
        }

        log.debug("持续时间不满，duration:{}", this.duration);
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            log.debug("有卡被选中，数量:{}", AbstractDungeon.gridSelectScreen.selectedCards.size());
            Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

            while (var3.hasNext()) {
                AbstractCard c = (AbstractCard) var3.next();
                cardGroup.removeCard(c);
                c.freeToPlayOnce = true;
                this.p.hand.applyPowers();
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        this.tickDuration();
        log.debug("结束从弃牌堆选择牌进行消耗");
    }
}
