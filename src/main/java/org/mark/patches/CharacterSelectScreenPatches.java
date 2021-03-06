package org.mark.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class CharacterSelectScreenPatches {
    public static ArrayList<AbstractGameEffect> char_effectsQueue = new ArrayList<>();

    public static ArrayList<AbstractGameEffect> char_effectsQueue_toRemove = new ArrayList<>();

    public static ShionPortraitAnimation shionSkin = new ShionPortraitAnimation();


    @SpirePatch(clz = CharacterSelectScreen.class, method = "initialize")
    public static class CharacterSelectScreenPatch_Initialize {

        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance) {
            char_effectsQueue.clear();
        }

        @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
        public static class CharacterSelectScreenPatch_portraitSkeleton {
            @SpireInsertPatch(rloc = 62)
            public static void Insert(CharacterSelectScreen __instance, SpriteBatch sb) {
                for (CharacterOption o : __instance.options) {
                    if (o.name.equals(CardCrawlGame.languagePack.getCharacterString("Shien").NAMES[0]) && o.selected) {
                        shionSkin.render(sb);

                        if (char_effectsQueue.size() > 0) {
                            for (AbstractGameEffect aChar_effectsQueue : char_effectsQueue) {
                                if (!aChar_effectsQueue.isDone) {
                                    aChar_effectsQueue.update();
                                    aChar_effectsQueue.render(sb);
                                } else {
                                    if (char_effectsQueue_toRemove == null)
                                        char_effectsQueue_toRemove = new ArrayList<>();
                                    if (!char_effectsQueue_toRemove.contains(aChar_effectsQueue))
                                        char_effectsQueue_toRemove.add(aChar_effectsQueue);
                                }
                            }
                            if (char_effectsQueue_toRemove != null)
                                char_effectsQueue.removeAll(char_effectsQueue_toRemove);
                        }
                    }
                }


            }
        }
    }

//    ??????????????????

    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")

    public static class CharacterOptionPatch_reloadAnimation {
        @SpireInsertPatch(rloc = 56)
        public static void Insert(CharacterOption __instance) {
            char_effectsQueue.clear();
            if (__instance.name.equals(CardCrawlGame.languagePack.getCharacterString("Shien").NAMES[0])) {
                if (shionSkin.hasAnimation())
                    shionSkin.loadPortraitAnimation();
            }
        }
    }
}