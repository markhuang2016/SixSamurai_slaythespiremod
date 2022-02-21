package org.mark.card.magic;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.RetrieveDrawPileSixSamuraiCardAction;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 紫炎的狼烟
 * 从抽牌堆里选择  张 六武众卡牌 加入手牌
 * @author: huangzhiqiang
 * @create: 2022/01/05 16:24
 */
public class ShiensSmokeSignal extends AbstractSixSamuraiCard {

    public static final String ID = ShiensSmokeSignal.class.getSimpleName();

    public ShiensSmokeSignal(){
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new RetrieveDrawPileSixSamuraiCardAction(abstractPlayer, this.magicNumber));
    }
}
