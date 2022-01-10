package org.mark.card.magic;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.DiscardSixSamuraiCardAction;
import org.mark.action.RetrieveExhaustPileSixSamuraiCardAction;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.enums.CardTag;

/**
 * @description: 六武众的影忍术
 * 丢弃一张 六武众 卡牌，从消耗牌堆选一张 六武众 卡拿到手牌
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:41
 */
public class SecretSkillsOfTheSixSamurai extends AbstractSixSamuraiCard {

    public static final String ID = SecretSkillsOfTheSixSamurai.class.getSimpleName();

    public SecretSkillsOfTheSixSamurai() {
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
        this.addToBot(new DiscardSixSamuraiCardAction(p, 1));
        this.addToBot(new RetrieveExhaustPileSixSamuraiCardAction(p, 1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        // TODO 效果：这个卡名的卡在1回合只能发动1张。
        return super.canUse(p, m) && p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).count() >= 1;
    }
}
