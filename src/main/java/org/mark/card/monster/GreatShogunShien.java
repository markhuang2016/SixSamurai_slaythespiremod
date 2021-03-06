package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.ShiensCard;
import org.mark.enums.CardTag;
import org.mark.enums.DamageTypeEnum;

import java.util.ArrayList;

/**
 * @description: 大将军紫炎
 * @author: huangzhiqiang
 * @create: 2022/01/06 11:55
 */
public class GreatShogunShien extends ShiensCard {

    public static final String ID = GreatShogunShien.class.getSimpleName();

    public GreatShogunShien() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 25;
        this.initMonster(7, 25, 24);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成 !D! 点伤害。若手牌有两张及以上的 六武众 卡牌时 ，此卡不消耗能量
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageTypeEnum.SixSamurai)));
        // TODO 只要这张卡在场上表侧表示存在，对方1回合只能有1次把魔法·陷阱卡发动。
        //自己场上有名字带有「六武众」的怪兽2只以上存在的场合，这张卡可以从手卡特殊召唤。
        //①：只要这张卡在场上表侧表示存在，对方1回合只能有1次把魔法·陷阱卡发动。
        //②：场上表侧表示存在的这张卡被破坏的场合，可以作为代替把自己场上表侧表示存在的1只名字带有「六武众」的怪兽破坏。
        this.temporaryFree = false;
    }

    @Override
    protected boolean isTemporaryFree(ArrayList<AbstractCard> handCards) {
        return handCards.stream().filter(x -> x.hasTag(CardTag.SixSamurai)).count() >= 2;
    }
}
