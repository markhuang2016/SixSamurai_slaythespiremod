package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.DiscardSixSamuraiCardAction;
import org.mark.enums.CardTag;
import org.mark.power.ArtifactEveryTurnPower;

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
        // TODO 每回合获取 免疫Debuff 一次
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactEveryTurnPower(p)));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) &&
            p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai) && !x.uuid.equals(this.uuid)).count() >= 2;
    }
}
