package org.mark.card;

import org.mark.enums.CardTag;

/**
 * @description: 影六武众卡牌
 * @author: huangzhiqiang
 * @create: 2022/01/11 14:55
 */
public abstract class SecretSixSamuraiCard extends SixSamuraiCard {

    public SecretSixSamuraiCard(String id, int cost, CardType cardType,
        CardRarity cardRarity, CardTarget cardTarget) {
        super(id, cost, cardType, cardRarity, cardTarget);
        this.tags.add(CardTag.SecretSixSamurai);
    }
}
