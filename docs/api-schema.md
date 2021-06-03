# Front2Back:

```
{ "command": COMMAND }
```
	
# Back2Front:
```
{
	"player": {
		"cards": [{"cardInfo": {"number": Int, "shape": CARD_SHAPE, "pic": String }}];
		"wins": Int;
		"money": Double;
		"totalGames": Int;
	},
	"dealer": {
		"cards": [{"cardInfo": {"number": Int, "shape": CARD_SHAPE, "pic": String }}];
	},
	"status": GAME_STATUS
	
}
```

```
{ 
	"wins": Int;
	"money": Double;
	"totalGames": Int;
}


