package org.mark.card.trap;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.AbstractSixSamuraiCard;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @description: 六武派二刀流
 * 击晕随机两只怪物
 * 击晕全部怪物
 * @author: huangzhiqiang
 * @create: 2022/01/06 14:45
 */
public class SixStyleDualWield extends AbstractSixSamuraiCard {

    public static final String ID = SixStyleDualWield.class.getSimpleName();

    public static final String UpgradeDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;

    public SixStyleDualWield() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UpgradeDescription;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // fixme 两只怪物时无效
        List<AbstractMonster> monsters =
            AbstractDungeon.getCurrRoom().monsters.monsters.stream().filter(x -> !x.isDeadOrEscaped())
                .collect(Collectors.toList());
        if (!this.upgraded && monsters.size() > 2) {
            Random random = new Random();
            for (int i = 0; i < 2; i++) {
                this.addToBot(new StunMonsterAction(monsters.remove(random.nextInt(monsters.size())), p));
            }
        } else {
            monsters.forEach(x -> this.addToBot(new StunMonsterAction(m, p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SixStyleDualWield();
    }
}
