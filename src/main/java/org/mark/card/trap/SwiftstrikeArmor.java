package org.mark.card.trap;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.power.SwiftstrikeArmorPower;

/**
 * @description: 神速之具足
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:40
 */
public class SwiftstrikeArmor extends AbstractSixSamuraiCard {

    public static final String ID = SwiftstrikeArmor.class.getSimpleName();

    public SwiftstrikeArmor() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        initMagicNumber(1);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            updateCost(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 每回合抽卡后额外抽 !M! 张卡，若抽到的是 六武众 卡，此卡下次使用不消耗 [G] 。
        this.addToBot(new ApplyPowerAction(p, p, new SwiftstrikeArmorPower(p, this.magicNumber)));

    }

}
