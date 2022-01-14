package org.mark.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.CardTag;

/**
 * @description: 六武院能力
 * 每使用一张 六武众 卡，敌方降低 !M! 点力量
 * @author: huangzhiqiang
 * @create: 2022/01/12 17:05
 */
public class TempleOfTheSixPower extends AbstractPower {

    public static final String POWER_ID = TempleOfTheSixPower.class.getSimpleName();
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Logger log = LogManager.getLogger(POWER_ID);

    public TempleOfTheSixPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("burst");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(CardTag.SixSamurai)) {
            AbstractDungeon.getMonsters().monsters.forEach(x->{
                this.addToBot(new ApplyPowerAction(x, this.owner, new StrengthPower(this.owner, -this.amount)));
            });
        }
    }
}
