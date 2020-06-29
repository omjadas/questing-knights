# SWEN90004 Assignment 1b Discussion

| Name     | Student Number |
| -------- | -------------- |
| Omja Das | 835780         |

## Problems in the Model

The model was based on the original specification instead of the implementation
that was completed in part A.

Through the use of LTSA it was discovered that there was the possibility of a
deadlock occurring when the King would re-enter the hall before knights had set
off to complete their quests (as knights are not allowed to enter or leave the
hall when the King is present).

To rectify the deadlock the model was modified so that knights are allowed to
exit the hall when the King is present, and the King cannot exit the hall when
any knights are present. This suggestion was made as it presents a minimal
change to the model and does not significantly change the operations of the
simulation. No performance trade-offs due to this suggestion have been
identified.

The deadlock that was detected using LTSA was not present in the implementation,
as this problem was also noticed during the course of part A. During part A a
different solution was implemented to deal with this problem, instead of
allowing knights to exit the hall before the King, the King was not allowed to
enter the hall when knights from previous meeting were still present.

The reason that the modeled suggestion does not match with the one that was
implemented as part of part A is that it was decided that the implemented
solution would be more difficult to model than the one that was eventually
suggested.

It is not thought that any other problems are present in the model, due to the
safety and liveness checks implemented in the model. These checks have all
passed when run using the LTSA tool.
