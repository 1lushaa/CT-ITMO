#include <iostream>
#include <vector>
using namespace std;
void gray(const int& n) {
    vector<vector<int>>a;
    vector<int>zero = { 0 };
    vector<int>one = { 1 };
    a.push_back(zero);
    a.push_back(one);
    for (int j = 0; j < n - 1; j++) {
        for (int i = a.size() - 1; i >= 0; i--) {
            a.push_back(a[i]);
        }
        for (int i = 0; i < a.size() / 2; i++) {
            a[i].insert(a[i].begin(), 0);
        }
        for (int i = a.size() / 2; i < a.size(); i++) {
            a[i].insert(a[i].begin(), 1);
        }
    }
    for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < a[i].size(); j++) {
            cout << a[i][j];
        }
        cout << endl;
    }
    return;
}
int main() {
    int n;
    cin >> n;
    gray(n);
}
