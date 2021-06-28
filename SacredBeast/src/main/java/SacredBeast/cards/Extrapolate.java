package SacredBeast.cards;

import SacredBeast.SB_Mod;
import SacredBeast.actions.EasyXCostAction;
import SacredBeast.actions.ExtrapolateAction;
import SacredBeast.actions.PlayGivenCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Iterator;

import static SacredBeast.SB_Mod.makeCardPath;


public class Extrapolate extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(Extrapolate.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = -1;


    public Extrapolate() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (effect, params) -> {
            ArrayList <AbstractCard> list = new ArrayList<AbstractCard>();
            int energy = !upgraded? effect:effect+1;

            addToBot(new ExtrapolateAction(energy));

            return true;
        }));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION; // If the description changes significantly
            initializeDescription();
        }
    }
}
