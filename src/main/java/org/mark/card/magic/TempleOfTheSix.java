package org.mark.card.magic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.power.TempleOfTheSixPower;

/**
 * @description: 六武院
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:33
 */
public class TempleOfTheSix extends AbstractSixSamuraiCard {

    public static final String ID = TempleOfTheSix.class.getSimpleName();

    public TempleOfTheSix() {
        super(ID, 1, AbstractCard.CardType.POWER, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        initMagicNumber(1);
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
        // 每使用一张 六武众 卡，敌方降低 !M! 点力量
        this.addToBot(new ApplyPowerAction(p, p, new TempleOfTheSixPower(p, this.magicNumber)));
    }

}
