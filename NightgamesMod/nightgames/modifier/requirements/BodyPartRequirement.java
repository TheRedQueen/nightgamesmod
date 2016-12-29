package nightgames.modifier.requirements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.*;
import nightgames.global.Global;
import nightgames.modifier.ModifierComponentLoader;

/**
 *Class for determining if the player has a given body part as a requirement of
 * a given modifier
 * 
 */
public class BodyPartRequirement extends ModifierRequirements implements ModifierComponentLoader<ModifierRequirements>{
    private static final String name = "needs-bodypart";
    private ArrayList<String> reqParts;
    
    /**
     *Zero argument constructor acts as placeholder when there are no requirements of this type
     */
    public BodyPartRequirement(){
    }
    
    /**
     *This constructor updates boolean requirementsMet by iterating through an ArrayList
     * of String representations of body parts
     * 
     * @param reqParts ArrayList String of required body parts
     * @see Player.has(String bodypart)
     */
    public BodyPartRequirement(ArrayList<String> reqParts){
        this.reqParts = reqParts;
        if (Global.getPlayer()!=null){
        reqParts.stream().forEach((s) -> {
            requirementsMet = (requirementsMet && Global.getPlayer().has(s));
        });
        }
    }
    
    /**
     *This constructor takes the JSON read and extracts the string representations 
     * of the body parts.
     * 
     * @param object JSON object representing the body part requirement
     * @return new BodyPartRequirement for the ArrayList String constructor
     */
    @Override
    public BodyPartRequirement instance(JsonObject object) {
        JsonArray array;
        reqParts = new ArrayList();
        //if there is a list of items, read them to an array...
        if (object.has("list")) {
            array = (JsonArray) object.get("list");
        }
        //...if not, just dump the item in an array...
        else{
            array = new JsonArray();
            array.add(object);
        }//Then iterate through the array
        for (Object obj: array){
            JsonObject jobj = (JsonObject) obj;
            reqParts.add(jobj.get("part").getAsString());
            
        }
        return new BodyPartRequirement(reqParts);
    }
    
    /**
     *Overriden name does nothing exciting.  Largely a getName()
     * 
     * 
     * @return the name of the requirement
     */
    @Override public String name() {
        return name;
    }

    /**
     * Overriden toString appends the list if required parts
     *
     * @return String description
     */
    @Override public String toString() {
        return name() + reqParts;
    }
}
