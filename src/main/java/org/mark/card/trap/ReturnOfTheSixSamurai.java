package org.mark.card.trap;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.RetrieveDiscardPileSixSamuraiCardAction;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 六武众推参！
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:48
 */
public class ReturnOfTheSixSamurai extends AbstractSixSamuraiCard {

    public static final String ID = ReturnOfTheSixSamurai.class.getSimpleName();

    public ReturnOfTheSixSamurai() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RetrieveDiscardPileSixSamuraiCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReturnOfTheSixSamurai();
    }
}
