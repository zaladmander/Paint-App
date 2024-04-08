# Photo App

## Overview

This project is a **photo drawing/editing app** with a photo selector, 
similar to a *simple* markup photo app. It will let the user choose photos from
their computer to edit. 

The people who will use it are people who want to **draw** on a photo
or draw for the fun of it. I'm interested in a project
like this because I personally enjoy art
(although I'm not great myself...) and I believe I would
find an app like this useful for my own everyday entertainment.

## Features
Editing can involve drawing with **varying brush strokes/sizes**,
**colours**, **opacities**, **brush types** (felts, pens, pencils, etc.)

Users can create custom brushes and save them
to a "pencil-case."

Users may also export their photos.



## User Stories

- As a user, I want to be able to customize my brush colour, texture, size,
or opacity. ✓

- As a user, I want to be able to create more custom brushes and add them
to a collection of brushes. ✓

- As a user, I want to be able to either draw from a blank canvas
or photos from my computer.

- As a user, I want to be able to have the choice of overwriting my 
edits to a photo or making a copy.

- As a user, I want to be able to view a list of photos on my computer and
select a photo to edit or tell the program where to pull my photos from.

- As a user, I want to be able to export my photos, edited or not, in the same
file type, or a different one.

- As a user, if I click to close a window, I want to be prompted to
either save and close, or close without saving.

- As a user, I want to be able to go back and forth between menus without having to commit to a task
  (forced to make a new paintbrush or etc.) 

- As a user, I want to be able to see a list of all my canvases, brushes, and pencilcases. ✓

### Phase 2

- As a user, I want to be able to save my brushes, canvases, pencilcases (by choice) ✓

- As a user, I want to be able to reload my work previously made on canvases (by choice) ✓

- As a user, I want to open the application
  and reload automatically all of my created brushes in their respective pencilcases

- As a user, I want to be able to remove all saved data by choice like a factory reset ✓

### Phase 3

- As a user, I want to be able to add multiple brushes to a pencilcase 
(and see the pencilcase with these additions) ✓

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by 
going to the top toolbar in a canvas window,
  clicking **Tools**, then **Brushes**, and you can create a PencilCase or click to enter a 
PencilCase and also create Brushes in that PencilCase
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by going to the top toolbar in a canvas window,
  clicking **Tools**, then **Brushes**, and you can delete a PencilCase or click to enter a
  PencilCase and also delete Brushes in that PencilCase
- You can locate my visual component by opening the application
and seeing the main menu
- You can save the state of my application by going to the top toolbar in a canvas window,
clicking file, and clicking save
- You can reload the state of my application by going to the top toolbar in a canvas window,
  clicking file, and clicking load

# Phase 4


## Phase 4: Task 2

- Case 'er' was added to BrushesRoom.
- New Brush Name set to ranni
- Brush 'ranni': Opacity set to 1.0
- Brush 'ranni': Size set to 44
- Brush 'ranni': Red set to 0
- Brush 'ranni': Green set to 204
- Brush 'ranni': Blue set to 204
- Brush 'ranni' was added to PencilCase er
- New Brush Name set to godrick
- Brush 'godrick': Opacity set to 1.0
- Brush 'godrick': Size set to 129
- Brush 'godrick': Red set to 255
- Brush 'godrick': Green set to 255
- Brush 'godrick': Blue set to 51
- Brush 'godrick' was added to PencilCase er

## Phase 4: Task 3

Reflecting on the UML class diagram, it is quite messy...

If I had more time to work on the project and refactor my design for 
improvements, I would certainly refactor a lot to do with Canvas and 
PaintingEditorMenu. Currently, canvas is a superclass(???) for what would
probably be a new class called GUIcanvas which would likely extend
canvas and have visual components, likely from JPanel, and would
tie into PaintingEditorMenu. This would help with saving and exporting
canvases.

I would probably refactor to remove any redundant fields that 
either aren't used or can be inherited. Also, remove any unused
import statements. 

Finally, I would refactor to create more abstract classes or methods
because I feel like many of my classes share very similar code
and thus, can be abstracted.