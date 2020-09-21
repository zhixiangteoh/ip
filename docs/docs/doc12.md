---
id: doc12
title: Task Index Not Specified
---

## Description

`TaskIndexNotSpecifiedException` is a Dude-specific exception that is thrown when a command expecting a postfix
 integer input as task index is entered as a standalone command.
 
> When this exception is thrown, a formatted error message is displayed, and the program resumes running as usual.

## Scenarios

This exception can be thrown from using these commands:

- [`done`](doc4.md#exceptions)
- [`delete`](doc5.md#exceptions)

## Examples

```
done
#    ____________________________________________________________
#    ☹ OOPS!!! I won't know which task unless you specify an index!
#    ____________________________________________________________
delete␣␣␣␣␣␣␣␣␣␣
#    ____________________________________________________________
#    ☹ OOPS!!! I won't know which task unless you specify an index!
#    ____________________________________________________________
```

> Notice how the exception is thrown regardless of the number of trailing spaces.