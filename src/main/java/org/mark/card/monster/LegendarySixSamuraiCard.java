package org.mark.card.monster;

import org.mark.enums.CardTag;

/**
 * @description: 真六武众 卡牌
 * @author: huangzhiqiang
 * @create: 2022/01/04 17:34
 */
public abstract class LegendarySixSamuraiCard extends SixSamuraiCard {

    public LegendarySixSamuraiCard(String id, int cost, CardType cardType, CardRarity cardRarity, CardTarget cardTarget){
        super(id, cost, cardType, cardRarity, cardTarget);
        this.tags.add(CardTag.LegendarySixSamurai);
    }

}
