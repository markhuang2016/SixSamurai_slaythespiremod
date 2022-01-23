package org.mark.card;

import org.mark.card.MonsterCard;
import org.mark.enums.CardTag;

/**
 * @description: 紫炎的卡
 * @author: huangzhiqiang
 * @create: 2022/01/05 17:21
 */
public abstract class ShiensCard extends MonsterCard {

    public ShiensCard(String id, int cost, CardType cardType, CardRarity cardRarity, CardTarget cardTarget) {
        super(id, cost, cardType, cardRarity, cardTarget);
        this.tags.add(CardTag.Shien);
    }
}
