#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#define ll long long
using namespace std;
void gen(const ll& n, vector<ll>& a) {
    if (a.size() == 1) {
        cout << "No solution" << endl;
    }
    else {
        a[a.size() - 1] -= 1;
        a[a.size() - 2] += 1;
        if (a[a.size() - 2] > a[a.size() - 1]) {
            a[a.size() - 2] += a[a.size() - 1];
            a.pop_back();
        }
        else {
            while (a[a.size() - 2] * 2 <= a[a.size() - 1]) {
                a.push_back(a[a.size() - 2]);
                a.push_back(a[a.size() - 2] - a[a.size() - 3]);
                a.erase(a.begin() + a.size() - 3);
            }
        }
        cout << n << "=";
        for (int i = 0; i < a.size() - 1; i++) {
            if (a[i] != 0) {
                cout << a[i] << "+";
            }
        }
        cout << a[a.size() - 1] << endl;
    }
}
int main() {
    string s;
    cin >> s;
    vector<ll>a;
    int i = 0;
    string num = "";
    while (s[i] != '=') {
        num += s[i];
        i++;
    }
    i++;
    ll n = stol(num);
    while (i < s.size()) {
        string curr = "";
        while (i < s.size() && s[i] != '+') {
            curr += s[i];
            i++;
        }
        a.push_back(stol(curr));
        i++;
    }
    gen(n, a);
    return 0;
}
