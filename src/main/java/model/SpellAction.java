package model;


import controller.DuelController;

public class SpellAction {
    private static SpellAction instance;

    public static SpellAction getInstance() {
        if (instance == null) instance = new SpellAction();
        return instance;
    }

    public void enableSpellAbsorptions(DuelController duelController) {
        for (int i = 0; i < 5; i++) {
            if (duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i) != null
                    && duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i) instanceof SpellCard
                    && duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i).equals(SpellCard.SPELL_ABSORPTION)) {
                SpellCard.SPELL_ABSORPTION.takeAction(duelController, TakeActionCase.ANY_SPELL_ACTIVATED, duelController.getPlayer(), i);
            }
            if (duelController.getRival().getBoard().getSpellAndTrapByNumber(i) != null
                    && duelController.getRival().getBoard().getSpellAndTrapByNumber(i) instanceof SpellCard
                    && duelController.getRival().getBoard().getSpellAndTrapByNumber(i).equals(SpellCard.SPELL_ABSORPTION)) {
                SpellCard.SPELL_ABSORPTION.takeAction(duelController, TakeActionCase.ANY_SPELL_ACTIVATED, duelController.getRival(), i);
            }
        }
    }


}
