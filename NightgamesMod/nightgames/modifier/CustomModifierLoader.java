package nightgames.modifier;

import nightgames.modifier.requirements.ModifierRequirements;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import nightgames.json.JsonUtils;
import nightgames.modifier.action.ActionModifier;
import nightgames.modifier.clothing.ClothingModifier;
import nightgames.modifier.item.ItemModifier;
import nightgames.modifier.skill.SkillModifier;
import nightgames.modifier.status.StatusModifier;

public final class CustomModifierLoader {
    private CustomModifierLoader() {
        // TODO Auto-generated constructor stub
    }

    public static Modifier readModifier(JsonObject object) {
        int bonus = object.get("bonus").getAsInt();
        String name = object.get("name").getAsString();
        String intro = object.get("intro").getAsString();
        String acceptance = object.get("acceptance").getAsString();
        boolean enabled = object.get("enabled").getAsBoolean();//enable or disable modifiers

        ActionModifier action = JsonUtils.getOptionalArray(object, "action")
                        .map(array -> readModifiers(array, ActionModifier.combiner, ActionModifier.loader))
                        .orElse(ActionModifier.combiner.nullModifier());
        SkillModifier skill = JsonUtils.getOptionalArray(object, "skill")
                        .map(array -> readModifiers(array, SkillModifier.combiner, SkillModifier.loader))
                        .orElse(SkillModifier.combiner.nullModifier());
        ClothingModifier clothing = JsonUtils.getOptionalArray(object, "clothing")
                        .map(array -> readModifiers(array, ClothingModifier.combiner, ClothingModifier.loader))
                        .orElse(ClothingModifier.combiner.nullModifier());
        ItemModifier item = JsonUtils.getOptionalArray(object, "item")
                        .map(array -> readModifiers(array, ItemModifier.combiner, ItemModifier.loader))
                        .orElse(ItemModifier.combiner.nullModifier());
        ModifierRequirements reqs = JsonUtils.getOptionalArray(object, "requirement")
                        .map(array -> readModifiers(array, ModifierRequirements.combiner, ModifierRequirements.loader))
                        .orElse(ModifierRequirements.combiner.nullModifier());//requirements
        
        if (enabled){//Don't return disabled modifiers...
            return new BaseModifier(clothing, item, StatusModifier.combiner.nullModifier(), skill, action,
                        BaseModifier.EMPTY_CONSUMER, reqs) {

            @Override
            public int bonus() {
                return bonus;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String intro() {
                return intro;
            }

            @Override
            public String acceptance() {
                return acceptance;
            }
        };
        }
        else{
            return null;//...return null instead, which is caught in an if block in Global.buildModifierPool()
        }
    }
    
    private static <T extends ModifierCategory<T>> T readModifierComponent(JsonObject object,
                    ModifierCategoryLoader<T> template) {

        return template.instance(object);
    }

    private static <T extends ModifierCategory<T>> T readModifiers(JsonArray array, ModifierComponentCombiner<T> combiner,
                    ModifierCategoryLoader<T> loader) {
        Collection<JsonObject> objects = new ArrayList<>();
        array.forEach(element -> objects.add(element.getAsJsonObject()));
        return objects.stream().map(obj -> CustomModifierLoader.readModifierComponent(obj, loader))
                        .reduce(combiner::combine).orElse(combiner.nullModifier());
    }

}
