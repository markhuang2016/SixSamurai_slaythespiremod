package org.mark.card.monster;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.RetrieveExhaustPileSixSamuraiCardAction;

/**
 * @description: 影六武众-玄蕃
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:38
 */
public class SecretSixSamuraiGenba extends SixSamuraiCard {

    public static final String ID = SecretSixSamuraiGenba.class.getSimpleName();

    public SecretSixSamuraiGenba() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO 平衡
        // ②：只让自己场上的「六武众」怪兽1只被效果破坏的场合，可以作为代替把墓地的这张卡除外。
        // 从消耗牌堆选一张 六武众 卡拿到手牌
        this.addToBot(new RetrieveExhaustPileSixSamuraiCardAction(p, 1));

    }

    @Override
    public AbstractCard makeCopy() {
        return new SecretSixSamuraiGenba();
    }
}
