# **Java Mini Projects (Structured Programming)**
#### Structured Programming mini-projects, developed for Coding Factory's Java course. 
- ### <u>Project 1: CombinationSixFiltered</u>
    Reads at least 6 and at maximum 49 integers from a file (eg. input.txt), that have values from 1 to 49. 
    They are inserted in an array, which, in turn, it is being sorted.
    All the possible sextets are produced, and they are being filtered by the following criteria:
    1. There should not be more than 4 even numbers.
    2. There should not be more than 4 odd numbers.
    3. There should not be more than 2 equal numbers in a row.
    4. There should not be more than 3 numbers that have the same final digit.
    5. There should not be more than 3 numbers in the same ten.  
    
    Finally, the filtered combinations are being printed in a file (eg. output.txt).
- ### <u>Project 2: ContactList</u>
    Contact List management system. It can store up to 500 contacts, having Lastname, Firstname and
    Phone Number fields. Each contact should be unique, meaning that there should not be any other
    contact having the same name or number.
    The user is given the following choices:
    - *Contact Search*, by name or number.
    - *New Contact Insertion*, provided that the given details do not already exist.
    - *Contact Update*.
    - *Contact Deletion*, provided that the given contact exists.
    - *Contact List Display*, sorted by lastname, either in ascending or descending order.
- ### <u>Project 3: FileCharReader</u>
  Reads the characters of a file (eg. input.txt) and it stores them in a 256X2 array, so that,
  in each row, the first column refers to each unique character read and the second one to the number
  of times that character exists in the file. Whether the file contains more than 256 unique 
  characters, the reading process is terminated and the total number of read characters is returned.
  After the reading is finished, the total number and the percentile frequency of appearances
  of each character is being displayed. The user chooses whether the former results are being 
  sorted in either ascending or descending order of character or appearance number. The statistics
  are also being presented in a chart.
- ### <u>Project 4: ParkingInfo</u>
  Being provided with an array containing the arrival and departure times of each car in a
  parking garage, it displays the maximum number of cars that are parked simultaneously
- ### <u>Project 5: LowHighIndex</u>
  Displays the first and the last position of appearance, in a sorted array, of a given number,
  provided that the former exists in the array.
- ### <u>Project 6: MaxSumContiguousSubarray</u>
  Checks a given array and displays its contiguous subarray with the maximum summary.
  The algorithm used for this functionality is briefly described.
  
  The array is traversed once and each local maximum summary is being calculated. For each 
  local maximum summary the initial and final subarray position is being stored.
  When the local maximum is higher than the global-one, its value is being stored in a 
  "globalMax" variable. Its initial and final positions are also being stored in the
  "maxPositionInitial" and "maxPositionFinal" variables, respectively.
- ### <u>Project 7: DeepShallowCopy</u>
  A programme that shows the difference between a shallow and a deep copy of an array.
  Two copies of a given array are being created; a shallow and a deep one. Thereafter,
  there are being displayed the potential changes that occur in the original array, should
  the copied arrays' values are modified. Equally, there are being shown the changes that
  potentially happen to each copied array, after a modification in the original array.
- ### <u>Project 8: TicTacToe</u>
  A console-based version of the classical Tic Tac Toe game. 

  
- ### <u>Project 9: Encryption-Decryption</u>
  An Encryption - Decryption system.

- ### <u>Project 10: BookingSystem</u>
  A simple theater booking management system
