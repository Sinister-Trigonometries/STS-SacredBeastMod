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
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class ExtrapolateAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int amount;

    public ExtrapolateAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            Iterator var2 = this.p.drawPile.group.iterator();

            AbstractCard card;
            while(var2.hasNext() & amount>0) {
                card = (AbstractCard)var2.next();
                if (card.exhaust==true & amount>0) {
                    if (card.cost<=amount){
                        tmp.addToRandomSpot(card);
                        if (card.cost!=-1){
                            amount-=card.cost;
                        }
                    }


                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }

            for(int i = 0; i < this.amount; ++i) {
                if (!tmp.isEmpty()) {
                    tmp.shuffle();
                    card = tmp.getBottomCard();
                    tmp.removeCard(card);
                    AbstractDungeon.player.drawPile.group.remove(card);
                    AbstractDungeon.getCurrRoom().souls.remove(card);
                    AbstractDungeon.player.limbo.group.add(card);
                    card.current_y = -200.0F * Settings.scale;
                    card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                    card.target_y = (float)Settings.HEIGHT / 2.0F;
                    card.targetAngle = 0.0F;
                    card.lighten(false);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.applyPowers();
                    this.addToTop(new NewQueueCardAction(card, this.target, false, true));
                    this.addToTop(new UnlimboAction(card));
                    if (!Settings.FAST_MODE) {
                        this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    } else {
                        this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                    }
                }
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}

