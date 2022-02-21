package org.mark.card.magic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.power.TempleOfTheSixPower;

/**
 * @description: 紫炎的霞城
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:55
 */
public class ShiensCastleOfMist extends AbstractSixSamuraiCard {

    public static final String ID = ShiensCastleOfMist.class.getSimpleName();

    public ShiensCastleOfMist() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        initMagicNumber(5);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 所有敌方降低 !M! 点力量
        AbstractDungeon.getMonsters().monsters.forEach(x->{
            this.addToBot(new ApplyPowerAction(x, p, new StrengthPower(p, -this.magicNumber)));
        });
    }

}
