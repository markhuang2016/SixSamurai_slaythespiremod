package org.mark.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.mark.SixSamuraiMod;
import org.mark.relic.TemporaryRelic;

import java.util.Objects;
import java.util.Optional;

/**
 * @description: 遗物移除
 * @author: huangzhiqiang
 * @create: 2022/01/17 23:09
 */
public class RemoveTemporaryRelicAction extends AbstractGameAction {

    private TemporaryRelic relic;

    public RemoveTemporaryRelicAction(TemporaryRelic relic) {
        this.relic = relic;
    }

    @Override
    public void update() {
        AbstractPlayer player = AbstractDungeon.player;
        Optional<AbstractRelic> removeOption = player.relics.stream()
            .filter(x -> Objects.equals(x, relic))
            .findAny();

        if (removeOption.isPresent()) {
            SixSamuraiMod.logger.info("找到要移除的遗物");
            AbstractRelic abstractRelic = removeOption.get();
            abstractRelic.onUnequip();
            player.relics.remove(abstractRelic);
            player.reorganizeRelics();

            if (Objects.nonNull(relic.card) && player.exhaustPile.contains(relic.card)) {
                relic.card.unfadeOut();
                player.exhaustPile.moveToDiscardPile(relic.card);
            }
        }
        this.isDone = true;
    }
}
