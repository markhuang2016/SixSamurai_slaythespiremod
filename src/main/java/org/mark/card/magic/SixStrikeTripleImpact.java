package org.mark.card.magic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.KillAllMinionAction;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 六武式三段冲
 * 对全部敌人造成 !D! 点伤害，若敌人为 爪牙 ，击杀
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:49
 */
public class SixStrikeTripleImpact extends AbstractSixSamuraiCard {


    public static final String ID = SixStrikeTripleImpact.class.getSimpleName();

    public SixStrikeTripleImpact() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new KillAllMinionAction(p, this.damage));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SixStrikeTripleImpact();
    }
}
