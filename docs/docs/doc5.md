---
id: doc5
title: Delete
---

## Function

Deletes a task from the task list. Change is written to saved output `duke.txt` file. 

## Description

Deletes a task. The `delete` command expects an integer value corresponding to the task index on the task list as
 displayed by the `list` command, prepended by a `space`.
 
The task list is automatically resized and re-indexed upon successful deletion of a task.
  
## Format

```
delete <index>
```

## Example

```
list
#
#    ____________________________________________________________
#    Here are the tasks in your list:
#    1.[E][✗] CS2113 lecture on Friday 18 September 2020 (at: 4pm to 6pm)
#    2.[D][✗] submit UG draft (by: today 2359)
#    3.[D][✓] complete 2113 homework (by: Sep 22 2020 11:59 PM)
#    ____________________________________________________________
delete 2
#
#    ____________________________________________________________
#    Noted. I've removed this task:
#      [D][✗] submit UG draft (by: today 2359)
#    Now you have 2 tasks in the list.
#    ____________________________________________________________
list
#
#    ____________________________________________________________
#    Here are the tasks in your list:
#    1.[E][✗] CS2113 lecture on Friday 18 September 2020 (at: 4pm to 6pm)
#    2.[D][✓] complete 2113 homework (by: Sep 22 2020 11:59 PM)
#    ____________________________________________________________
```

> Notice how the tasks are automatically re-indexed.

## Exceptions

[Invalid Command](doc10.md)

[Task Index Not Specified](doc12.md)