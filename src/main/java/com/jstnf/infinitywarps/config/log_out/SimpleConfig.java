package com.jstnf.infinitywarps.config.log_out;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

/**
 * InfinityWarps by jstnf
 * SimpleConfig
 * Accesses a loaded YAML configuration file.
 *
 * @author log-out
 * https://bukkit.org/threads/tut-custom-yaml-configurations-with-comments.142592/
 */
public class SimpleConfig
{
	private int comments;
	private SimpleConfigManager manager;

	private File file;
	private FileConfiguration config;

	public SimpleConfig(InputStream configStream, File configFile, int comments, JavaPlugin plugin)
	{
		this.comments = comments;
		this.manager = new SimpleConfigManager(plugin);

		this.file = configFile;
		this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(configStream));
	}

	public Object get(String path)
	{
		return this.config.get(path);
	}

	public Object get(String path, Object def)
	{
		return this.config.get(path, def);
	}

	public String getString(String path)
	{
		return this.config.getString(path);
	}

	public String getString(String path, String def)
	{
		return this.config.getString(path, def);
	}

	public int getInt(String path)
	{
		return this.config.getInt(path);
	}

	public int getInt(String path, int def)
	{
		return this.config.getInt(path, def);
	}

	public boolean getBoolean(String path)
	{
		return this.config.getBoolean(path);
	}

	public boolean getBoolean(String path, boolean def)
	{
		return this.config.getBoolean(path, def);
	}

	public void createSection(String path)
	{
		this.config.createSection(path);
	}

	public ConfigurationSection getConfigurationSection(String path)
	{
		return this.config.getConfigurationSection(path);
	}

	public double getDouble(String path)
	{
		return this.config.getDouble(path);
	}

	public double getDouble(String path, double def)
	{
		return this.config.getDouble(path, def);
	}

	public List<?> getList(String path)
	{
		return this.config.getList(path);
	}

	public List<?> getList(String path, List<?> def)
	{
		return this.config.getList(path, def);
	}

	public boolean contains(String path)
	{
		return this.config.contains(path);
	}

	public void removeKey(String path)
	{
		this.config.set(path, null);
	}

	public void set(String path, Object value)
	{
		this.config.set(path, value);
	}

	public void set(String path, Object value, String comment)
	{
		if (!this.config.contains(path))
		{
			this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comment);
			comments++;
		}
		this.config.set(path, value);
	}

	public void set(String path, Object value, String[] comment)
	{
		for (String comm : comment)
		{
			if (!this.config.contains(path))
			{
				this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comm);
				comments++;
			}
		}
		this.config.set(path, value);
	}

	public void setHeader(String[] header)
	{
		manager.setHeader(this.file, header);
		this.comments = header.length + 2;
		this.reloadConfig();
	}

	public void reloadConfig()
	{
		this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(manager.getConfigContent(file)));
	}

	public void saveConfig()
	{
		String config = this.config.saveToString();
		manager.saveConfig(config, this.file);
	}

	public Set<String> getKeys()
	{
		return this.config.getKeys(false);
	}
}