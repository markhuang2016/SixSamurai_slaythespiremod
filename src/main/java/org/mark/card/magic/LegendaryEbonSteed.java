package org.mark.card.magic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 漆黑的名马
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:36
 */
public class LegendaryEbonSteed extends AbstractSixSamuraiCard {

    public static final String ID = LegendaryEbonSteed.class.getSimpleName();

    public LegendaryEbonSteed() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        initMagicNumber(1);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获取 !M! 点 力量 和 敏捷 。
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber)));

    }

}
