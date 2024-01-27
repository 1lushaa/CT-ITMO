#include <iostream>
#include <vector>
using namespace std;
vector<bool> used;
vector<int>a;
void gen(const int& n) {
    if (a.size() == n) {
        for (int i = 0; i < n; i++) {
            cout << a[i] << " ";
        }
        cout << endl;
        return;
    }
    for (int i = 1; i <= n; i++) {
        if (!used[i]) {
            a.push_back(i);
            used[i] = true;
            gen(n);
            a.pop_back();
            used[i] = false;
        }
    }
}
int main() {
    int n;
    cin >> n;
    used.resize(n + 1, false);
    gen(n);
    return 0;
}
