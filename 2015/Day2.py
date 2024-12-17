f = open('day2.in')
sl = f.readlines()

# Part 1
total = 0
for s in sl:
    n = list(map(lambda x: int(x), s.split('x')))
    a = n[0] * n[1]
    b = n[1] * n[2]
    c = n[0] * n[2]
    total += 2 * (a + b + c) + min(a, b, c)
print(total)

# Part 2
total = 0
for s in sl:
    n = list(map(lambda x: int(x), s.split('x')))
    v = n[0] * n[1] * n[2]
    n.remove(max(n))
    total += v + 2 * n[0] + 2 * n[1]
print(total)
