package org.mark.card.optional;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.enums.CardTag;
import org.mark.power.SixSamuraiExtraDamagePower;
import org.mark.relic.GatewayOfTheSix;

import java.util.Objects;

/**
 * @description: 六武之门-叁式
 * @author: huangzhiqiang
 * @create: 2022/01/20 17:10
 */
public class GatewayOfTheSixThree extends AbstractGatewayOfTheSixOption {

    public static final String ID = GatewayOfTheSixThree.class.getSimpleName();

    public GatewayOfTheSixThree() {
        super(ID);
        this.initMagicNumber(6);
    }

    @Override
    public void executeOption() {
        // 从六武之门上移除 !M! 个武士道指示物，从弃牌堆选择一张 紫炎 卡，此卡回到手牌且下次使用不消耗能量。
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new MoveCardsAction(p.hand,p.discardPile,x->x.hasTag(CardTag.Shien),1,cards->{
            cards.stream().forEach(x -> x.freeToPlayOnce = true);
        }));
    }
}
