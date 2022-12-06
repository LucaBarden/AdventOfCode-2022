#include <fstream>
#include <iostream>
#include <string>
#include <unordered_set>

using namespace std;

int main() {
  // Read the input file
  ifstream input_file("input.txt");
  string stream;
  input_file >> stream;

  int part1 = -1, part2 = -1;
  for (int i = 0; i < stream.size(); ++i) {
    // Check if the substring of length 4 starting at index i contains no repeated characters
    unordered_set<char> seen;
    bool unique = true;
    for (int j = i; j < i + 4; ++j) {
      if (seen.count(stream[j])) {
        unique = false;
        break;
      }
      seen.insert(stream[j]);
    }
    if (unique && part1 == -1) {
      // Save the index of the end of the unique substring
      part1 = i + 4;
    }

    // Check if the substring of length 14 starting at index i contains no repeated characters
    seen.clear();
    unique = true;
    for (int j = i; j < i + 14; ++j) {
      if (seen.count(stream[j])) {
        unique = false;
        break;
      }
      seen.insert(stream[j]);
    }
    if (unique && part2 == -1) {
      // Save the index of the end of the unique substring
      part2 = i + 14;
      break;
    }
  }

  cout << "Part 1: " << part1 << endl;
  cout << "Part 2: " << part2 << endl;

  return 0;
}
