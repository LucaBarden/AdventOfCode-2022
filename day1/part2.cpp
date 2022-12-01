#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

#define last sums.size()

ifstream infile("input.txt");

bool isEmpty(string buffer)
{
    char regexNumber[] = "[0-9]+";
    regex pat(regexNumber);
    if (regex_match(buffer, pat))
    {
        return false;
    }
    return true;
}

signed main()
{

    int sum = 0;
    vector<int> sums;
    string i;
    while (getline(infile, i))
    {
        if (isEmpty(i))
        {
            sums.push_back(sum);
            sum = 0;
        }
        else
        {
            sum += stoi(i);
        }
    }
    sums.push_back(sum);

    sort(sums.begin(), sums.end());

    int maxsum = sums[last - 1] + sums[last - 2] + sums[last - 3];

    cout << "Result Part2: " << maxsum << endl;
}