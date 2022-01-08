package org.mark.card.monster;

import org.mark.card.AbstractSixSamuraiCard;
import org.mark.enums.CardTag;

/**
 * @description: 六武众的卡
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:21
 */
public abstract class SixSamuraiCard extends AbstractSixSamuraiCard {

    public SixSamuraiCard(String id, int cost, CardType cardType, CardRarity cardRarity, CardTarget cardTarget) {
        super(id, cost, cardType, cardRarity, cardTarget);
        this.tags.add(CardTag.SixSamurai);
    }
}
