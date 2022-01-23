package org.mark.card;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * @description: 怪物卡
 * @author: huangzhiqiang
 * @create: 2022/01/17 16:42
 */
public abstract class MonsterCard extends AbstractSixSamuraiCard {

    /**
     * 怪物星级
     */
    public int star = 1;

    /**
     * 攻击力
     */
    public int attack;

    /**
     * 守备力
     */
    public int defend;

    /**
     * 调整
     */
    public boolean tuner;

    /**
     * 同步怪兽
     */
    public boolean synchro;

    /**
     * 临时不消耗能量，特殊召唤
     */
    public boolean temporaryFree = false;

    public MonsterCard(String id, int cost, CardType cardType,
        CardRarity cardRarity, CardTarget cardTarget) {
        super(id, cost, cardType, cardRarity, cardTarget);
    }

    protected void initMonster(int star, int attack, int defend) {
        this.star = star;
        this.attack = attack;
        this.defend = defend;
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || temporaryFree;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) || temporaryFree;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        refresh();
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        this.temporaryFree = false;
    }

    protected boolean isTemporaryFree(ArrayList<AbstractCard> handCards) {
        return temporaryFree;
    }

    public void refresh() {
        if (AbstractDungeon.player.hand.group.contains(this) &&
            isTemporaryFree(AbstractDungeon.player.hand.group)) {
            this.temporaryFree = true;
        } else {
            this.temporaryFree = false;
        }
    }
}
