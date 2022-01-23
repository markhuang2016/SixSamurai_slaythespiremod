package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.SixSamuraiCard;

/**
 * @description: 六武众的影武者
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:47
 */
public class KagemushaOfTheSixSamurai extends SixSamuraiCard {

    public static final String ID = KagemushaOfTheSixSamurai.class.getSimpleName();

    public KagemushaOfTheSixSamurai() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseBlock = 18;
        this.initMonster(2,4,18);
        this.tuner = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得 !B! 点格挡
        this.addToBot(new GainBlockAction(p, this.block));
    }
}
