```md
# Line Editor

Write a simple word processor that accepts **text and commands** as input.

## Overview
- The file consists of up to **100 lines**.
- The program can **open an existing file** or **create a new one**.
- The editor keeps track of the **current line** at all times.
- Each command is followed by a **number (`#`)** indicating how many lines (starting from the current line) the command applies to.
- Each command operates on the specified line(s) in the file.

---

## Supported Commands

### `Substitute/oldstring/newstring/`
Substitute `newstring` for every occurrence of `oldstring` in the **current line**.  
- If `oldstring` does not appear, issue a message.  
- Print the modified line.

---

### `Type #`
Print the contents of the next `#` lines, including the current line.  
- Do not print trailing blank spaces.  
- The **current line becomes the last line printed**.

---

### `Copy #`
Copy the next `#` lines, including the current line, into a temporary storage area.  
- The **current line does not change**.

---

### `Paste`
Insert the contents of the temporary storage area **between the current line and the next line**.  
- The **current line becomes the last line pasted**.

---

### `Locate/string/`
Find the next occurrence of `string` after the current line.  
- If found, make that line the current line and print it.  
- If not found, issue a message and do not change the current line.

---

### `Insert #`
Insert `#` new lines **after the current line**.  
- The **current line becomes the last line entered**.

---

### `Delete #`
Delete the next `#` lines, including the current line.  
- The **first line after the deleted block becomes the current line**.

---

### `Replace #`
Replace the next `#` lines (starting from the current line) with `#` new lines.  
- The **current line becomes the last line entered**.

---

### `Move #`
Move to the line `#` lines past the current line.  
- Print that line and make it the current line.

---

### `Quit`
Save the file to disk and exit the editor.
```
