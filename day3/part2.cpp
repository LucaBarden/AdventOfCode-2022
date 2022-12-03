#include <bits/stdc++.h>

using namespace std;

ifstream infile("input.txt");
const string alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

int main()
{
    string in;
    long sum = 0;
    int group = 0;
    set<char> elve1;
    set<char> elve2;
    set<char> elve3;
    map<char, int> counts;

    while (infile >> in)
    {

        for (size_t i = 0; i < in.size(); i++)
        {
            if (group % 3 == 0)
            {
                elve1.insert(in[i]);
            }
            else if (group % 3 == 1)
            {
                elve2.insert(in[i]);
            }
            else
            {
                elve3.insert(in[i]);
            }
        }

        if (group % 3 == 2)
        {
            for (auto c : elve1)
            {
                counts[c] += 1;
            }
            for (auto c : elve2)
            {
                counts[c] += 1;
            }
            for (auto c : elve3)
            {
                counts[c] += 1;
            }

            char key;
            for (auto &i : counts)
            {
                if (i.second == 3)
                {
                    key = i.first;
                    break;
                }
            }
            sum += alphabet.find(key) + 1;
            elve1.clear();
            elve2.clear();
            elve3.clear();
            counts.clear();
        }

        group++;
    }

    cout << sum << endl;
}
