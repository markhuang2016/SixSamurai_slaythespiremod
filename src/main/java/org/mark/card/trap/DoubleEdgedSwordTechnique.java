package org.mark.card.trap;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.RetrieveDiscardPileSixSamuraiCardAction;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 诸刃之活人剑术
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:44
 */
public class DoubleEdgedSwordTechnique extends AbstractSixSamuraiCard {

    public static final String ID = DoubleEdgedSwordTechnique.class.getSimpleName();

    public DoubleEdgedSwordTechnique() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 从弃牌堆中选择 !M! 张卡牌加入手牌
        this.addToBot(new RetrieveDiscardPileSixSamuraiCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleEdgedSwordTechnique();
    }
}
