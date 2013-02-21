Android Alphabet App
============
An Android app that teaches children their ABCs by saying the letter that the child presses.


* Uses custom icons for each letter.
* Matches and plays recorded audio when shared event is fired by touching a letter.
* Due to 26 letters and 10 numbers, redundancy is reduced by generating much of the UI through code, instead of within the layout xml file.

Apk file is available for demonstration at https://github.com/alex-pope/alphabet-app/blob/master/bin/Alphabet.apk?raw=true.

To generate your own apk, audio files must be placed in ./res/raw/ directory in the format:
* a.mp3 - z.mp3 for letters
* a01.mp3 - a10.mp3 for numbers.
