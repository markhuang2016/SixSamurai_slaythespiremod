package org.mark.card.monster;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.ExhaustDiscardPileSixSamuraiCardAction;
import org.mark.enums.CardTag;

/**
 * @description: 真六武众-缘
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:05
 */
public class LegendarySixSamuraiEnishi extends LegendarySixSamuraiCard {

    public static final String ID = LegendarySixSamuraiEnishi.class.getSimpleName();

    public LegendarySixSamuraiEnishi() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 消耗弃牌堆 !M! 张卡，目标获得 眩晕
        // TODO 选卡不能终止
        this.addToBot(new ExhaustDiscardPileSixSamuraiCardAction(p, this.magicNumber));
        this.addToBot(new StunMonsterAction(m, p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LegendarySixSamuraiEnishi();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) &&
            p.discardPile.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).count() >= this.magicNumber;
    }
}
