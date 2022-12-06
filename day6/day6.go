package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	// Read the input file
	inputFile, err := os.Open("input.txt")
	if err != nil {
		panic(err)
	}
	defer inputFile.Close()

	var stream string
	scanner := bufio.NewScanner(inputFile)
	for scanner.Scan() {
		stream = scanner.Text()
		break
	}

	var part1, part2 int
	for i := 0; i < len(stream); i++ {
		// Check if the substring of length 4 starting at index i contains no repeated characters
		seen := make(map[rune]bool)
		unique := true
		for _, r := range stream[i : i+4] {
			if seen[r] {
				unique = false
				break
			}
			seen[r] = true
		}
		if unique && part1 == 0 {
			// Save the index of the end of the unique substring
			part1 = i + 4
		}

		// Check if the substring of length 14 starting at index i contains no repeated characters
		seen = make(map[rune]bool)
		unique = true
		for _, r := range stream[i : i+14] {
			if seen[r] {
				unique = false
				break
			}
			seen[r] = true
		}
		if unique && part2 == 0 {
			// Save the index of the end of the unique substring
			part2 = i + 14
			break
		}
	}

	fmt.Println("Part 1:", part1)
	fmt.Println("Part 2:", part2)
}
