---
id: doc4
title: Done
---

## Function

Mark a task done in the task list. Change is written to saved output `duke.txt` file. 

## Description

Irreversibly marks a task done by changing the cross in the done status box `[✗]` to a tick `[✓]`. If task was
 already previously marked as done, this command makes no visible changes. The `done` command expects an integer value
  corresponding to the task index on the task list as displayed by the `list` command, prepended by a `space`.
  
## Format

```
done <index>
```

## Examples

Case where task is originally not done:

```
list
#    ____________________________________________________________
#    Here are the tasks in your list:
#    1.[E][✗] CS2113 lecture on Friday 18 September 2020 (at: 4pm to 6pm)
#    2.[D][✗] submit UG draft (by: today 2359)
#    3.[D][✓] complete 2113 homework (by: Sep 22 2020 11:59 PM)
#    ____________________________________________________________
done 2
#    ____________________________________________________________
#    Nice! I've marked this task as done:
#      [D][✓] submit UG draft (by: today 2359)
#    ____________________________________________________________
```

Case where task is originally done:

```
list
#    ____________________________________________________________
#    Here are the tasks in your list:
#    1.[E][✗] CS2113 lecture on Friday 18 September 2020 (at: 4pm to 6pm)
#    2.[D][✓] submit UG draft (by: today 2359)
#    3.[D][✓] complete 2113 homework (by: Sep 22 2020 11:59 PM)
#    ____________________________________________________________
done 2
#    ____________________________________________________________
#    Nice! I've marked this task as done:
#      [D][✓] submit UG draft (by: today 2359)
#    ____________________________________________________________
```

> Notice the output is still shown although no visible changes are made.

## Exceptions

[Invalid Command](doc8.md)

[Task Index Not Specified](doc10.md)