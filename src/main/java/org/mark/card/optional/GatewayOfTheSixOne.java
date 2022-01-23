package org.mark.card.optional;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.power.SixSamuraiExtraDamagePower;
import org.mark.relic.GatewayOfTheSix;

import java.util.Objects;

/**
 * @description: 六武之门-壹式
 * @author: huangzhiqiang
 * @create: 2022/01/20 17:10
 */
public class GatewayOfTheSixOne extends AbstractGatewayOfTheSixOption {

    public static final String ID = GatewayOfTheSixOne.class.getSimpleName();

    public GatewayOfTheSixOne() {
        super(ID);
        this.baseDamage = 5;
        this.initMagicNumber(2);
    }

    @Override
    public void executeOption() {
        // 从六武之门上移除 !M! 个武士道指示物，下一次 六武众 卡牌造成伤害时额外造成 !D! 点。
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
            new SixSamuraiExtraDamagePower(AbstractDungeon.player, this.baseDamage), 1));
    }
}
