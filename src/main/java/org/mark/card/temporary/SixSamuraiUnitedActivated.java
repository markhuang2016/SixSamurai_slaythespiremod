package org.mark.card.temporary;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.enums.CardTag;

/**
 * @description: 六武众的团结-已发动
 * @author: huangzhiqiang
 * @create: 2022/01/20 16:07
 */
public class SixSamuraiUnitedActivated extends AbstractSixSamuraiCard {

    public static final String ID = SixSamuraiUnitedActivated.class.getSimpleName();

    public static final String RawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
    public static final String[] ExtendedDescription =
        CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;


    private int drawNumber = 0;
    private AbstractCard card;

    public SixSamuraiUnitedActivated(int magicNumber, boolean upgraded, AbstractCard card) {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.card = card;
        this.purgeOnUse = true;
        this.selfRetain = true;
        this.initMagicNumber(magicNumber);
        if (upgraded) {
            this.upgrade();
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 每使用一张 六武众 卡 ，抽一张牌 ，最多 !M! 张。 保留 。
        this.addToBot(new DrawCardAction(p, this.drawNumber));
        card.unfadeOut();
        AbstractDungeon.player.hand.moveToDiscardPile(card);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.hasTag(CardTag.SixSamurai)) {
            this.drawNumber++;
        }
        if (this.drawNumber > this.baseMagicNumber) {
            this.drawNumber = magicNumber;
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        this.rawDescription = RawDescription + ExtendedDescription[0] + drawNumber + ExtendedDescription[1];

        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        log.info("{}卡被复制了", this.cardID);
        return new SixSamuraiUnitedActivated(this.magicNumber, this.upgraded, this.card);
    }
}
