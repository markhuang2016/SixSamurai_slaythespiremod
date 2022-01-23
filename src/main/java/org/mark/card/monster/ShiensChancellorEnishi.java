package org.mark.card.monster;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.ExhaustDiscardPileSixSamuraiCardAction;
import org.mark.action.KillMinionAction;
import org.mark.card.ShiensCard;
import org.mark.enums.CardTag;

/**
 * @description: 紫炎之老中 缘
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:27
 */
public class ShiensChancellorEnishi extends ShiensCard {

    public static final String ID = ShiensChancellorEnishi.class.getSimpleName();

    public ShiensChancellorEnishi() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 22;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.initMonster(6, 22, 12);
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
        // 消耗 !M! 张弃牌堆中的 六武众 卡牌，造成 !D!点伤害，若目标为 爪牙 ，击杀
        this.addToBot(new ExhaustDiscardPileSixSamuraiCardAction(p, this.magicNumber));
        this.addToBot(new KillMinionAction(p, m, this.damage));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShiensChancellorEnishi();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        long count = p.discardPile.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).count();
        boolean b = count >= this.magicNumber;
        log.info("弃牌堆六武众卡数：{},超雷是否能使用：{},是否能使用：{},结果：{}", count, canUse, b, canUse && b);
        return canUse && b;
    }

}
