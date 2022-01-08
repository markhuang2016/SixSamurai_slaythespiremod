package org.mark;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mark.card.*;
import org.mark.card.magic.*;
import org.mark.card.monster.*;
import org.mark.card.trap.DoubleEdgedSwordTechnique;
import org.mark.card.trap.ReturnOfTheSixSamurai;
import org.mark.card.trap.SixStrikeThunderBlast;
import org.mark.card.trap.SixStyleDualWield;
import org.mark.character.Shien;
import org.mark.enums.CardEnum;
import org.mark.enums.PlayerEnum;
import org.mark.relic.GatewayOfTheSix;

import java.util.ArrayList;

/**
 * @description: 六武众-紫炎
 * @author: huangzhiqiang
 * @create: 2021/12/27 17:05
 */
@SpireInitializer
public class SixSamuraiMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber,
    PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber,
    EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber,
    OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber {

    public static final String ModId = "SixSamurai";

    private static final String MOD_BADGE = "img/UI_Seles/badge.png";
    //攻击、技能、能力牌的图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_SELES_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_SELES_s.png";
    private static final String POWER_CC = "img/512/bg_power_SELES_s.png";
    private static final String ENERGY_ORB_CC = "img/512/SELESOrb.png";
    //攻击、技能、能力牌的图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_SELES.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_SELES.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_SELES.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/SELESOrb.png";
    public static final String CARD_ENERGY_ORB = "img/UI_Seles/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/SelesButton.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/SelesPortrait.png";
    public static final Color Green = CardHelper.getColor(200, 200, 200);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public static final Logger logger = LogManager.getLogger(SixSamuraiMod.class.getSimpleName());

    public static void initialize() {
        new SixSamuraiMod();
    }
    
    public SixSamuraiMod(){
        BaseMod.subscribe(this);
        BaseMod.addColor(CardEnum.ShienColor, Green, Green, Green, Green, Green, Green, Green,
            assetPath("img/cardui/Shion/512/bg_attack_lime.png"),
            assetPath("img/cardui/Shion/512/bg_skill_lime.png"),
            assetPath("img/cardui/Shion/512/bg_power_lime.png"),
            assetPath("img/cardui/Shion/512/card_lime_orb.png"),
            assetPath("img/cardui/Shion/1024/bg_attack_lime.png"),
            assetPath("img/cardui/Shion/1024/bg_skill_lime.png"),
            assetPath("img/cardui/Shion/1024/bg_power_lime.png"),
            assetPath("img/cardui/Shion/1024/card_lime_orb.png"),
            assetPath("img/cardui/Shion/512/card_lime_small_orb.png"));

    }

    public static String assetPath(String path) {
        return "VUPShionMod" + "/" + path;
    }
    @Override
    public void receiveEditCards() {
        logger.info("========================= 开始加载卡片 =========================");
        loadCardsToAdd();
        cardsToAdd.forEach(x -> {
            BaseMod.addCard(x);
            UnlockTracker.unlockCard(x.cardID);
        });
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("========================= 开始加载人物 =========================");

        logger.info(Shien.characterStrings.NAMES[1]);

        BaseMod.addCharacter(new Shien(Shien.characterStrings.NAMES[1]),assetPath("characters/Shion/Button.png"), assetPath("characters/Shion/portrait.png"), PlayerEnum.Shien);
    }

    @Override
    public void receiveEditKeywords() {

    }

    @Override
    public void receiveEditRelics() {
        logger.info("========================= 开始加载遗物 =========================");
        BaseMod.addRelicToCustomPool(new GatewayOfTheSix(), CardEnum.ShienColor);
    }

    @Override
    public void receiveEditStrings() {
        logger.info("========================= 开始加载文案 =========================");
        String language = Settings.GameLanguage.ZHS.name();
        String path = ModId + "/localization/" + language.toLowerCase() + "/";

//        BaseMod.loadCustomStringsFile(EventStrings.class, assetPath(path + "EventStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, path + "UIStrings.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, path + "CardStrings.json");
//        BaseMod.loadCustomStringsFile(MonsterStrings.class, assetPath(path + "MonsterStrings.json"));
//        BaseMod.loadCustomStringsFile(PowerStrings.class, assetPath(path + "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, path + "RelicStrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, path + "CharacterStrings.json");
//        BaseMod.loadCustomStringsFile(OrbStrings.class, assetPath(path + "OrbStrings.json"));

    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePowersModified() {

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostDungeonInitialize() {

    }

    @Override
    public void receivePostEnergyRecharge() {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostInitialize() {

    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature,
        AbstractCreature abstractCreature1) {

    }

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {

    }



    private void loadCardsToAdd() {
        // TODO 调整各种稀有度卡片数量
        //将自定义的卡牌添加到这里
        this.cardsToAdd.clear();
        // 魔法卡
        this.cardsToAdd.add(new CunningOfTheSixSamurai());
        this.cardsToAdd.add(new LegendaryEbonSteed());
        this.cardsToAdd.add(new SecretSkillsOfTheSixSamurai());
        this.cardsToAdd.add(new ShiensSmokeSignal());
        this.cardsToAdd.add(new SixSamuraiUnited());
        this.cardsToAdd.add(new SixScrollsOfTheSamurai());
        this.cardsToAdd.add(new SixStrikeTripleImpact());

        // 怪兽卡
        this.cardsToAdd.add(new GreatShogunShien());
        this.cardsToAdd.add(new HandOfTheSixSamurai());
        this.cardsToAdd.add(new LegendarySixSamuraiEnishi());
        this.cardsToAdd.add(new LegendarySixSamuraiKageki());
        this.cardsToAdd.add(new LegendarySixSamuraiMizuho());
        this.cardsToAdd.add(new LegendarySixSamuraiShien());
        this.cardsToAdd.add(new LegendarySixSamuraiShinai());
        this.cardsToAdd.add(new SecretSixSamuraiFuma());
        this.cardsToAdd.add(new SecretSixSamuraiGenba());
        this.cardsToAdd.add(new ShiensChancellorEnishi());
        // 陷阱卡
        this.cardsToAdd.add(new DoubleEdgedSwordTechnique());
        this.cardsToAdd.add(new ReturnOfTheSixSamurai());
        this.cardsToAdd.add(new SixStrikeThunderBlast());
        this.cardsToAdd.add(new SixStyleDualWield());


//        this.cardsToAdd.add(new DefendShien());
//        this.cardsToAdd.add(new StrikeShien());
    }
}