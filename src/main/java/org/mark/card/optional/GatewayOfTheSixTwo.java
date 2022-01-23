package org.mark.card.optional;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.action.KillMinionAction;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.enums.CardTag;
import org.mark.relic.GatewayOfTheSix;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @description: 六武之门-贰式
 * @author: huangzhiqiang
 * @create: 2022/01/20 17:10
 */
public class GatewayOfTheSixTwo extends AbstractGatewayOfTheSixOption {

    public static final String ID = GatewayOfTheSixTwo.class.getSimpleName();

    public GatewayOfTheSixTwo() {
        super(ID);
        this.initMagicNumber(4);
    }

    @Override
    public void executeOption() {
        // 从六武之门上移除 !M! 个武士道指示物，从抽牌堆和弃牌堆中选择一张 六武众 或 紫炎 卡加入手牌
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.addAll(AbstractDungeon.player.drawPile.group);
        cards.addAll(AbstractDungeon.player.discardPile.group);
        // TODO 优化
        this.addToBot(
            new SelectCardsCenteredAction(cards, 1, "", false, x -> x.hasTag(CardTag.SixSamurai), chosen -> {
                chosen.forEach(card -> {
                    if (AbstractDungeon.player.drawPile.contains(card)) {
                        AbstractDungeon.player.drawPile.moveToHand(card);
                    } else if (AbstractDungeon.player.discardPile.contains(card)) {
                        AbstractDungeon.player.discardPile.moveToHand(card);
                    }
                });
            }));
    }
}
