package org.mark.card.trap;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.KillMinionAction;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 六武式风雷斩
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:44
 */
public class SixStrikeThunderBlast extends AbstractSixSamuraiCard {

    public static final String ID = SixStrikeThunderBlast.class.getSimpleName();

    public SixStrikeThunderBlast() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
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
        // 移除 !M! 个 六武之门 的 武士道指示物，若目标为爪牙，击杀; NL 若目标非爪牙， 击晕
        // TODO 移除 !M! 个 六武之门 的 武士道指示物
        this.addToBot(new KillMinionAction(p, m));
        this.addToBot(new StunMonsterAction(m, p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SixStrikeThunderBlast();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        // TODO 校验指数物数量
        return super.canUse(p, m);
    }
}
