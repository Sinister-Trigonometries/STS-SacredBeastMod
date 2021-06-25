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
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BottomDiscardToHandAction extends AbstractGameAction {
    private boolean changeCost;

    public BottomDiscardToHandAction(AbstractCreature target, boolean changecost) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.changeCost=changecost;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.discardPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            else {
                AbstractCard card = AbstractDungeon.player.discardPile.getTopCard();
                AbstractDungeon.player.discardPile.group.remove(card);
                card.applyPowers();
                AbstractDungeon.player.hand.addToHand(card);
                if (changeCost){
                    card.setCostForTurn(1);
                }
            }

            this.isDone = true;
        }

    }
}
