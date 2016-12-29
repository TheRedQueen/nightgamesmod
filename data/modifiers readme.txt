All of the modifiers sit between square brackets - the first character must be an open square bracket "[" and the last a closed square bracket "]".

Within those, each individual modifier is between braces "{" and "}" and seperated from the one following it by a comma.  There is no need for a comma before the first one or after the last.

Throughout the syntax examples, where I have used angle brackets "<" and ">" these are to signify things that can be changed.  The angle brackets themselves are not needed.

The fields inside the braces are:

 Name: 
	The name of the modifier.  This is an internal marker and is not visible to players.
 Name Example: 
	"No Fucking"
 Name Syntax: 
	"name":"<name-of-the-modifier>", 
 Complete Example of Name Field:
	"name":"No Fucking"
	
 Bonus:
	The bonus dollars awarded by Lily for playing with this modifier.  This can be negative but must be a whole number.
 Bonus Example:
	75
 Bonus Syntax:
	"bonus": <amount>, (note that the amount isn't in speech marks)
 Complete Example of Bonus Field:
	"bonus": 75,
	
 Enabled:
	Whether or not this modifier will be offered in game:
 Enabled Example:
	true or false
 Enabled Syntax
  "enabled": true (must be all lower case and not in speech marks)
 Complete Example of Enabled Field:
	"enabled": false
	
 Intro:
	What Lily says when she offers the player the chance to play the modifier.  This is written "in character" as Lily and her direct speech is both in single speech marks and italicised by putting <i> in front of it and </i> after it (<I> and </I> work as well.)  HTML can be used here, as of yet direct references to game objects cannot.  
 Intro Example:
	"<i>'Men...Or just anyone with a dick really.  Always trying to stick it in people... You aren't like that, are you? Think you can go a full night without fucking one of our mutual friends? Of course, if THEY do it, that's another story.'</i>",
 Intro Syntax:
	"intro": <"text-goes-here">,
 Complete Example of Intro Field:
	"intro":"<i>'Men...Or just anyone with a dick really.  Always trying to stick it in people... You aren't like that, are you? Think you can go a full night without fucking one of our mutual friends? Of course, if THEY do it, that's another story.'</i>",
 
 Bonus:
	What Lily says when and if the player decides to use the offered modifier.  The same syntax rules as the Intro field apply here
 Bonus Example:
	"<i>Great! I'll let the girls know their pussies are safe tonight. Well, as safe as they want them to be, anyway.</i>",
 Bonus Syntax:
	"bonus": <"text-goes-here">,
 Complete Example of Bonus Field:
	"acceptance":"<i>Great! I'll let the girls know their pussies are safe tonight. Well, as safe as they want them to be, anyway.</i>",
	
	

After these five compulsory fields come between one and five optional ones (technically you could have noe of them but that would give a modifier with no modifications).  These can come in any order (again, technically the above ones can as well, but it will be much easier if the order is the same in every modifier).  These five areas give the special rules of the modifications, the ones Lily explained in the intro, and fall in to four main categories and one "helper" category.  The four main modifier categories are Skill, Action, Clothing and Item. There are four types of Skill Modifier: ban-skills, ban-tactics, encourage-skills and encourage-tactics. The ban- modifiers prevent the player from using the listed skill(s) or tactics. Tactics are things like 'fucking', 'damage' or 'stripping'. The encourage- ones make NPCs more likely to use the skills/tactics provided.   There is only one Action modifier: ban-action. This bans a non-combat action from being used, like Masturbate, Clean Up or Hide (or Resupply, but that would break things). There are four clothing modifiers: force-clothing, no-panties, underwear-only and nude. underwear-only is the familiar 'boxers match', nude is self-explanatory, and no-panties is the opposite of underwear-only: clothing is fine, just no undies. force-clothing is more interesting: any listed clothing will be added to the player's outfit for the night, even if they don't own it. You can find the clothing in the data/clothing folder. Finally, we have the two item modifiers: ban-toys and ban-consumables.  The last category is requirements and comes in two types - needs-bodypart and on-day.  If something is set for needs-bodypart then Lily will not offer it to players without that body part, if something is set for on-day it will only happen on that day.

The syntax for all of these is the same and can be a bit trickier than the main ones above.  We'll build a simple one up as we go.

First comes the area, in lower case and in speech marks followed by a colon and an open square bracket:

"skill": [

after that comes each of the rules for that area.  First an open brace, then the word "type" in speech marks, then a colon, then the name of the area in speech marks, exactly as they are above then a comma:

"skill": [
{"type":"ban-action",

Then "value" in speech marks, a colon and an open brace:

"skill": [
{"type":"ban-action",
"value": {

If you are doing banconsumables, bantoys, nopanties, nude or underwear only, you then put two close braces - }} - as no more information is needed.  For all the others, the game needs to know more - what tactics are you banning, what body part is required, etc.  In those cases you put a single word in speech marks from the table below, then a colon, then the extra information in speech marks, and then the two closing braces.

"skill": [
{"type":"ban-action",
"value": {
"action":"MasturbateAction"}}

If that's the last thing you want to add from that category, then put a closing square bracket.  If you want to add more from that category, put a comma then use the last three lines again, just make sure you put a closing square bracket when you're done:

"skill": [
{"type":"ban-action",
"value": {
"action":"MasturbateAction"}},
{"type":"ban-skills",
"value": {
"skill":"Wild Thrust"}}]

If you then want to add from another category, put a comma and do the same again:

"skill": [
{"type":"ban-tactics",
"value": {
"tactics":"damage"}},
{"type":"ban-skills",
"value": {
"skill":"Wild Thrust"}}],

"action": [
{"type":"ban-action",
"value": {
"action":"MasturbateAction"}}]

When you're done, remember that the last character must be a closing square bracket:

"skill": [
{"type":"ban-tactics",
"value": {
"tactics":"damage"}},
{"type":"ban-skills",
"value": {
"skill":"Wild Thrust"}}],

"action": [
{"type":"ban-action",
"value": {
"action":"MasturbateAction"}}]
]

The only ones that are different are the encourage-tactics and encourage-skills from the Skills section whihc require two pieces of information - what skill or tactic to encourage, and how much to encourage it by (the weight).  This can be a positive number or a negative one (which will discourage NPCs from using it.)  3 is quite high, -3 quite low, and it can be a decimal.  To use these, simply add a new line under "action":

"skill": [
{"type":"encourage-tactic",
"value": {
"tactic":"fucking",
"weight":1.5}}]

To use:				Make your Keyword:
ban-tactics			"tactics"
ban-skills			"skill"
encourage-tactic	"tactic" and "weight"
encourage-skills	"skill" and "weight"
ban-action			"action"
force-clothing		"clothing"
needs-bodypart		"part"
on-day				"day"

Finally, its possible to get a bit more complicated and do lists of actions.  Have a look at the JSON file to see how they work.