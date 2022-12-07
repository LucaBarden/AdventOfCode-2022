class File:
    def __init__(self, name, size=0):
        self.name = name
        self.size = size
        self.children = []
        self.parent = None


FS = File('/')
pwd = FS

with open("day7.in") as input_file:
    lines = input_file.readlines()
    lines = (line.strip() for line in lines)
    lines = (line for line in lines if line)

    for line in lines:
        if line.startswith('$'):
            parts = line.split()
            cmd = parts[1]
            if cmd == 'cd':
                directory = parts[2]
                if directory == '..':
                    pwd = pwd.parent
                else:  
                    file = File(directory)
                    file.parent = pwd
                    pwd.children.append(file)
                    pwd = file

        else:
            size, name = line.split()

            if size != 'dir':
                file = File(name, int(size))
                pwd.children.append(file)


SIZES = {}

def dir_size(file):
    if not file.children:
        return file.size

    total_size = 0
    for child in file.children:
        total_size += dir_size(child)

    SIZES[file] = total_size
    return total_size

USED = dir_size(FS)

print("Part 1:", sum(s for s in SIZES.values() if s < 100000))

AVAILABLE = 70000000
NEED = 30000000
UNUSED = AVAILABLE - USED

for size in sorted(SIZES.values()):
    if UNUSED + size >= NEED:
        print("Part 2:", size)
        break
