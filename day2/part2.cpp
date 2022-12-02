#include <bits/stdc++.h>

using namespace std;


map<string,int> scores { {"A", 1}, {"B", 2}, {"C", 3}};

map<string, string> wins { {"A", "B"}, {"B", "C"}, {"C", "A"}};

map<string, string> lose { {"A", "C"}, {"B", "A"}, {"C", "B"}};

map<string, string> beats { {"X", "Z"}, {"Y", "X"}, {"Z", "Y"}};

ifstream infile("input.txt");


int main() {
  long sum = 0;
  string op, in;

  while(infile >> op >> in) {
     sum += scores[in];
     if(in == "Y") {
        sum += scores[op];
        sum += 3;
        cout << "Draw" << endl;
     }else if(in == "X") {
        sum += scores[lose[op]];
        cout << "Lose" << endl;
     }else {
        sum += scores[wins[op]];
        sum += 6;
        cout << "Win" << endl;
     }
  }

  cout << sum << endl;
}
