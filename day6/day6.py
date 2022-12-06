infile = "input.txt"
stream = open(infile).read().strip()

part1 = None
part2 = None
for i in range(len(stream)):
    signal1 = stream[i:i+4]
    signal2 = stream[i:i+14]
    if len(signal1) == len(set(signal1)) and not part1:
        part1 = i + 4
    if len(signal2) == len(set(signal2)) and not part2:
        part2 = i + 14
        break

print("Part 1:", part1)
print("Part 2:", part2)