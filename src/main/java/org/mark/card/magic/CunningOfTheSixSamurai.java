package org.mark.card.magic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.DiscardSixSamuraiCardAction;
import org.mark.action.RetrieveDiscardPileSixSamuraiCardAction;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.enums.CardTag;

/**
 * @description: 六武众之理
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:40
 */
public class CunningOfTheSixSamurai extends AbstractSixSamuraiCard {

    public static final String ID = CunningOfTheSixSamurai.class.getSimpleName();

    public CunningOfTheSixSamurai() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

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
        // 丢弃手牌里的一张 六武众 卡，选择弃牌堆里一张 六武众 卡回到手牌
        this.addToBot(new DiscardSixSamuraiCardAction(p, 1));
        this.addToBot(new RetrieveDiscardPileSixSamuraiCardAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CunningOfTheSixSamurai();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).count() >= 1;
    }
}
