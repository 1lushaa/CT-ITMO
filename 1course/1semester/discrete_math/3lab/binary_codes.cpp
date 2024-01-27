#include <iostream>
#include <vector>
using namespace std;
vector<int>a;
void gen(const int& n) {
    if (a.size() == n) {
        for (int i = 0; i < n; i++) {
            cout << a[i];
        }
        cout << endl;
        return;
    }
    for (int i = 0; i < 2; i++) {
        a.push_back(i);
        gen(n);
        a.pop_back();
    }
}
int main() {
    int n;
    cin >> n;
    gen(n);
    return 0;
}
