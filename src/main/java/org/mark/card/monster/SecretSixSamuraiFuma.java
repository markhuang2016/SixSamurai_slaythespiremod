package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.RetrieveDrawPileSixSamuraiCardAction;

/**
 * @description: 影六武众-风魔
 * 获取 !B! 点格挡。 NL 被丢弃到弃牌堆时，可以从抽牌堆选 此卡 以外的 六武众 卡加入手牌
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:28
 */
public class SecretSixSamuraiFuma extends SixSamuraiCard {

    public static final String ID = SecretSixSamuraiFuma.class.getSimpleName();

    public SecretSixSamuraiFuma() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 12;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeBlock(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO ②：只让自己场上的「六武众」怪兽1只被效果破坏的场合，可以作为代替把墓地的这张卡除外。

        // 获取 !B! 点格挡。 NL 被丢弃到弃牌堆时，可以从抽牌堆选 此卡 以外的 六武众 卡加入手牌
        this.addToBot(new GainBlockAction(p,this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SecretSixSamuraiFuma();
    }

    @Override
    public void triggerOnManualDiscard() {
        this.addToBot(new RetrieveDrawPileSixSamuraiCardAction(AbstractDungeon.player, 1));
    }
}
