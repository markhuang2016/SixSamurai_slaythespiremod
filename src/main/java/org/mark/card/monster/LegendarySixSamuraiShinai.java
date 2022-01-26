package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.action.RetrieveDiscardPileSixSamuraiCardAction;
import org.mark.card.LegendarySixSamuraiCard;
import org.mark.enums.DamageTypeEnum;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @description:真六武众-竹刀 造成 !D! 点伤害。 NL 若手牌中有 真六武众-瑞穗，此卡不消耗 [G] 。 NL 被丢弃时从墓地选一张 六武众 牌。
 * @author: huangzhiqiang
 * @create: 2022/01/04 17:31
 */
public class LegendarySixSamuraiShinai extends LegendarySixSamuraiCard {

    public static final String ID = LegendarySixSamuraiShinai.class.getSimpleName();

    public static final Logger log = LogManager.getLogger(ID);

    public LegendarySixSamuraiShinai() {
        this(true);
    }

    public LegendarySixSamuraiShinai(boolean completed) {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage = 15;
        this.initMonster(3, 15, 15);
        if (completed) {
            this.cardsToPreview = new LegendarySixSamuraiMizuho(false);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageTypeEnum.SixSamurai),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.temporaryFree = false;
    }

    @Override
    public void triggerOnManualDiscard() {
        log.info("竹刀被丢弃了");
        this.addToTop(new RetrieveDiscardPileSixSamuraiCardAction(AbstractDungeon.player, 1));
    }

    @Override
    protected boolean isTemporaryFree(ArrayList<AbstractCard> handCards) {
        return handCards.stream()
            .filter(x -> Objects.equals(x.cardID, LegendarySixSamuraiMizuho.ID)).count() > 0;
    }

}
