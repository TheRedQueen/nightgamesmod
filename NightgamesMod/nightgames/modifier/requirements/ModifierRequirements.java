package nightgames.modifier.requirements;

import nightgames.modifier.ModifierCategory;
import nightgames.modifier.ModifierComponent;

/**
 *Abstract superclass for all requirements classes
 */
public abstract class ModifierRequirements implements ModifierCategory<ModifierRequirements>, ModifierComponent{

    /**
     *Initialise a class to combine multiple requirements in to one
     * This should be changed from static eventually, left static for consistency
     */
    public static final ModifierRequirementsCombiner combiner = new ModifierRequirementsCombiner();

    /**
     *Initialise a class to load default requirements
     * This should be changed from static eventually, left static for consistency.
     */
    public static final ModifierRequirementsLoader loader = new ModifierRequirementsLoader();

    /**
     *Default is requirements met for all classes so that modifiers without requirements don't
     * fail the requirements met test. 
     * 
     * @see boolean BaseModifier.isApplicable()
     */
    protected boolean requirementsMet=true;
    
    /**
     *Empty constructor left for future expansion
     */
    //    public ModifierRequirements(){}
    
    /**
     *Creates a single instance of Modifier Requirements from two.
     * requirementsMet is simply a logical AND of the two instances
     * name is basically a concatenation
     * 
     * 
     * @param next The instance that this one is to be joined with
     * @return the unified instance
     */
    @Override
    public ModifierRequirements combine(ModifierRequirements next) {
        ModifierRequirements first = this;
        return new ModifierRequirements(){
            @Override
            public String name() {
                return first.name() + " and " + next.name();
            }
            @Override
            public boolean requirementsMet(){
                return first.requirementsMet() && next.requirementsMet();
            }
        };
    }
   
    /**
     *super-method for all subclasses
     * 
     * @return boolean representation of whether the requirements are met.
     */
    public boolean requirementsMet(){
        return requirementsMet;
    }
}
