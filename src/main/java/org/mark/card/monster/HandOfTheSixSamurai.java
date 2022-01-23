package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.SixSamuraiCard;

/**
 * @description: 六武众的先导
 * 丢弃一张卡，造成 !D! 点伤害
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:18
 */
public class HandOfTheSixSamurai extends SixSamuraiCard {

    public static final String ID = HandOfTheSixSamurai.class.getSimpleName();

    public HandOfTheSixSamurai() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 16;
        this.initMonster(3, 16, 10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p, p, 1, false));
        addToBot(new DamageAction(m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HandOfTheSixSamurai();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }
}
