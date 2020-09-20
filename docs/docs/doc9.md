---
id: doc9
title: Find
---

## Function

Filters tasks in the task list by key words and/or phrase.

## Description

Finds and displays all tasks in the task list matching the entered string. The `find` command expects a key word or
 phrase to be used to filter the task list.
 
> If no key word or phrase is specified, that is, only the `find` command is entered with 0 or more trailing spaces
>, the [`EmptyDescriptionException`](doc11.md) Exception is thrown. 

## Format

```
find <key word or phrase>
```

## Example

```
list
#
#    ____________________________________________________________
#    Here are the tasks in your list:
#    1.[E][✗] CS2113 lecture on Friday 18 September 2020 (at: 4pm to 6pm)
#    2.[D][✓] complete 2113 homework (by: Sep 22 2020 11:59 PM)
#    3.[T][✗] HIIT exercise with John 
#    4.[E][✗] meet-up with high school pals (at: next Friday)
#    5.[D][✗] complete CS2113 ip User Guide (by: Sep 24 2020 11:59 PM)
#    ____________________________________________________________
find 2113
#
#    ____________________________________________________________
#    Here are the matching tasks in your list:
#    1.[E][✗] CS2113 lecture on Friday 18 September 2020 (at: 4pm to 6pm)
#    2.[D][✓] complete 2113 homework (by: Sep 22 2020 11:59 PM)
#    ____________________________________________________________
```

## Exceptions

[Invalid Command](doc10.md)

[Empty Description](doc11.md)