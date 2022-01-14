package org.mark.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @description: 六武众的御灵代能力
 * @author: huangzhiqiang
 * @create: 2022/01/13 17:01
 */
public class SpiritOfTheSixSamuraiPower extends AbstractPower {
    public static final String POWER_ID = SpiritOfTheSixSamuraiPower.class.getSimpleName();
    private static final Logger log = LogManager.getLogger(POWER_ID);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;
    private int block;
    private int magicNumber;

    public SpiritOfTheSixSamuraiPower(AbstractCreature owner, int damage, int block, int magicNumber, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("artifact");
        this.type = PowerType.BUFF;
        this.damage = damage;
        this.block = block;
        this.magicNumber = magicNumber;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.addToBot(new ApplyPowerAction(owner, owner, new SixSamuraiExtraDamagePower(owner, this.damage),this.amount,true));
        this.addToBot(new ApplyPowerAction(owner, owner, new SixSamuraiExtraBlockPower(owner, this.block), this.amount, true));
        this.addToBot(new ApplyPowerAction(owner, owner, new SixSamuraiRealDamageDrawCardPower(owner, this.magicNumber), this.amount, true));
    }
}
