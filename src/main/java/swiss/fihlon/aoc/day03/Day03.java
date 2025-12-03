/*
 * Advent of Code 2025
 * Copyright (C) Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package swiss.fihlon.aoc.day03;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swiss.fihlon.aoc.Tools;

import java.io.IOException;
import java.util.Arrays;

/**
 * Solution for Day 3 of Advent of Code 2025.
 */
public class Day03 {

    /**
     * Logger for logging the solution.
     */
    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Day03.class);

    /**
     * Solves the puzzle by calculating the largest possible joltage for each bank
     * and returning the total sum.
     *
     * @param banks the array of bank strings
     * @return the total sum of the largest possible joltages
     */
    public long solvePuzzle(final @NotNull String[] banks) {
        return Arrays.stream(banks)
                .parallel()
                .mapToLong(this::largestJoltage)
                .sum();
    }

    /**
     * Determines the largest possible joltage by removing digits from the bank string.
     *
     * @param bank the string representing the bank of joltages
     * @return the largest possible joltage as a long value
     */
    private long largestJoltage(final @NotNull String bank) {
        final var batteryLimit = 12;
        final var bankLength = bank.length();
        final var batteryStack = new char[batteryLimit];

        var batteryCount = 0;
        int batteryDropCount = bankLength - batteryLimit; // so many digits can be omitted in total

        for (int position = 0; position < bankLength; position++) {
            final char digit = bank.charAt(position);

            // as long as the last digit is smaller and we can still throw something away
            while (batteryCount > 0 && batteryStack[batteryCount - 1] < digit && batteryDropCount > 0) {
                batteryCount--;
                batteryDropCount--;
            }

            if (batteryCount < batteryLimit) {
                // we still need numbers -> apply number
                batteryStack[batteryCount++] = digit;
            } else {
                // we already have enough digits; this digit can only be discarded
                batteryDropCount--;
            }
        }

        // build the long number from the 12 digits
        long value = 0L;
        for (int i = 0; i < batteryLimit; i++) {
            value = value * 10 + (batteryStack[i] - '0');
        }

        LOGGER.info("The largest possible joltage for '{}' is {}.", bank, value);
        return value;
    }

    /**
     * Main method to run the solution.
     *
     * @throws IOException if there is an error reading the input file
     */
    static void main() throws IOException {
        final var puzzleInput = Tools.readResourceLines("/day03.txt");
        final var day03 = new Day03();
        final var summary = day03.solvePuzzle(puzzleInput);
        LOGGER.info("The summary is: {}", summary);
    }
}
