package org.mark.card.magic;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.card.monster.SixSamuraiCard;
import org.mark.enums.CardTag;

/**
 * @description: 六武众的团结
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:46
 */
public class SixSamuraiUnited extends SixSamuraiCard {

    public static final String ID = SixSamuraiUnited.class.getSimpleName();

    public static final String RawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;

    public static final String[] ExtendedDescription =
        CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    private Logger log = LogManager.getLogger(ID);

    private int drawNumber = 0;

    public SixSamuraiUnited() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 每使用一张 六武众 卡 ，抽一张牌 ，最多 !M! 张
        this.addToBot(new DrawCardAction(p, this.drawNumber));
        refresh();
    }

    @Override
    public AbstractCard makeCopy() {
        return new SixSamuraiUnited();
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        log.debug(
            c.cardID + "被使用了,tags:" + c.tags.stream().map(x -> x.name()).reduce((a, b) -> a + "," + b).orElse(""));

        if (c.hasTag(CardTag.SixSamurai)) {
            this.drawNumber++;
        }
        if (this.drawNumber > this.baseMagicNumber) {
            this.drawNumber = magicNumber;
        }
        log.debug("drawNumber:" + drawNumber);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        this.rawDescription = RawDescription + ExtendedDescription[0] + drawNumber + ExtendedDescription[1];

        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        refresh();
    }

    private void refresh(){
        drawNumber = 0;
        this.rawDescription = RawDescription;
        this.initializeDescription();
    }

}
