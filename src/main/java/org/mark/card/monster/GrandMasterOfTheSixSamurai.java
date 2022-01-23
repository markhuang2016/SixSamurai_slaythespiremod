package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.SixSamuraiCard;
import org.mark.enums.CardTag;

import java.util.ArrayList;

/**
 * @description: 六武众的师范
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:50
 */
public class GrandMasterOfTheSixSamurai extends SixSamuraiCard {

    public static final String ID = GrandMasterOfTheSixSamurai.class.getSimpleName();

    public GrandMasterOfTheSixSamurai() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 21;
        this.initMonster(5, 21, 8);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成 !D! 点伤害。 NL 若手牌中有 此卡以外的 六武众 卡牌，此卡不消耗 [G] 。
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        this.temporaryFree = false;
    }
    // TODO
    // 效果：①：「六武众的师范」在自己场上只能有1只表侧表示存在。
    //②：自己场上有「六武众」怪兽存在的场合，这张卡可以从手卡特殊召唤。
    //③：这张卡被对方的效果破坏的场合，以自己墓地1只「六武众」怪兽为对象发动。那只怪兽加入手卡。

    @Override
    protected boolean isTemporaryFree(ArrayList<AbstractCard> handCards) {
        return handCards.stream()
            .anyMatch(x -> x.hasTag(CardTag.SixSamurai) && !x.cardID.contains(ID));
    }

}
