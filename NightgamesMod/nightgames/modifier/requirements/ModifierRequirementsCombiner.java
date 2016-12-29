package nightgames.modifier.requirements;

import nightgames.modifier.ModifierComponentCombiner;

/**
 *Workhorse class that creates the null instance (NULL_MODIFIER) and a blank template
 * 
 * Should be refactored out, kept for consistency
 */
public class ModifierRequirementsCombiner implements ModifierComponentCombiner<ModifierRequirements>{
    private static final ModifierRequirements NULL_MODIFIER = new ModifierRequirements(){
        private static final String name = "null-requirements";

        @Override 
        public String name() {
            return name;
        }

        @Override public String toString() {
            return name;
        }
        
        @Override
        public boolean requirementsMet(){
            return true; //default is true
        }
    };

    /**
     *Rather confusing method that calls the combine in ModifierRequirments
     * 
     * Should be refactored out, kept for consistency.
     * 
     * @param first an instance of ModifierRequirements
     * @param next another, to be combined with the first
     * @return the combined instance
     * @see ModifierRequirements.combine
     */
    @Override
    public ModifierRequirements combine(ModifierRequirements first, ModifierRequirements next) {
        return first.combine(next);
    }
    
    //Empty template
    private static final ModifierRequirements TEMPLATE = new ModifierRequirements() {
        @Override public String toString() {
            return "";
        }

        @Override public String name() {
            return "";
        }

        @Override public ModifierRequirements combine(ModifierRequirements next) {
            return next;
        }
    };
    
    /**
     *Getter for the emptyTemplate
     * 
     * @return empty template
     */
    @Override
    public ModifierRequirements template() {
        return TEMPLATE;
    }

    /**
     *Getter for the null case
     * 
     * @return null case
     */
    @Override
    public ModifierRequirements nullModifier() {
        return NULL_MODIFIER;
    }

}
