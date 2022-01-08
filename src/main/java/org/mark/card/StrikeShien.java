package org.mark.card;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.enums.CardEnum;

/**
 * @description: 打击 紫炎
 * @author: huangzhiqiang
 * @create: 2021/12/27 17:30
 */
public class StrikeShien extends CustomCard {

    public static final String ID = "StrikeShien";
    public static final String ImgUrl = "SixSamurai/img/cards/StrikeShien.png";

    public StrikeShien() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).NAME, ImgUrl, 1,
            CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.ATTACK, CardEnum.ShienColor,
            CardRarity.BASIC, CardTarget.ENEMY);

        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseDamage = 7;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,
            new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrikeShien();
    }

    @Override
    public void upgrade() {
        if (this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }
}
