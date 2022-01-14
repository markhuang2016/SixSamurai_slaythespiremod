package org.mark.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @description: 每回合开始，若无人工制品则，获得一层人工制品
 * @author: huangzhiqiang
 * @create: 2022/01/10 12:02
 */
public class ArtifactEveryTurnPower extends AbstractPower {

    public static final String POWER_ID = ArtifactEveryTurnPower.class.getSimpleName();
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Logger log = LogManager.getLogger(POWER_ID);

    public ArtifactEveryTurnPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 1;
        this.description = DESCRIPTIONS[0];
        this.loadRegion("artifact");
        this.type = PowerType.BUFF;
    }

    @Override
    public void atStartOfTurn() {
        log.info("powers:{}", owner.powers.stream().map(x -> x.ID).reduce((a, b) -> a + "," + b).orElse(""));
        addToBot(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, this.amount)));
    }
}
