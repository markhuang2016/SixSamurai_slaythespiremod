package org.mark.card.monster;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.action.DiscardSixSamuraiCardAction;
import org.mark.action.KillMinionAction;
import org.mark.card.LegendarySixSamuraiCard;
import org.mark.enums.CardTag;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @description: 真六武众-瑞穗
 * 丢弃一张六武众牌，对目标造成 !D! 点伤害，若目标为 爪牙 ，击杀。 NL 若手牌中有 真六武众-竹刀 ,此卡不消耗 [G]
 * @author: huangzhiqiang
 * @create: 2021/12/31 17:43
 */
public class LegendarySixSamuraiMizuho extends LegendarySixSamuraiCard {

    public static final String ID = LegendarySixSamuraiMizuho.class.getSimpleName();

    public LegendarySixSamuraiMizuho() {
        this(true);
    }

    public LegendarySixSamuraiMizuho(boolean completed) {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage = 16;
        this.initMonster(3, 16, 10);
        if (completed) {
            this.cardsToPreview = new LegendarySixSamuraiShinai(false);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardSixSamuraiCardAction(p, 1));
        this.addToBot(new KillMinionAction(p, m, this.damage));
        this.temporaryFree = false;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) &&
            p.hand.group.stream().anyMatch(x -> x.hasTag(CardTag.SixSamurai) && !x.uuid.equals(this.uuid));
    }

    @Override
    protected boolean isTemporaryFree(ArrayList<AbstractCard> handCards) {
        return handCards.stream()
            .filter(x -> Objects.equals(x.cardID, LegendarySixSamuraiShinai.ID)).count() > 0;
    }
}
