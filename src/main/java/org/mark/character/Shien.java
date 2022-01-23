package org.mark.character;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import org.mark.SixSamuraiMod;
import org.mark.card.monster.SecretSixSamuraiFuma;
import org.mark.card.magic.*;
import org.mark.card.monster.*;
import org.mark.card.trap.*;
import org.mark.enums.CardEnum;
import org.mark.enums.PlayerEnum;
import org.mark.modules.EnergyOrbShion;
import org.mark.relic.GatewayOfTheSix;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 紫炎
 * @author: huangzhiqiang
 * @create: 2021/12/27 18:11
 */
public class Shien extends CustomPlayer {

    public static final String id = "Shien";

    public static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(Shien.id);

    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String SHOULDER_2 = "VUPShionMod/characters/Shion/shoulder2.png";
    private static final String SHOULDER_1 = "VUPShionMod/characters/Shion/shoulder.png";
    private static final String CORPSE = "VUPShionMod/characters/Shion/corpse.png";
    private static final String STAND = null;
    // 能量动画
    private static final String[] ORB_TEXTURES = {
        "VUPShionMod/img/ui/topPanel/Shion/1.png",//4
        "VUPShionMod/img/ui/topPanel/Shion/2.png",//2
        "VUPShionMod/img/ui/topPanel/Shion/3.png",//3
        "VUPShionMod/img/ui/topPanel/Shion/4.png",//5
        "VUPShionMod/img/ui/topPanel/Shion/5.png",//1
        "VUPShionMod/img/ui/topPanel/Shion/border.png",
        "VUPShionMod/img/ui/topPanel/Shion/1d.png",//4
        "VUPShionMod/img/ui/topPanel/Shion/2d.png",//2
        "VUPShionMod/img/ui/topPanel/Shion/3d.png",//3
        "VUPShionMod/img/ui/topPanel/Shion/4d.png",//5
        "VUPShionMod/img/ui/topPanel/Shion/5d.png",//1
    };
    private static final String ORB_VFX = "VUPShionMod/img/ui/topPanel/Shion/energyVFX.png";
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    public Shien(String name) {
        //构造方法，初始化参数
        super(name, PlayerEnum.Shien, new EnergyOrbShion(ORB_TEXTURES, "VUPShionMod/img/ui/topPanel/Shion/energyVFX.png"), (String)null, (String)null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass(STAND, SHOULDER_2, SHOULDER_1, CORPSE,
            getLoadout(),
            0.0F, -5.0F, 240.0F, 480.0F,
            new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(SixSamuraiMod.assetPath("characters/Shion/animation/ShionAnimation.atlas"), SixSamuraiMod.assetPath("characters/Shion/animation/ShionAnimation.json"), 1.0f);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();
//        startingDeck.add(CunningOfTheSixSamurai.ID);
//        startingDeck.add(LegendaryEbonSteed.ID);
//        startingDeck.add(SecretSkillsOfTheSixSamurai.ID);
//        startingDeck.add(ShiensCastleOfMist.ID);
        startingDeck.add(ShiensDojo.ID);
        startingDeck.add(ShiensSmokeSignal.ID);
        startingDeck.add(SixSamuraiUnited.ID);
//        startingDeck.add(SixScrollsOfTheSamurai.ID);
//        startingDeck.add(SixStrikeTripleImpact.ID);
//        startingDeck.add(TempleOfTheSix.ID);

//        startingDeck.add(GrandMasterOfTheSixSamurai.ID);
//        startingDeck.add(GreatShogunShien.ID);
//        startingDeck.add(HandOfTheSixSamurai.ID);
        startingDeck.add(KagemushaOfTheSixSamurai.ID);
//        startingDeck.add(LegendarySecretOfTheSixSamurai.ID);
        startingDeck.add(LegendarySixSamuraiEnishi.ID);
        startingDeck.add(LegendarySixSamuraiKageki.ID);
//        startingDeck.add(LegendarySixSamuraiKizan.ID);
        startingDeck.add(LegendarySixSamuraiMizuho.ID);
//        startingDeck.add(LegendarySixSamuraiShien.ID);
        startingDeck.add(LegendarySixSamuraiShinai.ID);
        startingDeck.add(SecretSixSamuraiFuma.ID);
//        startingDeck.add(SecretSixSamuraiGenba.ID);
//        startingDeck.add(ShiensChancellorEnishi.ID);
        startingDeck.add(SpiritOfTheSixSamurai.ID);

//        startingDeck.add(DoubleEdgedSwordTechnique.ID);
//        startingDeck.add(ReturnOfTheSixSamurai.ID);
//        startingDeck.add(SixStrikeThunderBlast.ID);
//        startingDeck.add(SixStyleDualWield.ID);
//        startingDeck.add(SwiftstrikeArmor.ID);

        return startingDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(GatewayOfTheSix.ID);
        return startingRelics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], STARTING_HP, MAX_HP, HAND_SIZE, STARTING_GOLD,
            getAscensionMaxHPLoss(), this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "紫炎Title";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CardEnum.ShienColor;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.GREEN;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return Color.GREEN;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return "紫炎";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Shien(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.GREEN;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return null;
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel("VUPShionMod/img/scenes/ShionCutScene1.png"));
        panels.add(new CutscenePanel("VUPShionMod/img/scenes/ShionCutScene2.png"));
        panels.add(new CutscenePanel("VUPShionMod/img/scenes/ShionCutScene3.png"));
        return panels;
    }
}
