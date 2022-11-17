package SacredBeast.actions;

import SacredBeast.SB_Mod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

public class TargetPotionThrowAction extends AbstractGameAction {

    private AbstractPotion potion;
    private AbstractPlayer p;

    private AbstractMonster m;

    public TargetPotionThrowAction(final AbstractPlayer p, final AbstractMonster m,
                               AbstractPotion potion) {

        this.p = p;
        this.m = m;
        this.potion=potion;
        actionType = ActionType.USE;

    }

    @Override
    public void update() {
        CardCrawlGame.metricData.potions_floor_usage.add(AbstractDungeon.floorNum);
        potion.use(m);
        Iterator var1 = AbstractDungeon.player.relics.iterator();

        while(var1.hasNext()) {
            AbstractRelic r = (AbstractRelic)var1.next();
            r.onUsePotion();
        }
        SB_Mod.potionsUsed+=1;

        CardCrawlGame.sound.play("POTION_1");
        this.tickDuration();

    }
}
