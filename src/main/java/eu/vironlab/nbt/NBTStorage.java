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

import com.google.gson.*;
import net.querz.nbt.io.*;
import net.querz.nbt.tag.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.vironlab.nbt.exceptions.*;

public class NBTStorage implements INBTStorage {

    private File dataFile;
    private NamedTag namedTag;

    public NBTStorage(File dataFile) throws NBTStorageCreationException, NBTStorageLoadException {
        this.dataFile = dataFile;
        if(!dataFile.exists()) {
            try {
                CompoundTag mainCompundTag = new CompoundTag();
                mainCompundTag.put("fileCreatedAt", new LongTag(new Date().getTime()));
                mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
                this.namedTag = new NamedTag("storage_" + dataFile.getName(), mainCompundTag);
                NBTUtil.write(this.namedTag, dataFile);
            } catch (IOException ex) {
                throw new NBTStorageCreationException("Storage file '" + dataFile.getName() + "' could not be created...");
            }
        } else {
            try {
                this.namedTag = NBTUtil.read(dataFile);
            } catch(IOException ex) {
                throw new NBTStorageLoadException("Could not read Storage file: " + dataFile.getName());
            }
        }
    }

    public static NBTStorage fromFileString(String dataFileName) throws NBTStorageCreationException, NBTStorageLoadException {
        return new NBTStorage(new File(dataFileName));
    }

    @Override
    public File getDataFile() {
        return this.dataFile;
    }

    @Override
    public NamedTag getNamedTag() {
        return this.namedTag;
    }

    @Override
    public boolean save() throws NBTStorageWriteException {
        try {
            NBTUtil.write(this.namedTag, dataFile);
        } catch (IOException ex) {
            throw new NBTStorageWriteException("Storage file: " + dataFile.getName() + " could not be saved...");
        }
        return true;
    }

    @Override
    public String toString() {
        return this.namedTag.getTag().toString();
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject json = new JsonObject();
        json.add("data", JsonParser.parseString(this.toString()));
        return json;
    }

    @Override
    public String toPrettyString() {
        JsonObject json = new JsonObject();
        json.add("data", JsonParser.parseString(this.toString()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
        return prettyJson;
    }

    @Override
    public CompoundTag getMainCompundTag() {
        return (CompoundTag) this.namedTag.getTag();
    }

    @Override
    public INBTStorage addStringTag(String key, StringTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addArrayTag(String key, ArrayTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addCompundTag(String key, CompoundTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addIntegerTag(String key, IntTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addIntegerArrayTag(String key, IntArrayTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addFloatTag(String key, FloatTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addLongTag(String key, LongTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addLongArrayTag(String key, LongArrayTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addByteArrayTag(String key, ByteArrayTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addByteTag(String key, ByteTag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public INBTStorage addTagRaw(String key, Tag tag) {
        CompoundTag mainCompundTag = (CompoundTag) this.namedTag.getTag();
        mainCompundTag.put("fileChangedAt", new LongTag(new Date().getTime()));
        mainCompundTag.put(key, tag);
        this.namedTag.setTag(mainCompundTag);
        return this;
    }

    @Override
    public Object getValueOf(String key) {
        return getMainCompundTag().get(key);
    }

    @Override
    public String getString(String key) {
        try {
            return getMainCompundTag().getString(key);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int getInt(String key) {
        try {
            return getMainCompundTag().getInt(key);
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public int[] getIntegerArray(String key) {
        try {
            return getMainCompundTag().getIntArray(key);
        } catch (Exception ex) {
            return new int[0];
        }
    }

    @Override
    public List<Integer> getIntList(String key) {
        try {
            List<Integer> ints = new ArrayList<Integer>();
            for(int i : getIntegerArray(key))
                ints.add(i);
            return ints;
        } catch (Exception ex) {
            return new ArrayList<Integer>();
        }
    }

    @Override
    public long getLong(String key) {
        try {
            return getMainCompundTag().getLong(key);
        } catch (Exception ex) {
            return -1L;
        }
    }

    @Override
    public long[] getLongArray(String key) {
        try {
            return getMainCompundTag().getLongArray(key);
        } catch (Exception ex) {
            return new long[0];
        }
    }

    @Override
    public List<Long> getLongList(String key) {
        try {
            List<Long> longs = new ArrayList<Long>();
            for(long l : getLongArray(key))
                longs.add(l);
            return longs;
        } catch (Exception ex) {
            return new ArrayList<Long>();
        }
    }

    @Override
    public float getFloat(String key) {
        try {
            return getMainCompundTag().getFloat(key);
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public byte getByte(String key) {
        try {
            return getMainCompundTag().getByte(key);
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public byte[] getByteArray(String key) {
        try {
            return getMainCompundTag().getByteArray(key);
        } catch (Exception ex) {
            return null;
        }
    }

}
