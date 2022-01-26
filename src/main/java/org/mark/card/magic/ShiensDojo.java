package org.mark.card.magic;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.AbstractSixSamuraiCard;

/**
 * @description: 紫炎的道场
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:35
 */
public class ShiensDojo extends AbstractSixSamuraiCard {

    public static final String ID = ShiensDojo.class.getSimpleName();

    public ShiensDojo() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.purgeOnUse = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获取一个临时遗物 紫炎的道场 。 消耗 ，当遗物使用后，此卡会回到弃牌堆。
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(0, 0, new org.mark.relic.ShiensDojo(this));
    }

}
