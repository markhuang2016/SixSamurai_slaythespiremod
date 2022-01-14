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

    public SixSamuraiExtraBlockPower(AbstractCreature owner, int block) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 2;
        this.block = block;
        this.description = DESCRIPTIONS[0];
        // TODO 图标
        this.loadRegion("accuracy");
        this.type = PowerType.BUFF;
    }


    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        // fixme 时机错误
        if (card.hasTag(CardTag.SixSamurai)) {
            log.info("额外格挡能力：从六武众卡获取格挡");
            this.amount--;
            flash();
            if (this.amount <= 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            }
            return blockAmount + block;
        }
        return blockAmount;
    }

//    @Override
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.hasTag(CardTag.SixSamurai) && card.type == AbstractCard.CardType.ATTACK) {
//            log.info("额外伤害能力：六武众攻击卡已使用");
//            flash();
//            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        }
//    }

}
