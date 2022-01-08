package org.mark.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.SixSamuraiMod;
import org.mark.enums.CardEnum;

/**
 * @description: 六武众的卡
 * @author: huangzhiqiang
 * @create: 2021/12/31 16:54
 */
public abstract class AbstractSixSamuraiCard extends CustomCard {

    public static final String BaseCardImgUrl = "/img/cards/dest/";
    public static final String ImgSuffix = ".png";

    protected Logger log = LogManager.getLogger(this.cardID);

    public AbstractSixSamuraiCard(String id, int cost, CardType cardType, CardRarity cardRarity, CardTarget cardTarget) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, getImgUrl(id), cost,
            CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, cardType, CardEnum.ShienColor,
            getCardRarity(id),
            cardTarget);
        log.info(getImgUrl(id));
    }

    public static String getImgUrl(String id) {
        return SixSamuraiMod.ModId + BaseCardImgUrl + id + ImgSuffix;
    }

    public void initMagicNumber(int magicNumber) {
        this.baseMagicNumber = magicNumber;
        this.magicNumber = this.baseMagicNumber;
    }

    public static int i = 0;
    public static CardRarity getCardRarity(String Id) {
        if (i > 4) {
            i = 0;
        }
        return CardRarity.values()[i++];
    }
}
