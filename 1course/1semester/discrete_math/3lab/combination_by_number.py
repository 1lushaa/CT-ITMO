def fact(n):
    if n == 0:
        return 1
    return n*fact(n - 1)
 
n, k, m = map(int, input(). split())
 
a = []
num = 1
while k > 0:
    if m < fact(n - 1) / (fact(k - 1) * fact(n - k)):
       a.append(num)
       k = k - 1
    else:
        m = m - fact(n - 1) / (fact(k - 1) * fact(n - k))
    n = n - 1
    num = num + 1
 
print(*a)
