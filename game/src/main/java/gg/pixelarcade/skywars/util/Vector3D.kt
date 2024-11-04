/*
* Copyright 2012 Kristian S. Stangeland (Comphenix)
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library. If not, see <http://www.gnu.org/licenses/>.
*/
package gg.pixelarcade.skywars.util

import org.bukkit.Location
import org.bukkit.util.Vector

class Vector3D {
    @JvmField
    val x: Double
    @JvmField
    val y: Double
    @JvmField
    val z: Double

    /**
     * Construct an immutable 3D vector.
     */
    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    /**
     * Construct an immutable floating point 3D vector from a location object.
     *
     * @param location
     * - the location to copy.
     */
    constructor(location: Location) : this(location.toVector())

    /**
     * Construct an immutable floating point 3D vector from a mutable Bukkit
     * vector.
     *
     * @param vector
     * - the mutable real Bukkit vector to copy.
     */
    constructor(vector: Vector) {
        requireNotNull(vector) { "Vector cannot be NULL." }
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
    }

    /**
     * Convert this instance to an equivalent real 3D vector.
     *
     * @return Real 3D vector.
     */
    fun toVector(): Vector {
        return Vector(x, y, z)
    }

    /**
     * Adds the current vector and a given position vector, producing a result
     * vector.
     *
     * @param other
     * - the other vector.
     * @return The new result vector.
     */
    fun add(other: Vector3D): Vector3D {
        requireNotNull(other) { "other cannot be NULL" }
        return Vector3D(x + other.x, y + other.y, z + other.z)
    }

    /**
     * Adds the current vector and a given vector together, producing a result
     * vector.
     *
     * @param other
     * - the other vector.
     * @return The new result vector.
     */
    fun add(x: Double, y: Double, z: Double): Vector3D {
        return Vector3D(this.x + x, this.y + y, this.z + z)
    }

    /**
     * Substracts the current vector and a given vector, producing a result
     * position.
     *
     * @param other
     * - the other position.
     * @return The new result position.
     */
    fun subtract(other: Vector3D): Vector3D {
        requireNotNull(other) { "other cannot be NULL" }
        return Vector3D(x - other.x, y - other.y, z - other.z)
    }

    /**
     * Substracts the current vector and a given vector together, producing a
     * result vector.
     *
     * @param other
     * - the other vector.
     * @return The new result vector.
     */
    fun subtract(x: Double, y: Double, z: Double): Vector3D {
        return Vector3D(this.x - x, this.y - y, this.z - z)
    }

    /**
     * Multiply each dimension in the current vector by the given factor.
     *
     * @param factor
     * - multiplier.
     * @return The new result.
     */
    fun multiply(factor: Int): Vector3D {
        return Vector3D(x * factor, y * factor, z * factor)
    }

    /**
     * Multiply each dimension in the current vector by the given factor.
     *
     * @param factor
     * - multiplier.
     * @return The new result.
     */
    fun multiply(factor: Double): Vector3D {
        return Vector3D(x * factor, y * factor, z * factor)
    }

    /**
     * Divide each dimension in the current vector by the given divisor.
     *
     * @param divisor
     * - the divisor.
     * @return The new result.
     */
    fun divide(divisor: Int): Vector3D {
        require(divisor != 0) { "Cannot divide by null." }
        return Vector3D(x / divisor, y / divisor, z / divisor)
    }

    /**
     * Divide each dimension in the current vector by the given divisor.
     *
     * @param divisor
     * - the divisor.
     * @return The new result.
     */
    fun divide(divisor: Double): Vector3D {
        require(divisor != 0.0) { "Cannot divide by null." }
        return Vector3D(x / divisor, y / divisor, z / divisor)
    }

    /**
     * Retrieve the absolute value of this vector.
     *
     * @return The new result.
     */
    fun abs(): Vector3D {
        return Vector3D(kotlin.math.abs(x), kotlin.math.abs(y), kotlin.math.abs(z))
    }

    override fun toString(): String {
        return String.format("[x: %s, y: %s, z: %s]", x, y, z)
    }

    companion object {
        /**
         * Represents the null (0, 0, 0) origin.
         */
        val ORIGIN: Vector3D = Vector3D(0.0, 0.0, 0.0)
    }
}