package org.mark.card.magic;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.AbstractSixSamuraiCard;
import org.mark.card.temporary.SixSamuraiUnitedActivated;

/**
 * @description: 六武众的团结
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:46
 */
public class SixSamuraiUnited extends AbstractSixSamuraiCard {

    public static final String ID = SixSamuraiUnited.class.getSimpleName();

    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public SixSamuraiUnited() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.initMagicNumber(2);
        this.purgeOnUse = true;
        this.cardsToPreview = new SixSamuraiUnitedActivated(this.magicNumber, this.upgraded, this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeMagicNumber(1);
            this.cardsToPreview = new SixSamuraiUnitedActivated(this.magicNumber, true, this);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获取一张 六武众的团结-已发动 ，替换 。
        // 获取一张 六武众的团结-已发动+ ，替换 。
        this.addToBot(new MakeTempCardInHandAction(new SixSamuraiUnitedActivated(this.baseMagicNumber, this.upgraded, this), 1));
    }

}
