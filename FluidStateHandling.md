
|State|material is gaseous|material is liquid|material is solid/default|
|:---:|:---:|:---:|:---:|
|**type is gaseous**|pf: ""; T: temp|pf: "gaseous"; T: BT|pf: "gaseous"; T: BT|
|**type is liquid**|pf: "liquid"; T: MT|pf: ""; T: temp|pf: ""; T: MT|
|**default**|pf: state; T: temp|pf: state; T: temp|pf: state; T: temp|

pf = prefix before the fluids name
T = temperature of the fluid
temp = the materials temperature
BT = the materials boiling temperature
MT = the materials melting temperature
state = the types state


 - When deciding the stats for the fluid, check the types state first, then the materials state
 - The default state for types is for each state of the material the same, so a special material-state-handling is not necessary
