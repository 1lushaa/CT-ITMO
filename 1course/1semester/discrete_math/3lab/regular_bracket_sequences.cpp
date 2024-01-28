#include <iostream>
#include <vector>
using namespace std;
vector<char>a;
void gen(const int& n, const int& l, const int& r) {
    if (l + r == 2 * n) {
        for (int i = 0; i < 2 * n; i++) {
            cout << a[i];
        }
        cout << endl;
        return;
    }
    if (l < n) {
        a.push_back('(');
        gen(n, l + 1, r);
        a.pop_back();
    }
    if (l > r) {
        a.push_back(')');
        gen(n, l, r + 1);
        a.pop_back();
    }
}
int main() {
    int n;
    cin >> n;
    gen(n, 0, 0);
}
