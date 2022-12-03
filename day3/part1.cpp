#include <bits/stdc++.h>

using namespace std;

ifstream infile("input.txt");

const string alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

int main()
{
    string in;
    long sum = 0;
    while (infile >> in)
    {
        set<char> comp1;
        set<char> comp2;
        set<char> intersect;

        for (size_t i = 0; i < in.size(); i++)
        {
            (i < (in.size() / 2)) ? comp1.insert(in[i]) : comp2.insert(in[i]);
        }

        set_intersection(comp1.begin(), comp1.end(), comp2.begin(), comp2.end(),
                         inserter(intersect, intersect.begin()));

        for (auto c : intersect)
        {
            int value = alphabet.find(c) + 1;
            sum += value;
        }
    }

    cout << sum << endl;
}