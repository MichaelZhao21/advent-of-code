from collections import deque

f = open('day11.in')
l = list(map(lambda x: (int(x), 0), f.readline().split(' ')))

# Part 1
q = deque(iterable=l)
d = dict()
while True:
    n = q.popleft()
    if n[1] > 24:
        q.appendleft(n)
        break
    if n[0] == 0:
        q.append((1, n[1]+1))
        continue
    ns = str(n[0])
    if len(ns) % 2 != 0:
        q.append((n[0] * 2024, n[1]+1))
        continue
    m = len(ns) // 2
    q.append((int(ns[:m]), n[1]+1))
    q.append((int(ns[m:]), n[1]+1))
print(len(q))

exit(0)

# Part 2 (will not run in time lol)
q = deque(iterable=l)
d = dict()
while True:
    n = q.popleft()
    if n[1] > 74:
        q.appendleft(n)
        break
    if n[0] == 0:
        q.append((1, n[1]+1))
        continue
    ns = str(n[0])
    if len(ns) % 2 != 0:
        q.append((n[0] * 2024, n[1]+1))
        continue
    m = len(ns) // 2
    q.append((int(ns[:m]), n[1]+1))
    q.append((int(ns[m:]), n[1]+1))
print(len(q))