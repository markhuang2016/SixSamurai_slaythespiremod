package org.mark.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.enums.CardEnum;

public class DefendShien extends CustomCard {

    public static final String ID = "DefendShien";
    public static final String ImgUrl = "img/cards/DefendShien.png";

    public DefendShien() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).NAME, ImgUrl, 1,
            CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardEnum.ShienColor,
            CardRarity.BASIC, CardTarget.SELF);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.baseBlock = 7;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DefendShien();
    }
}
