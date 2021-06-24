package SacredBeast.cards;

import SacredBeast.SB_Mod;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;

import static SacredBeast.SB_Mod.makeCardPath;


public class Cower extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(Cower.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 0;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MN = 1;


    public Cower() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber=MAGIC_NUMBER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        if (SB_Mod.potionsUsed>0){
            addToBot(new DrawCardAction(p,magicNumber));

        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);    // If there is block to upgrade
            upgradeMagicNumber(UPGRADE_PLUS_MN);
            rawDescription = UPGRADE_DESCRIPTION; // If the description changes significantly
            initializeDescription();
        }
    }
}
