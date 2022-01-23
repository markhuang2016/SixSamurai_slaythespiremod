package org.mark.card.magic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.DiscardSixSamuraiCardAction;
import org.mark.action.RetrieveDrawPileCardAction;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.card.monster.GreatShogunShien;
import org.mark.enums.CardTag;

/**
 * @description: 六武之书
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:59
 */
public class SixScrollsOfTheSamurai extends AbstractSixSamuraiCard {

    public static final String ID = SixScrollsOfTheSamurai.class.getSimpleName();

    public SixScrollsOfTheSamurai() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new GreatShogunShien();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 丢弃 !M! 张 六武众 卡，把抽牌堆的一张 大将军紫炎 加入手牌
        this.addToBot(new DiscardSixSamuraiCardAction(p, this.magicNumber));
        this.addToBot(new RetrieveDrawPileCardAction(p, p, x -> x.cardID.contains(GreatShogunShien.ID), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SixScrollsOfTheSamurai();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) &&
            p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).count() >= this.magicNumber;
    }
}
