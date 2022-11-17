package SacredBeast.cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public abstract class AbstractSBCard extends CustomCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractDynamicCard" instead of "extends CustomCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.

    public int secondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int baseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)
    public int thirdMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int baseThirdMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedThirdMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isThirdMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)
    public Boolean isXCost;                     //If it is X-Cost
    public AbstractSBCard(final String id,
                          final String name,
                          final String img,
                          final int cost,
                          final String rawDescription,
                          final CardType type,
                          final CardColor color,
                          final CardRarity rarity,
                          final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isSecondMagicNumberModified = false;
        isThirdMagicNumberModified = false;
        isXCost=false;
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            secondMagicNumber = baseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }
        if (upgradedSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            thirdMagicNumber = baseThirdMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isThirdMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        baseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        secondMagicNumber = baseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }
    public void upgradeThirdMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        baseThirdMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        thirdMagicNumber = baseThirdMagicNumber; // Set the number to be equal to the base value.
        upgradedThirdMagicNumber = true; // Upgraded = true - which does what the above method does.
    }
    public boolean payPlatedArmor(AbstractPlayer p, int cost){
        if (p.hasPower(PlatedArmorPower.POWER_ID) && p.getPower(PlatedArmorPower.POWER_ID).amount > cost) {
            addToBot(
                    new ReducePowerAction(p, p, PlatedArmorPower.POWER_ID,cost));
            return true;
        }
        if (p.hasPower(PlatedArmorPower.POWER_ID) && p.getPower(PlatedArmorPower.POWER_ID).amount == cost) {
            addToBot(
                    new RemoveSpecificPowerAction(p,p, PlatedArmorPower.POWER_ID));
            return true;
        }
        else{
            return false;
        }
    }
    public int payUpToPlatedArmor(AbstractPlayer p, int max){


        if (p.hasPower(PlatedArmorPower.POWER_ID) && p.getPower(PlatedArmorPower.POWER_ID).amount > max) {
            addToBot(
                    new ReducePowerAction(p, p, PlatedArmorPower.POWER_ID,max));
            return max; //if they have more plated armor than they need we just return the max
        }
        else if (p.hasPower(PlatedArmorPower.POWER_ID) && p.getPower(PlatedArmorPower.POWER_ID).amount <= max) {
            addToBot(
                    new RemoveSpecificPowerAction(p, p, PlatedArmorPower.POWER_ID));
            return p.getPower(PlatedArmorPower.POWER_ID).amount; //if they have as much or less plated armor than they need we delete it and return however much they had.
        }

        return 0;
    }
    public void addUpToPlatedArmor(AbstractPlayer p, int amount, int max){
        int current = 0;
        if (p.hasPower(PlatedArmorPower.POWER_ID)){
            current += p.getPower(PlatedArmorPower.POWER_ID).amount;
        }
        int temp = current+amount;
        if (current>=max)
        {
            return;
        }

        else if (temp > max){
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p,max-current), max-current));
        }
        else{
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p,amount), amount));
        }
    }
    public void addPlatedArmorUnless(AbstractPlayer p, int amount){
        if (!p.hasPower(PlatedArmorPower.POWER_ID)){
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p,amount), amount));
        }
    }
}