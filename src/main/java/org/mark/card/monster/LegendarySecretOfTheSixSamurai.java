package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.SixSamuraiCard;
import org.mark.enums.CardTag;

/**
 * @description: 六武众的真影
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:51
 */
public class LegendarySecretOfTheSixSamurai extends SixSamuraiCard {

    public static final String ID = LegendarySecretOfTheSixSamurai.class.getSimpleName();

    public LegendarySecretOfTheSixSamurai() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 20;
        this.initMonster(4, 5, 20);
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
        // 获得 !B! 点格挡。 NL 若有 影六武众 卡牌使用后，此卡使用不消耗 [G] 。
        this.addToBot(new GainBlockAction(p, this.block));
        this.temporaryFree = false;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        super.triggerOnOtherCardPlayed(c);
        if (c.hasTag(CardTag.SecretSixSamurai) && !this.freeToPlayOnce) {
            this.temporaryFree = true;
        }
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        this.temporaryFree = false;
    }

    // TODO
    //②：1回合1次，把自己墓地1只4星以下的「六武众」怪兽除外才能发动。直到回合结束时，这张卡的属性·等级·攻击力·守备力变成和除外的怪兽相同。

}
