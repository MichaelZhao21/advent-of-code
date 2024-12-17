f = open('day1.in')
s = f.readline()

# Part 1
l = 0
for c in s.strip():
    if c == '(':
        l += 1
    else:
        l -= 1
print(l)

# Part 2
l = 0
i = 0
for c in s.strip():
    i += 1
    if c == '(':
        l += 1
    else:
        l -= 1
    if l == -1:
        print(i)
        break