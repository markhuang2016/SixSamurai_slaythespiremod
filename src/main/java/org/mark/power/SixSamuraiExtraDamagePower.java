package org.mark.power;

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
 * @description: 下次六武众卡牌造成伤害时额外造成 !D! 点伤害
 * @author: huangzhiqiang
 * @create: 2022/01/11 16:59
 */
public class SixSamuraiExtraDamagePower extends AbstractPower {

    public static final String POWER_ID = SixSamuraiExtraDamagePower.class.getSimpleName();

    private static final Logger log = LogManager.getLogger(POWER_ID);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;

    public SixSamuraiExtraDamagePower(AbstractCreature owner, int damage) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 1;
        this.damage = damage;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("vigor");
        this.type = PowerType.BUFF;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (Objects.equals(info.type, DamageTypeEnum.SixSamurai)) {
            this.amount--;
            flash();
            if (this.amount <= 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            }
            return damageAmount + this.damage;
        }
        return damageAmount;
    }
}
