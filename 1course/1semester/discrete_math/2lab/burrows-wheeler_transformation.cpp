#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<char>get_sdvig(vector<char>& a) {
    vector<char>to_return;
    to_return.push_back(a[a.size() - 1]);
    for (int i = 0; i < a.size() - 1; i++) {
        to_return.push_back(a[i]);
    }
    return to_return;
}
int main() {
    string s;
    cin >> s;
    vector<vector<char>>a;
    vector<char>b;
    for (int i = 0; i < s.size(); i++) {
        b.push_back(s[i]);
    }
    a.push_back(b);
    for (int i = 0; i < s.size() - 1; i++) {
        vector<char>curr = a[i];
        a.push_back(get_sdvig(curr));
    }
    sort(a.begin(), a.end());
    for (int i = 0; i < s.size(); i++) {
        cout << a[i][s.size() - 1];
    }
    cout << endl;
    return 0;
}
