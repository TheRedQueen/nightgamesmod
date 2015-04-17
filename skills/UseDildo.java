package skills;

import items.Item;
import global.Global;
import global.Modifier;
import combat.Combat;
import combat.Result;

import characters.Attribute;
import characters.Character;

public class UseDildo extends Skill{

	public UseDildo(Character self) {
		super(Item.Dildo.getName(), self);
	}

	@Override
	public boolean requirements() {
		return true;
	}

	@Override
	public boolean requirements(Character user) {
		return true;
	}

	@Override
	public boolean usable(Combat c, Character target) {
		return (self.has(Item.Dildo)||self.has(Item.Dildo2))&&self.canAct()&&target.hasPussy()&&c.getStance().reachBottom(self)&&target.pantsless()&&!c.getStance().penetration(self)
				&&(!self.human()||Global.getMatch().condition!=Modifier.notoys);
	}

	@Override
	public void resolve(Combat c, Character target) {
		if(target.roll(this, c, accuracy()+self.tohit())){
			if(self.has(Item.Dildo2)){
				if(self.human()){
					c.write(self,deal(c,0,Result.upgrade, target));
				}
				else if(target.human()){
					c.write(self,receive(c,0,Result.upgrade, target));
				}
				int m = 5+Global.random(15)+target.get(Attribute.Perception);
				target.body.pleasure(self, null, target.body.getRandom("pussy"), m, c);
			}
			else{
				if(self.human()){
					c.write(self,deal(c,0,Result.normal, target));
				}
				else if(target.human()){
					c.write(self,receive(c,0,Result.normal, target));
				}
				int m = Global.random(10)+target.get(Attribute.Perception);
				target.body.pleasure(self, null, target.body.getRandom("pussy"), m, c);
			}			
		}
		else{
			if(self.human()){
				c.write(self,deal(c,0,Result.miss, target));
			}
			else if(target.human()){
				c.write(self,receive(c,0,Result.miss, target));
			}
		}
	}

	@Override
	public Skill copy(Character user) {
		return new UseDildo(user);
	}

	@Override
	public Tactics type(Combat c) {
		return Tactics.pleasure;
	}

	@Override
	public String deal(Combat c, int damage, Result modifier, Character target) {
		if(modifier == Result.miss){
			return "You try to slip a dildo into "+target.name()+", but she blocks it.";
		}
		else if(modifier == Result.upgrade){
			return "You touch the imperceptively vibrating dildo to "+target.name()+"'s love button and she jumps as if shocked. Before she can defend herself, you " +
					"slip it into her " + target.body.getRandomPussy().describe(target) + ". She starts moaning in pleasure immediately.";
		}
		else{
			return "You rub the dildo against "+target.name()+"'s lower lips to lubricate it before you thrust it inside her. She can't help moaning a little as you " +
					"pump the rubber toy in and out of her " + target.body.getRandomPussy().describe(target) + ".";
		}
	}

	@Override
	public String receive(Combat c, int damage, Result modifier, Character target) {
		if(modifier == Result.miss){
			return Global.format("{self:SUBJECT-ACTION:try|tries} to slip a dildo into {other:direct-object}, but {other:subject} blocks it.", self,target);
		}
		else if(modifier == Result.upgrade){
			return Global.format("{self:SUBJECT-ACTION:touch|touches} the imperceptively vibrating dildo to {other:possessive} love button and {other:subject-action:jump|jumps} as if shocked. Before {other:subject} can defend {other:reflective}, {self:subject} " +
					"slip it into {other:possessive} {other:body-part:pussy}. {other:SUBJECT-ACTION:start|starts} moaning in pleasure immediately.",self,target);
		}
		else{
			return Global.format("{self:SUBJECT-ACTION:rub|rubs} the dildo against {other:possessive} lower lips to lubricate it before {self:subject-action:thrust|thrusts} it inside {self:direct-object}. "
					+"{other:SUBJECT} can't help moaning a little as {self:subject-action:pump|pumps} the rubber toy in and out of {other:possessive} {other:body-part:pussy}.",self,target);
		}
	}

	@Override
	public String describe() {
		return "Pleasure opponent with your dildo";
	}

}
