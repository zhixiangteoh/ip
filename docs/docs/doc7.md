---
id: doc7
title: Create - Event
---

## Function

Creates a Event task. Change is written to saved output `duke.txt` file. 

## Description

Creates an Event task, marked `[E][✗]`, that is characterized as having both a task description and a date-time
 deadline, marked and appended by the `/at` delimiter. 
 
The `event` command accepts a string of words that is interpreted as the task description, the delimiter `/at
`, followed by a date-time string with the format `yyyy-MM-dd HHmm`.

> Note: If the date-time string entered does not correspond exactly to the `yyyy-MM-dd HHmm` format, the unformatted
> input will be used as the date-time representation for the task. 
 
> If no task description is specified, that is, only the `event` command is entered with 0 or more trailing spaces
>, the [`EmptyDescriptionException`](doc11.md) Exception is thrown. 

## Format

```
event <task description> /at <date-time>
```

## Examples

Case where date-time input corresponds to `yyyy-MM-dd HHmm` format:

```
event meet-up with high school pals /at 2020-09-25 2000
#    ____________________________________________________________
#    Got it. I've added this task:
#        [E][✗] meet-up with high school pals (at: Sep 25 2020 08:00 PM)
#    Now you have 4 tasks in the list.
#    ____________________________________________________________
```

Cases where date-time input does not correspond exactly to `yyyy-MM-dd HHmm` format:

```
event meet-up with high school pals /at 2020-09-25
#    ____________________________________________________________
#    Got it. I've added this task:
#        [E][✗] meet-up with high school pals (at: 2020-09-25)
#    Now you have 4 tasks in the list.
#    ____________________________________________________________
```

> Note how the formatting is not done if only the date is in the right format.

```
event meet-up with high school pals /at next Friday
#
#    ____________________________________________________________
#    Got it. I've added this task:
#        [E][✗] meet-up with high school pals (at: next Friday)
#    Now you have 4 tasks in the list.
#    ____________________________________________________________
```

## Exceptions

[Invalid Command](doc10.md)

[Empty Description](doc11.md)