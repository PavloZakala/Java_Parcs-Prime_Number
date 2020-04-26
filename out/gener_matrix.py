import argparse

from random import randint

if __name__ == "__main__":
    
    parser = argparse.ArgumentParser()
    parser.add_argument('--h', default=10, type=int)
    parser.add_argument('--w', default=10, type=int)
    args = parser.parse_args()

    with open("M_{}_{}".format(args.w, args.h), "w") as f:
        f.write("{} {}\n".format(args.w, args.h))
        for i in range(args.h):
            arr = []
            for i in range(args.w):
                arr.append(str(randint(-10, 15)))
            
            f.write(" ".join(arr) + "\n")
        



