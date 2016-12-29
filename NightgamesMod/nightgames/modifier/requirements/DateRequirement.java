package nightgames.modifier.requirements;

import com.google.gson.JsonObject;
import nightgames.global.Global;
import nightgames.modifier.ModifierComponentLoader;

/**
 *Class for determining if it is a current as a requirement of
 * a given modifier
 */
public class DateRequirement extends ModifierRequirements implements ModifierComponentLoader<ModifierRequirements>{
private static final String name = "on-day";
private int date;
    
    /**
     *Zero argument constructor acts as placeholder when there are no requirements of this type
     */
    public DateRequirement(){}

    /**
     *This constructor takes the date from the JSON and determines whether it is
     * "today" in game time
     * 
     * @param d int representation of target day from JSON
     */
    public DateRequirement(int d){
        requirementsMet = (d==Global.getDate());
    }

    /**
     *This constructor takes the JSON read and extracts the int date representation
     * 
     * @param object JSON object representing the date requirement
     * @return new BodyPartRequirement for the int constructor
     */
    @Override
    public DateRequirement instance(JsonObject object) {
        
        JsonObject jobj = (JsonObject) object;
        date = jobj.get("day").getAsInt();
        return new DateRequirement(date);
        
    }
    
    /**
     **Overriden name does nothing exciting.  Largely a getName()
     * 
     * 
     * @return the name of the requirement
     */
    @Override public String name() {
        return name;
    }

    /**
     * Overriden toString appends the target date
     *
     * @return String description
     */
    @Override public String toString() {
        return name() + date;
    }

}
