---
id: doc6
title: Create - ToDo
---

## Function

Creates a ToDo task. Change is written to saved output `duke.txt` file. 

## Description

Creates a ToDo task, marked `[T][✗]`, that is characterized as only having a task description. The `todo` command
 accepts a string of words that is interpreted as the task description. 
 
> If no task description is specified, that is, only the `todo` command is entered with 0 or more trailing spaces
>, the [`EmptyDescriptionException`](doc11.md) Exception is thrown. 

## Format

```
todo <task description>
```

## Example

```
todo HIIT exercise with John
#    ____________________________________________________________
#    Got it. I've added this task:
#        [T][✗] HIIT exercise with John
#    Now you have 3 tasks in the list.
#    ____________________________________________________________
```

## Exceptions

[Invalid Command](doc10.md)

[Empty Description](doc11.md)
