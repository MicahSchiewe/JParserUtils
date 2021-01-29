# JParser Local
Allows interfacing with JParser via the command line.

## Setup
Clone the project. To run, will require you follow the installation instructions for JParser at (https://github.com/cloudhubs/jparser)

## CL Arguments:
* -fromdir <directory>: Runs JParser from the provided root directory, and writes the results to file `ast.json` in the root directory. Warning: not pretty-printed.
* -camelcasejson <filename.json>: Takes a JSON file and standardizes all field names to the camelCase convention, writing result to file `camelCase.json` in the root directory. Ad-hoc thing I made to help compare ASTs from our parser and JParser, since the naming convention was sometimes different.

Adding a new CL argument is as simple as creating a new constant in Options and implementing `handle(String[] args)`, it'll be automatically parsed. Remember this was basically a scratch-pad program I threw together.