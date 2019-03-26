package com.jstnf.infinitywarps.config;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.config.log_out.SimpleConfig;
import com.jstnf.infinitywarps.config.log_out.SimpleConfigManager;

import java.io.File;

public class ConfigManager
{
	public SimpleConfig main;
	public SimpleConfigManager manager;

	public ConfigManager(IWMain plugin)
	{
		manager = new SimpleConfigManager(plugin);
		initMainConfig(plugin);
	}

	public void initMainConfig(IWMain plugin)
	{
		if (!new File(plugin.getDataFolder(), "config.yml").exists())
		{
			String[] header = { "", "InfinityWarps by jstnf/pokeball92870", "Configuration File", "" };
			main = manager.getNewConfig("config.yml", header);

			for (ConfigPaths path : ConfigPaths.values())
			{
				setDefault(main, path);
			}

			main.saveConfig();
		}
		else
		{
			String[] header = { "", "InfinityWarps by jstnf/pokeball92870", "Configuration File", "" };
			main = manager.getNewConfig("config.yml", header);
		}
	}

	private void setDefault(SimpleConfig conf, ConfigPaths path)
	{
		if (path.getComments() == null)
		{
			conf.set(path.getPath(), path.getDefaultValue());
		}
		else
		{
			conf.set(path.getPath(), path.getDefaultValue(), path.getComments());
		}
	}

	public void checkPaths()
	{

	}
}