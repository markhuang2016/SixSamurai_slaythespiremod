package org.mark.power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.mark.relic.TemporaryRelic;

import java.util.ArrayList;

/**
 * @description: 移除临时遗物的能力
 * @author: huangzhiqiang
 * @create: 2022/01/20 14:48
 */
public class RemoveTemporaryRelicPower extends AbstractPower {

    public static final String POWER_ID = RemoveTemporaryRelicPower.class.getSimpleName();
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static ArrayList<TemporaryRelic> temporaryRelics = new ArrayList<>();

    public RemoveTemporaryRelicPower(TemporaryRelic relic) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = 1;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("artifact");
        this.type = PowerType.BUFF;
        temporaryRelics.add(relic);
    }

    public void remove(TemporaryRelic relic) {
        if (temporaryRelics.remove(relic)) {
            this.amount--;
            if (this.amount <= 0) {
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            }

        }
    }

    @Override
    public void onVictory() {
        AbstractPlayer player = AbstractDungeon.player;
        temporaryRelics.forEach(x->{
            x.onUnequip();
            player.relics.remove(x);
            player.reorganizeRelics();
        });
    }
}
