package SacredBeast.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.random.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.potionRng;

public class BrewColorPotionAction extends AbstractGameAction {

    public enum PotionColor { RED, GREEN, BLUE, YELLOW, RAINBOW, WHITE}

    public AbstractPotion potion;

    Random randomizer = AbstractDungeon.cardRandomRng;
    public BrewColorPotionAction(PotionColor color){
        potion=null;
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_XFAST;

        switch(color){
            case RED:
                this.potion=getRedPotion();
                break;
            case BLUE:
                this.potion=getBluePotion();
                break;
            case GREEN:
                this.potion=getGreenPotion();
                break;
        }



    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            if (AbstractDungeon.player.hasRelic("Sozu")) {
                AbstractDungeon.player.getRelic("Sozu").flash();
            } else {
                AbstractDungeon.player.obtainPotion(this.potion);
            }
        }

        this.tickDuration();
    }

    public AbstractPotion getRedPotion() { //gets a red potion. Thankfully, they are all Common, so no need to account for rarity
        List<String> possible = Arrays.asList("Fire Potion", "Explosive Potion", "BlessingoftheForge", "AttackPotion","FearPotion","Strength Potion","SteroidPotion");

        //pick an index from within size, then fetch a potion keysting from possible, then turn a potion keystring into an actual potion
        return PotionHelper.getPotion(
                possible.get(
                        randomizer.random(0,possible.size()-1)));
    }

    public AbstractPotion getGreenPotion() { //gets a green potion. I've kept the chance to get a rare 10% despite the lack of uncommons.
        List<String> possible;
        float rarity = randomizer.random(0f,1f);

        if (rarity<.9) {
            possible = Arrays.asList("Dexterity Potion", "SpeedPotion", "SkillPotion", "Poison Potion"); //Common potions
        }
        else{
            possible = Arrays.asList("FairyPotion", "Fruit Juice", "SneckoOil"); //Rare Potions - Note this selection is Very Good
        }

        //pick an index from within size, then fetch a potion keysting from possible, then turn a potion keystring into an actual potion
        return PotionHelper.getPotion(
                possible.get(
                        randomizer.random(0,possible.size()-1)));
    }

    public AbstractPotion getBluePotion() { //gets a Blue potion.
        List<String> possible;
        float rarity = randomizer.random(0f,1f);

        if (rarity<.65) {
            possible = Arrays.asList("Block Potion", "PowerPotion", "Swift Potion"); //Common potions
        }
        else if (rarity <.9){
            possible = Arrays.asList("EssenceOfSteel", "LiquidMemories"); //Uncommon potions
        }
        else{
            possible = Arrays.asList("CultistPotion", "HeartOfIron"); //Rare Potions
        }

        //pick an index from within size, then fetch a potion keysting from possible, then turn a potion keystring into an actual potion
        return PotionHelper.getPotion(
                possible.get(
                        randomizer.random(0,possible.size()-1)));
    }
}
