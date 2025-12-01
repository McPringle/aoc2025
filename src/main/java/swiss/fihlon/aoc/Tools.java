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

package swiss.fihlon.aoc;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Utility class providing helper methods for Advent of Code solutions.
 */
public class Tools {

    /**
     * Reads all lines from a resource file and returns them as an array of strings.
     *
     * @param resourceName the name of the resource file
     * @return an array of strings containing the lines of the resource file
     * @throws IOException if an I/O error occurs
     */
    public static String[] readResourceLines(final @NotNull String resourceName) throws IOException {
        try (final var in = Tools.class.getResourceAsStream(resourceName)) {
            if (in == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }
            try (final var reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return reader
                        .lines()
                        .toList()
                        .toArray(String[]::new);
            }
        }
    }
}
