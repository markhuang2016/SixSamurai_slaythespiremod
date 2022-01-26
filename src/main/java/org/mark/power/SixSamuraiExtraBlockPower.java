package org.mark.power;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.CardTag;
import org.mark.enums.DamageTypeEnum;

import java.util.Objects;

/**
 * @description: 下次六武众卡牌获取格挡时额外获得 !B! 点
 * @author: huangzhiqiang
 * @create: 2022/01/11 17:14
 */
public class SixSamuraiExtraBlockPower extends AbstractPower {
    public static final String POWER_ID = SixSamuraiExtraBlockPower.class.getSimpleName();

    private static final Logger log = LogManager.getLogger(POWER_ID);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int block;
    private boolean cycle = false;

    public SixSamuraiExtraBlockPower(AbstractCreature owner, int block) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 1;
        this.block = block;
        this.description = DESCRIPTIONS[0];
        // TODO 图标
        this.loadRegion("accuracy");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onGainedBlock(float blockAmount) {
        log.info("额外格挡能力：获取格挡");
        if (cycle) {
            log.info("额外格挡能力：获取格挡 递归了，中断");
            return;
        }
        this.addToBot(new GainBlockAction(owner, this.block));
        this.amount--;
        flash();
        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        cycle = true;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.cycle = false;
    }

}
