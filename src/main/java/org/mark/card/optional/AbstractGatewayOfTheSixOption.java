package org.mark.card.optional;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.relic.GatewayOfTheSix;

import java.util.Objects;

/**
 * @description: 六武之门选项卡
 * @author: huangzhiqiang
 * @create: 2022/01/20 17:48
 */
public abstract class AbstractGatewayOfTheSixOption extends AbstractSixSamuraiCard {

    public AbstractGatewayOfTheSixOption(String id) {
        super(id, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void onChoseThisOption() {
        AbstractRelic gatewayOfTheSix = AbstractDungeon.player.getRelic(GatewayOfTheSix.ID);

        if (Objects.nonNull(gatewayOfTheSix) && gatewayOfTheSix.counter >= this.magicNumber) {
            gatewayOfTheSix.counter -= this.magicNumber;
            executeOption();
        }
    }

    public abstract void executeOption();
}
