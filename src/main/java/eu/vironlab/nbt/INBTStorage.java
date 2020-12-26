/**
 * Copyright © 2020 | vironlab.eu | All Rights Reserved.
 * <p>
 * ___    _______                        ______         ______
 * __ |  / /___(_)______________ _______ ___  / ______ ____  /_
 * __ | / / __  / __  ___/_  __ \__  __ \__  /  _  __ `/__  __ \
 * __ |/ /  _  /  _  /    / /_/ /_  / / /_  /___/ /_/ / _  /_/ /
 * _____/   /_/   /_/     \____/ /_/ /_/ /_____/\__,_/  /_.___/
 * <p>
 * ____  _______     _______ _     ___  ____  __  __ _____ _   _ _____
 * |  _ \| ____\ \   / / ____| |   / _ \|  _ \|  \/  | ____| \ | |_   _|
 * | | | |  _|  \ \ / /|  _| | |  | | | | |_) | |\/| |  _| |  \| | | |
 * | |_| | |___  \ V / | |___| |__| |_| |  __/| |  | | |___| |\  | | |
 * |____/|_____|  \_/  |_____|_____\___/|_|   |_|  |_|_____|_| \_| |_|
 * <p>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Contact:
 * <p>
 * Discordserver:   https://discord.gg/wvcX92VyEH
 * Website:         https://vironlab.eu/
 * Mail:            contact@vironlab.eu
 */
package eu.vironlab.nbt;

import com.google.gson.JsonObject;
import eu.vironlab.nbt.exceptions.NBTStorageWriteException;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.*;

import java.io.File;
import java.util.List;

public interface INBTStorage {

    /**
     * Resolve the data file.
     * @return file
     */
    File getDataFile();

    /**
     * Resolve the NamedTag ( saved to dataFile )
     * @return namedTag
     */
    NamedTag getNamedTag();

    boolean save() throws NBTStorageWriteException;

    /**
     * Converts the NamedTag to a JSON String
     * @return
     */
    String toString();

    /**
     * Converts the NamedTag to a JsonObject { "data": namedTagAsJSON }
     * @return
     */
    JsonObject toJsonObject();

    /**
     * Converts the NamedTag to a pretty JSON String ( line indent by 2 spaces )
     * @return
     */
    String toPrettyString();

    CompoundTag getMainCompundTag();

    INBTStorage addStringTag(String key, StringTag tag);

    INBTStorage addArrayTag(String key, ArrayTag tag);

    INBTStorage addCompundTag(String key, CompoundTag tag);

    INBTStorage addIntegerTag(String key, IntTag tag);
    INBTStorage addIntegerArrayTag(String key, IntArrayTag tag);

    INBTStorage addFloatTag(String key, FloatTag tag);

    INBTStorage addLongTag(String key, LongTag tag);
    INBTStorage addLongArrayTag(String key, LongArrayTag tag);

    INBTStorage addByteArrayTag(String key, ByteArrayTag tag);
    INBTStorage addByteTag(String key, ByteTag tag);

    INBTStorage addTagRaw(String key, Tag tag);

    Object getValueOf(String key);

    String getString(String key);

    int getInt(String key);
    int[] getIntegerArray(String key);
    List<Integer> getIntList(String key);

    long getLong(String key);
    long[] getLongArray(String key);
    List<Long> getLongList(String key);

    float getFloat(String key);

    byte getByte(String key);
    byte[] getByteArray(String key);

}
