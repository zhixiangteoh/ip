---
id: doc11
title: Empty Description
---

## Description

`EmptyDescriptionException` is a Dude-specific exception that is thrown when a command expecting some postfix string
 input is entered as a standalone command.
 
> When this exception is thrown, a formatted error message is displayed, and the program resumes running as usual.

## Scenarios

This exception can be thrown from using these commands:

- [`todo`](doc6.md#exceptions)
- [`event`](doc7.md#exceptions)
- [`deadline`](doc8.md#exceptions)
- [`find`](doc9.md#exceptions)

## Examples

```
todo
#    ____________________________________________________________
#    ☹ OOPS!!! The description of a(n) todo cannot be empty.
#    ____________________________________________________________
event␣␣␣␣␣␣␣␣␣␣
#    ____________________________________________________________
#    ☹ OOPS!!! The description of a(n) todo cannot be empty.
#    ____________________________________________________________
find
#    ____________________________________________________________
#    ☹ OOPS!!! The description of a(n) todo cannot be empty.
#    ____________________________________________________________
```

> Notice how the exception is thrown regardless of the number of trailing spaces.