package SacredBeast.cards.Attacks;

import SacredBeast.SB_Mod;
import SacredBeast.actions.BrewColorPotionAction;
import SacredBeast.actions.BrewColorPotionAction.PotionColor;
import SacredBeast.cards.AbstractDynamicCard;
import SacredBeast.characters.SB_Character;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static SacredBeast.SB_Mod.makeCardPath;


public class RabidEssence extends AbstractDynamicCard {

    // TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(RabidEssence.class.getSimpleName());
    public static final String IMG = makeCardPath("SB_COMMON_ATTACK.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // STAT DECLARATION 1
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    // STAT DECLARATION 2
    private static final int COST = 1;
    private static final int DAMAGE = 5; // Damage never upgrades so we only need damage here.
    private static final int POTION = 1;
    private static final int UPGRADE_PLUS_PT = 1;


    public RabidEssence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        magicNumber = baseMagicNumber = POTION;
        FleetingField.fleeting.set(this, true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for(int i=0; i<magicNumber;i++)
            AbstractDungeon.actionManager.addToBottom(
                    new BrewColorPotionAction(
                            PotionColor.RED));

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(
                        p, DAMAGE, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE));




    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_PT);
            rawDescription=UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}

