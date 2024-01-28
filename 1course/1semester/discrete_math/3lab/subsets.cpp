#include <iostream>
#include <vector>
using namespace std;
vector<int>a;
void gen(const int& n, const int& curr) {
    if (!a.empty()) {
        for (int i = 0; i < a.size(); i++) {
            cout << a[i] << " ";
        }
        cout << endl;
    }
    for (int i = curr + 1; i <= n; i++) {
        a.push_back(i);
        gen(n, i);
        a.pop_back();
    }
}
int main() {
    int n;
    cin >> n;
    cout << endl;
    gen(n, 0);
}
