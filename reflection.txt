# SWEN90004 Assignment 1a Reflection

| Name     | Student Number |
| -------- | -------------- |
| Omja Das | 835780         |

## Implementation

This implementation accurately reflects the system as described in the spec.

### Design Decisions

This implementation closely follows the suggested design and scaffolding
provided.

It was decided that each Agenda would be able to contain at most one Quest, the
implementation does, however, allow the number of Quests an Agenda can contain
to be modified in the Params.java file.

The Hall keeps track of the Knights that have entered. The Table keeps track of
the Knights that are sitting.

The Agendas are not passed to the Knights, instead they are encapsulated in the
Hall, which manages Quests by releasing and assigning Quests when appropriate.

### Insights

#### Agenda Size

Limiting the number of Quests that an Agenda can contain to 1 could potentially
cause performance to suffer when running the simulation, as before Knights can
be assigned a Quest it has to be produced and placed in the Agenda.

Increasing the size of the Agendas to equal
the number of Knights in the simulation could help to eliminate the waiting.

#### Knights Staying in the Hall after a Meeting

During the implementation of the system it was noticed that a logical deadlock
could be reached if Knights from a previous meeting had not yet left to complete
their Quest when the King re-entered the Hall. Once the King has entered the
Hall Knights are not allowed to enter or leave, and the meeting cannot start
until all Knights are sitting at the Table.

A possible solution to this deadlock would be to enforce that all Knights must
have exited from the previous meeting before the King could re-enter the Hall.
This solution was implemented.

Another possible solution would be to allow Knights from previous meetings to
leave the Hall even if the King is present.
