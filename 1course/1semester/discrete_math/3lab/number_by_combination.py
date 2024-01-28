def fact(n):
    if n == 0:
        return 1
    return n*fact(n - 1)
 
n, k = map(int, input(). split())
a = []
a = list(map(int, input().split()))
a.insert(0, 0)
ans = 0
for i in range(1, k + 1):
    for j in range(a[i - 1] + 1, a[i]):
        ans += fact(n - j) // (fact(n - k - j + i) * fact(k - i))
print(ans)
