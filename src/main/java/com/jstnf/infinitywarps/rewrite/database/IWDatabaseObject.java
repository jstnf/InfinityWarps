package com.jstnf.infinitywarps.rewrite.database;

import com.jstnf.infinitywarps.rewrite.gui.IWItemIcon;

/**
 * Abstract class of an InfinityWarps database object
 *
 * @author jstnf
 * 18 April 2019
 */
public abstract class IWDatabaseObject
{
	private String name, alias;

	public IWDatabaseObject(String alias)
	{
		this.alias = alias;
		this.name = IWDatabaseUtils.convertToDatabaseName(alias);
	}

	public String getName()
	{
		return name;
	}

	public String getAlias()
	{
		return alias;
	}
}