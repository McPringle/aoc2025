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

package swiss.fihlon.aoc.day01;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a dial that can be turned left or right. The dial has positions ranging from 0 to 99.
 */
public final class Dial {

    /**
     * Logger for logging dial operations.
     */
    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Dial.class);

    /**
     * Counter for how many times the dial points to zero.
     */
    private final AtomicInteger zeroCounter = new AtomicInteger(0);

    /**
     * The current position of the dial (0-99).
     */
    private int position;

    /**
     * Constructs a Dial starting at the given position.
     */
    public Dial(int position) {
        this.position = position;
        LOGGER.info("The dial starts by pointing at {}.", position);
    }

    /**
     * Turns the dial based on the given rotation command.
     *
     * @param rotation the rotation command (e.g., "L10" or "R20")
     * @throws IllegalArgumentException if the rotation command is invalid
     */
    public void turn(final @NotNull String rotation) {
        final var direction = rotation.charAt(0);
        final var steps = Integer.parseInt(rotation.substring(1));
        if (direction == 'L') {
            turnLeft(steps);
        } else if (direction == 'R') {
            turnRight(steps);
        } else {
            throw new IllegalArgumentException("Invalid rotation: " + rotation);
        }
        LOGGER.info("The dial is rotated {} to point at {}.", rotation, position);
    }

    /**
     * Turns the dial left by the specified number of steps.
     *
     * @param steps the number of steps to turn left
     */
    private void turnLeft(final int steps) {
        int stepsToGo = steps;
        while (stepsToGo > 0) {
            if (position == 0) {
                position = 99;
            } else {
                position--;
                if (position == 0) {
                    zeroCounter.incrementAndGet();
                }
            }
            stepsToGo--;
        }
    }

    /**
     * Turns the dial right by the specified number of steps.
     *
     * @param steps the number of steps to turn right
     */
    private void turnRight(final int steps) {
        int stepsToGo = steps;
        while (stepsToGo > 0) {
            if (position == 99) {
                position = 0;
                zeroCounter.incrementAndGet();
            } else {
                position++;
            }
            stepsToGo--;
        }
    }

    /**
     * Returns the number of times the dial has pointed to zero.
     *
     * @return the count of zero positions
     */
    public int getPassword() {
        return zeroCounter.get();
    }
}
