package nightgames.modifier.requirements;

import java.util.Collection;
import nightgames.modifier.ModifierCategoryLoader;
import nightgames.modifier.ModifierComponentLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *Another workhorse class that needs to go eventually but was kept for consistency
 * Assembles a static variable as an unmodifiable list of blank templates
 */
public class ModifierRequirementsLoader implements ModifierCategoryLoader<ModifierRequirements>{
    private static final List<ModifierComponentLoader<ModifierRequirements>> TEMPLATES = Collections.unmodifiableList(
                    Arrays.asList(new BodyPartRequirement(), new DateRequirement()));

    /**
     *Confusing and unneeded getter for templates collection, which is static and can be accessed directly
     * 
     * @return the templates collection
     */
    @Override public Collection<ModifierComponentLoader<ModifierRequirements>> getTemplates() {
        return TEMPLATES;
    }

}
