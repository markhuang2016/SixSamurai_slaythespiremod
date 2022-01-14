package org.mark.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.enums.DamageTypeEnum;

import java.util.ArrayList;

/**
 * @description: 对所有怪物造成指定伤害，若怪物为 爪牙 ，击杀
 * @author: huangzhiqiang
 * @create: 2022/01/07 10:07
 */
public class KillAllMinionAction extends AbstractGameAction{
    protected Logger log = LogManager.getLogger("KillMinionAction");

    public KillAllMinionAction(AbstractPlayer p, int damage) {
        this.setValues(null, p, damage);
    }

    public KillAllMinionAction(AbstractPlayer p) {
        this.setValues(null, p, 0);
    }

    @Override
    public void update() {
        // TODO 优化击杀
        log.info("开始");
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        int[] damages = new int[monsters.size()];
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).hasPower("Minion")) {
                damages[i] = monsters.get(i).currentBlock + monsters.get(i).currentHealth;
            } else {
                damages[i] = this.amount;
            }
        }
        this.addToBot(new DamageAllEnemiesAction(this.source, damages, DamageTypeEnum.SixSamurai,
            AttackEffect.SLASH_HORIZONTAL));
        this.isDone = true;

        log.info("结束");
    }
}
