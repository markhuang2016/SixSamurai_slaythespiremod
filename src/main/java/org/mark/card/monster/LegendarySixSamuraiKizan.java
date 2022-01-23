package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.LegendarySixSamuraiCard;
import org.mark.enums.CardTag;
import org.mark.enums.DamageTypeEnum;

/**
 * @description: 真六武众-辉斩
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:27
 */
public class LegendarySixSamuraiKizan extends LegendarySixSamuraiCard {

    public static final String ID = LegendarySixSamuraiKizan.class.getSimpleName();

    public LegendarySixSamuraiKizan() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 18;
        this.initMonster(4, 18, 5);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成 !D! 点伤害。 NL 若除此卡外手牌有 六武众 卡，这张卡不消耗 [G]。 NL 若除此卡外手牌有两张及以上 六武众 卡，这张卡额外造成3点伤害。
        int damage = baseDamage;
        if (p.hand.group.stream().filter(x -> x.hasTag(CardTag.SixSamurai) && !x.uuid.equals(this.uuid)).count() >=
            2) {
            damage += 3;
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageTypeEnum.SixSamurai)));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        refresh();
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        if (this.freeToPlayOnce) {
            this.freeToPlayOnce = false;
        }
    }

    public void refresh() {
        if (AbstractDungeon.player.hand.group.contains(this) &&
            AbstractDungeon.player.hand.group.stream()
                .anyMatch(x -> x.hasTag(CardTag.SixSamurai) && !x.cardID.contains(ID))) {
            if (!this.freeToPlayOnce) {
                this.freeToPlayOnce = true;
            }
        } else {
            if (this.freeToPlayOnce) {
                this.freeToPlayOnce = false;
            }
        }
    }

}
