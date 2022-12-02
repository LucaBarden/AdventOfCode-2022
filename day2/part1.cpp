#include <bits/stdc++.h>

using namespace std;


map<string,int> scores { {"X", 1}, {"Y", 2}, {"Z", 3}};

map<string, string> inputs { {"X", "A"}, {"Y", "B"}, {"Z", "C"}};

map<string, string> beats { {"A", "C"}, {"B", "A"}, {"C", "B"}};

ifstream infile("input.txt");


int main() {
  long sum = 0;
  string op, in;

  while(infile >> op >> in) {
     sum += scores[in];
     if(op == inputs[in]) {
        sum += 3;
        cout << "Draw" << endl;
     }else if(beats[op] == inputs[in]) {
        sum += 0;
        cout << "Lose" << endl;
     }else {
        sum += 6;
        cout << "Win" << endl;
     }
  }

  cout << sum << endl;
}
