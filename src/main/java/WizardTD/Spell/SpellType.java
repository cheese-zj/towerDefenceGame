package WizardTD.Spell;

/**
 * Enum representing different types of spells in the WizardTD game.
 * <p>
 * This enum defines four different spell types: BLAST, POISON, SLOW, and PROTECTION. Each
 * type corresponds to a specific spell ability or behavior that can be applied in the game.
 * The enum provides utility methods to navigate and represent the spell types.
 * </p>
 */
public enum SpellType {
    BLAST("Blast"),
    POISON("Poison"),
    SLOW("Slow"),
    PROTECTION("Protection");

    private static final SpellType[] vals = values();
    private final String name;

    /**
     * Constructs a new SpellType with the specified name.
     *
     * @param s  The friendly name for the spell type.
     */
    private SpellType(String s){
        name = s;
    }

    /**
     * Gets the next spell type in the list.
     * <p>
     * This method wraps around to the beginning of the list after the last element,
     * ensuring continuous navigation through spell types.
     * </p>
     *
     * @return The next SpellType in the list.
     */
    public SpellType next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    /**
     * @return The name of the spell type.
     */
    public String toString() {
        return this.name;
    }

}
