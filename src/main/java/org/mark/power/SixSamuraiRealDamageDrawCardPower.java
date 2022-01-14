package org.mark.power;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.DamageTypeEnum;

/**
 * @description: 六武众卡牌对怪物造成生命值伤害时，抽卡
 * @author: huangzhiqiang
 * @create: 2022/01/11 17:26
 */
public class SixSamuraiRealDamageDrawCardPower extends AbstractPower {

    public static final String POWER_ID = SixSamuraiRealDamageDrawCardPower.class.getSimpleName();

    private static final Logger log = LogManager.getLogger(POWER_ID);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int drawNumber;

    public SixSamuraiRealDamageDrawCardPower(AbstractCreature owner, int drawNumber) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = drawNumber;
        this.drawNumber = drawNumber;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("carddraw");
        this.type = PowerType.BUFF;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageTypeEnum.SixSamurai) {
            this.flash();
            this.addToBot(new DrawCardAction(owner, this.drawNumber));
            this.amount--;
            if (this.amount <= 0) {
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            }
        }
    }

}
