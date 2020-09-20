---
id: doc8
title: Create - Deadline
---

## Function

Creates a Deadline task. Change is written to saved output `duke.txt` file. 

## Description

Creates a Deadline task, marked `[D][✗]`, that is characterized as having both a task description and a date-time
 deadline, marked and appended by the `/by` delimiter. 
 
The `event` command accepts a string of words that is interpreted as the task description, the delimiter `/by
`, followed by a date-time string with the format `yyyy-MM-dd HHmm`.

> Note: If the date-time string entered does not correspond exactly to the `yyyy-MM-dd HHmm` format, the unformatted
> input will be used as the date-time representation for the task. 
 
> If no task description is specified, that is, only the `deadline` command is entered with 0 or more trailing spaces
>, the [`EmptyDescriptionException`](doc11.md) Exception is thrown. 

## Format

```
deadline <task description> /by <date-time>
```

## Examples

Case where date-time input corresponds to `yyyy-MM-dd HHmm` format:

```
deadline complete CS2113 ip User Guide /by 2020-09-24 2359
#
#    ____________________________________________________________
#    Got it. I've added this task:
#        [D][✗] complete CS2113 ip User Guide (by: Sep 24 2020 11:59 PM)
#    Now you have 5 tasks in the list.
#    ____________________________________________________________
```

Cases where date-time input does not correspond exactly to `yyyy-MM-dd HHmm` format:

```
deadline complete CS2113 ip User Guide /by 2020-09-24
#
#    ____________________________________________________________
#    Got it. I've added this task:
#        [D][✗] complete CS2113 ip User Guide (by: 2020-09-24)
#    Now you have 5 tasks in the list.
#    ____________________________________________________________
```

> Note how the formatting is not done if only the date is in the right format.

```
deadline complete CS2113 ip User Guide /by next Thursday
#
#    ____________________________________________________________
#    Got it. I've added this task:
#        [D][✗] complete CS2113 ip User Guide (by: next Thursday)
#    Now you have 5 tasks in the list.
#    ____________________________________________________________
```

## Exceptions

[Invalid Command](doc10.md)

[Empty Description](doc11.md)