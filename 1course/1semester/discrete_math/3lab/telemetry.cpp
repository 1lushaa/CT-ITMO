#include <iostream>
#include <vector>
using namespace std;
vector<vector<int>>a;
void reverse(const int& k) {
    int size = a.size() - 1;
    for (int j = 0; j < k - 1; j++) {
        int last_size = a.size() - 1;
        for (int i = last_size; i >= last_size - size; i--) {
            a.push_back(a[i]);
        }
    }
}
void add(const int& k) {
    int num = 0;
    int i = 0;
    int cnt = 0;
    while (i < a.size()) {
        a[i].insert(a[i].begin(), num);
        i++;
        if (i % (a.size() / k) == 0) {
            num++;
        }
    }
}
void gray(const int& n, const int& k) {
    for (int i = 0; i < k; i++) {
        vector<int>curr(1, i);
        a.push_back(curr);
    }
    for (int i = 0; i < n - 1; i++) {
        reverse(k);
        add(k);
    }
}
int main() {
    int n, k;
    cin >> n >> k;
    gray(n, k);
    for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < a[i].size(); j++) {
            cout << a[i][j];
        }
        cout << endl;
    }
}
