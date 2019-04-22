package com.jstnf.infinitywarps.rewrite.database;

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

	/**
	 * Get the name of the database object used in methods, files, and commands.
	 * @return the database object's name (identifier).
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get the alias of the database object used in GUIs and command outputs.
	 * @return the database object's alias.
	 */
	public String getAlias()
	{
		return alias;
	}
}