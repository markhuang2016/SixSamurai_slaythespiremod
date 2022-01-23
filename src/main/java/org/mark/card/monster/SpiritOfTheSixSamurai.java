package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.SixSamuraiCard;
import org.mark.power.SixSamuraiExtraBlockPower;
import org.mark.power.SixSamuraiExtraDamagePower;
import org.mark.power.SixSamuraiRealDamageDrawCardPower;
import org.mark.power.SpiritOfTheSixSamuraiPower;

/**
 * @description: 六武众的御灵代
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:45
 */
public class SpiritOfTheSixSamurai extends SixSamuraiCard {

    public static final String ID = SpiritOfTheSixSamurai.class.getSimpleName();

    public SpiritOfTheSixSamurai() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseDamage = 5;
        this.baseBlock = 5;
        initMagicNumber(1);
        this.initMonster(3,5,5);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeDamage(3);
            upgradeBlock(3);
            upgradeMagicNumber(1);
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SixSamuraiExtraDamagePower(p, this.damage),1,true));
        this.addToBot(new ApplyPowerAction(p, p, new SixSamuraiExtraBlockPower(p, this.block), 1, true));
        this.addToBot(new ApplyPowerAction(p, p, new SixSamuraiRealDamageDrawCardPower(p, this.magicNumber), 1, true));
        this.addToBot(
            new ApplyPowerAction(p, p, new SpiritOfTheSixSamuraiPower(p, this.damage, this.block, this.magicNumber, 1),
                1,
                true));

        // 使用时和使用后每回合开始时获得一层 六武众力量、六武众敏捷，六武众攫取
        // 六武众力量 ：六武众造成的伤害额外增加X点
        // 六武众敏捷 ：六武众获取的格挡额外获取X点
        // 六武众攫取 ：下一次六武众卡牌对怪物造成直接伤害时，抽一张牌

    }

    // TODO
    // 同盟/效果：①：1回合1次，自己的主要阶段时可以当作装备卡使用给自己场上的名字带有「六武众」的怪兽装备，或者把装备解除以表侧攻击表示特殊召唤。只在这个效果当作装备卡使用的场合，装备怪兽的攻击力·守备力上升500。装备怪兽战斗破坏对方怪兽的场合，自己从卡组抽1张卡。（1只怪兽可以装备的同盟最多1张。装备怪兽被破坏的场合，作为代替把这张卡破坏。）

}
