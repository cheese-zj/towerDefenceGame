package WizardTD.Spell;

public enum SpellType {
    BLAST("Blast"),
    POISON("Poison"),
    SLOW("Slow"),
    PROTECTION("Protection");

    private static final SpellType[] vals = values();
    private final String name;

    private SpellType(String s){
        name = s;
    }

    public SpellType next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public String toString() {
        return this.name;
    }

}
