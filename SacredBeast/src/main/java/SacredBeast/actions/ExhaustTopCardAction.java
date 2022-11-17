//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package SacredBeast.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustTopCardAction extends AbstractGameAction {
    private int num;

    public ExhaustTopCardAction(AbstractCreature target, int numCards) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.num = numCards;

    }

    public void update() {

            for (int i = 0;i<num;i++) {
                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.isDone = true;
                }

                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    CardGroup drawpile = AbstractDungeon.player.drawPile;
                    drawpile.moveToExhaustPile(drawpile.getTopCard());
                }
            }
        this.tickDuration();



    }
}
