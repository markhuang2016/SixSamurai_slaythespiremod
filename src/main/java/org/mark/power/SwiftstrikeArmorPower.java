package org.mark.power;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @description: 神速之具足能力
 * @author: huangzhiqiang
 * @create: 2022/01/12 16:23
 */
public class SwiftstrikeArmorPower extends AbstractPower {

    public static final String POWER_ID = SwiftstrikeArmorPower.class.getSimpleName();
    private static final Logger log = LogManager.getLogger(POWER_ID);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean open = false;

    public SwiftstrikeArmorPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("artifact");
        this.type = PowerType.BUFF;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        // fixme 第一张0非
        this.addToTop(new DrawCardAction(this.owner, this.amount));
        this.open = true;
        log.info("神速之具足：能力开启，即将抽牌");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        log.info("神速之具足：抽卡，能力开启:{}", open);
        if (open) {
            card.freeToPlayOnce = true;
            log.info("神速之具足：能力即将关闭");
            open = false;
        }
    }
}
