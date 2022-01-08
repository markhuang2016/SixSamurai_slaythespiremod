package org.mark.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @description: 如果目标为爪牙，击杀
 * @author: huangzhiqiang
 * @create: 2022/01/04 11:57
 */
public class KillMinionAction extends AbstractGameAction {

    protected Logger log = LogManager.getLogger("KillMinionAction");

    public KillMinionAction(AbstractPlayer p, AbstractMonster m, int damage) {
        this.setValues(m, p, damage);
    }

    public KillMinionAction(AbstractPlayer p, AbstractMonster m) {
        this.setValues(m, p, 0);
    }

    @Override
    public void update() {
        // TODO 优化击杀
        log.info("是否为爪牙：{},当前护甲:{},当前血量：{},伤害：{}", target.hasPower("Minion"), target.currentBlock,
            target.currentHealth, target.currentBlock + target.currentHealth);

        if (target.hasPower("Minion")) {
            this.addToBot(
                new DamageAction(target,
                    new DamageInfo(source, target.currentBlock + target.currentHealth, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        } else if (this.amount > 0) {
            this.addToBot(new DamageAction(target, new DamageInfo(source, this.amount, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        this.isDone = true;
    }
}
