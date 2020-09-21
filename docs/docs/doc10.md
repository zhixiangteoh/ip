---
id: doc10
title: Invalid Command
---

## Description

`InvalidCommandException` is a Dude-specific exception that is thrown when a program-incomprehensible command, that
 is, one not specified on the [Command Summary](doc13.md), is entered. 
 
> When this exception is thrown, a formatted error message is displayed, and the program resumes running as usual.

## Scenarios

This exception is thrown whenever an input entered is not prefixed by one of these [commands](doc13.md).

## Examples

```
tod
#    ____________________________________________________________
#    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
#    ____________________________________________________________
blah
#    ____________________________________________________________
#    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
#    ____________________________________________________________
some gibberish
#    ____________________________________________________________
#    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
#    ____________________________________________________________
```