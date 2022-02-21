package org.mark.card.monster;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.mark.card.SecretSixSamuraiCard;
import org.mark.enums.DamageTypeEnum;

/**
 * @description: 影六武众-木猿
 * @author: huangzhiqiang
 * @create: 2022/01/08 09:30
 */
public class SecretSixSamuraiKizaru extends SecretSixSamuraiCard {

    public static final String ID = SecretSixSamuraiKizaru.class.getSimpleName();

    public SecretSixSamuraiKizaru() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        initMonster(4, 19, 10);
        this.baseDamage = 19;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, DamageTypeEnum.SixSamurai)));
    }



    // TODO
    // ①：这张卡特殊召唤成功时才能发动。自己场上存在的属性以外的1只「六武众」怪兽从卡组加入手卡。
    //②：只让自己场上的「六武众」怪兽1只被效果破坏的场合，可以作为代替把墓地的这张卡除外。
    // 1900 1000

}
