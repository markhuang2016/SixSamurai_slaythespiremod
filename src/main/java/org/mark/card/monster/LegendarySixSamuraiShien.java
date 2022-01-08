package org.mark.card.monster;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.DiscardSixSamuraiCardAction;

/**
 * @description: 真六武众-紫炎
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:37
 */
public class LegendarySixSamuraiShien extends LegendarySixSamuraiCard {

    public static final String ID = LegendarySixSamuraiShien.class.getSimpleName();

    public LegendarySixSamuraiShien() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }


    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardSixSamuraiCardAction(p, 2));
        // TODO 没回和获取 免疫Debuff 一次
    }

    @Override
    public AbstractCard makeCopy() {
        return new LegendarySixSamuraiShien();
    }
}
