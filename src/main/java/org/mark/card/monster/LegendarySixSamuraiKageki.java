package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.SixSamuraiCardInHandFreeUseOnceAction;
import org.mark.card.LegendarySixSamuraiCard;
import org.mark.enums.CardTag;
import org.mark.enums.DamageTypeEnum;

/**
 * @description: 真六武众-阴鬼
 * 造成 !D! 点伤害，若手牌中除 真六武众-阴鬼 外的 六武众 卡牌，伤害增加15点。NL 打出后可选择手牌中的一张 六武众 卡牌，此卡下一次打出时不消耗 [G]
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:01
 */
public class LegendarySixSamuraiKageki extends LegendarySixSamuraiCard {

    public static final String ID = LegendarySixSamuraiKageki.class.getSimpleName();

    public LegendarySixSamuraiKageki() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.initMonster(3,2,20);
        this.baseDamage = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int damage = baseDamage;
        if (p.hand.group.stream().anyMatch(x -> x.hasTag(CardTag.SixSamurai) && !x.cardID.contains(ID))) {
            damage += 15;
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageTypeEnum.SixSamurai)));
        this.addToBot(new SixSamuraiCardInHandFreeUseOnceAction(p, 1));

    }

    @Override
    public AbstractCard makeCopy() {
        return new LegendarySixSamuraiKageki();
    }
}
