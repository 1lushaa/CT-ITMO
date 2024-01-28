#include <iostream>
#include <vector>
using namespace std;
void gen_next(vector<int> a) {
    int idx = -1;
    for (int i = a.size() - 1; i > 0; i--) {
        if (a[i - 1] < a[i]) {
            idx = i - 1;
            int min = i;
            for (int j = i; j < a.size(); j++) {
                if (a[j] > a[idx] && a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a[idx], a[min]);
            break;
        }
    }
    if (idx == -1) {
        for (int i = 0; i < a.size(); i++) {
            cout << "0 ";
        }
    }
    else {
        for (int i = 0; i <= idx; i++) {
            cout << a[i] << " ";
        }
        for (int i = a.size() - 1; i > idx; i--) {
            cout << a[i] << " ";
        }
    }
    cout << endl;
    return;
}
void gen_prev(vector<int> a) {
    int idx = -1;
    for (int i = a.size() - 1; i > 0; i--) {
        if (a[i - 1] > a[i]) {
            idx = i - 1;
            int max = i;
            for (int j = i; j < a.size(); j++) {
                if (a[j] < a[idx] && a[j] > a[max]) {
                    max = j;
                }
            }
            swap(a[idx], a[max]);
            break;
        }
    }
    if (idx == -1) {
        for (int i = 0; i < a.size(); i++) {
            cout << "0 ";
        }
    }
    else {
        for (int i = 0; i <= idx; i++) {
            cout << a[i] << " ";
        }
        for (int i = a.size() - 1; i > idx; i--) {
            cout << a[i] << " ";
        }
    }
    cout << endl;
    return;
}
int main() {
    int n;
    cin >> n;
    vector<int>a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    gen_prev(a);
    gen_next(a);
}
